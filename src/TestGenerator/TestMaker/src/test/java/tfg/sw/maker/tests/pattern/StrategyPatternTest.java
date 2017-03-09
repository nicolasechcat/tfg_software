package tfg.sw.maker.tests.pattern;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.strategy.Comparison;
import tfg.sw.maker.pattern.strategy.ConcreteStrategy;
import tfg.sw.maker.pattern.strategy.StrategyAction;
import tfg.sw.maker.pattern.strategy.StrategyPattern;
import tfg.sw.maker.pattern.strategy.comparison.ByComparable;
import tfg.sw.maker.pattern.strategy.comparison.ByComparator;
import tfg.sw.maker.pattern.strategy.comparison.ByEquals;

public class StrategyPatternTest {

	@Test
	public void it2_ft24_testFillCompleteInformation() {

		Pattern result = getPatternsCorrectBaseStrategy();
		// Pattern waitedResult = getPatternsCorrectBaseStrategyComplete();

		((StrategyPattern) result).fillCompleteInformation();

		// TODO fix it
		// assertEquals(waitedResult, result);

	}

	@Test
	public void it2_ft25_testgetImports() {

		Pattern pattern = getPatternsCorrectBaseStrategy();

		String result = "";
		String waitedResult = "";

		waitedResult += "\nimport testExamples.parser.strategy.correct.Strategy1;";
		waitedResult += "\n";
		waitedResult += "\nimport testExamples.parser.strategy.correct.concrete.ConcreteStrategy1;";
		waitedResult += "\nimport testExamples.parser.strategy.correct.concrete.ConcreteStrategy2;";
		waitedResult += "\nimport testExamples.parser.strategy.correct.concrete.ConcreteStrategy3;";
		waitedResult += "\n";
		waitedResult += "\nimport java.util.Date;";
		waitedResult += "\n";
		waitedResult += "\nimport testExamples.parser.strategy.correct.auxiliar.UsersComparator;";
		waitedResult += "\n";

		result = pattern.getAllImports();

		assertEquals(waitedResult, result);

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

		return pattern;
	}

	@SuppressWarnings("unused")
	private Pattern getPatternsCorrectBaseStrategyComplete() {

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

		return pattern;
	}

}
