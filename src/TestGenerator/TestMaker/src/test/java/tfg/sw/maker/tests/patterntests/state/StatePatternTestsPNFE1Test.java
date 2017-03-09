package tfg.sw.maker.tests.patterntests.state;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.maker.state.StatePatternTestsPNFE1;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.state.ConcreteState;
import tfg.sw.maker.pattern.state.Parameter;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.Transition;

public class StatePatternTestsPNFE1Test {

	@Test
	public void testBasic() {

		Pattern pattern = getPatternsCorrectBaseStatePattern1();

		List<PatternTest> tests = pattern.getPatternTests();

		assertTrue(tests.size() > 0);

		PatternTest test = new StatePatternTestsPNFE1(pattern);

		String result = test.getTests("destiny");

		assertTrue(result.length() > 0);
	}

	private Pattern getPatternsCorrectBaseStatePattern1() {

		StatePattern pattern;

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

		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");

		pattern = new StatePattern("State", "pattern.state.ExampleCorrect",
				"State1Header", actionNames, false,
				"pattern.state.ExampleCorrect", "Context",
				"new Context (new State1())", "pattern.state.ExampleCorrect",
				csl);

		pattern.fillCompleteInformation();

		return pattern;
	}

}
