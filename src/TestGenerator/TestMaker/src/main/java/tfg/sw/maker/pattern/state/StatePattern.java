package tfg.sw.maker.pattern.state;

import java.util.ArrayList;
import java.util.List;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.maker.state.StatePatternTestsPFE1;
import tfg.sw.maker.maker.state.StatePatternTestsPFE2;
import tfg.sw.maker.maker.state.StatePatternTestsPNFE1;
import tfg.sw.maker.pattern.Pattern;

public class StatePattern extends Pattern {
	
	
	
	private String generalStatePackage;
	private String generalStateClassName;
	private List<String> actionNames;
	private boolean orderDependent = true;
	
	private String stateContextPackage;
	private String stateContextClassName;
	private String stateContextBuilder;
	
	private String concreteStatesPackage;
	private List<ConcreteState> concreteStates;
	
	public StatePattern (String patternId, String generalStatePackage,
			String generalStateClassName, List<String> actionNames,
			boolean orderDependent, String stateContextPackage,
			String stateContextClassName, String stateContextBuilder,
			String concreteStatesPackage, List<ConcreteState> concreteStates) {
		super();
		super.patternId = patternId;
		this.generalStatePackage = generalStatePackage;
		this.generalStateClassName = generalStateClassName;
		this.actionNames = actionNames;
		this.orderDependent = orderDependent;
		this.stateContextPackage = stateContextPackage;
		this.stateContextClassName = stateContextClassName;
		this.stateContextBuilder = stateContextBuilder;
		this.concreteStatesPackage = concreteStatesPackage;
		this.concreteStates = concreteStates;
	}
	
	
	public StatePattern () {
		super();
		
		this.patternId = "";
		this.generalStatePackage = "";
		this.generalStateClassName = "";
		this.actionNames = new ArrayList<String>();
		this.orderDependent = false;
		this.stateContextPackage = "";
		this.stateContextClassName = "";
		this.stateContextBuilder = "";
		this.concreteStatesPackage = "";
		this.concreteStates = new ArrayList<ConcreteState>();
	}
	
	public void fillCompleteInformation () {
		
		boolean actionUsed = false;
		
		List<Object[]> actionParameters = new ArrayList<Object[]>();
		Object[] aux;
		
		// Complete the builders
		for (ConcreteState cs : this.concreteStates) {
			if (cs.getBuilderInvocation() == null
					|| cs.getBuilderInvocation().trim().isEmpty())
				cs.setBuilderInvocation("new " + cs.getClassName() + "()");
		}
		
		// Search for a valid parameters list for each action
		for (String a : this.actionNames) {
			aux = null;
			for (ConcreteState cs : this.concreteStates) {
				for (Transition t : cs.getTransitions()) {
					if (a.compareTo(t.getActionName()) == 0) {
						aux = new Object[2];
						aux[0] = a;
						aux[1] = t.getParameters();
						actionParameters.add(aux);
						break;
					}
				}
				if (aux != null)
					break;
			}
		}
		
		// for each ConcreteState, look for each possible action to have a
		// transition
		for (ConcreteState cs : this.concreteStates) {
			for (String a : this.actionNames) {
				actionUsed = false;
				for (Transition t : cs.getTransitions()) {
					if (a.compareTo(t.getActionName()) == 0) {
						actionUsed = true;
						break;
					}
				}
				
				// If the action has no transition in the state, then one
				// transition to itself is created
				if (!actionUsed) {
					
					// Look for a parameters set for this action
					aux = null;
					for (Object[] o : actionParameters) {
						if (((String) o[0]).compareTo(a) == 0) {
							aux = o;
							break;
						}
					}
					
					@SuppressWarnings ("unchecked")
					List<Parameter> params = (List<Parameter>) aux[1];
					
					cs.getTransitions().add(
							new Transition(cs.getClassName(), a, params, cs));
				}
			}
		}
		
		// Fill the destinyState with a ConcreteState object
		for (ConcreteState cs : this.concreteStates) {
			for (Transition t : cs.getTransitions()) {
				for (ConcreteState search : this.concreteStates) {
					if (t.getDestinyStateName()
							.compareTo(search.getClassName()) == 0) {
						t.setDestinyState(search);
						break;
					}
				}
			}
		}
		
	}
	
	
	
	public String getGeneralStatePackage () {
		return generalStatePackage;
	}
	
	public void setGeneralStatePackage (String generalStatePackage) {
		this.generalStatePackage = generalStatePackage;
	}
	
	public String getGeneralStateClassName () {
		return generalStateClassName;
	}
	
	public void setGeneralStateClassName (String generalStateClassName) {
		this.generalStateClassName = generalStateClassName;
	}
	
	
	public List<String> getActionNames () {
		return actionNames;
	}
	
	public void setActionNames (List<String> actionNames) {
		this.actionNames = actionNames;
	}
	
	public boolean isOrderDependent () {
		return orderDependent;
	}
	
	public void setOrderDependent (boolean orderDependent) {
		this.orderDependent = orderDependent;
	}
	
	public String getStateContextPackage () {
		return stateContextPackage;
	}
	
	public void setStateContextPackage (String stateContextPackage) {
		this.stateContextPackage = stateContextPackage;
	}
	
	public String getStateContextClassName () {
		return stateContextClassName;
	}
	
	public void setStateContextClassName (String stateContextClassName) {
		this.stateContextClassName = stateContextClassName;
	}
	
	public String getStateContextBuilder () {
		return stateContextBuilder;
	}
	
	public void setStateContextBuilder (String stateContextBuilder) {
		this.stateContextBuilder = stateContextBuilder;
	}
	
	public String getConcreteStatesPackage () {
		return concreteStatesPackage;
	}
	
	public void setConcreteStatesPackage (String concreteStatesPackage) {
		this.concreteStatesPackage = concreteStatesPackage;
	}
	
	public List<ConcreteState> getConcreteStates () {
		return concreteStates;
	}
	
	public void setConcreteStates (List<ConcreteState> concreteStates) {
		this.concreteStates = concreteStates;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		StatePattern other = (StatePattern) obj;
		
		if (this.patternId.compareTo(other.patternId) != 0)
			return false;
		
		if (this.generalStatePackage.compareTo(other.generalStatePackage) != 0)
			return false;
		
		if (this.generalStateClassName.compareTo(other.generalStateClassName) != 0)
			return false;
		
		if (this.actionNames.size() != other.actionNames.size())
			return false;
		
		for (String s : other.actionNames)
			if (!this.actionNames.contains(s))
				return false;
		
		if (this.orderDependent != other.orderDependent)
			return false;
		
		if (this.stateContextPackage.compareTo(other.stateContextPackage) != 0)
			return false;
		
		if (this.stateContextClassName.compareTo(other.stateContextClassName) != 0)
			return false;
		
		if (this.concreteStatesPackage.compareTo(other.concreteStatesPackage) != 0)
			return false;
		
		if (this.stateContextBuilder.compareTo(other.stateContextBuilder) != 0)
			return false;
		
		if (this.concreteStates.size() != other.concreteStates.size())
			return false;
		
		for (ConcreteState cs : other.concreteStates)
			if (!this.concreteStates.contains(cs))
				return false;
		
		
		return true;
	}
	
	public boolean lightEquals (Object obj) {
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		StatePattern aux = (StatePattern) obj;
		
		if (aux.getPatternId().compareTo(this.patternId) != 0)
			return false;
		
		if (aux.getGeneralStateClassName()
				.compareTo(this.generalStateClassName) != 0)
			return false;
		
		if (aux.isOrderDependent() != this.orderDependent)
			return false;
		
		return true;
	}
	
	
	
	@Override
	public String toString () {
		return "\n\nStatePattern [patternId=" + patternId
				+ "\n\tgeneralStatePackage=" + generalStatePackage
				+ "\n\tgeneralStateClassName=" + generalStateClassName
				+ "\n\n\tactionNames=" + actionNames + "\n\torderDependent="
				+ orderDependent + "\n\tstateContextPackage="
				+ stateContextPackage + "\n\tstateContextClassName="
				+ stateContextClassName + "\n\tstateContextBuilder="
				+ stateContextBuilder + "\n\tconcreteStatesPackage="
				+ concreteStatesPackage + "\n\n\tconcreteStates="
				+ concreteStates + "]";
	}
	
	public String getMinImports () {
		String result = "";
		
		result += "\nimport " + this.generalStatePackage + "."
				+ this.generalStateClassName + ";";
		result += "\n";
		result += "\nimport " + this.stateContextPackage + "."
				+ this.stateContextClassName + ";";
		result += "\n";
		
		for (ConcreteState cs : this.concreteStates) {
			if (cs.isInitialState()) {
				result += "\nimport " + this.concreteStatesPackage + "."
						+ cs.getClassName() + ";";
			}
		}
		
		result += "\n";
		
		return result;
	}
	
	public String getAllImports () {
		String result = "";
		
		result += "\nimport " + this.generalStatePackage + "."
				+ this.generalStateClassName + ";";
		result += "\n";
		result += "\nimport " + this.stateContextPackage + "."
				+ this.stateContextClassName + ";";
		result += "\n";
		
		for (ConcreteState cs : this.concreteStates) {
			result += "\nimport " + this.concreteStatesPackage + "."
					+ cs.getClassName() + ";";
		}
		
		result += "\n";
		
		return result;
	}
	
	@Override
	public List<PatternTest> getPatternTests () {
		List<PatternTest> tests = new ArrayList<PatternTest>();
		
		tests.add(new StatePatternTestsPFE1(this));
		tests.add(new StatePatternTestsPFE2(this));
		tests.add(new StatePatternTestsPNFE1(this));
		
		return tests;
	}
	
	
	/**
	 * Generates all the transition method call
	 * 
	 * @return
	 */
	public List<Transition> getDiferentTransitions () {
		List<Transition> transitions = new ArrayList<Transition>();
		List<Transition> result = new ArrayList<Transition>();
		
		// go throw all the transitions and put together the ones with diferent
		// action or parameters
		for (ConcreteState cs : this.getConcreteStates()) {
			for (Transition t : cs.getTransitions()) {
				
				boolean used = false;
				for (Transition t2 : transitions) {
					if (t.getExecution().compareTo(t2.getExecution()) == 0) {
						used = true;
						break;
					}
				}
				
				if (!used)
					transitions.add(t);
			}
		}
		
		// Order the transitions by actions
		for (String action : getActionNames()) {
			for (Transition t : transitions) {
				if (action.compareTo(t.getActionName()) == 0)
					result.add(t);
			}
		}
		
		return result;
	}
	
	
	public List<ConcreteState> getFinalStates () {
		
		List<ConcreteState> result = new ArrayList<ConcreteState>();
		
		for (ConcreteState cs : this.concreteStates) {
			if (cs.isFinalState())
				result.add(cs);
		}
		
		return result;
	}
	
	
	/*
	 * private StateHeader executeActionSecuence (List<Integer> transitonOrder){
	 * 
	 * StateContext context = new State1Context (8080, "pruebas");
	 * 
	 * // If the context doesn't begin in the initial state if
	 * (!context.getState().equals(new StateConcrete1 ())) return false;
	 * 
	 * // Execute the transitions in the defined order for (int i:
	 * transitonOrder) { switch (i) { case 1:
	 * context.getState().action1(context, true, "transicion"); break; case 2:
	 * context.getState().action1(context, false, "transicion"); break; case 3:
	 * context.getState().action2(context); break; default: return false; } }
	 * 
	 * // Conpare the final state obtained with the final states defined
	 * StateHeader state = context.getState(); return
	 * (getFinalStatesInstances().contains(state));
	 * 
	 * }
	 * 
	 * // Function that return a list with an instance of each final state
	 * private List<StateHeader> getFinalStatesInstances() { ... }
	 */
	
	public String getTransitionExecuttorFunction () {
		String result = "";
		
		result += "\n\n\tprivate "
				+ this.generalStateClassName
				+ " executeActionSecuence (List<Integer> transitonOrder) throws Exception {";
		
		result += "\n\t\t";
		result += "\n\t\t" + this.getStateContextClassName() + " context = "
				+ getStateContextBuilder() + ";";
		
		ConcreteState aux = null;
		for (ConcreteState cs : getConcreteStates())
			if (cs.isInitialState()) {
				aux = cs;
				break;
			}
		
		result += "\n\t\t";
		result += "\n\t\t// If the context doesn't begin in the initial state";
		result += "\n\t\tString initialStateClassName = context.getState().getClass().getCanonicalName();";
		result += "\n\t\tif (initialStateClassName.compareTo(\""
				+ concreteStatesPackage + "." + aux.getClassName()
				+ "\") != 0) " + "\n\t\t\treturn null;";
		
		result += "\n\t\t";
		result += "\n\t\t// Execute the transitions in the defined order";
		result += "\n\t\tfor (int i: transitonOrder) {";
		result += "\n\t\t\tswitch (i) {";
		
		int i = 1;
		String action = "";
		for (Transition t : getDiferentTransitions()) {
			
			if (action.compareTo(t.getActionName()) != 0) {
				action = t.getActionName();
				
				result += "\n\n\n\t\t\t// Action: " + action;
			}
			
			result += "\n\n\t\t\t\tcase " + String.valueOf(i).trim() + ":";
			result += "\n\t\t\t\t\tcontext.getState()." + t.getExecution()
					+ ";";
			result += "\n\t\t\t\t\tbreak;";
			
			i++;
		}
		
		result += "\n\t\t\t\tdefault:\n\t\t\t\tthrow new Exception ();";
		
		result += "\n\t\t\t}";
		result += "\n\t\t}";
		
		
		result += "\n\n\n\t\treturn context.getState();";
		
		result += "\n\t}";
		result += "\n";
		
		return result;
	}
	
	public String getFinalStateCanonicalNameFunction () {
		String result = "";
		
		result += "\n\tprivate List<String> getFinalStatesCanonicalNames () {";
		result += "\n\n\t\tList<String> result = new ArrayList<String> ();";
		result += "\n\n";
		
		for (ConcreteState cs : getFinalStates()) {
			result += "\n\t\tresult.add(\"" + concreteStatesPackage + "."
					+ cs.getClassName() + "\");";
		}
		
		result += "\n\n\t\treturn result;";
		result += "\n\t}";
		
		return result;
	}
}
