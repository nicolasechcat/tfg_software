package tfg.sw.maker.maker.state;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.Transition;

public class StatePatternTestsPFE2 extends PatternTest {

	private StatePattern statePattern;

	public StatePatternTestsPFE2(Pattern pattern) {
		super(pattern);

		statePattern = (StatePattern) pattern;
		testFileName = "StatePatternAceptedTransitionSecuenceTest";
	}

	@Override
	public String getTests(String destinyPackage) {

		String result = "";

		result += "package " + destinyPackage + ";";

		result += "\n\n";

		result += "\nimport static org.junit.Assert.*;";
		result += "\n";
		result += "\nimport java.util.ArrayList;";
		result += "\nimport java.util.List;";
		result += "\n";
		result += "\nimport org.junit.Test;";
		result += "\n";

		result += statePattern.getMinImports();

		result += "\n\n";

		result += "public class " + getTestsFileName() + " {";

		result += "\n";

		result += "\n\t@Test";
		result += "\n\tpublic void testTransitionSecuenceToFinalState () throws Exception {";

		result += "\n\t\tList<Integer> transitionOrder = new ArrayList<Integer> ();";

		result += "\n\t\t/*";
		result += "\n\t\t* Insert the desired transition order of execution as in the example";
		result += "\n\t\t* for the secuence 3 -> 1 -> 1";
		result += "\n\t\t*";
		result += "\n\t\t*\t\ttransitionOrder.add(3);";
		result += "\n\t\t*\t\ttransitionOrder.add(1);";
		result += "\n\t\t*\t\ttransitionOrder.add(1);";
		result += "\n\t\t*";
		result += "\n\t\t* The transition identifiers are listed belown:";
		result += "\n\t\t*";

		int i = 1;
		String action = "";
		for (Transition t : statePattern.getDiferentTransitions()) {

			if (action.compareTo(t.getActionName()) != 0) {
				action = t.getActionName();
				result += "\n\t\t*";
				result += "\n\t\t*\tAction: " + action;
				result += "\n\t\t*";
			}

			result += "\n\t\t*\t\t" + String.valueOf(i).trim() + " - "
					+ t.getParametersAsString();

			i++;
		}

		result += "\n\t\t*";
		result += "\n\t\t*";
		result += "\n\t\t*/\n\n";

		result += "\n\t\n\n";

		result += "\n\t\t/*";
		result += "\n\t\t* Don't touch the remaining code";
		result += "\n\t\t*/";

		result += "\n\n";

		result += "\n\t\t";
		result += "\n\t\t" + statePattern.getGeneralStateClassName()
				+ " lastState = executeActionSecuence (transitionOrder);";
		result += "\n\t\t";
		result += "\n\t\tassertFalse (lastState == null);";
		result += "\n\t\t";
		result += "\n\t\tList<String> finalStates = getFinalStatesCanonicalNames ();";
		result += "\n\t\tString lastStateCanonicalName = lastState.getClass().getCanonicalName();";
		result += "\n\t\t";
		result += "\n\t\tassertTrue (finalStates.contains (lastStateCanonicalName));";

		result += "\n\tthrow new UnsupportedOperationException (\"The user must finish this test. This exception must be removed after that.\");\n\n";

		result += "\n\t}";

		result += "\n";

		result += statePattern.getTransitionExecuttorFunction();
		result += "\n";
		result += statePattern.getFinalStateCanonicalNameFunction();

		result += "\n";

		result += "}";

		return result;
	}
}
