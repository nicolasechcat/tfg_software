package tfg.sw.analyzer.pattern.state;

import java.util.ArrayList;
import java.util.List;

import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternParser;
import tfg.sw.analyzer.pattern.PatternWriter;
import tfg.sw.analyzer.util.SystemUtil;

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
		this.patternId = patternId;
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
	
	public static PatternParser getParser () {
		return new StatePatternParser();
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
	
	@Override
	public PatternWriter getWriter () {
		return StatePatternWritter.getInstance();
	}
	
	/**
	 * 1- All the transitions are defined within valid actions.
	 * 
	 * 2- All the transitions have a valid concrete state as destiny.
	 * 
	 * 3- Exists none or more than one initial state.
	 * 
	 * 4- There are non unreachable states
	 * 
	 * @throws SistemUtilException
	 */
	@Override
	public String validate () throws SistemUtilException {
		
		String result = "";
		ConcreteState initialState = null;
		
		for (ConcreteState cs : this.concreteStates) {
			
			List<Transition> processedTransitions = new ArrayList<Transition>();
			
			for (Transition t : cs.getTransitions()) {
				
				// Confirm transition actions
				
				if (!this.actionNames.contains(t.getActionName()))
					result += "\n\t- "
							+ String.format(
									SystemUtil
											.getMessageById("state_validation_transition_no_action"),
									cs.getClassName(), t.getActionName());
				
				
				// Confirm transition destinies
				
				boolean correctDestiny = false;
				for (ConcreteState cs2 : this.concreteStates) {
					if (t.getDestinyStateName().compareTo(cs2.getClassName()) == 0)
						correctDestiny = true;
				}
				if (!correctDestiny)
					result += "\n\t- "
							+ String.format(
									SystemUtil
											.getMessageById("state_validation_transition_no_destiny"),
									cs.getClassName(), t.getDestinyStateName());
				
				
				// Confirm no multiple transition equals
				if (processedTransitions.contains(t)) {
					result += "\n\t- "
							+ String.format(
									SystemUtil
											.getMessageById("state_validation_transition_repited"),
									cs.getClassName());
				}
				
				processedTransitions.add(t);
			}
			
			// Look for an initial state
			if (cs.isInitialState()) {
				if (initialState != null) {
					result += "\n\t- "
							+ String.format(
									SystemUtil
											.getMessageById("state_validation_multiple_initial_states"),
									initialState.getClassName(), cs
											.getClassName());
				} else {
					initialState = cs;
				}
			}
		}
		
		if (initialState == null) {
			result += "\n\t- "
					+ String.format(
							SystemUtil
									.getMessageById("state_validation_transition_no_initial_state"),
							this.patternId);
		}
		
		
		// To look for unreachable states:
		// 1- start with an empty list
		// 2- insert in the list the initial state
		// 3- for each transition of the initial state, insert the destiny in
		// the list.
		// 4- repeat 3 with each new added state.
		// 5- All the states that are not in the list, are unreachable states.
		
		List<ConcreteState> unReachableStates = new ArrayList<ConcreteState>();
		List<ConcreteState> notProcessedYet = new ArrayList<ConcreteState>();
		
		for (ConcreteState cs : this.getConcreteStates()) {
			unReachableStates.add(cs);
		}
		
		if (initialState != null) {
			notProcessedYet.add(initialState);
			unReachableStates.remove(initialState);
		} else {
			notProcessedYet.add(unReachableStates.get(0));
			unReachableStates.remove(0);
		}
		
		while (!notProcessedYet.isEmpty()) {
			
			// Take out the first state not processed yet
			ConcreteState cs = notProcessedYet.get(0);
			notProcessedYet.remove(0);
			
			// For each transition of it, if the destiny is still in
			// unreachable, we take it out and insert it in notProcessedYet
			for (Transition t : cs.getTransitions()) {
				ConcreteState aux = null;
				for (ConcreteState cs2 : unReachableStates) {
					if (cs2.getClassName().compareTo(t.getDestinyStateName()) == 0) {
						aux = cs2;
						break;
					}
				}
				if (aux != null) {
					notProcessedYet.add(aux);
					unReachableStates.remove(aux);
				}
			}
			
		}
		
		// After this loop, all the states in unReachableStates are unreachable
		// from the initial state with the defined transitions
		if (!unReachableStates.isEmpty()) {
			result += "\n\t- "
					+ SystemUtil
							.getMessageById("state_validation_unreachable_states");
			
			for (ConcreteState cs : unReachableStates) {
				result += cs.getClassName() + ", ";
			}
			
			result = result.trim();
			result = result.trim().substring(0, result.length() - 1);
		}
		
		return result;
	}
}
