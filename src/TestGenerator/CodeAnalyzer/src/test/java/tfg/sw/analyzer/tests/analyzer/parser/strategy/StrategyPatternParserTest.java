package tfg.sw.analyzer.tests.analyzer.parser.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.analyzer.analyzer.ParsedFile;
import tfg.sw.analyzer.analyzer.PatternParsedFiles;
import tfg.sw.analyzer.analyzer.Parser;
import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternParser;
import tfg.sw.analyzer.pattern.strategy.Comparison;
import tfg.sw.analyzer.pattern.strategy.ConcreteStrategy;
import tfg.sw.analyzer.pattern.strategy.StrategyAction;
import tfg.sw.analyzer.pattern.strategy.StrategyPattern;
import tfg.sw.analyzer.pattern.strategy.comparison.ByComparable;
import tfg.sw.analyzer.pattern.strategy.comparison.ByComparator;
import tfg.sw.analyzer.pattern.strategy.comparison.ByEquals;
import tfg.sw.analyzer.util.SystemUtil;

public class StrategyPatternParserTest {
	
	private String rootWrong = "src/test/resources/testExamples/parser/strategy/wrong/";
	private String rootCorrect = "src/test/resources/testExamples/parser/strategy/correct/";
	
	private PatternParser patternParser = StrategyPattern.getParser();
	
	@Test
	public void it2_ft01_testAllOk () throws FileNotFoundException,
			ParserException, SistemUtilException {
		Pattern result = null;
		Pattern waitedResult = null;
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// WaitedResult
		waitedResult = getPatternsCorrectBaseStrategyPattern1();
		
		List<PatternParsedFiles> resultFirstSteps = Parser
				.firstStepParsePatternHeader(patternFiles);
		
		Parser.secondStepAddFileToPatternGroup(patternFiles, resultFirstSteps);
		
		for (PatternParsedFiles gp : resultFirstSteps) {
			result = patternParser.parse(gp);
		}
		
		assertEquals(waitedResult, result);
	}
	
	
	@Test
	public void it2_ft02_testPatternElementTypeNotKnown ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(true, "PatternElementPatternTypeNotKnown.java",
			"strategy_parser_element_not_known");
	}
	
	
	
	@Test
	public void it2_ft03_testStrategyTypeWithTooMuchHeaderMarkers ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "StrategyTypeWithTooMuchHeaderMarkers.java",
			"strategy_parser_strategy_class_header_too_much_markers");
	}
	
	@Test
	public void it2_ft04_testPatternElementStrategyTypeInsideNotAdmited ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementStrategyTypeInsideNotAdmited.java",
			"strategy_parser_strategy_class_marker_not_admited");
	}
	
	@Test
	public void it2_ft05_testPatternElementStrategyTypeInsideNotAdmitedElementType ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementStrategyTypeInsideNotAdmitedElementType.java",
			"strategy_parser_element_not_action_found");
	}
	
	@Test
	public void it2_ft06_testConcreteStrategyWithMultipleMarkedBlocks ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(true, "ConcreteStrategyWithMultipleMarkedBlocks.java",
			"strategy_parser_concrete_strategy_more_marked_blocks");
	}
	
	@Test
	public void it2_ft07_PatternElementActionToTimesSameBlock ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "PatternElementActionToTimesSameBlock.java",
			"strategy_parser_element_action_multiple_actions_same_block");
	}
	
	@Test
	public void it2_ft08_PatternElementActionWithParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "PatternElementActionWithParameters.java",
			"strategy_parser_element_action_action_more_parameters");
	}
	
	@Test
	public void it2_ft09_PatternElementComparisonMoreThanOne ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "PatternElementComparisonMoreThanOne.java",
			"strategy_parser_element_action_multiple_comparisons_same_block");
	}
	
	@Test
	public void it2_ft10_PatternElementComparisonNoParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "PatternElementComparisonNoParameters.java",
			"strategy_parser_element_action_comparison_no_more_parameters");
	}
	
	@Test
	public void it2_ft11_PatternElementComparisonTypeNotKnown ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "PatternElementComparisonTypeNotKnown.java",
			"strategy_parser_element_action_comparison_not_known");
	}
	
	@Test
	public void it2_ft12_PatternElementComparisonEqualsWithParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonEqualsWithParameters.java",
			"strategy_parser_element_action_comparison_equals_more_parameters");
	}
	
	@Test
	public void it2_ft13_PatternElementComparisonComparableWithParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonComparableWithParameters.java",
			"strategy_parser_element_action_comparison_comparable_more_parameters");
	}
	
	@Test
	public void it2_ft14_PatternElementComparisonComparatorOnlyOneParameter ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonComparatorOnlyOneParameter.java",
			"strategy_parser_element_action_comparison_comparator_one_parameter");
	}
	
	@Test
	public void it2_ft15_PatternElementComparisonComparatorMoreThanThreeParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonComparatorMoreThanThreeParameters.java",
			"strategy_parser_element_action_comparison_comparator_more_parameters");
	}
	
	@Test
	public void it2_ft16_PatternElementComparisonComparatorWrongFirstParameter ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonComparatorWrongFirstParameter.java",
			"strategy_parser_element_action_comparison_comparator_wrong_parameters");
	}
	
	@Test
	public void it2_ft17_PatternElementComparisonComparatorWrongSecondParameter ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonComparatorWrongSecondParameter.java",
			"strategy_parser_element_action_comparison_comparator_wrong_parameters");
	}
	
	@Test
	public void it2_ft18_PatternElementComparisonComparatorWrongThirdParameter ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementComparisonComparatorWrongThirdParameter.java",
			"strategy_parser_element_action_comparison_comparator_wrong_parameters");
	}
	
	
	@Test
	public void it2_ft19_PatternElementExecutionNoMoreParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false, "PatternElementExecutionNoMoreParameters.java",
			"strategy_parser_wrong_parameter_set_less_parameters");
	}
	
	
	@Test
	public void it2_ft20_PatternElementExecutionTooMuchParameters ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementExecutionTooMuchParameters.java",
			"strategy_parser_wrong_parameter_set_too_much_parameters");
	}
	
	@Test
	public void it2_ft21_PatternElementExecutionWrongParameterFormat ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		executeErrorTest(false,
			"PatternElementExecutionWrongParameterFormat.java",
			"strategy_parser_wrong_parameter_set_second_parameter");
	}
	
	
	/*
	 * ******************************************************************************************
	 * ******************************************************************************************
	 * ******************************************************************************************
	 */
	
	
	
	private void executeErrorTest (boolean baseHeader, String fileName,
			String messageId) throws SistemUtilException, FileNotFoundException {
		String waitedResult = SystemUtil.getMessageById(messageId);
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		if (baseHeader)
			patternFiles.addAll(getFilePatternsCorrectBase());
		else
			patternFiles.addAll(getFilePatternsCorrectBaseNoHeader());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong + fileName);
		patternFiles.add(patternFile);
		
		
		String result = "";
		
		try {
			List<PatternParsedFiles> resultFirstSteps = Parser
					.firstStepParsePatternHeader(patternFiles);
			Parser.secondStepAddFileToPatternGroup(patternFiles,
				resultFirstSteps);
			
			// Pattern p;
			for (PatternParsedFiles gp : resultFirstSteps) {
				// p = patternParser.parse(gp);
				patternParser.parse(gp);
				// System.out.println(p);
			}
			
			fail();
			
		} catch (ParserException ex) {
			result = ex.getMessage();
		}
		
		assertTrue(result.startsWith(waitedResult));
	}
	
	
	/*
	 * ******************************************************************************************
	 * ******************************************************************************************
	 * ******************************************************************************************
	 */
	
	
	
	// @Test
	// public void it2_ft_test ()
	// throws FileNotFoundException, ParserException, SistemUtilException {
	//
	// String waitedResult = SystemUtil
	// .getMessageById("strategy_parser_element_not_known");
	//
	// List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
	//
	// // Correct ones
	// patternFiles.addAll(getFilePatternsCorrectBase());
	// patternFiles.addAll(getFilePatternsCorrectBaseNoHeader());
	//
	// // Wrong one
	// ParsedFile patternFile = new ParsedFile(rootWrong
	// + "PatternElementPatternTypeNotKnown.java");
	// patternFiles.add(patternFile);
	//
	//
	// String result = "";
	//
	// try {
	// List<ParsedPatternFiles> resultFirstSteps = Parser
	// .firstStepParsePatternHeader(patternFiles);
	// Parser.secondStepAddFileToPatternGroup(patternFiles, resultFirstSteps);
	//
	// // Pattern p;
	// for (ParsedPatternFiles gp : resultFirstSteps) {
	// // p = patternParser.parse(gp);
	// patternParser.parse(gp);
	// // System.out.println(p);
	// }
	//
	// fail();
	//
	// } catch (ParserException ex) {
	// result = ex.getMessage();
	// }
	//
	// assertTrue(result.startsWith(waitedResult));
	// }
	
	
	/*
	 * ******************************************************************************************
	 * ******************************************************************************************
	 * ******************************************************************************************
	 */
	
	private List<ParsedFile> getFilePatternsCorrectBase ()
			throws FileNotFoundException {
		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFile = new ParsedFile(rootCorrect + "Strategy1.java");
		patternFiles.add(patternFile);
		
		patternFiles.addAll(getFilePatternsCorrectBaseNoHeader());
		
		return patternFiles;
	}
	
	private List<ParsedFile> getFilePatternsCorrectBaseNoHeader ()
			throws FileNotFoundException {
		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFile = new ParsedFile(rootCorrect + "ConcreteStrategy1.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "ConcreteStrategy2.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "ConcreteStrategy3.java");
		patternFiles.add(patternFile);
		
		return patternFiles;
	}
	
	private Pattern getPatternsCorrectBaseStrategyPattern1 ()
			throws FileNotFoundException {
		
		Pattern pattern;
		
		String patternId = "Strategy1";
		
		String genericPackage = "testExamples.parser.strategy.correct";
		String genericClassName = "Strategy1";
		String concretePackage = "testExamples.parser.strategy.correct.concrete";
		
		List<StrategyAction> actions = new ArrayList<StrategyAction>();
		StrategyAction action;
		
		List<String> parameterSets;
		Comparison comparison;
		
		
		// action1
		
		// @patternElement Execution < new Date (2017, 01, 01), true >
		parameterSets = new ArrayList<String>();
		parameterSets.add("new Date (2017, 01, 01), true");
		
		// @patternElement Execution < new Date (2017, 12, 31), true >
		parameterSets.add("new Date (2017, 12, 31), true");
		
		comparison = new ByEquals();
		
		action = new StrategyAction("Date", "java.util.Date", "action1",
				parameterSets, comparison);
		actions.add(action);
		
		
		
		// action2
		
		parameterSets = new ArrayList<String>();
		
		comparison = new ByComparable();
		
		action = new StrategyAction("UsersClassComparable", "", "action2",
				parameterSets, comparison);
		actions.add(action);
		
		
		
		// action3
		
		// @patternElement Execution < new UsersClassComparable () >
		parameterSets = new ArrayList<String>();
		parameterSets.add("new UsersClassComparable ()");
		
		// @patternElement Comparison Comparator
		// <testExamples.parser.strategy.correct.auxiliar> <UsersComparator> <
		// new UsersComparator (3) >
		
		comparison = new ByComparator(
				"testExamples.parser.strategy.correct.auxiliar",
				"UsersComparator", "new UsersComparator (3)");
		
		action = new StrategyAction("UsersClassComparator1", "", "action3",
				parameterSets, comparison);
		actions.add(action);
		
		
		
		// action4
		
		// @patternElement Comparison Comparator
		parameterSets = new ArrayList<String>();
		comparison = new ByComparator();
		
		action = new StrategyAction("UsersClassComparator2", "", "action4",
				parameterSets, comparison);
		actions.add(action);
		
		
		
		
		List<ConcreteStrategy> concreteStrategies = new ArrayList<ConcreteStrategy>();
		ConcreteStrategy concrete;
		
		// ConcreteStrategy1
		concrete = new ConcreteStrategy("ConcreteStrategy1",
				"new ConcreteStrategy1 ()");
		concreteStrategies.add(concrete);
		
		// ConcreteStrategy2
		concrete = new ConcreteStrategy("ConcreteStrategy2", "");
		concreteStrategies.add(concrete);
		
		// ConcreteStrategy3
		concrete = new ConcreteStrategy("ConcreteStrategy3", "");
		concreteStrategies.add(concrete);
		
		
		
		pattern = new StrategyPattern(patternId, genericPackage,
				genericClassName, actions, concretePackage, concreteStrategies);
		
		return pattern;
	}
}
