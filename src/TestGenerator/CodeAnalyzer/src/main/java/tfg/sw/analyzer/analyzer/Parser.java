package tfg.sw.analyzer.analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.exception.StatePatternParserException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternName;
import tfg.sw.analyzer.pattern.PatternParser;
import tfg.sw.analyzer.pattern.state.StatePattern;
import tfg.sw.analyzer.pattern.state.StatePatternParser;
import tfg.sw.analyzer.pattern.strategy.StrategyPattern;
import tfg.sw.analyzer.pattern.strategy.StrategyPatternParser;
import tfg.sw.analyzer.util.SystemUtil;

public class Parser {
	
	private static final String OBSERVER_MARKER = "Observer";
	
	public static final String MARKER_CHARACTER = "@";
	
	/*
	 * Steps: 1- Take all the pattern definitions of the files. 2- Group all the
	 * files of the same pattern together. 3- Use a specific parser for each
	 * pattern with the associated files
	 */
	public static List<Pattern> parseFileList (List<File> files)
			throws ParserException, FileNotFoundException, SistemUtilException {
		
		String error = "";
		
		List<ParsedFile> patternFiles;
		List<PatternParsedFiles> groupedFiles;
		List<Pattern> result = new ArrayList<Pattern>();
		
		patternFiles = new ArrayList<ParsedFile>();
		for (File file : files) {
			ParsedFile aux = new ParsedFile(file);
			if (!aux.getMarkedComments().isEmpty())
				if (aux.getMarkedComments().get(0).getScope() != 0) {
					throwException(
						file,
						SystemUtil
								.getMessageById("parser_no_before_header_marker"));
				} else {
					patternFiles.add(aux);
				}
		}
		
		/*
		 * After this, all the files have marked comments
		 */
		
		// If no file is marked
		if (patternFiles.isEmpty())
			return result;
		
		/*
		 * First step: Take all the pattern definitions of the files
		 */
		groupedFiles = firstStepParsePatternHeader(patternFiles);
		
		if (groupedFiles.isEmpty())
			return result;
		
		/*
		 * Second step: Group all the files of the same pattern together
		 */
		secondStepAddFileToPatternGroup(patternFiles, groupedFiles);
		
		/*
		 * Third step: Use a specific parser for each pattern with the
		 * associated files
		 */
		PatternParser patternParser = null;
		
		for (PatternParsedFiles p : groupedFiles) {
			switch (p.getPatternName()) {
				case STATE:
					patternParser = StatePattern.getParser();
					break;
				
				case STRATEGY:
					patternParser = StrategyPattern.getParser();
					break;
				
				default:
					throwException(SystemUtil
							.getMessageById("parser_no_parser_for_pattern")
							+ p.getPatternName().toString());
			}
			Pattern pattern = patternParser.parse(p);
			
			String validation = pattern.validate();
			
			if (validation.contains("Error")) {
				error += String.format(
					SystemUtil.getMessageById("parser_validation_error"),
					pattern.getPatternId())
						+ validation
						+ "\n\n-----------------------------\n\n";
			}
			
			result.add(pattern);
		}
		
		if (!error.trim().isEmpty())
			throw new ParserException(error);
		
		return result;
	}
	
	public static List<PatternParsedFiles> firstStepParsePatternHeader (
			List<ParsedFile> files) throws ParserException,
			FileNotFoundException, SistemUtilException {
		
		List<PatternParsedFiles> result = new ArrayList<PatternParsedFiles>();
		PatternParsedFiles tmp;
		
		for (ParsedFile pf : files) {
			
			List<Marker> markers = getBeforeClassHeaderMarkers(pf);
			String classIdentifier = getClassIdentifier(pf);
			
			if (markers.size() == 0)
				continue;
			
			tmp = firstStepProcessBeforeClassHeaderMarkers(pf, classIdentifier);
			
			if (!result.contains(tmp)) {
				if (tmp != null)
					result.add(tmp);
			} else {
				/*
				 * The identifier must be unique for each pattern header
				 * definition
				 */
				File conflict = result.get(result.indexOf(tmp))
						.getHeaderPatternFile().getFile();
				
				throwException(SystemUtil
						.getMessageById("parser_multiple_use_of_pattern_id")
						+ "\n\tIdentifier: "
						+ tmp.getPatternId()
						+ "\n\tFile: "
						+ tmp.getHeaderPatternFile().getFile()
								.getAbsolutePath()
						+ "\n\tFile: "
						+ conflict.getAbsolutePath());
			}
		}
		
		return result;
	}
	
	private static PatternParsedFiles firstStepProcessBeforeClassHeaderMarkers (
			ParsedFile patternFile, String classIdentifier)
			throws ParserException, SistemUtilException {
		// Process before header markers
		
		PatternParsedFiles result = null;
		
		List<Marker> markers;
		Marker m;
		List<String> params;
		String patternId;
		
		// ***********************************************
		// General
		try {
			
			markers = getBeforeClassHeaderMarkers(patternFile);
			
			m = markers.get(0);
			
			if (markers.size() < 2)
				throwException(
					patternFile,
					m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_wrong_header_markers_number_less"));
			
			if (markers.size() > 3)
				throwException(
					patternFile,
					m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_wrong_header_markers_number_much"));
			
			// ***********************************************
			// First marker
			
			if (m.getMarkerId().compareTo("pattern") != 0)
				throwException(patternFile, m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_wrong_header_first_marker"));
			
			params = m.getParams();
			
			if (params.size() == 0)
				throwException(
					patternFile,
					m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_marker_without_type"));
			
			if (params.size() > 2)
				throwException(
					patternFile,
					m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_marker_too_much_params"));
			
			if (params.size() == 2) {
				if (!params.get(1).startsWith("<")
						|| !params.get(1).endsWith(">"))
					throwException(
						patternFile,
						m.getLineNumber(),
						SystemUtil
								.getMessageById("parser_pattern_marker_id_between"));
				
				// remove the first and last character (< and >)
				patternId = params.get(1).substring(1,
					params.get(1).length() - 1);
				
				// TODO Complete with all the special
				// characters
				if (SystemUtil.hasSpecialCharacters(patternId))
					throwException(
						patternFile,
						m.getLineNumber(),
						SystemUtil
								.getMessageById("parser_pattern_marker_id_special_characters"));
			} else {
				patternId = classIdentifier;
			}
			// ***********************************************
			// Second marker
			
			m = markers.get(1);
			
			if (m.getMarkerId().compareTo("patternElement") != 0)
				throwException(patternFile, m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_not_found"));
			
			params = m.getParams();
			
			if (params.size() == 0)
				throwException(
					patternFile,
					m.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_type_not_found"));
			
			// If it's not a general class, end here
			switch (params.get(0).trim()) {
				case StatePatternParser.STATE_MARKER:
				case "Observed":
				case StrategyPatternParser.STRATEGY_MARKER:
					break;
				default:
					return result;
			}
			
			// ***********************************************
			// Third marker. Only for validation
			if (markers.size() == 3) {
				getBuilderFromMarkers(markers);
			}
			
			// ***********************************************
			// ***********************************************
			
			result = firstStepGetFilesGroup(patternFile, patternId);
			
			if (result == null)
				throwException(
					patternFile,
					markers.get(0).getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_type_not_identified"));
			
		} catch (FileNotFoundException e) {
			throwException(SystemUtil.getMessageById("internal")
					+ " - File not found.");
		}
		
		return result;
	}
	
	/**
	 * Create the concrete Pattern in base of the markers The first marker has
	 * the pattern type
	 * 
	 * @param patternFile
	 * @param patternId
	 * @return
	 * @throws ParserException
	 * @throws SistemUtilException
	 */
	private static PatternParsedFiles firstStepGetFilesGroup (
			ParsedFile patternFile, String patternId) throws ParserException,
			SistemUtilException {
		
		PatternParsedFiles result = null;
		
		try {
			String patternType = patternFile.getMarkedComments().get(0)
					.getMarkers().get(0).getParams().get(0);
			
			PatternName patternName = Parser.getPatternName(patternType);
			
			if (patternName != PatternName.UNKNOWN)
				result = new PatternParsedFiles(patternName, patternId,
						patternFile);
			
		} catch (FileNotFoundException e) {
			throwException(SystemUtil.getMessageById("internal")
					+ " - File not found.");
		}
		
		return result;
	}
	
	/*
	 * Almost none of the error checking is going to throw an exception because
	 * it's supposed to have been checked beforehand in step1
	 */
	public static void secondStepAddFileToPatternGroup (
			List<ParsedFile> patternFiles,
			List<PatternParsedFiles> groupedPatternFiles)
			throws ParserException, FileNotFoundException, SistemUtilException {
		
		for (ParsedFile patternFile : patternFiles) {
			List<Marker> markers = getBeforeClassHeaderMarkers(patternFile);
			String filePatternId = "";
			
			if (markers == null || markers.size() == 0) {
				// This should never happen because of the file pre-processing
				throwException(patternFile.getFile(),
					SystemUtil.getMessageById("internal"));
			}
			
			/*
			 * If it's explicitly defined
			 */
			if (markers.get(0).getParams().size() == 2) {
				
				String tmp = markers.get(0).getParams().get(1);
				
				if (!tmp.startsWith("<") || !tmp.endsWith(">"))
					// This should never happen because of the file
					// pre-processing
					throwException(patternFile.getFile(), markers.get(0)
							.getLineNumber(),
						SystemUtil.getMessageById("internal"));
				
				tmp = tmp.substring(1, tmp.length() - 1);
				
				filePatternId = tmp;
				
			} else {
				
				String classIdentifier = getClassIdentifier(patternFile);
				
				/*
				 * If it's not explicitly defined, it's searched into the
				 * extends or implements. If it has more than one matching, it's
				 * consider an error for the moment (only one pattern membership
				 * is accepted)
				 */
				for (PatternParsedFiles pfs : groupedPatternFiles) {
					
					// If it's the header class, it's already inside and the
					// patternName is the classIdentifier
					if (pfs.getPatternId().compareTo(classIdentifier) == 0)
						if (filePatternId.isEmpty()) {
							filePatternId = classIdentifier;
						} else {
							throwException(
								patternFile,
								SystemUtil
										.getMessageById("parser_multiple_pattern_group_membership"));
						}
					
					// else, search in each inherit class
					for (String s : getExtendsAndImplements(patternFile)) {
						
						if (pfs.getPatternId().compareTo(s) == 0) {
							if (filePatternId.isEmpty()) {
								filePatternId = s;
							} else {
								throwException(
									patternFile,
									SystemUtil
											.getMessageById("parser_multiple_pattern_group_membership"));
							}
						}
					}
				}
			}
			
			boolean findFlag = false;
			boolean generalOneFlag = false;
			for (PatternParsedFiles pfs : groupedPatternFiles) {
				
				// If it's the header class, it's already inside
				if (pfs.getHeaderPatternFile().equals(patternFile)) {
					findFlag = true;
					generalOneFlag = true;
				}
				
				if (pfs.getPatternId().compareTo(filePatternId) == 0) {
					if (getPatternName(markers.get(0).getParams().get(0)) != pfs
							.getPatternName())
						throwException(
							patternFile,
							SystemUtil
									.getMessageById("parser_multiple_pattern_types_by_identifier")
									+ "\n\tIdentifier = " + filePatternId);
					
					if (!generalOneFlag)
						pfs.addPatternFile(patternFile);
					findFlag = true;
					continue;
				}
			}
			
			if (!findFlag)
				throwException(
					patternFile,
					SystemUtil
							.getMessageById("parser_pattern_class_identifier_not_matched")
							+ "\n\tIdentifier: " + filePatternId);
		}
		
		for (PatternParsedFiles pfs : groupedPatternFiles) {
			if (pfs.getPatterFileList().size() == 0) {
				throwException(
					pfs.getHeaderPatternFile(),
					SystemUtil
							.getMessageById("parser_pattern_lonelly_general_class")
							+ "\n\tIdentifier: " + pfs.getPatternId());
			}
		}
		
	}
	
	/**
	 * Takes all the block comments of a file and process them looking for
	 * markers.
	 * 
	 * @param file
	 * @return A MarkedBlockComment for each marked block comment.
	 *         MarkedBlockComment contains a list with all the markers, the
	 *         comments as String and the code that comes after the block
	 *         comment. If no marked block comment is found within the scope 0
	 *         (out of the class) and 1 (the method and attributes declaration)
	 *         returns an empty list
	 * @throws FileNotFoundException
	 */
	public static List<MarkedBlockComment> getAllBlockCommentMarkers (File file)
			throws FileNotFoundException {
		
		List<MarkedBlockComment> result = new ArrayList<MarkedBlockComment>();
		List<MarkedBlockComment> partialResult = new ArrayList<MarkedBlockComment>();
		
		Scanner reader = null;
		String line = "";
		long lineNumber = 0;
		long blockBeginLine = 0;
		
		String codeAfter = "";
		long lineNumberCodeAfter = 0;
		
		boolean insideBlockComment = false;
		int scope = 0;
		
		if (file.exists() && file.canRead()) {
			
			try {
				
				reader = new Scanner(file, "UTF-8");
				line = reader.nextLine();
				lineNumber++;
				
				String blockComment = "";
				MarkedBlockComment markedBlockComment;
				
				/*
				 * Process the file until: (end of class and allBlockComments)
				 * OR (arrives to class header)
				 */
				while (line != null) {
					
					line = line.trim();
					
					// Only before class and before method are interesting
					// The ifs order matters
					if (scope < 2 && line.compareTo("/*") == 0) {
						insideBlockComment = true;
						blockBeginLine = lineNumber;
					}
					
					// It's public method or public class
					if (!insideBlockComment
							&& scope < 2
							&& (line.startsWith("public")
									|| line.startsWith("protected") || !codeAfter
										.isEmpty())) {
						if (line.startsWith("public")
								|| line.startsWith("protected"))
							lineNumberCodeAfter = lineNumber;
						
						codeAfter += " " + line;
					}
					
					// scope init
					// It could be an interface marked method or a class marked
					// methos, so the line can end in ; or {
					if (!insideBlockComment && !line.startsWith("//")
							&& (line.endsWith("{") || line.endsWith(";"))) {
						
						if (scope < 2 && partialResult.size() > 0) {
							
							// ADDING THE CODE AFTER THE BLOCK COMMENT AND IT'S
							// LINE NUMBER
							for (MarkedBlockComment mbc : partialResult) {
								mbc.setCodeAfterBlock(codeAfter
										.replace("{", "").trim());
								mbc.setLineNumberCodeAfterBlock(lineNumberCodeAfter);
								result.add(mbc);
							}
						}
						
						if (scope < 2) {
							partialResult = new ArrayList<MarkedBlockComment>();
							codeAfter = "";
						}
						
						if (line.endsWith("{"))
							scope++;
					}
					
					// scope end
					if (!insideBlockComment && !line.startsWith("//")
							&& line.endsWith("}")) {
						scope--;
					}
					
					// Only before class and before method are interesting
					if (scope < 2) {
						
						if (line.compareTo("*/") == 0) {
							
							List<Marker> markers = processMarkedBlockComment(
								blockComment, blockBeginLine + 1,
								file.getAbsolutePath());
							if (markers.size() > 0) {
								
								markedBlockComment = new MarkedBlockComment(
										markers, blockComment, scope);
								
								partialResult.add(markedBlockComment);
							}
							
							insideBlockComment = false;
							blockComment = "";
						}
						
						// put together all the block comment
						if (insideBlockComment && line.compareTo("/*") != 0) {
							// Take out all the possible *
							while (!line.isEmpty() && line.startsWith("*"))
								line = line.substring(1).trim();
							
							if (!blockComment.isEmpty())
								blockComment += "\n";
							blockComment += line;
						}
					}
					
					// Next line
					line = reader.nextLine();
					lineNumber++;
				}
			} catch (NoSuchElementException ex) {
			} finally {
				reader.close();
			}
		}
		
		return result;
	}
	
	/**
	 * Process a marked block comment and return a list of markers
	 * 
	 * @param blockComment
	 * @param beginLineNumber
	 * @param filePath
	 * @return
	 */
	private static List<Marker> processMarkedBlockComment (String blockComment,
			long beginLineNumber, String filePath) {
		
		List<Marker> result = new ArrayList<Marker>();
		
		if (blockComment == null || blockComment.isEmpty())
			return result;
		
		String[] lineSplitted = blockComment.split("\n");
		
		if (lineSplitted.length == 0)
			return result;
		
		Marker marker = null;
		String line;
		String acumulatedLine = "";
		int i;
		long markerBeginLine = 0;
		
		for (i = 0; i < lineSplitted.length; i++) {
			line = lineSplitted[i].trim();
			
			if (line.startsWith("@")) {
				if (acumulatedLine.startsWith("@")) {
					marker = new Marker(filePath, markerBeginLine,
							acumulatedLine);
					if (marker.isMarker())
						result.add(marker);
				}
				markerBeginLine = beginLineNumber + i;
				acumulatedLine = line;
			} else {
				acumulatedLine += " " + line;
			}
		}
		
		// the last marked line is not processed inside the for
		if (acumulatedLine.startsWith("@")) {
			marker = new Marker(filePath, markerBeginLine, acumulatedLine);
			if (marker.isMarker())
				result.add(marker);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param file
	 *            to be parsed
	 * @return a list with the markers that are in the first marked block
	 *         comment before the class reserved word
	 * @throws FileNotFoundException
	 */
	public static List<Marker> getBeforeClassHeaderMarkers (File file)
			throws FileNotFoundException {
		
		List<Marker> result = new ArrayList<Marker>();
		
		List<MarkedBlockComment> markers = getAllBlockCommentMarkers(file);
		if (markers.size() > 0)
			result = markers.get(0).getMarkers();
		
		return result;
		
	}
	
	public static List<Marker> getBeforeClassHeaderMarkers (
			ParsedFile patternFile) throws FileNotFoundException,
			ParserException, SistemUtilException {
		
		List<Marker> result = new ArrayList<Marker>();
		
		List<MarkedBlockComment> markers = patternFile.getMarkedComments();
		if (markers.size() > 0) {
			// This should never happen because of the file pre-processing
			if (markers.get(0).getScope() != 0)
				throwException(patternFile.getFile(),
					SystemUtil.getMessageById("internal"));
			
			result = markers.get(0).getMarkers();
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param file
	 *            to be parsed
	 * @return the class identifier name. It's supposed to be the same as the
	 *         file name.
	 * @throws ParserException
	 * @throws SistemUtilException
	 */
	public static String getClassIdentifier (ParsedFile patternFile)
			throws ParserException, SistemUtilException {
		String result = "";
		
		List<MarkedBlockComment> markers = patternFile.getMarkedComments();
		if (markers.size() > 0) {
			
			String header = markers.get(0).getCodeAfterBlock();
			boolean classFlag = false;
			
			for (String si : header.split(" ")) {
				
				String s = si.trim();
				/*
				 * If class reserved word is passed, but is no class name
				 */
				if (!classFlag
						&& (s.compareTo("extends") == 0
								|| s.compareTo("implements") == 0 || s
								.compareTo("{") == 0)) {
					throwException(
						patternFile,
						SystemUtil
								.getMessageById("parser_get_class_identifier_id_not_found"));
				}
				
				/*
				 * If class reserved word is passed and is not empty and the if
				 * before doesn't was true, then s is the class name
				 */
				if (classFlag && !s.isEmpty() && s.compareTo("{") != 0) {
					result = s;
					break;
				}
				
				// When arrived to class reserved word, flag true
				if (s.compareTo("class") == 0 || s.compareTo("interface") == 0) {
					classFlag = true;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param file
	 *            to be parsed
	 * @return a list with all the classes which are extended and all the
	 *         interfaces that are implemented
	 * @throws FileNotFoundException
	 */
	public static List<String> getExtendsAndImplements (ParsedFile patternFile)
			throws FileNotFoundException {
		
		List<String> result = new ArrayList<String>();
		
		String header = patternFile.getMarkedComments().get(0)
				.getCodeAfterBlock();
		
		boolean extImplFlag = false;
		
		// If it has extends or implements clause
		if (header.contains(" extends ") || header.contains(" implements ")) {
			
			for (String si : header.split(" ")) {
				
				String s = si.replace(",", "").trim();
				
				if (s.compareTo("trhows") == 0)
					break;
				
				/*
				 * If extends or implements clause has passed
				 */
				if (extImplFlag) {
					/*
					 * And it's not the other one
					 */
					if (s.compareTo("extends") != 0
							&& s.compareTo("implements") != 0) {
						result.add(s);
					}
				}
				
				/*
				 * After the first one: extends or implements
				 */
				if (s.compareTo("extends") == 0
						|| s.compareTo("implements") == 0) {
					extImplFlag = true;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws ParserException
	 * @throws SistemUtilException
	 */
	public static String getPackage (File file) throws FileNotFoundException,
			ParserException, SistemUtilException {
		Scanner reader = null;
		String line = "";
		long lineNumber = 0;
		
		if (file.exists() && file.canRead()) {
			
			try {
				
				reader = new Scanner(file, "UTF-8");
				line = reader.nextLine();
				lineNumber++;
				
				while (line != null) {
					
					line = line.trim();
					
					if (line != "" && !line.startsWith("package"))
						throwException(
							file,
							lineNumber,
							SystemUtil
									.getMessageById("parser_file_must_contain_package_definition"));
					
					if (line.startsWith("package")) {
						String[] aux = line.split(" ");
						
						if (aux.length != 2)
							throwException(
								file,
								lineNumber,
								SystemUtil
										.getMessageById("parser_file_package_definition_must_be_valid"));
						
						return aux[1].trim().replace(";", "").trim();
					}
					
					// Next line
					line = reader.nextLine();
					lineNumber++;
				}
			} finally {
				reader.close();
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static List<String> getImports (File file)
			throws FileNotFoundException {
		Scanner reader = null;
		String line = "";
		
		List<String> result = new ArrayList<String>();
		
		if (file.exists() && file.canRead()) {
			
			try {
				
				reader = new Scanner(file, "UTF-8");
				line = reader.nextLine();
				
				while (line != null) {
					
					line = line.trim();
					
					if (line.contains(" class ") || line.startsWith("class "))
						break;
					
					if (line.startsWith("import")) {
						result.add(line.replaceFirst("import", "")
								.replace(";", "").trim());
					}
					
					// Next line
					line = reader.nextLine();
				}
			} catch (NoSuchElementException ex) {
			} finally {
				reader.close();
			}
		}
		
		return result;
	}
	
	public static String getClassBuilderMarker (File file)
			throws ParserException, SistemUtilException {
		
		String result = "";
		
		try {
			List<Marker> befHeadMarkers = Parser
					.getBeforeClassHeaderMarkers(file);
			
			return getBuilderFromMarkers(befHeadMarkers);
		} catch (FileNotFoundException e) {
			throwException(file, SystemUtil.getMessageById("internal")
					+ ". File not found.");
		}
		
		/*
		 * the default value
		 */
		return result;
	}
	
	/**
	 * Gets the builder if exists
	 * 
	 * @param markers
	 *            must be before class header markers
	 * @return
	 * @throws SistemUtilException
	 * @throws StatePatternParserException
	 */
	public static String getBuilderFromMarkers (List<Marker> markers)
			throws ParserException, SistemUtilException {
		
		String result = "";
		
		if (markers.size() == 3) {
			Marker marker = markers.get(2);
			if (marker.getMarkerId().compareTo("patternElement") != 0)
				throwException(
					marker.getFilePath(),
					marker.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_builder_wrong"));
			
			if (marker.getParams().size() != 2)
				throwException(
					marker.getFilePath(),
					marker.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_builder_wrong"));
			
			if (marker.getParams().get(0).compareTo("Builder") != 0)
				throwException(
					marker.getFilePath(),
					marker.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_builder_wrong"));
			
			result = marker.getParams().get(1);
			
			if (!result.startsWith("<") || !result.endsWith(">"))
				throwException(
					marker.getFilePath(),
					marker.getLineNumber(),
					SystemUtil
							.getMessageById("parser_pattern_element_builder_wrong"));
			
			result = result.substring(1, result.length() - 1);
		}
		
		return result.trim();
	}
	
	private static void throwException (ParsedFile patternFile,
			long lineNumber, String message) throws ParserException {
		throw new ParserException(message + " \n\t File: "
				+ patternFile.getFile().getAbsolutePath() + "\n\tLine: "
				+ lineNumber);
	}
	
	private static void throwException (File file, long lineNumber,
			String message) throws ParserException {
		throw new ParserException(message + " \n\t File: "
				+ file.getAbsolutePath() + "\n\tLine: " + lineNumber);
	}
	
	private static void throwException (String path, long lineNumber,
			String message) throws ParserException {
		throw new ParserException(message + " \n\t File: " + path
				+ "\n\tLine: " + lineNumber);
	}
	
	private static void throwException (ParsedFile patternFile, String message)
			throws ParserException {
		throw new ParserException(message + " \n\t File: "
				+ patternFile.getFile().getAbsolutePath());
	}
	
	private static void throwException (File file, String message)
			throws ParserException {
		throw new ParserException(message + " \n\t File: "
				+ file.getAbsolutePath());
	}
	
	private static void throwException (String message) throws ParserException {
		throw new ParserException(message);
	}
	
	public static PatternName getPatternName (String name) {
		switch (name) {
			case StatePatternParser.STATE_MARKER:
				return PatternName.STATE;
			case StrategyPatternParser.STRATEGY_MARKER:
				return PatternName.STRATEGY;
			case OBSERVER_MARKER:
				return PatternName.OBSERVER;
			default:
				return PatternName.UNKNOWN;
		}
	}
}
