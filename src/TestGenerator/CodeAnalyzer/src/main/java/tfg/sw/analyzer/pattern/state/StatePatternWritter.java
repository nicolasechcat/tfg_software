package tfg.sw.analyzer.pattern.state;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternWriter;

public class StatePatternWritter implements PatternWriter {
	
	private static StatePatternWritter writter;
	
	private StatePatternWritter () {
	}
	
	public static PatternWriter getInstance () {
		
		if (writter == null) {
			writter = new StatePatternWritter();
		}
		
		return writter;
	}
	
	@Override
	public Element patternToDomElement (Pattern pattern, Document document) {
		
		StatePattern statePattern = (StatePattern) pattern;
		
		Element rootElement = document.createElement("statePattern");
		
		/*
		 * Fill the DOM tree
		 */
		
		Element element = null;
		Text empty = document.createTextNode("");
		
		// general information
		
		element = document.createElement("id");
		element.appendChild(document.createTextNode(statePattern.getPatternId()));
		rootElement.appendChild(element);
		
		if (statePattern.isOrderDependent()) {
			element = document.createElement("orderdependent");
		} else {
			element = document.createElement("nonorderdependent");
		}
		element.appendChild(empty);
		rootElement.appendChild(element);
		
		
		
		// General state
		
		Element generalElement = document.createElement("generalstate");
		
		element = document.createElement("package");
		element.appendChild(document.createTextNode(statePattern
				.getGeneralStatePackage()));
		generalElement.appendChild(element);
		
		element = document.createElement("classname");
		element.appendChild(document.createTextNode(statePattern
				.getGeneralStateClassName()));
		generalElement.appendChild(element);
		
		rootElement.appendChild(generalElement);
		
		
		
		// Actions
		
		if (statePattern.getActionNames().size() > 0) {
			Element actionsElement = document.createElement("actions");
			
			for (String s : statePattern.getActionNames()) {
				
				element = document.createElement("action");
				element.appendChild(document.createTextNode(s));
				actionsElement.appendChild(element);
			}
			
			rootElement.appendChild(actionsElement);
		}
		
		
		// Context information
		
		Element contextElement = document.createElement("context");
		
		element = document.createElement("package");
		element.appendChild(document.createTextNode(statePattern
				.getStateContextPackage()));
		contextElement.appendChild(element);
		
		element = document.createElement("classname");
		element.appendChild(document.createTextNode(statePattern
				.getStateContextClassName()));
		contextElement.appendChild(element);
		
		if (statePattern.getStateContextBuilder() != null
				&& !statePattern.getStateContextBuilder().isEmpty()) {
			element = document.createElement("builder");
			element.appendChild(document.createTextNode(statePattern
					.getStateContextBuilder()));
			contextElement.appendChild(element);
		}
		
		rootElement.appendChild(contextElement);
		
		
		
		// Concrete states
		
		Element statesElement = document.createElement("states");
		
		element = document.createElement("package");
		element.appendChild(document.createTextNode(statePattern
				.getConcreteStatesPackage()));
		statesElement.appendChild(element);
		
		for (ConcreteState cs : statePattern.getConcreteStates()) {
			
			Element stateElement = document.createElement("state");
			
			element = document.createElement("classname");
			element.appendChild(document.createTextNode(cs.getClassName()));
			stateElement.appendChild(element);
			
			if (cs.isInitialState()) {
				element = document.createElement("initialstate");
				element.appendChild(empty);
				stateElement.appendChild(element);
			}
			
			if (cs.isFinalState()) {
				element = document.createElement("finalstate");
				element.appendChild(empty);
				stateElement.appendChild(element);
			}
			
			if (cs.getBuilderInvocation() != null
					&& !cs.getBuilderInvocation().isEmpty()) {
				element = document.createElement("builder");
				element.appendChild(document.createTextNode(cs
						.getBuilderInvocation()));
				stateElement.appendChild(element);
			}
			
			
			// Transitions
			if (cs.getTransitions().size() > 0) {
				Element transitionsElement = document
						.createElement("transitions");
				for (Transition t : cs.getTransitions()) {
					
					Element transitionElement = document
							.createElement("transition");
					
					element = document.createElement("action");
					element.appendChild(document.createTextNode(t
							.getActionName()));
					transitionElement.appendChild(element);
					
					element = document.createElement("destiny");
					element.appendChild(document.createTextNode(t
							.getDestinyStateName()));
					transitionElement.appendChild(element);
					
					// Parameters
					
					if (t.getParameters().size() > 0) {
						
						Element parametersElement = document
								.createElement("parameters");
						int i = 0;
						for (Parameter p : t.getParameters()) {
							
							element = document.createElement("parameter");
							element.setAttribute("position", String.valueOf(i)
									.trim());
							element.appendChild(document.createTextNode(p
									.getValue().trim()));
							parametersElement.appendChild(element);
							
							i++;
						}
						
						transitionElement.appendChild(parametersElement);
					}
					
					transitionsElement.appendChild(transitionElement);
				}
				stateElement.appendChild(transitionsElement);
			}
			
			statesElement.appendChild(stateElement);
		}
		
		rootElement.appendChild(statesElement);
		
		return rootElement;
		
	}
}
