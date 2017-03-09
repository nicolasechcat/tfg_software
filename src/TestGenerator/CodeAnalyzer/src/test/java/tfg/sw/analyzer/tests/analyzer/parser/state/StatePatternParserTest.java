package tfg.sw.analyzer.tests.analyzer.parser.state;

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
import tfg.sw.analyzer.pattern.state.ConcreteState;
import tfg.sw.analyzer.pattern.state.Parameter;
import tfg.sw.analyzer.pattern.state.StatePattern;
import tfg.sw.analyzer.pattern.state.Transition;
import tfg.sw.analyzer.util.SystemUtil;



public class StatePatternParserTest {
	
	private String rootWrong = "src/test/resources/testExamples/parser/state/wrong/";
	private String rootCorrect = "src/test/resources/testExamples/parser/state/correct/";
	
	private PatternParser patternParser = StatePattern.getParser();
	
	@Test
	public void it1_ft22_testAllOk () throws FileNotFoundException,
			ParserException, SistemUtilException {
		
		List<Pattern> result = new ArrayList<Pattern>();
		List<Pattern> waitedResult = new ArrayList<Pattern>();
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrect());
		
		// WaitedResult
		waitedResult.addAll(getPatternsCorrectBase());
		
		List<PatternParsedFiles> resultFirstSteps = Parser
				.firstStepParsePatternHeader(patternFiles);
		
		Parser.secondStepAddFileToPatternGroup(patternFiles, resultFirstSteps);
		
		for (PatternParsedFiles gp : resultFirstSteps) {
			result.add(patternParser.parse(gp));
		}
		
		assertEquals(waitedResult.size(), result.size());
		
		assertEquals(waitedResult.get(0).toString(), result.get(0).toString());
		assertEquals(waitedResult.get(1).toString(), result.get(1).toString());
		
		for (Pattern p : result) {
			
			if (!waitedResult.contains(p))
				fail();
		}
	}
	
	/*
	 * ******************************************************************************************
	 * ******************************************************************************************
	 * ******************************************************************************************
	 */
	
	
	@Test
	public void it1_ft23_testStateNoActions () throws SistemUtilException,
			FileNotFoundException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_no_actions");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "StateWithNoActions.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarConcreteState.java");
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
	
	@Test
	public void it1_ft24_testPatternElementTypeNotKnown ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_element_not_known");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternElementTypeNotKnown.java");
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
	
	@Test
	public void it1_ft25_testStateMultipleContexts ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_multiple_context");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "State1Context2.java");
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
	
	
	@Test
	public void it1_ft26_testStateWithNoContext ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_context_not_found");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarConcreteState.java");
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
	
	@Test
	public void it1_ft27_testStateWithNoConcreteState ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_concrete_state_not_found");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
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
	
	@Test
	public void it1_ft28_testPatternElementStateTooMuchParameters ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_state_wrong_parameter_number");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternElementStateTooMuchParameters.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarConcreteState.java");
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
	
	@Test
	public void it1_ft29_testPatternElementStateWrongParameter ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_state_wrong_parameter");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternElementStateWrongParameter.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarConcreteState.java");
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
	
	@Test
	public void it1_ft30_testPatternElementConcreteStateTooMuchParameters ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_concrete_state_wrong_parameter_number");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong
				+ "PatternElementConcreteStateTooMuchParameters.java");
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
	
	@Test
	public void it1_ft31_testPatternElementConcreteStateWrongParameter1 ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_concrete_state_wrong_parameter");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong
				+ "PatternElementConcreteStateWrongParameter1.java");
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
	
	@Test
	public void it1_ft31_testPatternElementConcreteStateWrongParameter2 ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_concrete_state_wrong_parameter");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong
				+ "PatternElementConcreteStateWrongParameter2.java");
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
	
	@Test
	public void it1_ft32_testPatternElementConcreteStateWrongParameterBothInitial ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_concrete_state_wrong_parameter_initial");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong
				+ "PatternElementConcreteStateTwoParametersInitial.java");
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
	
	@Test
	public void it1_ft33_testPatternElementConcreteStateWrongParameterBothFinal ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_concrete_state_wrong_parameter_final");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarContext.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong
				+ "PatternElementConcreteStateTwoParametersFinal.java");
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
	
	
	@Test
	public void it1_ft34_testPatternElementContextTooMuchParameters ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_context_wrong_parameter_number");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "AuxiliarGeneralState.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong
				+ "PatternElementContextTooMuchParameters.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootWrong + "AuxiliarConcreteState.java");
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
	
	
	
	
	@Test
	public void it1_ft35_testPatternActionWrongParameterNumberLess ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_less_parameters");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionWrongParameterNumberLess.java");
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
	
	
	@Test
	public void it1_ft36_testPatternActionWreongParameterNumberTooMuch ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_too_much_parameters");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionWreongParameterNumberTooMuch.java");
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
	
	
	@Test
	public void it1_ft37_testPatternActionNotTransition ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_parameter_not_known");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionNotTransition.java");
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
	
	
	@Test
	public void it1_ft38_testPatternActionWrongTransitionParameter1 ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_third_parameter");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionWrongTransitionParameter1.java");
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
	
	
	@Test
	public void it1_ft38_testPatternActionWrongTransitionParameter2 ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_third_parameter");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionWrongTransitionParameter2.java");
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
	
	
	@Test
	public void it1_ft39_testPatternActionWrongTransitionParameterNoContext ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_third_parameter_no_context");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionWrongTransitionParameterNoContext.java");
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
	
	
	@Test
	public void it1_ft40_testPatternActionWrongTransitionParameterMultipleContext ()
			throws FileNotFoundException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("state_parser_wrong_action_third_parameter_more_context");
		
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		// Wrong one
		ParsedFile patternFile = new ParsedFile(rootWrong
				+ "PatternActionWrongTransitionParameterTwoContext.java");
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
	// public void test () throws FileNotFoundException, SistemUtilException {
	//
	// String waitedResult = SistemUtil
	// .getMessageById("parser_pattern_class_identifier_not_matched");
	//
	// List<PatternFile> patternFiles = new ArrayList<PatternFile>();
	//
	// // Correct ones
	// patternFiles.addAll(getFilePatternsCorrectBase());
	//
	// // Wrong one
	// PatternFile patternFile = new PatternFile(rootWrong
	// + "AuxiliarGeneralState.java");
	// patternFiles.add(patternFile);
	//
	// patternFile = new PatternFile(rootWrong + "AuxiliarContext.java");
	// patternFiles.add(patternFile);
	//
	// patternFile = new PatternFile(rootWrong + "AuxiliarConcreteState.java");
	// patternFiles.add(patternFile);
	//
	// String result = "";
	//
	// try {
	// List<PatternFiles> resultFirstSteps = Parser
	// .firstStepParsePatternHeader(patternFiles);
	// Parser.secondStepAddFileToPatternGroup(patternFiles,
	// resultFirstSteps);
	//
	// // Pattern p;
	// for (PatternFiles gp : resultFirstSteps) {
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
		patternFile = new ParsedFile(rootCorrect + "State1Context.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "State1Header.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "State1Concrete1.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "State1Concrete2.java");
		patternFiles.add(patternFile);
		
		return patternFiles;
	}
	
	private List<ParsedFile> getFilePatternsCorrect ()
			throws FileNotFoundException {
		ParsedFile patternFile;
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		// Correct ones
		patternFiles.addAll(getFilePatternsCorrectBase());
		
		
		patternFile = new ParsedFile(rootCorrect + "State2Concrete1.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "State2Header.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "State2Context.java");
		patternFiles.add(patternFile);
		
		return patternFiles;
	}
	
	
	
	
	private Pattern getPatternsCorrectBaseStatePattern1 ()
			throws FileNotFoundException {
		
		Pattern pattern;
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				true, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", false, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		return pattern;
	}
	
	private List<Pattern> getPatternsCorrectBase ()
			throws FileNotFoundException {
		Pattern pattern;
		List<Pattern> patternFiles = new ArrayList<Pattern>();
		
		// Parameter p;
		// List<Parameter> pl = new ArrayList<Parameter>();
		
		// Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		patternFiles.add(getPatternsCorrectBaseStatePattern1());
		
		
		// State2
		// State2 Concrete1
		
		// pl = new ArrayList<Parameter>();
		tl = new ArrayList<Transition>();
		csl = new ArrayList<ConcreteState>();
		actionNames = new ArrayList<String>();
		
		cs = new ConcreteState("State2Concrete1", tl, "", true, false);
		
		csl.add(cs);
		
		actionNames.add("action1");
		
		pattern = new StatePattern("State2",
				"testExamples.parser.state.correct", "State2Header",
				actionNames, true, "testExamples.parser.state.correct",
				"State2Context", "", "testExamples.parser.state.correct", csl);
		
		patternFiles.add(pattern);
		
		return patternFiles;
	}
	
}
