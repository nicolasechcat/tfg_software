package tfg.sw.maker.pattern.state;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import tfg.sw.maker.exception.PatternBuilderException;
import tfg.sw.maker.pattern.Builder;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.PatternBuilder;

public class StatePatternBuilder implements PatternBuilder {

	private static StatePatternBuilder instance = null;

	private StatePatternBuilder() {

	}

	public static PatternBuilder getInstance() {
		if (instance == null)
			instance = new StatePatternBuilder();

		return instance;
	}

	public Pattern getPatternFromXML(Element rootElement)
			throws PatternBuilderException {

		StatePattern result = new StatePattern();

		if (rootElement.getTagName().compareTo(Builder.STATE_PATTER_ID) != 0)
			generateException("");

		Element element = (Element) rootElement.getFirstChild();

		while (element != null) {

			switch (element.getTagName()) {
			case "id":
				if (element.getChildNodes().getLength() != 1)
					generateException("");

				result.setPatternId(element.getFirstChild().getNodeValue());

				break;

			case "orderdependent":
				result.setOrderDependent(true);
				break;

			case "nonorderdependent":
				result.setOrderDependent(false);
				break;

			case "generalstate":
				if (element.getChildNodes().getLength() != 2)
					generateException("");

				result.setGeneralStatePackage(getPackageChildValue(element));
				result.setGeneralStateClassName(getClassNameChildValue(element));

				break;

			case "actions":
				result.setActionNames(getActionsFromElement(element));
				break;

			case "context":
				result.setStateContextPackage(getPackageChildValue(element));
				result.setStateContextClassName(getClassNameChildValue(element));
				result.setStateContextBuilder(getBuilderChildValue(element));
				break;

			case "states":
				result.setConcreteStatesPackage(getPackageChildValue(element));
				result.setConcreteStates(getStatesFromElement(element));
				break;

			default:
				generateException("");
			}

			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private String getPackageChildValue(Element element)
			throws PatternBuilderException {
		Element auxElement = (Element) element.getElementsByTagName("package")
				.item(0);

		if (auxElement == null || auxElement.getChildNodes().getLength() != 1)
			generateException("");

		return auxElement.getFirstChild().getNodeValue();
	}

	private String getClassNameChildValue(Element element)
			throws PatternBuilderException {
		Element auxElement = (Element) element
				.getElementsByTagName("classname").item(0);

		if (auxElement == null || auxElement.getChildNodes().getLength() != 1)
			generateException("");

		return auxElement.getFirstChild().getNodeValue();
	}

	private String getBuilderChildValue(Element element)
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

	private List<String> getActionsFromElement(Element actions)
			throws PatternBuilderException {

		List<String> result = new ArrayList<String>();

		Element element = (Element) actions.getFirstChild();

		while (element != null) {

			if (element.getNodeName().compareTo("action") != 0)
				generateException("");

			result.add(element.getFirstChild().getNodeValue());

			element = (Element) element.getNextSibling();
		}

		return result;

	}

	private List<ConcreteState> getStatesFromElement(Element states)
			throws PatternBuilderException {

		List<ConcreteState> result = new ArrayList<ConcreteState>();

		boolean packageFlag = false;
		Element element = (Element) states.getFirstChild();

		while (element != null) {

			if (element.getNodeName().compareTo("state") != 0
					&& element.getNodeName().compareTo("package") != 0)
				generateException("");

			if (element.getNodeName().compareTo("package") == 0) {
				if (packageFlag) {
					generateException("");
				} else {
					packageFlag = true;
				}
			} else {
				result.add(getConcreteStateFromElement(element));
			}

			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private ConcreteState getConcreteStateFromElement(Element state)
			throws PatternBuilderException {

		ConcreteState result = new ConcreteState();

		Element element = (Element) state.getFirstChild();

		while (element != null) {

			switch (element.getNodeName()) {
			case "classname":
				result.setClassName(element.getFirstChild().getNodeValue());
				break;

			case "initialstate":
				result.setInitialState(true);
				break;

			case "finalstate":
				result.setFinalState(true);
				break;

			case "builder":
				result.setBuilderInvocation(element.getFirstChild()
						.getNodeValue());
				break;

			case "transitions":
				result.setTransitions(getTransitionsFromElement(element));
				break;

			default:
				generateException("");
			}
			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private List<Transition> getTransitionsFromElement(Element states)
			throws PatternBuilderException {

		List<Transition> result = new ArrayList<Transition>();

		Element element = (Element) states.getFirstChild();

		while (element != null) {

			if (element.getNodeName().compareTo("transition") != 0)
				generateException("");

			result.add(getTransitionFromElement(element));

			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private Transition getTransitionFromElement(Element state)
			throws PatternBuilderException {

		Transition result = new Transition();

		Element element = (Element) state.getFirstChild();

		while (element != null) {

			switch (element.getNodeName()) {

			case "action":
				result.setActionName(element.getFirstChild().getNodeValue());
				break;

			case "destiny":
				result.setDestinyStateName(element.getFirstChild()
						.getNodeValue());
				break;

			case "parameters":
				List<Parameter> parameters = new ArrayList<Parameter>();

				Element paramElement = (Element) element.getFirstChild();
				Parameter parameter;

				while (paramElement != null) {
					parameter = new Parameter();

					parameter.setOrder(Integer.valueOf(paramElement
							.getAttribute("position")));
					parameter.setValue(paramElement.getFirstChild()
							.getNodeValue());

					parameters.add(parameter);

					paramElement = (Element) paramElement.getNextSibling();
				}

				result.setParameters(parameters);

				break;

			default:
				generateException("");
			}
			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private void generateException(String message)
			throws PatternBuilderException {
		throw new PatternBuilderException("Error: ");
	}
}
