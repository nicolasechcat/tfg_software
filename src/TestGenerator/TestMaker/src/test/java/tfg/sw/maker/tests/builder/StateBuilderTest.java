package tfg.sw.maker.tests.builder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import tfg.sw.maker.exception.PatternBuilderException;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.PatternBuilder;
import tfg.sw.maker.pattern.state.ConcreteState;
import tfg.sw.maker.pattern.state.Parameter;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.StatePatternBuilder;
import tfg.sw.maker.pattern.state.Transition;

public class StateBuilderTest {

	@Test
	public void it1_ft48_testXMLBuilderToPattern()
			throws PatternBuilderException, ParserConfigurationException {

		Pattern waitedResult = getPatternsCorrectBaseStatePattern1();

		PatternBuilder builder = StatePatternBuilder.getInstance();

		Pattern result = builder
				.getPatternFromXML((Element) genetareDOMStatePattern1Element()
						.getDocumentElement().getFirstChild());

		assertEquals(waitedResult, result);
	}

	private Document genetareDOMStatePattern1Element()
			throws ParserConfigurationException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document document = docBuilder.newDocument();
		Element rootElements = document.createElement("patterns");
		document.appendChild(rootElements);

		Element rootElement = document.createElement("statePattern");
		rootElements.appendChild(rootElement);

		Element element = null;
		Text empty = document.createTextNode("");

		// general information
		element = document.createElement("id");
		element.appendChild(document.createTextNode("State1Header"));
		rootElement.appendChild(element);

		element = document.createElement("nonorderdependent");
		element.appendChild(empty);
		rootElement.appendChild(element);

		// General state

		Element generalElement = document.createElement("generalstate");

		element = document.createElement("package");
		element.appendChild(document
				.createTextNode("testExamples.parser.state.correct"));
		generalElement.appendChild(element);

		element = document.createElement("classname");
		element.appendChild(document.createTextNode("State1Header"));
		generalElement.appendChild(element);

		rootElement.appendChild(generalElement);

		// Actions

		Element actionsElement = document.createElement("actions");

		element = document.createElement("action");
		element.appendChild(document.createTextNode("action1"));
		actionsElement.appendChild(element);

		element = document.createElement("action");
		element.appendChild(document.createTextNode("action2"));
		actionsElement.appendChild(element);

		element = document.createElement("action");
		element.appendChild(document.createTextNode("action3"));
		actionsElement.appendChild(element);

		rootElement.appendChild(actionsElement);

		// Context information

		Element contextElement = document.createElement("context");

		element = document.createElement("package");
		element.appendChild(document
				.createTextNode("testExamples.parser.state.correct"));
		contextElement.appendChild(element);

		element = document.createElement("classname");
		element.appendChild(document.createTextNode("State1Context"));
		contextElement.appendChild(element);

		element = document.createElement("builder");
		element.appendChild(document
				.createTextNode("new State1Context (new State1Concrete1())"));
		contextElement.appendChild(element);

		rootElement.appendChild(contextElement);

		// Concrete states

		Element statesElement = document.createElement("states");

		element = document.createElement("package");
		element.appendChild(document
				.createTextNode("testExamples.parser.state.correct"));
		statesElement.appendChild(element);

		// State 1
		Element stateElement = document.createElement("state");

		element = document.createElement("classname");
		element.appendChild(document.createTextNode("State1Concrete1"));
		stateElement.appendChild(element);

		element = document.createElement("initialstate");
		element.appendChild(empty);
		stateElement.appendChild(element);

		element = document.createElement("finalstate");
		element.appendChild(empty);
		stateElement.appendChild(element);

		element = document.createElement("builder");
		element.appendChild(document.createTextNode("new State1Concrete1()"));
		stateElement.appendChild(element);

		// Transitions

		// Transition 1
		Element transitionsElement = document.createElement("transitions");

		Element transitionElement = document.createElement("transition");

		element = document.createElement("action");
		element.appendChild(document.createTextNode("action1"));
		transitionElement.appendChild(element);

		element = document.createElement("destiny");
		element.appendChild(document.createTextNode("State1Concrete2"));
		transitionElement.appendChild(element);

		transitionsElement.appendChild(transitionElement);

		// Transition 2

		transitionElement = document.createElement("transition");

		element = document.createElement("action");
		element.appendChild(document.createTextNode("action3"));
		transitionElement.appendChild(element);

		element = document.createElement("destiny");
		element.appendChild(document.createTextNode("State1Concrete2"));
		transitionElement.appendChild(element);

		transitionsElement.appendChild(transitionElement);

		// Parameters

		Element parametersElement = document.createElement("parameters");

		element = document.createElement("parameter");
		element.setAttribute("position", "0");
		element.appendChild(document.createTextNode("context"));
		parametersElement.appendChild(element);

		transitionElement.appendChild(parametersElement);

		transitionsElement.appendChild(transitionElement);

		stateElement.appendChild(transitionsElement);

		statesElement.appendChild(stateElement);

		// End state 1

		// State 2

		stateElement = document.createElement("state");

		element = document.createElement("classname");
		element.appendChild(document.createTextNode("State1Concrete2"));
		stateElement.appendChild(element);

		element = document.createElement("finalstate");
		element.appendChild(empty);
		stateElement.appendChild(element);

		// Transitions

		// Transition 1

		transitionsElement = document.createElement("transitions");
		transitionElement = document.createElement("transition");

		element = document.createElement("action");
		element.appendChild(document.createTextNode("action2"));
		transitionElement.appendChild(element);

		element = document.createElement("destiny");
		element.appendChild(document.createTextNode("State1Concrete1"));
		transitionElement.appendChild(element);

		transitionsElement.appendChild(transitionElement);

		// Parameters

		parametersElement = document.createElement("parameters");

		element = document.createElement("parameter");
		element.setAttribute("position", "0");
		element.appendChild(document.createTextNode("\"test\""));
		parametersElement.appendChild(element);

		element = document.createElement("parameter");
		element.setAttribute("position", "1");
		element.appendChild(document.createTextNode("context"));
		parametersElement.appendChild(element);

		element = document.createElement("parameter");
		element.setAttribute("position", "2");
		element.appendChild(document.createTextNode("5"));
		parametersElement.appendChild(element);

		transitionElement.appendChild(parametersElement);

		transitionsElement.appendChild(transitionElement);

		stateElement.appendChild(transitionsElement);

		statesElement.appendChild(stateElement);

		// End state 2

		rootElement.appendChild(statesElement);

		return document;
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

		actionNames.add("action1");
		actionNames.add("action2");
		actionNames.add("action3");

		pattern = new StatePattern("State1Header",
				"testExamples.parser.state.correct", "State1Header",
				actionNames, false, "testExamples.parser.state.correct",
				"State1Context", "new State1Context (new State1Concrete1())",
				"testExamples.parser.state.correct", csl);

		return pattern;
	}

}
