package tfg.sw.analyzer.pattern.strategy;

import java.util.List;

public class StrategyAction {
	
	private String returnType;
	private String returnTypePackage;
	private String name;
	
	private List<String> parameters;
	private Comparison comparison;
	
	public StrategyAction (String returnType, String returnTypePackage,
			String name, List<String> parameters, Comparison comparison) {
		super();
		this.returnType = returnType;
		this.returnTypePackage = returnTypePackage;
		this.name = name;
		this.parameters = parameters;
		this.comparison = comparison;
	}
	
	
	
	public String getReturnType () {
		return returnType;
	}
	
	
	
	public String getReturnTypePackage () {
		return returnTypePackage;
	}
	
	
	
	public void setReturnTypePackage (String returnTypePackage) {
		this.returnTypePackage = returnTypePackage;
	}
	
	
	public void setReturnType (String returnType) {
		this.returnType = returnType;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public List<String> getParameters () {
		return parameters;
	}
	
	public void setParameters (List<String> parameters) {
		this.parameters = parameters;
	}
	
	public Comparison getComparison () {
		return comparison;
	}
	
	public void setComparison (Comparison comparison) {
		this.comparison = comparison;
	}
	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comparison == null) ? 0 : comparison.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((returnType == null) ? 0 : returnType.hashCode());
		result = prime
				* result
				+ ((returnTypePackage == null) ? 0 : returnTypePackage
						.hashCode());
		return result;
	}
	
	
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrategyAction other = (StrategyAction) obj;
		if (comparison == null) {
			if (other.comparison != null)
				return false;
		} else if (!comparison.equals(other.comparison))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		}
		
		if (parameters.size() != other.parameters.size())
			return false;
		
		for (String ps : other.parameters)
			if (!parameters.contains(ps))
				return false;
		
		
		if (returnType == null) {
			if (other.returnType != null)
				return false;
		} else if (!returnType.equals(other.returnType))
			return false;
		
		if (returnTypePackage == null) {
			if (other.returnTypePackage != null)
				return false;
		} else if (!returnTypePackage.equals(other.returnTypePackage))
			return false;
		
		return true;
	}
	
	
	
	@Override
	public String toString () {
		return "StrategyAction [returnType=" + returnType
				+ ", returnTypePackage=" + returnTypePackage + ", name=" + name
				+ ", parametersSet=" + parameters + ", comparison="
				+ comparison + "]";
	}
	
	
	
	
}
