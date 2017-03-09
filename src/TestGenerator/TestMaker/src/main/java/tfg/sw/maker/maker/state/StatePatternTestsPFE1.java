package tfg.sw.maker.maker.state;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.state.ConcreteState;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.Transition;

public class StatePatternTestsPFE1 extends PatternTest {

	private StatePattern statePattern = null;

	public StatePatternTestsPFE1(Pattern pattern) {
		super(pattern);

		this.statePattern = (StatePattern) pattern;
		testFileName = "StatePatternTransitionsTest";
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

		result += statePattern.getAllImports();

		result += "\n\n";

		result += "public class " + getTestsFileName() + " {";

		result += "\n";

		int count = 0;

		for (ConcreteState cs : statePattern.getConcreteStates()) {
			for (Transition t : cs.getTransitions()) {
				result += "\n\n";
				result += getTransitionTest(
						statePattern.getGeneralStateClassName(),
						statePattern.getStateContextClassName(),
						statePattern.getStateContextBuilder(), cs, t, count);
				count++;
			}
		}

		result += "\n";
		result += "}";

		return result;
	}

	// @Test
	// public void test001TransitionState1ActionNameState2 ()
	// throws Exception {
	//
	// StateHeader initialState = new ConcreteState1 ();
	// StateHeader resultState = new ConcreteState2 ();
	// Context context = new State1Context (8080, "pruebas");
	//
	// context.setState1Header (initialState);
	// initialState.action1 (context, true, "transicion");
	//
	// assertEquals (resultState, context.getState1Header ());
	// }
	private String getTransitionTest(String stateHeaderName,
			String contextName, String contextBuilder,
			ConcreteState initialState, Transition transition, int number) {

		String result = "";

		String testName = "test" + String.valueOf(number).trim()
				+ "_Transition" + "_" + initialState.getClassName().trim()
				+ "_" + transition.getActionName().trim() + "_"
				+ transition.getDestinyState().getClassName().trim();

		result += "\n\t@Test";
		result += "\n\tpublic void " + testName
				+ "\n\t\t () throws Exception {";

		result += "\n\t\t";

		result += "\n\t\t" + stateHeaderName + " initialState = "
				+ initialState.getBuilderInvocation() + ";";

		result += "\n\t\t" + stateHeaderName + " resultState = "
				+ transition.getDestinyState().getBuilderInvocation() + ";";

		result += "\n\t\t" + contextName + " context = " + contextBuilder + ";";

		result += "\n\t\t";

		result += "\n\t\tcontext.set" + stateHeaderName.trim()
				+ " (initialState);";

		result += "\n\t\tinitialState." + transition.getActionName().trim()
				+ " " + transition.getParametersAsString() + ";";

		result += "\n\t\t";

		result += "\n\t\tassertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());";

		result += "\n\t}";

		return result;

	}
}
