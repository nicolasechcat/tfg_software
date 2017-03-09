package tfg.sw.maker.tests.patterntests.strategy;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.maker.strategy.StrategyPatternTestsPFEST1;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.strategy.Comparison;
import tfg.sw.maker.pattern.strategy.ConcreteStrategy;
import tfg.sw.maker.pattern.strategy.StrategyAction;
import tfg.sw.maker.pattern.strategy.StrategyPattern;
import tfg.sw.maker.pattern.strategy.comparison.ByComparable;
import tfg.sw.maker.pattern.strategy.comparison.ByComparator;
import tfg.sw.maker.pattern.strategy.comparison.ByEquals;

public class StrategyPatternTestsPFEST1Test {

	@Test
	public void testBasic() {

		Pattern pattern = getPatternsCorrectBaseStrategy();

		List<PatternTest> tests = pattern.getPatternTests();

		assertTrue(tests.size() > 0);

		PatternTest test = new StrategyPatternTestsPFEST1(pattern);

		String result = test.getTests("destiny");

		// System.out.println(result);

		assertTrue(result.length() > 0);

	}

	private Pattern getPatternsCorrectBaseStrategy() {

		StrategyPattern pattern;

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

		pattern.fillCompleteInformation();

		return pattern;
	}
}
