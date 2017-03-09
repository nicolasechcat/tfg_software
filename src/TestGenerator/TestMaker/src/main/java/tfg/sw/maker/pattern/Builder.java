package tfg.sw.maker.pattern;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tfg.sw.maker.exception.PatternBuilderException;
import tfg.sw.maker.pattern.state.StatePattern;
import tfg.sw.maker.pattern.state.StatePatternBuilder;
import tfg.sw.maker.pattern.strategy.StrategyPattern;
import tfg.sw.maker.pattern.strategy.StrategyPatternBuilder;

public class Builder {

	public static final String STATE_PATTER_ID = "statePattern";
	public static final String STRATEGY_PATTER_ID = "strategyPattern";

	public static List<Pattern> getPatternFromXML(Document rootElement)
			throws PatternBuilderException {

		List<Pattern> result = new ArrayList<Pattern>();

		Element element = (Element) rootElement.getFirstChild();

		if (element.getTagName().compareTo("patterns") != 0)
			generateException("");

		element = (Element) element.getFirstChild();

		PatternBuilder builder;
		Pattern pattern;

		while (element != null) {

			builder = null;
			pattern = null;

			switch (element.getTagName()) {

			case STATE_PATTER_ID:
				builder = StatePatternBuilder.getInstance();
				pattern = builder.getPatternFromXML(element);
				((StatePattern) pattern).fillCompleteInformation();
				break;

			case STRATEGY_PATTER_ID:
				builder = StrategyPatternBuilder.getInstance();
				pattern = builder.getPatternFromXML(element);
				((StrategyPattern) pattern).fillCompleteInformation();
				break;

			default:
				generateException("");
			}

			result.add(pattern);

			element = (Element) element.getNextSibling();
		}

		return result;
	}

	private static void generateException(String message)
			throws PatternBuilderException {
		throw new PatternBuilderException("Error: ");
	}

}
