package tfg.sw.test.unity.pattern;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong.State;

import tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong.Context;

import tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong.State1;
import java.util.Random;

public class ExampleOrederDependentWrongStatePatternOrderDependentTest {

	@Test
	public void orderDependencyTest () throws Exception {
		
		List<List<Integer>> transitionsList = new ArrayList<List<Integer>> ();
		List<Integer> transitionCombination = new ArrayList<Integer>();
		List<Integer> transitionCombinationTmp = new ArrayList<Integer>();
		List<Integer> permutedTransitions = new ArrayList<Integer>();
		
		int permutationsSize = 10;
		int transitionsNumber = 5;
		
		// Get a random combination of actions
		Random random = new Random();
		for (int i = 0; i < permutationsSize; i++) {		
			int k = random.nextInt(transitionsNumber) + 1;
			transitionCombination.add (k);
		}
		
		// permute the random combination permutation times and add ir to transitionsList
		for (int i = 0; i < 10; i++) {
			transitionCombinationTmp = new ArrayList<Integer> ();
			for (int action : transitionCombination) 
				transitionCombinationTmp.add(action);
			
			permutedTransitions = new ArrayList<Integer> ();
			for (int j = 0; j < permutationsSize; j++) {
				int k = random.nextInt(transitionCombinationTmp.size());
				permutedTransitions.add (transitionCombinationTmp.get(k));
				transitionCombinationTmp.remove(k);
			}
			transitionsList.add(permutedTransitions);
		}
		
		// Execute the transition permutations
		State execution, executionBefore;
		executionBefore = executeActionSecuence (transitionsList.get(0));
		
		for (int i = 1; i < 10; i++) {
			execution = executeActionSecuence (transitionsList.get(i));
			
			// Compare results
			// Is not order dependent and has different
			assertEquals(execution.getClass().getCanonicalName(), executionBefore.getClass().getCanonicalName());
			
			executionBefore = execution;
		}
		
	}


	private State executeActionSecuence (List<Integer> transitonOrder) throws Exception {
		
		Context context = new Context (new State1());
		
		// If the context doesn't begin in the initial state
		String initialStateClassName = context.getState().getClass().getCanonicalName();
		if (initialStateClassName.compareTo("tfg.sw.implementationExample.pattern.state.ExampleOrderDependentWrong.State1") != 0) 
			return null;
		
		// Execute the transitions in the defined order
		for (int i: transitonOrder) {
			switch (i) {


			// Action: action1

				case 1:
					context.getState().action1(context);
					break;


			// Action: action2

				case 2:
					context.getState().action2("test", context, -5);
					break;

				case 3:
					context.getState().action2("test", context, 5);
					break;

				case 4:
					context.getState().action2("est", context, 5);
					break;


			// Action: action3

				case 5:
					context.getState().action3(context);
					break;
				default:
				throw new Exception ();
			}
		}


		return context.getState();
	}


}