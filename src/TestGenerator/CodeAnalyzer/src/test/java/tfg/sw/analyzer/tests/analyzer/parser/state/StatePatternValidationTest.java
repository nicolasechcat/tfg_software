package tfg.sw.analyzer.tests.analyzer.parser.state;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.state.ConcreteState;
import tfg.sw.analyzer.pattern.state.Parameter;
import tfg.sw.analyzer.pattern.state.StatePattern;
import tfg.sw.analyzer.pattern.state.Transition;
import tfg.sw.analyzer.util.SystemUtil;

public class StatePatternValidationTest {
	
	@Test
	public void it1_ft41_testStatePatternValidationNoInitialState ()
			throws SistemUtilException {
		// No hay estado inicial definido
		
		Pattern pattern;
		String validation = "";
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				false, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", false, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		
		// System.out.print(pattern);
		validation = pattern.validate();
		
		// System.out.println(validation.trim());
		//
		// System.out
		// .print(String
		// .format("\n\t- "
		// + SistemUtil
		// .getMessageById("state_validation_transition_no_initial_state"),
		// "State1Header").trim());
		
		if (validation.isEmpty())
			fail();
		
		if (validation
				.trim()
				.compareTo(
						String.format(
								"\n\t- "
										+ SystemUtil
												.getMessageById("state_validation_transition_no_initial_state"),
								"State1Header").trim()) != 0)
			fail();
		
	}
	
	@Test
	public void it1_ft42_testStatePatternValidationMultipleInitialStates ()
			throws SistemUtilException {
		// Hay más de un estado inicial definidos
		
		Pattern pattern;
		String validation = "";
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				true, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", true, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		
		validation = pattern.validate();
		
		if (validation == null)
			fail();
		
		if (validation.isEmpty())
			fail();
		
		if (validation
				.compareTo(String.format(
						"\n\t- "
								+ SystemUtil
										.getMessageById("state_validation_multiple_initial_states"),
						"State1Concrete1", "State1Concrete2")) != 0)
			fail();
	}
	
	@Test
	public void it1_ft43_testStatePatternValidation ()
			throws SistemUtilException {
		// Hay más de un estado inicial definidos
		
		Pattern pattern;
		String validation = "";
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				true, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", false, true);
		
		csl.add(cs);
		
		// State1 Concrete3
		
		cs = new ConcreteState("State1Concrete3", tl, "", false, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		
		validation = pattern.validate();
		
		if (validation.isEmpty())
			fail();
		
		if (validation
				.trim()
				.compareTo(
						("\n\t- "
								+ SystemUtil
										.getMessageById("state_validation_unreachable_states") + "State1Concrete3")
								.trim()) != 0)
			fail();
	}
	
	
	@Test
	public void it1_ft44_testStatePatternValidationTransitionDestinyUnknown ()
			throws SistemUtilException {
		// @patternAction Transition con primer parámetro adicional no
		// coincidente con el nombre de ningun @patternElement ConcreteState
		
		Pattern pattern;
		String validation = "";
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete3", "action3", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				true, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", false, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		
		validation = pattern.validate();
		
		if (validation == null)
			fail();
		
		if (validation.isEmpty())
			fail();
		
		if (validation
				.trim()
				.compareTo(
						String.format(
								"\n\t- "
										+ SystemUtil
												.getMessageById("state_validation_transition_no_destiny"),
								"State1Concrete1", "State1Concrete3").trim()) != 0)
			fail();
	}
	
	
	@Test
	public void it1_ft45_testStatePatternValidationTransitionWithMultipleDefinitions ()
			throws SistemUtilException {
		// Se define una transición dos veces exactamente igual (mismos estados
		// de origen y destino, misma acción y mismos parámetros)
		
		Pattern pattern;
		String validation = "";
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				true, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", false, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		
		validation = pattern.validate();
		
		if (validation == null)
			fail();
		
		if (validation.isEmpty())
			fail();
		
		if (validation
				.trim()
				.compareTo(
						String.format(
								"\n\t- "
										+ SystemUtil
												.getMessageById("state_validation_transition_repited"),
								"State1Concrete1").trim()) != 0)
			fail();
	}
	
	
	@Test
	public void it1_ft46_testStatePatternValidationTransitionUponNoExistentAction ()
			throws SistemUtilException {
		// Se define un @patterAction sobre un método que no coincide con ningún
		// método \textit{public} o \textit{protected} del @patternElement State
		
		Pattern pattern;
		String validation = "";
		
		Parameter p;
		List<Parameter> pl = new ArrayList<Parameter>();
		
		Transition t;
		List<Transition> tl = new ArrayList<Transition>();
		
		ConcreteState cs;
		List<ConcreteState> csl = new ArrayList<ConcreteState>();
		
		List<String> actionNames = new ArrayList<String>();
		
		// State1
		// State1 Concrete1
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State1Concrete2", "action1", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action3", pl);
		tl.add(t);
		
		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State1Concrete2", "action4", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete1", tl, "new State1Concrete1()",
				true, true);
		
		csl.add(cs);
		
		// State1 Concrete2
		
		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1Concrete1", "action2", pl);
		tl.add(t);
		
		cs = new ConcreteState("State1Concrete2", tl, "", false, true);
		
		csl.add(cs);
		
		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");
		
		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);
		
		
		validation = pattern.validate();
		
		if (validation == null)
			fail();
		
		if (validation.isEmpty())
			fail();
		
		if (validation
				.trim()
				.compareTo(
						String.format(
								"\n\t- "
										+ SystemUtil
												.getMessageById("state_validation_transition_no_action"),
								"State1Concrete1", "action4").trim()) != 0)
			fail();
	}
	
}
