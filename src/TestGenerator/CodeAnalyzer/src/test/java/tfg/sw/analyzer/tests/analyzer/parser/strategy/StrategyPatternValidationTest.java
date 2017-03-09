package tfg.sw.analyzer.tests.analyzer.parser.strategy;

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
import tfg.sw.analyzer.pattern.strategy.StrategyPattern;
import tfg.sw.analyzer.util.SystemUtil;

public class StrategyPatternValidationTest {
	
	private String rootCorrect = "src/test/resources/testExamples/parser/strategy/correct/";
	
	private PatternParser patternParser = StrategyPattern.getParser();
	
	@Test
	public void it2_ft22_NotEnoughtConcreteStrategies ()
			throws FileNotFoundException, ParserException, SistemUtilException {
		
		String waitedResult = SystemUtil
				.getMessageById("strategy_validation_only_one_concrete_strategy");
		List<ParsedFile> patternFiles = new ArrayList<ParsedFile>();
		
		ParsedFile patternFile = new ParsedFile(rootCorrect + "Strategy1.java");
		patternFiles.add(patternFile);
		
		patternFile = new ParsedFile(rootCorrect + "ConcreteStrategy1.java");
		patternFiles.add(patternFile);
		
		try {
			List<PatternParsedFiles> resultFirstSteps = Parser
					.firstStepParsePatternHeader(patternFiles);
			Parser.secondStepAddFileToPatternGroup(patternFiles,
				resultFirstSteps);
			
			// Pattern p;
			for (PatternParsedFiles gp : resultFirstSteps) {
				// p = patternParser.parse(gp);
				Pattern p = patternParser.parse(gp);
				// System.out.println(p);
				
				assertTrue(p.validate().startsWith(waitedResult));
			}
		} catch (ParserException ex) {
			fail();
		}
	}
	
	/*
	 * ******************************************************************************************
	 * ******************************************************************************************
	 * ******************************************************************************************
	 */
	
	
	
	
}
