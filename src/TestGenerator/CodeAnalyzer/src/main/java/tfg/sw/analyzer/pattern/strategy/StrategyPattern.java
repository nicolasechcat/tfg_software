package tfg.sw.analyzer.pattern.strategy;

import java.util.List;

import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternParser;
import tfg.sw.analyzer.pattern.PatternWriter;
import tfg.sw.analyzer.util.SystemUtil;

public class StrategyPattern extends Pattern {
	
	private String genericStrategyPackage;
	private String className;
	
	private List<StrategyAction> actions;
	
	private String concreteStrategyPackage;
	private List<ConcreteStrategy> concreteStrategies;
	
	public StrategyPattern (String patternId, String genericStrategyPackage,
			String className, List<StrategyAction> actions,
			String concreteStrategyPackage,
			List<ConcreteStrategy> concreteStrategies) {
		super();
		super.patternId = patternId;
		this.genericStrategyPackage = genericStrategyPackage;
		this.className = className;
		this.concreteStrategyPackage = concreteStrategyPackage;
		this.actions = actions;
		this.concreteStrategies = concreteStrategies;
	}
	
	
	
	public List<StrategyAction> getActions () {
		return actions;
	}
	
	public void setActions (List<StrategyAction> actions) {
		this.actions = actions;
	}
	
	public List<ConcreteStrategy> getConcreteStrategies () {
		return concreteStrategies;
	}
	
	public void setConcreteStrategies (List<ConcreteStrategy> concreteStrategies) {
		this.concreteStrategies = concreteStrategies;
	}
	
	public String getGenericStrategyPackage () {
		return genericStrategyPackage;
	}
	
	public void setGenericStrategyPackage (String genericStrategyPackage) {
		this.genericStrategyPackage = genericStrategyPackage;
	}
	
	public String getClassName () {
		return className;
	}
	
	public void setClassName (String className) {
		this.className = className;
	}
	
	public String getConcreteStrategyPackage () {
		return concreteStrategyPackage;
	}
	
	public void setConcreteStrategyPackage (String concreteStrategyPackage) {
		this.concreteStrategyPackage = concreteStrategyPackage;
	}
	
	@Override
	public PatternWriter getWriter () {
		return StrategyPatternWritter.getInstance();
	}
	
	@Override
	public String validate () throws SistemUtilException {
		
		if (this.concreteStrategies.size() < 2)
			return SystemUtil
					.getMessageById("strategy_validation_only_one_concrete_strategy");
		
		return "";
	}
	
	public static PatternParser getParser () {
		return new StrategyPatternParser();
	}
	
	
	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actions == null) ? 0 : actions.hashCode());
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime
				* result
				+ ((concreteStrategies == null) ? 0 : concreteStrategies
						.hashCode());
		result = prime
				* result
				+ ((concreteStrategyPackage == null) ? 0
						: concreteStrategyPackage.hashCode());
		result = prime
				* result
				+ ((genericStrategyPackage == null) ? 0
						: genericStrategyPackage.hashCode());
		return result;
	}
	
	
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrategyPattern other = (StrategyPattern) obj;
		
		
		if (actions == null) {
			if (other.actions != null)
				return false;
		}
		
		if (actions.size() != other.actions.size())
			return false;
		
		for (StrategyAction sa : other.actions)
			if (!actions.contains(sa))
				return false;
		
		
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		
		
		if (concreteStrategies == null) {
			if (other.concreteStrategies != null)
				return false;
		}
		
		if (concreteStrategies.size() != other.concreteStrategies.size())
			return false;
		
		for (ConcreteStrategy cs : other.concreteStrategies)
			if (!concreteStrategies.contains(cs))
				return false;
		
		if (concreteStrategyPackage == null) {
			if (other.concreteStrategyPackage != null)
				return false;
		} else if (!concreteStrategyPackage
				.equals(other.concreteStrategyPackage))
			return false;
		if (genericStrategyPackage == null) {
			if (other.genericStrategyPackage != null)
				return false;
		} else if (!genericStrategyPackage.equals(other.genericStrategyPackage))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString () {
		return "\n\nStrategyPattern [patternId=" + super.patternId
				+ ", genericStrategyPackage=" + genericStrategyPackage
				+ ", className=" + className + ", actions=" + actions
				+ ", concreteStrategyPackage=" + concreteStrategyPackage
				+ ", concreteStrategies=" + concreteStrategies + "]\n\n";
	}
	
	
	
	
	
}
