package tfg.sw.maker.pattern.state;

import java.util.ArrayList;
import java.util.List;

public class ConcreteState {
	
	private String className;
	private List<Transition> transitions;
	private String builderInvocation;
	private boolean initialState;
	private boolean finalState;
	
	
	public ConcreteState (String className, List<Transition> transitions,
			String builderInvocation, boolean initialState, boolean finalState) {
		super();
		this.className = className;
		this.transitions = transitions;
		this.builderInvocation = builderInvocation;
		this.initialState = initialState;
		this.finalState = finalState;
	}
	
	
	
	public ConcreteState () {
		super();
		
		this.className = "";
		this.transitions = new ArrayList<Transition>();
		this.builderInvocation = "";
		this.initialState = false;
		this.finalState = false;
	}
	
	
	
	public String getClassName () {
		return className;
	}
	
	public void setClassName (String className) {
		this.className = className;
	}
	
	public List<Transition> getTransitions () {
		return transitions;
	}
	
	public void setTransitions (List<Transition> transitions) {
		this.transitions = transitions;
	}
	
	public String getBuilderInvocation () {
		
		// if (builderInvocation != null && !builderInvocation.isEmpty())
		return builderInvocation;
		
		// return "new " + this.getClassName() + " ()";
	}
	
	public void setBuilderInvocation (String builderInvocation) {
		this.builderInvocation = builderInvocation;
	}
	
	public boolean isInitialState () {
		return initialState;
	}
	
	public void setInitialState (boolean initialState) {
		this.initialState = initialState;
	}
	
	public boolean isFinalState () {
		return finalState;
	}
	
	public void setFinalState (boolean finalState) {
		this.finalState = finalState;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		ConcreteState other = (ConcreteState) obj;
		
		if (this.className.compareTo(other.className) != 0)
			return false;
		
		if (this.transitions.size() != other.transitions.size())
			return false;
		
		for (Transition t : other.transitions)
			if (!this.transitions.contains(t))
				return false;
		
		if (this.initialState != other.initialState)
			return false;
		
		if (this.finalState != other.finalState)
			return false;
		
		return true;
	}
	
	@Override
	public String toString () {
		return "ConcreteState [className=" + className + "\n\n\ttransitions="
				+ transitions + "\n\tbuilderInvocation=" + builderInvocation
				+ "\n\tinitialState=" + initialState + "\n\tfinalState="
				+ finalState + "]";
	}
	
	
	
}
