package tfg.sw.test.unity.pattern;


import static org.junit.Assert.*;

import org.junit.Test;

import tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions.State;

import tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions.Context;

import tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions.State1;
import tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions.State2;
import tfg.sw.implementationExample.pattern.state.ExampleWrongTransitions.State3;


public class ExampleWrongTransitionsStatePatternTransitionsTest {



	@Test
	public void test0TransitionState1action1State2
		 () throws Exception {
		
		State initialState = new State1();
		State resultState = new State2();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action1 (context);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test1TransitionState1action2State1
		 () throws Exception {
		
		State initialState = new State1();
		State resultState = new State1();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action2 ("test", context, 5);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test2TransitionState1action3State2
		 () throws Exception {
		
		State initialState = new State1();
		State resultState = new State2();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action3 (context);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test3TransitionState2action1State3
		 () throws Exception {
		
		State initialState = new State2();
		State resultState = new State3();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action1 (context);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test4TransitionState2action2State3
		 () throws Exception {
		
		State initialState = new State2();
		State resultState = new State3();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action2 ("test", context, 5);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test5TransitionState2action3State2
		 () throws Exception {
		
		State initialState = new State2();
		State resultState = new State2();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action3 (context);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test6TransitionState3action1State1
		 () throws Exception {
		
		State initialState = new State3();
		State resultState = new State1();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action1 (context);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test7TransitionState3action3State2
		 () throws Exception {
		
		State initialState = new State3();
		State resultState = new State2();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action3 (context);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}


	@Test
	public void test8TransitionState3action2State3
		 () throws Exception {
		
		State initialState = new State3();
		State resultState = new State3();
		Context context = new Context (new State1());
		
		context.setState (initialState);
		initialState.action2 ("test", context, 5);
		
		assertEquals (resultState.getClass().getCanonicalName(), context.getState().getClass().getCanonicalName());
	}
}