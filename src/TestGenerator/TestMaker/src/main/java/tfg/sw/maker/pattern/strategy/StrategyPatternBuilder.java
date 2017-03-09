package tfg.sw.maker.pattern.strategy;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import tfg.sw.maker.exception.PatternBuilderException;
import tfg.sw.maker.pattern.Builder;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.PatternBuilder;
import tfg.sw.maker.pattern.strategy.comparison.ByComparable;
import tfg.sw.maker.pattern.strategy.comparison.ByComparator;
import tfg.sw.maker.pattern.strategy.comparison.ByEquals;

public class StrategyPatternBuilder implements PatternBuilder {

	private static StrategyPatternBuilder instance = null;

	private StrategyPatternBuilder() {

	}

	public static StrategyPatternBuilder getInstance() {
		if (instance == null)
			instance = new StrategyPatternBuilder();

		return instance;
	}

	@Override
	public Pattern getPatternFromXML(Element rootElement)
			throws PatternBuilderException {

		StrategyPattern result = new StrategyPattern();

		if (rootElement.getTagName().compareTo(Builder.STRATEGY_PATTER_ID) != 0)
			generateException("");

		Element element = (Element) rootElement.getFirstChild();

		while (element != null) {

			switch (element.getTagName()) {
			case "id":
				if (element.getChildNodes().getLength() != 1)
					generateException("");

				result.setPatternId(element.getFirstChild().getNodeValue());

				break;

			case "generalStrategy":
				if (!result.getGenericStrategyPackage().trim().isEmpty()
						|| !result.getClassName().trim().isEmpty()
						|| (result.getActions() != null && result.getActions()
								.size() > 0))
					generateException("");
				result.setActions(getActionsFromElement(element));
				result.setGenericStrategyPackage(getPackageFromElement(element));
				result.setClassName(getClassNameFromElement(element));
				break;

			case "concreteStrategies":
				if (!result.getConcreteStrategyPackage().trim().isEmpty()
						|| (result.getConcreteStrategies() != null && result
								.getConcreteStrategies().size() > 0))
					generateException("");
				result.setConcreteStrategyPackage(getPackageFromElement(element));
				result.setConcreteStrategies(getConcreteStrategiesFromElement(element));
				break;

			default:
				generateException("");
			}

			element = (Element) element.getNextSibling();
		}

		return result;

	}

	/**
	 * Retrieves the content of a classname sub-element of the given element
	 * 
	 * @param element
	 * @return
	 * @throws PatternBuilderException
	 */
	private String getClassNameFromElement(Element element)
			throws PatternBuilderException {

		Element auxElement = (Element) element
				.getElementsByTagName("classname").item(0);

		if (auxElement == null || auxElement.getChildNodes().getLength() != 1)
			generateException("");

		return auxElement.getFirstChild().getNodeValue();
	}

	/**
	 * Retrieves the content of a package sub-element of the given element
	 * 
	 * @param element
	 * @return
	 * @throws PatternBuilderException
	 */
	private String getPackageFromElement(Element element)
			throws PatternBuilderException {
		Element auxElement = (Element) element.getElementsByTagName("package")
				.item(0);

		if (auxElement == null || auxElement.getChildNodes().getLength() != 1)
			generateException("");

		return auxElement.getFirstChild().getNodeValue();
	}

	/**
	 * Retrieves the content of a builder sub-element of the given element
	 * 
	 * @param element
	 * @return
	 * @throws PatternBuilderException
	 */
	private String getBuilderFromElement(Element element)
			throws PatternBuilderException {
		Element auxElement = (Element) element.getElementsByTagName("builder")
				.item(0);

		// there is no Builder
		if (auxElement == null)
			return "";

		if (auxElement.getChildNodes().getLength() != 1)
			generateException("");

		return auxElement.getFirstChild().getNodeValue();
	}

	private List<StrategyAction> getActionsFromElement(Element actions)
			throws PatternBuilderException {

		List<StrategyAction> result = new ArrayList<StrategyAction>();

		NodeList elements = actions.getElementsByTagName("strategyActions");

		if (elements == null || elements.getLength() != 1)
			generateException("");

		Element element = (Element) elements.item(0).getFirstChild();

		while (element != null) {

			if (element.getNodeName().compareTo("strategyAction") != 0)
				generateException("");

			result.add(getStrategyActionFromElement(element));

			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private StrategyAction getStrategyActionFromElement(Element action)
			throws PatternBuilderException {

		StrategyAction result = new StrategyAction();
		Element element = (Element) action.getFirstChild();

		while (element != null) {

			switch (element.getNodeName()) {
			case "returnType":
				if (!result.getReturnType().trim().isEmpty())
					generateException("");
				result.setReturnType(element.getFirstChild().getNodeValue());
				break;

			case "returnTypePackage":
				if (!result.getReturnTypePackage().trim().isEmpty())
					generateException("");
				if (element.hasChildNodes())
					result.setReturnTypePackage(element.getFirstChild()
							.getNodeValue());
				break;

			case "name":
				if (!result.getName().trim().isEmpty())
					generateException("");
				result.setName(element.getFirstChild().getNodeValue());
				break;

			case "parameterSet":
				if (result.getParameters() != null
						&& result.getParameters().size() != 0)
					generateException("");
				result.setParameters(getParameterSetFromElement(element));
				break;

			case "comparison":
				if (result.getComparison() != null)
					generateException("");
				result.setComparison(getComparisonFromElement(element));
				break;

			default:
				generateException("");
			}

			element = (Element) element.getNextSibling();
		}
		return result;
	}

	private Comparison getComparisonFromElement(Element comparison)
			throws PatternBuilderException {
		Comparison result = null;

		Element element = (Element) comparison.getFirstChild();

		switch (element.getNodeName()) {
		case "equals":
			result = new ByEquals();
			break;

		case "comparable":
			result = new ByComparable();
			break;

		case "comparator":
			ByComparator aux = new ByComparator();
			if (element.getFirstChild() != null
					&& element.getFirstChild().hasChildNodes()) {

				aux.setPackageValue(getPackageFromElement(element));
				aux.setClassName(getClassNameFromElement(element));
				aux.setBuilder(getBuilderFromElement(element));
			}
			result = aux;
			break;

		default:
			generateException("");
		}

		return result;
	}

	private List<String> getParameterSetFromElement(Element parameters)
			throws PatternBuilderException {

		List<String> result = new ArrayList<String>();

		Element element = (Element) parameters.getFirstChild();

		while (element != null) {

			if (element.getNodeName().compareTo("parameters") != 0)
				generateException("");

			result.add(element.getFirstChild().getNodeValue());

			element = (Element) element.getNextSibling();
		}
		return result;
	}

	private List<ConcreteStrategy> getConcreteStrategiesFromElement(
			Element strategies) throws PatternBuilderException {

		List<ConcreteStrategy> result = new ArrayList<ConcreteStrategy>();

		boolean packageFlag = false;
		Element element = (Element) strategies.getFirstChild();

		while (element != null) {

			if (element.getNodeName().compareTo("concreteStrategy") != 0
					&& element.getNodeName().compareTo("package") != 0)
				generateException("");

			if (element.getNodeName().compareTo("package") == 0) {
				if (packageFlag) {
					generateException("");
				} else {
					packageFlag = true;
				}
			} else {
				result.add(getConcreteStrategyFromElement(element));
			}

			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private ConcreteStrategy getConcreteStrategyFromElement(Element strategy)
			throws PatternBuilderException {

		ConcreteStrategy result = new ConcreteStrategy();

		result.setClassName(getClassNameFromElement(strategy));
		result.setBuilder(getBuilderFromElement(strategy));

		return result;
	}

	private void generateException(String message)
			throws PatternBuilderException {
		throw new PatternBuilderException("Error: ");
	}

}
