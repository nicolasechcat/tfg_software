package tfg.sw.maker.maker.state;

import java.util.List;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.Transition;

public class StatePatternTestsPNFE1 extends PatternTest {

	private StatePattern statePattern = null;
	private int permutationsNumber = 10;

	public StatePatternTestsPNFE1(Pattern pattern) {
		super(pattern);

		this.statePattern = (StatePattern) pattern;
		testFileName = "StatePatternOrderDependentTest";
	}

	@Override
	public String getTests(String destinyPackage) {

		String result = "";

		List<Transition> transitions = statePattern.getDiferentTransitions();

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
		result += "import java.util.Random;";

		result += "\n\n";

		result += "public class " + getTestsFileName() + " {";

		result += "\n";

		result += "\n\t@Test";
		result += "\n\tpublic void orderDependencyTest () throws Exception {";
		result += "\n\t\t";

		result += "\n\t\tList<List<Integer>> transitionsList = new ArrayList<List<Integer>> ();";
		result += "\n\t\tList<Integer> transitionCombination = new ArrayList<Integer>();";
		result += "\n\t\tList<Integer> transitionCombinationTmp = new ArrayList<Integer>();";
		result += "\n\t\tList<Integer> permutedTransitions = new ArrayList<Integer>();";
		result += "\n\t\t";
		result += "\n\t\tint permutationsSize = 10;";
		result += "\n\t\tint transitionsNumber = " + transitions.size() + ";";
		result += "\n\t\t";

		result += "\n\t\t// Get a random combination of actions";
		result += "\n\t\tRandom random = new Random();";
		result += "\n\t\tfor (int i = 0; i < permutationsSize; i++) {		";
		result += "\n\t\t\tint k = random.nextInt(transitionsNumber) + 1;";
		result += "\n\t\t\ttransitionCombination.add (k);";
		result += "\n\t\t}";

		result += "\n\t\t";

		result += "\n\t\t// permute the random combination permutation times and add ir to transitionsList";
		result += "\n\t\tfor (int i = 0; i < " + permutationsNumber
				+ "; i++) {";
		result += "\n\t\t\ttransitionCombinationTmp = new ArrayList<Integer> ();";
		result += "\n\t\t\tfor (int action : transitionCombination) ";
		result += "\n\t\t\t\ttransitionCombinationTmp.add(action);";
		result += "\n\t\t\t";
		result += "\n\t\t\tpermutedTransitions = new ArrayList<Integer> ();";
		result += "\n\t\t\tfor (int j = 0; j < permutationsSize; j++) {";
		result += "\n\t\t\t\tint k = random.nextInt(transitionCombinationTmp.size());";
		result += "\n\t\t\t\tpermutedTransitions.add (transitionCombinationTmp.get(k));";
		result += "\n\t\t\t\ttransitionCombinationTmp.remove(k);";
		result += "\n\t\t\t}";
		result += "\n\t\t\ttransitionsList.add(permutedTransitions);";
		result += "\n\t\t}";
		result += "\n\t\t";

		result += "\n\t\t// Execute the transition permutations";
		result += "\n\t\t" + statePattern.getGeneralStateClassName()
				+ " execution, executionBefore;";

		if (statePattern.isOrderDependent())
			result += "\n\t\tboolean hasDiferent = false;";

		result += "\n\t\texecutionBefore = executeActionSecuence (transitionsList.get(0));";
		result += "\n\t\t";
		result += "\n\t\tfor (int i = 1; i < " + permutationsNumber
				+ "; i++) {";
		result += "\n\t\t\texecution = executeActionSecuence (transitionsList.get(i));";
		result += "\n\t\t\t";
		result += "\n\t\t\t// Compare results";

		if (statePattern.isOrderDependent()) {
			result += "\n\t\t\thasDiferent = hasDiferent || (execution.getClass().getCanonicalName() != executionBefore.getClass().getCanonicalName());";
			result += "\n\t\t\t";
			result += "\n\t\t\texecutionBefore = execution;";
			result += "\n\t\t}";
			result += "\n\t\t// Is order dependent  and not has diferent";
			result += "\n\t\tassertTrue (hasDiferent);";
		} else {
			result += "\n\t\t\t// Is not order dependent and has different";
			result += "\n\t\t\tassertEquals(execution.getClass().getCanonicalName(), executionBefore.getClass().getCanonicalName());";
			result += "\n\t\t\t";
			result += "\n\t\t\texecutionBefore = execution;";
			result += "\n\t\t}";
			result += "\n\t\t";
		}

		result += "\n\t}";

		result += "\n";

		result += statePattern.getTransitionExecuttorFunction();
		result += "\n";
		// result += statePattern.getFinalStateClassNamesFunction();

		result += "\n";
		result += "}";

		return result;
	}
}
