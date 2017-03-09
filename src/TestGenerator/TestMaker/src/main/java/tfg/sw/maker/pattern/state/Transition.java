package tfg.sw.maker.pattern.state;

import java.util.ArrayList;
import java.util.List;

public class Transition {
	
	private List<Parameter> parameters;
	private String actionName;
	private String destinyStateName;
	private ConcreteState destinyState;
	
	public Transition (String destinyStateName, String actionName,
			List<Parameter> parameters) {
		super();
		this.parameters = parameters;
		this.actionName = actionName;
		this.destinyStateName = destinyStateName;
		this.destinyState = null;
	}
	
	
	
	public Transition () {
		super();
		
		this.parameters = new ArrayList<Parameter>();
		this.actionName = "";
		this.destinyStateName = "";
		this.destinyState = null;
	}
	
	
	
	public Transition (String destinyStateName, String actionName,
			List<Parameter> parameters, ConcreteState destinyState) {
		super();
		this.parameters = parameters;
		this.actionName = actionName;
		this.destinyStateName = destinyStateName;
		this.destinyState = destinyState;
	}
	
	
	
	public ConcreteState getDestinyState () {
		return destinyState;
	}
	
	public void setDestinyState (ConcreteState destinyState) {
		this.destinyState = destinyState;
	}
	
	public String getParametersAsString () {
		
		String result = "(";
		
		if (this.parameters.size() == 0) {
			result += "context)";
		} else {
			for (Parameter p : parameters) {
				result += p.getValue() + ", ";
			}
			
			result = result.trim();
			result = result.substring(0, result.length() - 1) + ")";
		}
		
		
		return result;
		
	}
	
	public String getExecution () {
		return this.actionName + this.getParametersAsString();
	}
	
	public List<Parameter> getParameters () {
		return parameters;
	}
	
	public void setParameters (List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public String getActionName () {
		return actionName;
	}
	
	public void setActionName (String actionName) {
		this.actionName = actionName;
	}
	
	public String getDestinyStateName () {
		return destinyStateName;
	}
	
	public void setDestinyStateName (String destinyStateName) {
		this.destinyStateName = destinyStateName;
	}
	
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		Transition other = (Transition) obj;
		
		if (this.actionName.compareTo(other.actionName) != 0)
			return false;
		
		if (this.destinyStateName.compareTo(other.destinyStateName) != 0)
			return false;
		
		if (this.parameters == null || other.parameters == null)
			if (!(this.parameters == null && other.parameters == null))
				return false;
		
		if (this.parameters.size() != other.parameters.size())
			return false;
		
		for (Parameter p : other.parameters)
			if (!this.parameters.contains(p))
				return false;
		
		return true;
	}
	
	@Override
	public String toString () {
		return "Transition [parameters=" + parameters + "\n\n\tactionName="
				+ actionName + "\n\tdestinyStateName=" + destinyStateName + "]";
	}
	
	
}
