package tfg.sw.test.unity.pattern;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.implementationExample.pattern.state.ExampleOrderDependentCorrect.State;

import tfg.sw.implementationExample.pattern.state.ExampleOrderDependentCorrect.Context;

import tfg.sw.implementationExample.pattern.state.ExampleOrderDependentCorrect.State1;


public class ExampleOrederDependentCorrectStatePatternAceptedTransitionSecuenceTest {

	@Test
	public void testTransitionSecuenceToFinalState () throws Exception {
		List<Integer> transitionOrder = new ArrayList<Integer> ();
		/*
		* Insert the desired transition order of execution as in the example
		* for the secuence 3 -> 1 -> 1
		*
		*		transitionOrder.add(3);
		*		transitionOrder.add(1);
		*		transitionOrder.add(1);
		*
		* The transition identifiers are listed belown:
		*
		*
		*	Action: action1
		*
		*		1 - (context)
		*
		*	Action: action2
		*
		*		2 - ("test", context, -5)
		*		3 - ("test", context, 5)
		*		4 - ("est", context, 5)
		*
		*	Action: action3
		*
		*		5 - (context)
		*
		*
		*/


	


		/*
		* Don't touch the remaining code
		*/


		
		State lastState = executeActionSecuence (transitionOrder);
		
		assertFalse (lastState == null);
		
		List<String> finalStates = getFinalStatesCanonicalNames ();
		String lastStateCanonicalName = lastState.getClass().getCanonicalName();
		
		assertTrue (finalStates.contains (lastStateCanonicalName));
	throw new UnsupportedOperationException ("The user must finish this test. This exception must be removed after that.");


	}


	private State executeActionSecuence (List<Integer> transitonOrder) throws Exception {
		
		Context context = new Context (new State1());
		
		// If the context doesn't begin in the initial state
		String initialStateClassName = context.getState().getClass().getCanonicalName();
		if (initialStateClassName.compareTo("tfg.sw.implementationExample.pattern.state.ExampleOrderDependentCorrect.State1") != 0) 
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


	private List<String> getFinalStatesCanonicalNames () {

		List<String> result = new ArrayList<String> ();


		result.add("tfg.sw.implementationExample.pattern.state.ExampleOrderDependentCorrect.State1");
		result.add("tfg.sw.implementationExample.pattern.state.ExampleOrderDependentCorrect.State3");

		return result;
	}
}