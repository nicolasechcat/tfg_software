package tfg.sw.maker.maker.strategy;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.strategy.ConcreteStrategy;
import tfg.sw.maker.pattern.strategy.StrategyAction;
import tfg.sw.maker.pattern.strategy.StrategyPattern;
import tfg.sw.maker.pattern.strategy.comparison.ByComparable;
import tfg.sw.maker.pattern.strategy.comparison.ByComparator;
import tfg.sw.maker.pattern.strategy.comparison.ByEquals;

public class StrategyPatternTestsPFEST1 extends PatternTest {

	private StrategyPattern strategyPattern = null;

	public StrategyPatternTestsPFEST1(Pattern pattern) {
		super(pattern);

		this.strategyPattern = (StrategyPattern) pattern;
		testFileName = "StrategyPatternComparisonsTest";

	}

	@Override
	public String getTests(String destinyPackage) {

		String result = "";

		result += "package " + destinyPackage + ";";

		result += "\n\n";

		result += "\nimport static org.junit.Assert.*;";
		result += "\n";
		result += "\nimport org.junit.Test;";
		result += "\n";
		result += "\nimport java.util.Comparator;";
		result += "\n";

		result += strategyPattern.getAllImports();

		result += "\n\n";

		result += "public class " + getTestsFileName() + " {";

		result += "\n";

		for (StrategyAction sa : strategyPattern.getActions()) {
			if (sa.getComparison().getClass() == ByComparator.class) {
				ByComparator comp = (ByComparator) sa.getComparison();

				if (comp.getClassName().isEmpty())
					result += "\n\n"
							+ createComparator(sa.getName(), sa.getReturnType());
			}
		}

		int count = 0;

		if (strategyPattern != null) {

			for (StrategyAction sa : strategyPattern.getActions()) {

				int i = 0;
				int j = 0;
				int max = strategyPattern.getConcreteStrategies().size();

				// combinations of 2 strategies without repeat combination
				for (i = 0; i < max - 1; i++) {
					ConcreteStrategy strategyA = strategyPattern
							.getConcreteStrategies().get(i);
					for (j = i + 1; j < max; j++) {

						int paramCount = 0;

						for (String p : sa.getParameters()) {
							ConcreteStrategy strategyB = strategyPattern
									.getConcreteStrategies().get(j);
							result += "\n\n";

							result += getActionAnd2StrategiesTest(sa,
									strategyA, strategyB, count, p, paramCount);

							paramCount++;

						}
						count++;
					}
				}
			}
		}

		result += "\n";
		result += "}";

		return result;
	}

	/*
	 * private class Action4Comparator implements
	 * Comparator<UsersClassComparator2> {
	 * 
	 * @override public int compare(UsersClassComparator2 o1,
	 * UsersClassComparator2 o2) { throw new UnsupportedOperationException
	 * (``The user must finish this test. This exception must be " + "removed
	 * after that.''); } }
	 */
	private String createComparator(String name, String returnType) {
		String result = "";

		result += "\n\tprivate class " + name
				+ "Comparator implements Comparator<" + returnType + "> {";

		result += "\n\t\tpublic int compare(" + returnType + " o1, "
				+ returnType + " o2) {";

		result += "\n\t\t\tthrow new UnsupportedOperationException "
				+ "(\"The user must finish this test. This exception must "
				+ "be removed after that.\");";

		result += "\n\t\t}";
		result += "\n\t}";

		return result;
	}

	/*
	 * @Test public void test001_action4_Strategy1_Strategy3 () throws Exception
	 * {
	 * 
	 * StrategyExample strategy1 = new Strategy1 (new Date (2017, 01, 01),
	 * true); StrategyExample Strategy2 = new Strategy3 (new Date (2017, 01,
	 * 01), true);
	 * 
	 * UsersClassComparator2 result1 = strategy1.action4 ();
	 * UsersClassComparator2 result2 = strategy2.action4 ();
	 * 
	 * // @patternElement Comparison Comparator Comparator comparator = new
	 * Action4Comparator (); int comparisonResult = comparator.compare(result1,
	 * result2); assertEquals (0, comparisonResult); }
	 * 
	 * private class Action4Comparator implements
	 * Comparator<UsersClassComparator2> {
	 * 
	 * @override public int compare(UsersClassComparator2 o1,
	 * UsersClassComparator2 o2) { throw new UnsupportedOperationException
	 * (``The user must finish this test. This exception must be " + "removed
	 * after that.''); } }
	 */
	private String getActionAnd2StrategiesTest(StrategyAction sa,
			ConcreteStrategy strategyA, ConcreteStrategy strategyB, int count,
			String parameters, int paramCount) {
		String result = "";

		result += "\t@Test";
		result += "\n\tpublic void test" + String.valueOf(count).trim() + "_"
				+ sa.getName() + "_" + strategyA.getClassName() + "_"
				+ strategyB.getClassName() + "_"
				+ String.valueOf(paramCount).trim() + " () throws Exception";
		result += "\n\t{";

		result += "\n\t\t" + this.strategyPattern.getClassName()
				+ " strategy1 = " + strategyA.getBuilder() + ";";
		result += "\n\t\t" + this.strategyPattern.getClassName()
				+ " strategy2 = " + strategyB.getBuilder() + ";";

		result += "\n\t\t";

		result += "\n\t\t" + sa.getReturnType() + " result1 = strategy1."
				+ sa.getName() + " (" + parameters + ");";
		result += "\n\t\t" + sa.getReturnType() + " result2 = strategy2."
				+ sa.getName() + " (" + parameters + ");";

		result += "\n\t\t";

		if (sa.getComparison().getClass() == ByEquals.class) {
			result += "\n\t\tassertEquals (result1, result2);";
		}

		if (sa.getComparison().getClass() == ByComparable.class) {
			result += "\n\t\tint comparisonResult = result1.compareTo(result2);";
			result += "\n\t\tassertEquals (0, comparisonResult);";
		}

		if (sa.getComparison().getClass() == ByComparator.class) {
			ByComparator comp = (ByComparator) sa.getComparison();

			String name = comp.getClassName();
			String builder = comp.getBuilder();

			if (name.isEmpty())
				name = sa.getName() + "Comparator";

			if (builder.isEmpty())
				builder = "new " + name + " ()";

			result += "\n\t\tComparator comparator = " + builder + ";";
			result += "\n\t\tint comparisonResult = comparator.compare(result1, result2);";
			result += "\n\t\tassertEquals (0, comparisonResult);";
		}

		result += "\n\t}\n\n";

		return result;
	}

}
