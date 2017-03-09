package tfg.sw.maker.tests.pattern;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.state.ConcreteState;
import tfg.sw.maker.pattern.state.Parameter;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.Transition;

public class StatePatternTest {

	@Test
	public void it1_ft49_testFillCompleteInformation() {

		Pattern result = getPatternsCorrectBaseStatePattern1();
		// Pattern waitedResult = getPatternsCorrectBaseStatePattern1Complete();

		((StatePattern) result).fillCompleteInformation();

		// TODO fix it
		// assertEquals(waitedResult, result);

	}

	@Test
	public void it1_ft50_testgetImports() {

		Pattern pattern = getPatternsCorrectBaseStatePattern1();
		// \nimport static org.junit.Assert.*;\n\nimport
		// java.util.ArrayList;\nimport java.util.List;\n\nimport
		// org.junit.Test;\n\nimport
		// testExamples.parser.state.correct.State1Header;\n\nimport
		// testExamples.parser.state.correct.State1Context;\n\nimport
		// testExamples.parser.state.correct.states.State1Concrete1;\nimport
		// testExamples.parser.state.correct.states.State1Concrete2;\nimport
		// testExamples.parser.state.correct.states.State1Concrete3;\n

		String result = "";
		String waitedResult = "";

		waitedResult += "\nimport testExamples.parser.state.correct.State;";
		waitedResult += "\n";
		waitedResult += "\nimport testExamples.parser.state.correct.Context;";
		waitedResult += "\n";
		waitedResult += "\nimport testExamples.parser.state.correct.states.State1;";
		waitedResult += "\nimport testExamples.parser.state.correct.states.State2;";
		waitedResult += "\nimport testExamples.parser.state.correct.states.State3;";
		waitedResult += "\n";

		result = pattern.getAllImports();

		assertEquals(waitedResult, result);

	}

	private Pattern getPatternsCorrectBaseStatePattern1() {

		Pattern pattern;

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
		t = new Transition("State2", "action1", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State2", "action3", pl);
		tl.add(t);

		cs = new ConcreteState("State1", tl, "new State1()", true, true);

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
		t = new Transition("State1", "action2", pl);
		tl.add(t);

		cs = new ConcreteState("State2", tl, "", false, true);

		csl.add(cs);

		// State1 Concrete3

		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();

		cs = new ConcreteState("State3", tl, "new State3()", false, true);

		csl.add(cs);

		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");

		pattern = new StatePattern("State",
				"testExamples.parser.state.correct", "State", actionNames,
				false, "testExamples.parser.state.correct", "Context",
				"new Context (new State1())",
				"testExamples.parser.state.correct.states", csl);

		return pattern;
	}

	@SuppressWarnings("unused")
	private Pattern getPatternsCorrectBaseStatePattern1Complete() {

		Pattern pattern;

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
		t = new Transition("State2", "action1", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1", "action2", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State2", "action3", pl);
		tl.add(t);

		cs = new ConcreteState("State1", tl, "new State1()", true, true);

		csl.add(cs);

		// State1 Concrete2

		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State2", "action1", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State1", "action2", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State2", "action3", pl);
		tl.add(t);

		cs = new ConcreteState("State2", tl, "new State2 ()", false, true);

		csl.add(cs);

		// State1 Concrete3

		tl = new ArrayList<Transition>();
		pl = new ArrayList<Parameter>();
		t = new Transition("State3", "action1", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("\"test\"", 0);
		pl.add(p);
		p = new Parameter("context", 1);
		pl.add(p);
		p = new Parameter("5", 2);
		pl.add(p);
		t = new Transition("State3", "action2", pl);
		tl.add(t);

		pl = new ArrayList<Parameter>();
		p = new Parameter("context", 0);
		pl.add(p);
		t = new Transition("State3", "action3", pl);
		tl.add(t);

		cs = new ConcreteState("State3", tl, "new State3()", false, true);

		csl.add(cs);

		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");

		pattern = new StatePattern("State",
				"testExamples.parser.state.correct", "State", actionNames,
				false, "testExamples.parser.state.correct", "Context",
				"new Context (new State1())",
				"testExamples.parser.state.correct", csl);

		return pattern;
	}

}
