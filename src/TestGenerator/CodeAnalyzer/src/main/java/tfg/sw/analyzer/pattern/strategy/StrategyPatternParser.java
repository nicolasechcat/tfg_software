package tfg.sw.analyzer.pattern.strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import tfg.sw.analyzer.analyzer.MarkedBlockComment;
import tfg.sw.analyzer.analyzer.Marker;
import tfg.sw.analyzer.analyzer.ParsedFile;
import tfg.sw.analyzer.analyzer.PatternParsedFiles;
import tfg.sw.analyzer.analyzer.Parser;
import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.exception.StrategyPatternParserException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternParser;
import tfg.sw.analyzer.pattern.strategy.comparison.ByComparable;
import tfg.sw.analyzer.pattern.strategy.comparison.ByComparator;
import tfg.sw.analyzer.pattern.strategy.comparison.ByEquals;
import tfg.sw.analyzer.util.SystemUtil;

public class StrategyPatternParser implements PatternParser {
	
	public static final String STRATEGY_MARKER = "Strategy";
	public static final String CONCRETE_STRATEGY_MARKER = "ConcreteStrategy";
	
	public static final String PATTERN_ELEMENT_ACTION_TAG = "Action";
	public static final String PATTERN_ELEMENT_COMPARISON_TAG = "Comparison";
	public static final String PATTERN_ELEMENT_EXECUTION_TAG = "Execution";
	
	public static final String PATTERN_ELEMENT_COMPARISON_EQUALS_TAG = "Equals";
	public static final String PATTERN_ELEMENT_COMPARISON_COMPARABLE_TAG = "Comparable";
	public static final String PATTERN_ELEMENT_COMPARISON_COMPARATOR_TAG = "Comparator";
	
	@Override
	public Pattern parse (PatternParsedFiles info) throws ParserException,
			SistemUtilException {
		
		ParsedFile header = info.getHeaderPatternFile();
		String generalStrategyPackage = "";
		String generalStrategyClassName = "";
		
		
		List<StrategyAction> actions = null;
		
		String concreteStrategiesPackage = "";
		List<ConcreteStrategy> concreteStrategies = null;
		
		/*
		 * Extract the general state class info
		 */
		
		if (header.getMarkedComments().get(0).getMarkers().size() != 2)
			throwException(
				header,
				SystemUtil
						.getMessageById("strategy_parser_strategy_class_header_too_much_markers"));
		
		try {
			generalStrategyPackage = Parser.getPackage(header.getFile());
			generalStrategyClassName = Parser.getClassIdentifier(header);
		} catch (FileNotFoundException e1) {
			throwException(header, SystemUtil.getMessageById("internal")
					+ ". File not found.");
		}
		
		// Retrieve actions
		actions = getActionsFromHeaderFileInfo(header);
		
		// Retrieve the concrete strategies
		concreteStrategies = new ArrayList<ConcreteStrategy>();
		for (ParsedFile pf : info.getPatterFileList()) {
			// If it's not the header, it should be a concrete strategy
			if (pf != header) {
				
				String patternElementType = pf.getMarkedComments().get(0)
						.getMarkers().get(1).getParams().get(0);
				
				// If it's not a concrete strategy
				if (patternElementType.compareTo(CONCRETE_STRATEGY_MARKER) != 0)
					throwException(
						pf.getFile(),
						SystemUtil
								.getMessageById("strategy_parser_element_not_known"));
				
				ConcreteStrategy tmp = getConcreteStrategy(pf);
				concreteStrategies.add(tmp);
				
				// retrieve the concrete strategies package (must be the same
				// for all of
				// them)
				if (concreteStrategiesPackage.isEmpty())
					try {
						concreteStrategiesPackage = Parser.getPackage(pf
								.getFile());
					} catch (FileNotFoundException e1) {
						throwException(header,
							SystemUtil.getMessageById("internal")
									+ ". File not found.");
					}
				
			}
		}
		
		
		
		StrategyPattern result = new StrategyPattern(info.getPatternId(),
				generalStrategyPackage, generalStrategyClassName, actions,
				concreteStrategiesPackage, concreteStrategies);
		
		return result;
	}
	
	private List<StrategyAction> getActionsFromHeaderFileInfo (ParsedFile header)
			throws StrategyPatternParserException, SistemUtilException {
		
		List<StrategyAction> result = new ArrayList<StrategyAction>();
		
		// For all the marked block comments
		for (MarkedBlockComment mbc : header.getMarkedComments()) {
			
			// If it's the class one, doesn't matter now
			if (mbc.getCodeAfterBlock().contains(" class ")
					|| mbc.getCodeAfterBlock().contains(" interface "))
				continue;
			
			// the other ones, should be processed
			StrategyAction action = getActionFromMarkers(mbc,
				header.getImports());
			
			// If it was not an action, it's a validation error
			if (action == null)
				throwException(header.getFile(),
					SystemUtil.getMessageById("internal")
							+ ". Unknown action rol.");
			result.add(action);
			
		}
		
		return result;
	}
	
	/**
	 * Retrieves the action information of the markers block
	 * 
	 * @param markers
	 * @return
	 * @throws StrategyPatternParserException
	 * @throws SistemUtilException
	 */
	private StrategyAction getActionFromMarkers (MarkedBlockComment markers,
			List<String> imports) throws StrategyPatternParserException,
			SistemUtilException {
		StrategyAction result = null;
		
		String returnType = getMethodReturnType(markers.getCodeAfterBlock());
		String name = getMethodName(markers.getCodeAfterBlock());
		
		if (returnType.isEmpty() || name.isEmpty())
			throwException(markers.getMarkers().get(0).getFilePath(),
				markers.getLineNumberCodeAfterBlock(),
				SystemUtil.getMessageById("Wrong method signature"));
		
		Comparison comparison = null;
		List<String> parametersSet = new ArrayList<String>();
		
		for (Marker m : markers.getMarkers()) {
			if (m.getMarkerId().compareTo(Marker.PATERN_ELEMENT_TAG) != 0)
				throwException(
					m,
					SystemUtil
							.getMessageById("strategy_parser_strategy_class_marker_not_admited"));
		}
		
		// the first marker of the block must be @ppaternElement Action
		Marker marker = markers.getMarkers().get(0);
		
		// If the marker is no action, as this function is going to be called
		// only parsing the general class, it's an error
		if (marker.getParams().get(0).compareTo(PATTERN_ELEMENT_ACTION_TAG) != 0)
			throwException(
				marker,
				SystemUtil
						.getMessageById("strategy_parser_element_not_action_found"));
		
		if (marker.getParams().size() != 1)
			throwException(
				marker,
				SystemUtil
						.getMessageById("strategy_parser_element_action_action_more_parameters"));
		
		
		// For the other markers
		for (int i = 1; i < markers.getMarkers().size(); i++) {
			marker = markers.getMarkers().get(i);
			
			List<String> params = marker.getParams();
			
			// If it's another Action definition it's an error
			if (params.get(0).compareTo(PATTERN_ELEMENT_ACTION_TAG) == 0)
				throwException(
					marker,
					SystemUtil
							.getMessageById("strategy_parser_element_action_multiple_actions_same_block"));
			
			// If it's the Comparison element
			if (params.get(0).compareTo(PATTERN_ELEMENT_COMPARISON_TAG) == 0) {
				// If a comparison has already been found for this marker block
				// it's an error
				if (comparison != null)
					throwException(
						marker,
						SystemUtil
								.getMessageById("strategy_parser_element_action_multiple_comparisons_same_block"));
				
				comparison = analizeComparisonMarker(marker);
				
				continue;
			}
			
			// If it's the Parameters element
			if (params.get(0).compareTo(PATTERN_ELEMENT_EXECUTION_TAG) == 0) {
				
				String parameters = analizeExecutionMarker(marker);
				
				parametersSet.add(parameters);
				
				continue;
			}
		}
		
		if (comparison == null)
			comparison = new ByEquals();
		
		String returnTypePackage = "";
		
		for (String s : imports)
			if (s.endsWith(returnType)) {
				returnTypePackage = s;
				break;
			}
		
		result = new StrategyAction(returnType, returnTypePackage, name,
				parametersSet, comparison);
		
		return result;
	}
	
	private String getMethodReturnType (String methodSign) {
		String result = "";
		
		String[] splitted = methodSign.trim().split(" ");
		
		if (splitted.length < 3)
			return "";
		
		int returnTypePosition = 1;
		
		if (splitted[1].trim().compareTo("abstract") == 0)
			returnTypePosition = 2;
		
		result = splitted[returnTypePosition].trim();
		
		return result;
	}
	
	private String getMethodName (String methodSign) {
		String result = "";
		
		String[] splitted = methodSign.trim().split(" ");
		
		if (splitted.length < 3)
			return "";
		
		int returnTypePosition = 2;
		
		if (splitted[1].trim().compareTo("abstract") == 0)
			returnTypePosition = 3;
		
		result = splitted[returnTypePosition].trim();
		
		if (result.contains("(")) {
			int i = result.indexOf("(");
			result = result.substring(0, i);
		}
		
		return result;
	}
	
	/**
	 * Analyzes the marker @patternElement Comparison and creates the
	 * representation class
	 * 
	 * @param marker
	 * @return
	 * @throws SistemUtilException
	 * @throws StrategyPatternParserException
	 */
	private Comparison analizeComparisonMarker (Marker marker)
			throws StrategyPatternParserException, SistemUtilException {
		
		Comparison result = null;
		
		List<String> params = marker.getParams();
		
		// If it has no more parameters
		if (params.size() < 2)
			throwException(
				marker,
				SystemUtil
						.getMessageById("strategy_parser_element_action_comparison_no_more_parameters"));
		
		// Analyze the comparison type
		switch (params.get(1)) {
			case PATTERN_ELEMENT_COMPARISON_EQUALS_TAG:
				// Equals has no more parameters
				if (params.size() >= 3)
					throwException(
						marker,
						SystemUtil
								.getMessageById("strategy_parser_element_action_comparison_equals_more_parameters"));
				result = new ByEquals();
				break;
			
			case PATTERN_ELEMENT_COMPARISON_COMPARABLE_TAG:
				// Comparable has no more parameters
				if (params.size() >= 3)
					throwException(
						marker,
						SystemUtil
								.getMessageById("strategy_parser_element_action_comparison_comparable_more_parameters"));
				result = new ByComparable();
				break;
			
			case PATTERN_ELEMENT_COMPARISON_COMPARATOR_TAG:
				
				// @patternElement Comparison Comparator
				// <testExamples.parser.strategy.correct.auxiliar>
				// <UsersComparator> < new UsersComparator (3) >
				
				// Comparator can have none, two or three additional parameters.
				// Otherwhise, it's an error
				if (params.size() == 3)
					throwException(
						marker,
						SystemUtil
								.getMessageById("strategy_parser_element_action_comparison_comparator_one_parameter"));
				
				if (params.size() > 5)
					throwException(
						marker,
						SystemUtil
								.getMessageById("strategy_parser_element_action_comparison_comparator_more_parameters"));
				
				if (params.size() == 2) {
					result = new ByComparator();
				} else {
					// If any additional parameter is not between < and > it's
					// an error
					if (!params.get(2).startsWith("<")
							|| !params.get(2).endsWith(">"))
						throwException(
							marker,
							SystemUtil
									.getMessageById("strategy_parser_element_action_comparison_comparator_wrong_parameters"));
					
					if (!params.get(3).startsWith("<")
							|| !params.get(3).endsWith(">"))
						throwException(
							marker,
							SystemUtil
									.getMessageById("strategy_parser_element_action_comparison_comparator_wrong_parameters"));
					
					if (params.size() == 5
							&& (!params.get(4).startsWith("<") || !params
									.get(4).endsWith(">")))
						throwException(
							marker,
							SystemUtil
									.getMessageById("strategy_parser_element_action_comparison_comparator_wrong_parameters"));
					
					
					String par1 = params.get(2)
							.substring(1, params.get(2).length() - 1).trim();
					
					String par2 = params.get(3)
							.substring(1, params.get(3).length() - 1).trim();
					
					String par3 = params.size() == 5 ? params.get(4)
							.substring(1, params.get(4).length() - 1).trim()
							: "";
					
					result = new ByComparator(par1, par2, par3);
					
				}
				
				break;
			
			default:
				throwException(
					marker.getFilePath(),
					marker.getLineNumber(),
					SystemUtil
							.getMessageById("strategy_parser_element_action_comparison_not_known"));
		}
		
		return result;
		
	}
	
	private String analizeExecutionMarker (Marker marker)
			throws StrategyPatternParserException, SistemUtilException {
		
		String result = "";
		
		if (marker.getParams().size() < 2)
			throwException(
				marker,
				SystemUtil
						.getMessageById("strategy_parser_wrong_parameter_set_less_parameters"));
		
		if (marker.getParams().size() > 2)
			throwException(
				marker,
				SystemUtil
						.getMessageById("strategy_parser_wrong_parameter_set_too_much_parameters"));
		
		
		// Get the parameters
		
		String methParam = marker.getParams().get(1);
		
		if (!methParam.startsWith("<") || !methParam.endsWith(">"))
			throwException(
				marker,
				SystemUtil
						.getMessageById("strategy_parser_wrong_parameter_set_second_parameter"));
		
		String cleanedMethParam = methParam
				.substring(1, methParam.length() - 1).trim();
		
		
		result = cleanedMethParam;
		
		return result;
	}
	
	private ConcreteStrategy getConcreteStrategy (ParsedFile parsedFile)
			throws ParserException, SistemUtilException {
		
		ConcreteStrategy result = null;
		
		// Get the class name
		String className = Parser.getClassIdentifier(parsedFile);
		
		if (parsedFile.getMarkedComments().size() > 1)
			throwException(
				parsedFile.getFile(),
				SystemUtil
						.getMessageById("strategy_parser_concrete_strategy_more_marked_blocks"));
		
		// If it has builder element
		String builderInvocation = Parser.getBuilderFromMarkers(parsedFile
				.getMarkedComments().get(0).getMarkers());
		
		result = new ConcreteStrategy(className, builderInvocation);
		
		return result;
	}
	
	private static void throwException (Marker marker, String message)
			throws StrategyPatternParserException {
		throwException(marker.getFilePath(), marker.getLineNumber(), message);
	}
	
	private static void throwException (String filePath, long lineNumber,
			String message) throws StrategyPatternParserException {
		throw new StrategyPatternParserException(message + " \n\t File: "
				+ filePath + "\n\tLine: " + lineNumber);
	}
	
	private static void throwException (ParsedFile parsedFile, String message)
			throws StrategyPatternParserException {
		throwException(parsedFile.getFile(), message);
	}
	
	private static void throwException (File file, String message)
			throws StrategyPatternParserException {
		throw new StrategyPatternParserException(message + " \n\t File: "
				+ file.getAbsolutePath());
	}
	
}
