package tfg.sw.analyzer.pattern.strategy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternWriter;
import tfg.sw.analyzer.pattern.strategy.comparison.ByComparable;
import tfg.sw.analyzer.pattern.strategy.comparison.ByComparator;
import tfg.sw.analyzer.pattern.strategy.comparison.ByEquals;

public class StrategyPatternWritter implements PatternWriter {
	
	private static StrategyPatternWritter writter;
	
	private StrategyPatternWritter () {
	}
	
	public static StrategyPatternWritter getInstance () {
		
		if (writter == null) {
			writter = new StrategyPatternWritter();
		}
		
		return writter;
	}
	
	@Override
	public Element patternToDomElement (Pattern pattern, Document document) {
		
		StrategyPattern strategyPattern = (StrategyPattern) pattern;
		
		Element rootElement = document.createElement("strategyPattern");
		Node empty = document.createTextNode("");
		
		/*
		 * Fill the DOM tree
		 */
		
		Element element = null;
		
		// general information
		
		element = document.createElement("id");
		element.appendChild(document.createTextNode(strategyPattern
				.getPatternId()));
		rootElement.appendChild(element);
		
		
		// General strategy
		
		Element generalElement = document.createElement("generalStrategy");
		
		element = document.createElement("package");
		element.appendChild(document.createTextNode(strategyPattern
				.getGenericStrategyPackage()));
		generalElement.appendChild(element);
		
		element = document.createElement("classname");
		element.appendChild(document.createTextNode(strategyPattern
				.getClassName()));
		generalElement.appendChild(element);
		
		// Strategy actions
		
		if (strategyPattern.getActions().size() > 0) {
			Element actionsElement = document.createElement("strategyActions");
			
			for (StrategyAction sa : strategyPattern.getActions()) {
				
				Element saElement = document.createElement("strategyAction");
				
				// return type
				element = document.createElement("returnType");
				element.appendChild(document.createTextNode(sa.getReturnType()));
				saElement.appendChild(element);
				
				// return type package
				element = document.createElement("returnTypePackage");
				element.appendChild(document.createTextNode(sa
						.getReturnTypePackage()));
				saElement.appendChild(element);
				
				// name
				element = document.createElement("name");
				element.appendChild(document.createTextNode(sa.getName()));
				saElement.appendChild(element);
				
				// parameterSet
				Element paramsElement = document.createElement("parameterSet");
				if (sa.getParameters().size() > 0)
					for (String s : sa.getParameters()) {
						element = document.createElement("parameters");
						element.appendChild(document.createTextNode(s));
						paramsElement.appendChild(element);
					}
				else
					paramsElement.appendChild(empty);
				
				saElement.appendChild(paramsElement);
				
				
				// Comparison
				saElement.appendChild(getComparisonElement(sa.getComparison(),
					document));
				
				actionsElement.appendChild(saElement);
			}
			
			generalElement.appendChild(actionsElement);
		}
		
		rootElement.appendChild(generalElement);
		
		// Concrete strategies
		
		Element strategiesElement = document
				.createElement("concreteStrategies");
		
		element = document.createElement("package");
		element.appendChild(document.createTextNode(strategyPattern
				.getConcreteStrategyPackage()));
		strategiesElement.appendChild(element);
		
		for (ConcreteStrategy cs : strategyPattern.getConcreteStrategies()) {
			
			Element strategyElement = document
					.createElement("concreteStrategy");
			
			element = document.createElement("classname");
			element.appendChild(document.createTextNode(cs.getClassName()));
			strategyElement.appendChild(element);
			
			if (cs.getBuilder() != null && !cs.getBuilder().isEmpty()) {
				element = document.createElement("builder");
				element.appendChild(document.createTextNode(cs.getBuilder()));
				strategyElement.appendChild(element);
			}
			
			strategiesElement.appendChild(strategyElement);
		}
		
		rootElement.appendChild(strategiesElement);
		
		return rootElement;
		
	}
	
	private Node getComparisonElement (Comparison comparison, Document document) {
		
		Element element;
		Element result = document.createElement("comparison");
		
		
		Node empty = document.createTextNode("");
		
		if (comparison.getClass() == ByEquals.class) {
			element = document.createElement("equals");
			element.appendChild(empty);
			result.appendChild(element);
			return result;
		}
		
		if (comparison.getClass() == ByComparable.class) {
			element = document.createElement("comparable");
			element.appendChild(empty);
			result.appendChild(element);
			return result;
		}
		
		if (comparison.getClass() == ByComparator.class) {
			Element comparatorElement = document.createElement("comparator");
			result.appendChild(comparatorElement);
			
			ByComparator comparator = (ByComparator) comparison;
			
			if (comparator.getClassName().isEmpty())
				return result;
			
			element = document.createElement("package");
			element.appendChild(document.createTextNode(comparator
					.getPackageValue()));
			comparatorElement.appendChild(element);
			
			element = document.createElement("classname");
			element.appendChild(document.createTextNode(comparator
					.getClassName()));
			comparatorElement.appendChild(element);
			
			if (!comparator.getBuilder().isEmpty()) {
				element = document.createElement("builder");
				element.appendChild(document.createTextNode(comparator
						.getBuilder()));
				comparatorElement.appendChild(element);
			}
			
			result.appendChild(comparatorElement);
			
			return result;
		}
		
		
		return empty;
	}
}
