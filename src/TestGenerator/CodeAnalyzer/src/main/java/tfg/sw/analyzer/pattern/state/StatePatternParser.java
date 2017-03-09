package tfg.sw.analyzer.pattern.state;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tfg.sw.analyzer.analyzer.MarkedBlockComment;
import tfg.sw.analyzer.analyzer.Marker;
import tfg.sw.analyzer.analyzer.ParsedFile;
import tfg.sw.analyzer.analyzer.Parser;
import tfg.sw.analyzer.analyzer.PatternParsedFiles;
import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.exception.StatePatternParserException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternParser;
import tfg.sw.analyzer.util.SystemUtil;

@SuppressWarnings ("unused")
public class StatePatternParser implements PatternParser {
	
	public static final String STATE_MARKER = "State";
	public static final String CONTEXT_MARKER = "Context";
	public static final String CONCRETE_STATE_MARKER = "ConcreteState";
	
	@Override
	public Pattern parse (PatternParsedFiles info) throws ParserException,
			SistemUtilException {
		
		File header = null;
		String generalStatePackage = "";
		String generalStateClassName = "";
		String generalStateBuilder = "";
		List<String> actionNames = new ArrayList<String>();
		boolean orderDependent = false;
		
		File context = null;
		String stateContextPackage = "";
		String stateContextClassName = "";
		String stateContextBuilder = "";
		
		String concreteStatesPackage = "";
		List<ConcreteState> concreteStates = new ArrayList<ConcreteState>();
		
		/*
		 * Extract the general state class info
		 */
		try {
			header = info.getHeaderPatternFile().getFile();
			generalStatePackage = Parser.getPackage(header);
			generalStateClassName = header.getName().replace(".java", "");
			generalStateBuilder = Parser.getClassBuilderMarker(header);
		} catch (FileNotFoundException e1) {
			throwException(header, SystemUtil.getMessageById("internal")
					+ ". File not found.");
		}
		
		/*
		 * Look for order dependence
		 */
		orderDependent = isOrderDependent(header);
		
		/*
		 * Look for the context class
		 */
		try {
			context = getContextFile(info.getPatterFileList());
			stateContextPackage = Parser.getPackage(context);
			stateContextClassName = context.getName().replace(".java", "");
			stateContextBuilder = Parser.getClassBuilderMarker(context);
		} catch (FileNotFoundException e1) {
			throwException(context, SystemUtil.getMessageById("internal")
					+ ". File not found.");
		}
		
		/*
		 * Create the list of admitted actions
		 */
		actionNames = getGeneralActions(header, stateContextClassName);
		
		if (actionNames.size() == 0) {
			throwException(header,
				SystemUtil.getMessageById("state_parser_no_actions"));
		}
		
		/*
		 * Create each concrete state object with the information in the file
		 */
		concreteStates = getConcreteStates(info.getPatterFileList(),
			actionNames);
		
		/*
		 * Create each concrete state object with the information in the file
		 */
		concreteStatesPackage = getConcreteStatesPackage(info
				.getPatterFileList());
		
		StatePattern result = new StatePattern(info.getPatternId(),
				generalStatePackage, generalStateClassName, actionNames,
				orderDependent, stateContextPackage, stateContextClassName,
				stateContextBuilder, concreteStatesPackage, concreteStates);
		
		return result;
	}
	
	/**
	 * 
	 * @param files
	 * @return The context file of the pattern
	 * @throws SistemUtilException
	 * @throws StatePatternParserException
	 *             if it's no context or more than one
	 */
	private static File getContextFile (List<ParsedFile> files)
			throws ParserException, SistemUtilException {
		
		File context = null;
		
		for (ParsedFile pf : files) {
			File f = pf.getFile();
			try {
				List<Marker> markers = Parser.getBeforeClassHeaderMarkers(f);
				if (markers.get(1).getParams().get(0).compareTo("Context") == 0) {
					if (context != null)
						throwException(
							f,
							SystemUtil
									.getMessageById("state_parser_multiple_context")
									+ ".\nThe other context is:"
									+ context.getAbsolutePath());
					if (markers.get(1).getParams().size() != 1)
						throwException(
							f,
							SystemUtil
									.getMessageById("state_parser_context_wrong_parameter_number"));
					context = f;
				}
			} catch (FileNotFoundException e) {
				throwException(f, SystemUtil.getMessageById("internal")
						+ ". File not found.");
			}
		}
		if (context == null)
			throwException(SystemUtil
					.getMessageById("state_parser_context_not_found"));
		
		return context;
	}
	
	/**
	 * 
	 * @param file
	 *            Must be the general state marked class or it will return
	 *            always null
	 * @return
	 * @throws StatePatternParserException
	 * @throws /
	 */
	private static boolean isOrderDependent (File file)
			throws StatePatternParserException, SistemUtilException {
		
		try {
			List<Marker> befHeadMarkers = Parser
					.getBeforeClassHeaderMarkers(file);
			
			if (befHeadMarkers.get(1).getParams().size() > 2)
				throwException(
					file,
					befHeadMarkers.get(1).getLineNumber(),
					SystemUtil
							.getMessageById("state_parser_state_wrong_parameter_number"));
			
			/*
			 * If not defined, let the value assigned by default
			 */
			if (befHeadMarkers.get(1).getParams().size() == 2) {
				String aux = befHeadMarkers.get(1).getParams().get(1);
				
				switch (aux) {
					case "orderdep":
						return true;
					case "nonorderdep":
						return false;
					default:
						throwException(
							file,
							befHeadMarkers.get(1).getLineNumber(),
							SystemUtil
									.getMessageById("state_parser_state_wrong_parameter"));
				}
			}
		} catch (FileNotFoundException e) {
			throwException(file, SystemUtil.getMessageById("internal")
					+ ". File not found.");
		}
		
		/*
		 * the default value
		 */
		return false;
	}
	
	/**
	 * Validates if the action has the context as parameter too.
	 * 
	 * @param file
	 *            It must be the general state marked class
	 * @return A list with the public methods of the general state class
	 * @throws StatePatternParserException
	 * @throws SistemUtilException
	 */
	private static List<String> getGeneralActions (File file, String contextName)
			throws StatePatternParserException, SistemUtilException {
		
		List<String> result = new ArrayList<String>();
		
		Scanner reader = null;
		String line = "";
		long lineNumber = 0;
		// To have the beginning line of a multi-line sentence
		int lineNumberAux = 0;
		
		boolean insideBlockComment = false;
		
		if (file.exists() && file.canRead()) {
			
			try {
				reader = new Scanner(file, "UTF-8");
				line = reader.nextLine();
				lineNumber++;
				
				/*
				 * Process the file until class header outside a comment
				 */
				while (line != null
						&& !(!insideBlockComment && !line.contains("//") && (line
								.contains(" class ") || line
								.contains(" interface ")))) {
					
					line = line.trim();
					
					if (line.startsWith("/*"))
						insideBlockComment = true;
					
					if (line.contains("*/"))
						insideBlockComment = false;
					
					// Next line
					line = reader.nextLine();
					lineNumber++;
				}
				
				/*
				 * Real until the end of the class header
				 */
				while (!line.contains("{")) {
					line = reader.nextLine();
					lineNumber++;
				}
				
				// First inside class line
				line = reader.nextLine();
				lineNumber++;
				
				int scopeCount = 1;
				
				/*
				 * Process the file until the end of the class
				 */
				while (scopeCount > 0 && !line.contains("}")) {
					
					line = line.replace("\t", " ").trim();
					
					if (line.contains("{"))
						scopeCount++;
					
					if (line.contains("}"))
						scopeCount--;
					
					/*
					 * If it's a method sign and it's not private
					 */
					if ((scopeCount == 1 || (scopeCount == 2 && line
							.contains("{")))
							&& (line.startsWith("public") || line
									.startsWith("protected"))) {
						
						String action = "";
						
						// If the header is multi-line, take it all together
						String head = line;
						while (!line.contains("{") && !line.contains(";")) {
							line = reader.nextLine();
							lineNumberAux++;
							
							head += " " + line;
						}
						
						// Split by the beginning of parameters
						String[] firstSplit = head.split("\\(");
						
						if (firstSplit.length != 2)
							throwException(
								file,
								lineNumber,
								SystemUtil
										.getMessageById("parser_wrong_method_signature_parameters"));
						
						String[] secondSplit = firstSplit[0].split(" ");
						
						if (secondSplit.length < 3)
							throwException(
								file,
								lineNumber,
								SystemUtil
										.getMessageById("state_parser_wrong_method_signature_before_parameters"));
						
						// The last one must be the method name
						action = (secondSplit[secondSplit.length - 1]).trim();
						
						/*
						 * Split the parameters and process each one to look for
						 * the context
						 */
						String[] thirdSplit = firstSplit[1].trim().split("\\)");
						
						if (thirdSplit.length < 2)
							throwException(
								file,
								lineNumber,
								SystemUtil
										.getMessageById("parser_wrong_method_signature_parameters"));
						
						thirdSplit = thirdSplit[0].trim().split(",");
						
						if (thirdSplit.length == 0)
							throwException(
								file,
								lineNumber,
								SystemUtil
										.getMessageById("state_parser_wrong_method_signature_no_context"));
						
						boolean hasContext = false;
						
						for (int i = 0; i < thirdSplit.length; i++) {
							String[] paramSplit = thirdSplit[i].trim().split(
								" ");
							
							if (paramSplit.length < 2)
								throwException(
									file,
									lineNumber,
									SystemUtil
											.getMessageById("parser_wrong_method_signature_parameters_type"));
							
							if (paramSplit[0].compareTo(contextName) == 0) {
								if (hasContext)
									throwException(
										file,
										lineNumber,
										SystemUtil
												.getMessageById("state_parser_wrong_method_signature_multiple_context"));
								hasContext = true;
							}
						}
						if (hasContext)
							result.add(action);
						
						lineNumber += lineNumberAux;
					}
					
					line = reader.nextLine();
					lineNumber++;
				}
			} catch (FileNotFoundException e) {
				throwException(file, SystemUtil.getMessageById("internal")
						+ ". File not found.");
			} finally {
				reader.close();
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param files
	 * @return
	 * @throws ParserException
	 * @throws SistemUtilException
	 */
	private List<ConcreteState> getConcreteStates (List<ParsedFile> files,
			List<String> validActions) throws ParserException,
			SistemUtilException {
		
		List<ConcreteState> result = new ArrayList<ConcreteState>();
		
		for (ParsedFile pf : files) {
			File f = pf.getFile();
			try {
				
				String className = "";
				List<Transition> transitions = new ArrayList<Transition>();
				String builderInvocation = "";
				List<MarkedBlockComment> markedBlocks;
				MarkedBlockComment markedBlock;
				List<Marker> markers;
				Marker marker;
				
				boolean initialState = false;
				boolean finalState = false;
				
				markedBlocks = Parser.getAllBlockCommentMarkers(f);
				
				if (markedBlocks.size() == 0)
					throwException(f, SystemUtil.getMessageById("internal")
							+ ". The file has no marked comment block");
				
				// Analyze the beforeHeader markers
				markedBlock = markedBlocks.get(0);
				markers = markedBlock.getMarkers();
				
				if (markers.size() == 0)
					throwException(
						f,
						SystemUtil.getMessageById("internal")
								+ ". It has been created a MarkedBlockComment with no markers");
				
				if (markers.get(1).getParams().get(0).compareTo(STATE_MARKER) == 0)
					continue;
				
				if (markers.get(1).getParams().get(0).compareTo(CONTEXT_MARKER) == 0)
					continue;
				
				if (markers.get(1).getParams().get(0)
						.compareTo(CONCRETE_STATE_MARKER) != 0)
					throwException(
						f,
						SystemUtil
								.getMessageById("state_parser_element_not_known"));
				
				// If it arrives here, it's ConcreteState
				if (markers.get(1).getParams().size() > 3)
					throwException(
						f,
						SystemUtil
								.getMessageById("state_parser_concrete_state_wrong_parameter_number"));
				
				/*
				 * Process all the other MarkedBlockComment
				 */
				
				// Get the class name
				className = Parser.getClassIdentifier(pf);
				
				// Search for initial and final identifiers. Error if one
				// parameter is not one of them, or both are the same one
				if (markers.get(1).getParams().size() > 1) {
					for (int j = 1; j < markers.get(1).getParams().size(); j++) {
						
						if (markers.get(1).getParams().get(j)
								.compareTo("initial") == 0) {
							if (initialState)
								throwException(
									f,
									markers.get(1).getLineNumber(),
									SystemUtil
											.getMessageById("state_parser_concrete_state_wrong_parameter_initial"));
							initialState = true;
						} else {
							if (markers.get(1).getParams().get(j)
									.compareTo("final") == 0) {
								if (finalState)
									throwException(
										f,
										markers.get(1).getLineNumber(),
										SystemUtil
												.getMessageById("state_parser_concrete_state_wrong_parameter_final"));
								finalState = true;
							} else {
								throwException(
									f,
									markers.get(1).getLineNumber(),
									SystemUtil
											.getMessageById("state_parser_concrete_state_wrong_parameter"));
								
							}
						}
						
					}
				}
				
				// If it has builder element
				builderInvocation = Parser.getBuilderFromMarkers(markers);
				
				/*
				 * Process each remaining marker (must be transitions)
				 */
				for (int i = 1; i < markedBlocks.size(); i++) {
					markedBlock = markedBlocks.get(i);
					markers = markedBlock.getMarkers();
					String actionName = "";
					
					if (markers.size() == 0)
						throwException(f, SystemUtil.getMessageById("internal")
								+ ". Wrong markers preprocess");
					
					/*
					 * Get the action name
					 */
					
					String[] codeAfterMarker = markedBlock.getCodeAfterBlock()
							.split("\\(")[0].split(" ");
					
					actionName = codeAfterMarker[codeAfterMarker.length - 1];
					
					if (!validActions.contains(actionName))
						throwException(
							f,
							String.format(
								SystemUtil
										.getMessageById("state_parser_wrong_no_existent_action"
												+ ". Action %s"), actionName));
					
					/*
					 * Each marker must be an action (transition)
					 */
					for (Marker m : markers) {
						String destinyState = "";
						List<Parameter> parameters = new ArrayList<Parameter>();
						
						/*
						 * Get the destiny state
						 */
						if (m.getMarkerId().compareTo("patternAction") != 0)
							throwException(
								f,
								m.getLineNumber(),
								SystemUtil
										.getMessageById("state_parser_marker_not_known"));
						
						if (m.getParams().size() < 2)
							throwException(
								f,
								m.getLineNumber(),
								SystemUtil
										.getMessageById("state_parser_wrong_action_less_parameters"));
						
						if (m.getParams().size() > 3)
							throwException(
								f,
								m.getLineNumber(),
								SystemUtil
										.getMessageById("state_parser_wrong_action_too_much_parameters"));
						
						if (m.getParams().get(0).compareTo("Transition") != 0)
							throwException(
								f,
								m.getLineNumber(),
								SystemUtil
										.getMessageById("state_parser_wrong_action_parameter_not_known"));
						
						destinyState = m.getParams().get(1);
						
						/*
						 * Get the parameters
						 */
						
						if (m.getParams().size() == 3) {
							boolean context = false;
							
							String methParam = m.getParams().get(2);
							
							if (!methParam.startsWith("<")
									|| !methParam.endsWith(">"))
								throwException(
									m.getFilePath(),
									m.getLineNumber(),
									SystemUtil
											.getMessageById("state_parser_wrong_action_third_parameter"));
							
							String[] splitedMethParam = methParam.substring(1,
								methParam.length() - 1).split(", ");
							
							for (int j = 0; j < splitedMethParam.length; j++) {
								if (!splitedMethParam[j].isEmpty()) {
									parameters.add(new Parameter(
											splitedMethParam[j].trim(), j));
									
									if (splitedMethParam[j]
											.compareTo("context") == 0)
										if (context) {
											throwException(
												m.getFilePath(),
												m.getLineNumber(),
												SystemUtil
														.getMessageById("state_parser_wrong_action_third_parameter_more_context")
														+ ". Only one context is permited as parameter.");
										} else {
											context = true;
										}
								}
							}
							
							if (!context)
								throwException(
									m.getFilePath(),
									m.getLineNumber(),
									SystemUtil
											.getMessageById("state_parser_wrong_action_third_parameter_no_context")
											+ ". No context found in the parameters");
						}
						
						transitions.add(new Transition(destinyState,
								actionName, parameters));
					}
				}
				
				result.add(new ConcreteState(className, transitions,
						builderInvocation, initialState, finalState));
				
			} catch (FileNotFoundException e) {
				throwException(f, SystemUtil.getMessageById("internal")
						+ ". File not found.");
			}
		}
		
		if (result.size() == 0)
			throwException(SystemUtil
					.getMessageById("state_parser_concrete_state_not_found"));
		
		return result;
	}
	
	private String getConcreteStatesPackage (List<ParsedFile> files)
			throws ParserException, SistemUtilException {
		
		String result = "";
		
		for (ParsedFile pf : files) {
			File f = pf.getFile();
			try {
				List<Marker> markers = Parser.getBeforeClassHeaderMarkers(f);
				
				if (markers.get(1).getParams().get(0).compareTo(STATE_MARKER) == 0)
					continue;
				
				if (markers.get(1).getParams().get(0).compareTo(CONTEXT_MARKER) == 0)
					continue;
				
				if (markers.get(1).getParams().get(0)
						.compareTo(CONCRETE_STATE_MARKER) != 0)
					throwException(
						f,
						SystemUtil
								.getMessageById("state_parser_element_not_known"));
				
				result = Parser.getPackage(f);
				break;
				
			} catch (FileNotFoundException e) {
				throwException(f, SystemUtil.getMessageById("internal")
						+ ". File not found.");
			}
		}
		
		return result;
	}
	
	private static void throwException (File file, long lineNumber,
			String message) throws StatePatternParserException {
		throw new StatePatternParserException(message + " \n\t File: "
				+ file.getAbsolutePath() + "\n\tLine: " + lineNumber);
	}
	
	private static void throwException (String filePath, long lineNumber,
			String message) throws StatePatternParserException {
		throw new StatePatternParserException(message + " \n\t File: "
				+ filePath + "\n\tLine: " + lineNumber);
	}
	
	private static void throwException (File file, String message)
			throws StatePatternParserException {
		throw new StatePatternParserException(message + " \n\t File: "
				+ file.getAbsolutePath());
	}
	
	private static void throwException (String message)
			throws StatePatternParserException {
		throw new StatePatternParserException(message);
	}
	
}
