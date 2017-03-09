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
import tfg.sw.maker.pattern.strategy.Comparison;
import tfg.sw.maker.pattern.strategy.ConcreteStrategy;
import tfg.sw.maker.pattern.strategy.StrategyAction;
import tfg.sw.maker.pattern.strategy.StrategyPattern;
import tfg.sw.maker.pattern.strategy.StrategyPatternBuilder;
import tfg.sw.maker.pattern.strategy.comparison.ByComparable;
import tfg.sw.maker.pattern.strategy.comparison.ByComparator;
import tfg.sw.maker.pattern.strategy.comparison.ByEquals;

public class StrategyBuilderTest {

	@Test
	public void it2_ft23_testXMLBuilderToPattern()
			throws PatternBuilderException, ParserConfigurationException {

		Pattern waitedResult = getPatternsCorrectBaseStrategy();

		PatternBuilder builder = StrategyPatternBuilder.getInstance();

		Pattern result = builder
				.getPatternFromXML((Element) genetareDOMStrategyPattern()
						.getDocumentElement().getFirstChild());

		assertEquals(waitedResult, result);
	}

	private Document genetareDOMStrategyPattern()
			throws ParserConfigurationException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document document = docBuilder.newDocument();
		Element rootElements = document.createElement("patterns");
		document.appendChild(rootElements);

		Element rootElement = document.createElement("strategyPattern");
		rootElements.appendChild(rootElement);

		Element element = null;
		Text empty = document.createTextNode("");

		/*
		 * Fill the DOM tree
		 */

		// general information

		element = document.createElement("id");
		element.appendChild(document.createTextNode("Strategy1"));
		rootElement.appendChild(element);

		// General strategy

		Element generalElement = document.createElement("generalStrategy");

		element = document.createElement("package");
		element.appendChild(document
				.createTextNode("testExamples.parser.strategy.correct"));
		generalElement.appendChild(element);

		element = document.createElement("classname");
		element.appendChild(document.createTextNode("Strategy1"));
		generalElement.appendChild(element);

		// Strategy actions
		Element actionsElement = document.createElement("strategyActions");

		// Action1
		Element saElement = document.createElement("strategyAction");

		element = document.createElement("returnType");
		element.appendChild(document.createTextNode("Date"));
		saElement.appendChild(element);

		element = document.createElement("returnTypePackage");
		element.appendChild(document.createTextNode("java.util.Date"));
		saElement.appendChild(element);

		element = document.createElement("name");
		element.appendChild(document.createTextNode("action1"));
		saElement.appendChild(element);

		Element paramsElement = document.createElement("parameterSet");
		element = document.createElement("parameters");
		element.appendChild(document
				.createTextNode("new Date (2017, 01, 01), true"));
		paramsElement.appendChild(element);
		element = document.createElement("parameters");
		element.appendChild(document
				.createTextNode("new Date (2017, 12, 31), true"));
		paramsElement.appendChild(element);
		saElement.appendChild(paramsElement);

		Element comparisonElement = document.createElement("comparison");
		comparisonElement.appendChild(document.createElement("equals"));
		saElement.appendChild(comparisonElement);

		actionsElement.appendChild(saElement);

		// Action2
		saElement = document.createElement("strategyAction");

		element = document.createElement("returnType");
		element.appendChild(document.createTextNode("UsersClassComparable"));
		saElement.appendChild(element);

		element = document.createElement("returnTypePackage");
		element.appendChild(empty);
		saElement.appendChild(element);

		element = document.createElement("name");
		element.appendChild(document.createTextNode("action2"));
		saElement.appendChild(element);

		paramsElement = document.createElement("parameterSet");
		paramsElement.appendChild(empty);
		saElement.appendChild(paramsElement);

		comparisonElement = document.createElement("comparison");
		comparisonElement.appendChild(document.createElement("comparable"));
		saElement.appendChild(comparisonElement);

		actionsElement.appendChild(saElement);

		// Action3
		saElement = document.createElement("strategyAction");

		element = document.createElement("returnType");
		element.appendChild(document.createTextNode("UsersClassComparator1"));
		saElement.appendChild(element);

		element = document.createElement("returnTypePackage");
		element.appendChild(empty);
		saElement.appendChild(element);

		element = document.createElement("name");
		element.appendChild(document.createTextNode("action3"));
		saElement.appendChild(element);

		paramsElement = document.createElement("parameterSet");
		element = document.createElement("parameters");
		element.appendChild(document
				.createTextNode("new UsersClassComparable ()"));
		paramsElement.appendChild(element);
		saElement.appendChild(paramsElement);

		comparisonElement = document.createElement("comparison");

		Element comparatorElement = document.createElement("comparator");

		element = document.createElement("package");
		element.appendChild(document
				.createTextNode("testExamples.parser.strategy.correct.auxiliar"));
		comparatorElement.appendChild(element);

		element = document.createElement("classname");
		element.appendChild(document.createTextNode("UsersComparator"));
		comparatorElement.appendChild(element);

		element = document.createElement("builder");
		element.appendChild(document.createTextNode("new UsersComparator (3)"));
		comparatorElement.appendChild(element);

		comparisonElement.appendChild(comparatorElement);
		saElement.appendChild(comparisonElement);

		actionsElement.appendChild(saElement);

		// Action4
		saElement = document.createElement("strategyAction");

		element = document.createElement("returnType");
		element.appendChild(document.createTextNode("UsersClassComparator2"));
		saElement.appendChild(element);

		element = document.createElement("returnTypePackage");
		element.appendChild(empty);
		saElement.appendChild(element);

		element = document.createElement("name");
		element.appendChild(document.createTextNode("action4"));
		saElement.appendChild(element);

		paramsElement = document.createElement("parameterSet");
		paramsElement.appendChild(empty);
		saElement.appendChild(paramsElement);

		comparisonElement = document.createElement("comparison");

		comparatorElement = document.createElement("comparator");
		comparatorElement.appendChild(empty);

		comparisonElement.appendChild(comparatorElement);
		saElement.appendChild(comparisonElement);

		actionsElement.appendChild(saElement);

		generalElement.appendChild(actionsElement);

		rootElement.appendChild(generalElement);

		// Concrete strategies

		Element strategiesElement = document
				.createElement("concreteStrategies");

		element = document.createElement("package");
		element.appendChild(document
				.createTextNode("testExamples.parser.strategy.correct.concrete"));
		strategiesElement.appendChild(element);

		Element strategyElement = document.createElement("concreteStrategy");
		element = document.createElement("classname");
		element.appendChild(document.createTextNode("ConcreteStrategy1"));
		strategyElement.appendChild(element);
		element = document.createElement("builder");
		element.appendChild(document.createTextNode("new ConcreteStrategy1 ()"));
		strategyElement.appendChild(element);
		strategiesElement.appendChild(strategyElement);

		strategyElement = document.createElement("concreteStrategy");
		element = document.createElement("classname");
		element.appendChild(document.createTextNode("ConcreteStrategy2"));
		strategyElement.appendChild(element);
		strategiesElement.appendChild(strategyElement);

		strategyElement = document.createElement("concreteStrategy");
		element = document.createElement("classname");
		element.appendChild(document.createTextNode("ConcreteStrategy3"));
		strategyElement.appendChild(element);
		strategiesElement.appendChild(strategyElement);

		rootElement.appendChild(strategiesElement);

		return document;
	}

	private Pattern getPatternsCorrectBaseStrategy() {

		Pattern pattern;

		String patternId = "Strategy1";

		String genericPackage = "testExamples.parser.strategy.correct";
		String genericClassName = "Strategy1";
		String concretePackage = "testExamples.parser.strategy.correct.concrete";

		List<StrategyAction> actions = new ArrayList<StrategyAction>();
		StrategyAction action;

		List<String> parameterSets;
		Comparison comparison;

		// action1

		// @patternElement Execution < new Date (2017, 01, 01), true >
		parameterSets = new ArrayList<String>();
		parameterSets.add("new Date (2017, 01, 01), true");

		// @patternElement Execution < new Date (2017, 12, 31), true >
		parameterSets.add("new Date (2017, 12, 31), true");

		comparison = new ByEquals();

		action = new StrategyAction("Date", "java.util.Date", "action1",
				parameterSets, comparison);
		actions.add(action);

		// action2

		parameterSets = new ArrayList<String>();

		comparison = new ByComparable();

		action = new StrategyAction("UsersClassComparable", "", "action2",
				parameterSets, comparison);
		actions.add(action);

		// action3

		// @patternElement Execution < new UsersClassComparable () >
		parameterSets = new ArrayList<String>();
		parameterSets.add("new UsersClassComparable ()");

		// @patternElement Comparison Comparator
		// <testExamples.parser.strategy.correct.auxiliar> <UsersComparator> <
		// new UsersComparator (3) >

		comparison = new ByComparator(
				"testExamples.parser.strategy.correct.auxiliar",
				"UsersComparator", "new UsersComparator (3)");

		action = new StrategyAction("UsersClassComparator1", "", "action3",
				parameterSets, comparison);
		actions.add(action);

		// action4

		// @patternElement Comparison Comparator
		parameterSets = new ArrayList<String>();
		comparison = new ByComparator();

		action = new StrategyAction("UsersClassComparator2", "", "action4",
				parameterSets, comparison);
		actions.add(action);

		List<ConcreteStrategy> concreteStrategies = new ArrayList<ConcreteStrategy>();
		ConcreteStrategy concrete;

		// ConcreteStrategy1
		concrete = new ConcreteStrategy("ConcreteStrategy1",
				"new ConcreteStrategy1 ()");
		concreteStrategies.add(concrete);

		// ConcreteStrategy2
		concrete = new ConcreteStrategy("ConcreteStrategy2", "");
		concreteStrategies.add(concrete);

		// ConcreteStrategy3
		concrete = new ConcreteStrategy("ConcreteStrategy3", "");
		concreteStrategies.add(concrete);

		pattern = new StrategyPattern(patternId, genericPackage,
				genericClassName, actions, concretePackage, concreteStrategies);

		return pattern;
	}
}
