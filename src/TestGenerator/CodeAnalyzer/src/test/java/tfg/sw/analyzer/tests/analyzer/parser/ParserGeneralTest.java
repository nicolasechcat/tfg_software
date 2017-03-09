package tfg.sw.analyzer.tests.analyzer.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.analyzer.analyzer.MarkedBlockComment;
import tfg.sw.analyzer.analyzer.Marker;
import tfg.sw.analyzer.analyzer.ParsedFile;
import tfg.sw.analyzer.analyzer.PatternParsedFiles;
import tfg.sw.analyzer.analyzer.Parser;
import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.pattern.PatternName;
import tfg.sw.analyzer.util.SystemUtil;

public class ParserGeneralTest {

	public static final String MARKER_CHARACTER = "@";

	private String rootWrong = "src/test/resources/testExamples/parser/general/wrong/";
	private String rootWrongFirstStep = rootWrong + "firstStep/";
	private String rootWrongSecondStep = rootWrong + "secondStep/";
	private String rootCorrect = "src/test/resources/testExamples/parser/general/correct/";
	private String rootCorrectFirstStep = rootCorrect + "firstStep/";
	private String rootCorrectSecondStep = rootCorrect + "secondStep/";

	/*
	 * ******************************************************************
	 * ******************************************************************
	 * First step
	 * ******************************************************************
	 * ******************************************************************
	 */

	@Test
	public void it1_ft01_testMarkedFileOk() throws FileNotFoundException,
			SistemUtilException, ParserException {

		String waittedPatternId = "MarkedFileOk";
		PatternName waitedPatternName = PatternName.STATE;
		List<ParsedFile> waitedPatternFiles = new ArrayList<ParsedFile>();

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootCorrectFirstStep + "MarkedFileOk.java");
		patternFiles.add(patternFile);

		List<PatternParsedFiles> result = null;
		result = Parser.firstStepParsePatternHeader(patternFiles);

		PatternParsedFiles resultFiles = result.get(0);

		assertEquals(patternFile, resultFiles.getHeaderPatternFile());
		assertEquals(waittedPatternId, resultFiles.getPatternId());
		assertEquals(waitedPatternName, resultFiles.getPatternName());
		assertEquals(waitedPatternFiles, resultFiles.getPatterFileList());

	}

	@Test
	public void it1_ft02_testNoMarkedFile() throws FileNotFoundException,
			SistemUtilException, ParserException {

		List<MarkedBlockComment> waitedResult = new ArrayList<MarkedBlockComment>();

		File file = new File(rootCorrectFirstStep + "NoMarkers.java");

		List<MarkedBlockComment> result = null;

		result = new ParsedFile(file).getMarkedComments();

		assertEquals(waitedResult, result);
	}

	@Test
	public void it1_ft03_testMarkedFileButNotBeforeClassHeader()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_no_before_header_marker");

		List<File> files = new ArrayList<File>();
		files.add(new File(rootWrongFirstStep + "NoHeaderMarkers.java"));

		String result = "";

		try {
			Parser.parseFileList(files);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft04_testMoreThanThreeHeaderMarkers()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_wrong_header_markers_number_much");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "MoreThanThreeHeaderMarkers.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft05_testLessThanTwoHeaderMarkers()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil.getMessageById(
				"parser_wrong_header_markers_number_less").trim();

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "LessThanTwoHeaderMarkers.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage().trim();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft06_testWrongFirstHeaderMarker()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_wrong_header_first_marker");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "WrongFirstHeaderMarker.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft07_testWrongSecondHeaderMarker()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_not_found");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "WrongSecondHeaderMarker.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft08_testWrongThirdHeaderMarker()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_builder_wrong");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "WrongThirdHeaderMarker.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft09_testPatternMarkerNoType()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_marker_without_type");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternMarkerNoType.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft10_testPatternMarkerTooMuchParameters()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_marker_too_much_params");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternMarkerTooMuchParameters.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft11_testPatternTypeNotRecognized()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_type_not_identified");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternTypeNotRecognized.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft12_testPatternWrongIdentifier1()
			throws FileNotFoundException, SistemUtilException {

		// <id> right, but id>

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_marker_id_between");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternWrongIdentifier1.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft12_testPatternWrongIdentifier2()
			throws FileNotFoundException, SistemUtilException {

		// <id> right, but <id

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_marker_id_between");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternWrongIdentifier2.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft13_testPatternWrongIdentifierCharacters()
			throws FileNotFoundException, SistemUtilException {

		// <id> right, but <i>d>

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_marker_id_special_characters");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternWrongIdentifierCharacters.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft14_testPatternElementNoParameters()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_type_not_found");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternElementNoParameters.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft15_testPatternElementBuilderNoMoreParameters()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_builder_wrong");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternElementBuilderNoMoreParameters.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft16_testPatternElementBuilderTooMuchParameters()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_builder_wrong");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternElementBuilderTooMuchParameters.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft17_testPatternElementBuilderWrongParameter1()
			throws FileNotFoundException, SistemUtilException {

		// <builder instance> ok, but <builder instance

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_builder_wrong");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternElementBuilderWrongParameter1.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft17_testPatternElementBuilderWrongParameter2()
			throws FileNotFoundException, SistemUtilException {

		// <builder instance> ok, but builder instance>

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_element_builder_wrong");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		patternFile = new ParsedFile(rootWrongFirstStep
				+ "PatternElementBuilderWrongParameter2.java");
		patternFiles.add(patternFile);

		String result = "";

		try {
			Parser.firstStepParsePatternHeader(patternFiles);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	/*
	 * ******************************************************************
	 * ******************************************************************
	 * Second step
	 * ******************************************************************
	 * ******************************************************************
	 */

	private List<ParsedFile> getSecondStepCorrectBase()
			throws FileNotFoundException {
		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		// Correct ones
		patternFile = new ParsedFile(rootCorrectSecondStep
				+ "State1Context.java");
		patternFiles.add(patternFile);

		patternFile = new ParsedFile(rootCorrectSecondStep
				+ "State1Header.java");
		patternFiles.add(patternFile);

		patternFile = new ParsedFile(rootCorrectSecondStep
				+ "State1Concrete.java");
		patternFiles.add(patternFile);

		patternFile = new ParsedFile(rootCorrectSecondStep
				+ "State2Concrete.java");
		patternFiles.add(patternFile);

		patternFile = new ParsedFile(rootCorrectSecondStep
				+ "State2Header.java");
		patternFiles.add(patternFile);

		patternFile = new ParsedFile(rootCorrectSecondStep
				+ "State2Context.java");
		patternFiles.add(patternFile);

		return patternFiles;
	}

	@Test
	public void it1_ft18_testOneFileWithoutGroup()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_class_identifier_not_matched");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		// Correct ones
		patternFiles.addAll(getSecondStepCorrectBase());

		// Wrong one
		patternFile = new ParsedFile(rootWrongSecondStep
				+ "NoGeneralMarkedClassWithoutGroup.java");
		patternFiles.add(patternFile);

		String result = "";
		List<PatternParsedFiles> resultFirstStep = null;

		try {
			resultFirstStep = Parser.firstStepParsePatternHeader(patternFiles);

			Parser.secondStepAddFileToPatternGroup(patternFiles,
					resultFirstStep);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft19_testHeaderUniqueOneInGroup()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_pattern_lonelly_general_class");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		// Correct ones
		patternFiles.addAll(getSecondStepCorrectBase());

		// Wrong one
		patternFile = new ParsedFile(rootWrongSecondStep
				+ "HeaderUniqueOneInGroup.java");
		patternFiles.add(patternFile);

		String result = "";
		List<PatternParsedFiles> resultFirstStep = null;

		try {
			resultFirstStep = Parser.firstStepParsePatternHeader(patternFiles);

			Parser.secondStepAddFileToPatternGroup(patternFiles,
					resultFirstStep);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft20_testClassWithMoreThanOneIdentifier()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_multiple_pattern_group_membership");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		// Correct ones
		patternFiles.addAll(getSecondStepCorrectBase());

		// Wrong one
		patternFile = new ParsedFile(rootWrongSecondStep
				+ "ClassWithMoreThanOneIdentifier.java");
		patternFiles.add(patternFile);

		String result = "";
		List<PatternParsedFiles> resultFirstStep = null;

		try {
			resultFirstStep = Parser.firstStepParsePatternHeader(patternFiles);

			Parser.secondStepAddFileToPatternGroup(patternFiles,
					resultFirstStep);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft21_testPatternTypeAndFilePatternTypeDontMatch()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_multiple_pattern_types_by_identifier");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		// Correct ones
		patternFiles.addAll(getSecondStepCorrectBase());

		// Wrong one
		patternFile = new ParsedFile(rootWrongSecondStep
				+ "PatternTypeAndFilePatternTypeDontMatch.java");
		patternFiles.add(patternFile);

		String result = "";
		List<PatternParsedFiles> resultFirstStep = null;

		try {
			resultFirstStep = Parser.firstStepParsePatternHeader(patternFiles);

			Parser.secondStepAddFileToPatternGroup(patternFiles,
					resultFirstStep);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	@Test
	public void it1_ft55_testPatternTypeAndFilePatternTypeDontMatch()
			throws FileNotFoundException, SistemUtilException {

		String waitedResult = SystemUtil
				.getMessageById("parser_multiple_use_of_pattern_id");

		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();

		// Correct ones
		patternFiles.addAll(getSecondStepCorrectBase());

		// Wrong one
		patternFile = new ParsedFile(rootWrongSecondStep
				+ "State3HeaderIDState1Header.java");
		patternFiles.add(patternFile);

		String result = "";
		List<PatternParsedFiles> resultFirstStep = null;

		try {
			resultFirstStep = Parser.firstStepParsePatternHeader(patternFiles);

			Parser.secondStepAddFileToPatternGroup(patternFiles,
					resultFirstStep);
		} catch (ParserException ex) {
			result = ex.getMessage();
		}

		assertTrue(result.startsWith(waitedResult));
	}

	// **************************************************************************************************
	// **************************************************************************************************
	// **************************************************************************************************

	@Test
	public void it1_it01_testCorrectBlockCommentMarkers()
			throws FileNotFoundException {

		File file = new File(rootCorrectSecondStep + "State1Header.java");

		Marker m1 = new Marker(file.getAbsolutePath(), 4, "@pattern State");
		Marker m2 = new Marker(file.getAbsolutePath(), 5,
				"@patternElement State");

		String codeAfter = "public abstract class State1Header";

		List<MarkedBlockComment> result = Parser
				.getAllBlockCommentMarkers(file);

		assertEquals(1, result.size());
		assertEquals(2, result.get(0).getMarkers().size());

		assertEquals(m1, result.get(0).getMarkers().get(0));
		assertEquals(m2, result.get(0).getMarkers().get(1));

		assertEquals(codeAfter, result.get(0).getCodeAfterBlock());
	}

	@Test
	public void it1_it02_testCorrectBlockCommentMultilineMarkers()
			throws FileNotFoundException {

		File file = new File(rootCorrect + "MultiLineMarker.java");

		Marker m11 = new Marker(file.getAbsolutePath(), 7,
				"@pattern State <State1Header>");
		Marker m12 = new Marker(file.getAbsolutePath(), 8,
				"@patternElement SpecificState initial");

		String codeAfter1 = "public class MultiLineMarker extends State1Header";

		Marker m21 = new Marker(file.getAbsolutePath(), 12,
				"@patternAction Transition State1Concrete2 <context, 000000000412411215235>");

		String codeAfter2 = "public void action1 (State1Context context)";

		List<MarkedBlockComment> result = Parser
				.getAllBlockCommentMarkers(file);

		assertEquals(2, result.size());
		assertEquals(2, result.get(0).getMarkers().size());
		assertEquals(1, result.get(1).getMarkers().size());

		assertEquals(m11, result.get(0).getMarkers().get(0));
		assertEquals(m12, result.get(0).getMarkers().get(1));
		assertEquals(codeAfter1, result.get(0).getCodeAfterBlock());

		assertEquals(m21, result.get(1).getMarkers().get(0));
		assertEquals(codeAfter2, result.get(1).getCodeAfterBlock());

	}
}
