package tfg.sw.maker.pattern.strategy;

import java.util.ArrayList;
import java.util.List;

import tfg.sw.maker.maker.PatternTest;
import tfg.sw.maker.maker.strategy.StrategyPatternTestsPFEST1;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.pattern.strategy.comparison.ByComparator;

public class StrategyPattern extends Pattern {

	private String genericStrategyPackage;
	private String className;

	private List<StrategyAction> actions;

	private String concreteStrategyPackage;
	private List<ConcreteStrategy> concreteStrategies;

	public StrategyPattern() {
		super();

		super.patternId = "";
		this.genericStrategyPackage = "";
		this.className = "";
		this.concreteStrategyPackage = "";
		this.actions = new ArrayList<StrategyAction>();
		this.concreteStrategies = new ArrayList<ConcreteStrategy>();
	}

	public StrategyPattern(String patternId, String genericStrategyPackage,
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

	public void fillCompleteInformation() {
		for (ConcreteStrategy cs : concreteStrategies)
			if (cs.getBuilder() == null || cs.getBuilder().isEmpty())
				cs.setBuilder("new " + cs.getClassName() + " ()");

		for (StrategyAction sa : actions) {
			if (sa.getComparison().getClass() == ByComparator.class) {
				ByComparator comp = (ByComparator) sa.getComparison();

				if (!comp.getClassName().isEmpty())
					if (comp.getBuilder().isEmpty())
						comp.setBuilder("new " + comp.getClassName() + " ()");
			}

			if (sa.getParameters().size() == 0)
				sa.getParameters().add("");
		}
	}

	public List<StrategyAction> getActions() {
		return actions;
	}

	public void setActions(List<StrategyAction> actions) {
		this.actions = actions;
	}

	public List<ConcreteStrategy> getConcreteStrategies() {
		return concreteStrategies;
	}

	public void setConcreteStrategies(List<ConcreteStrategy> concreteStrategies) {
		this.concreteStrategies = concreteStrategies;
	}

	public String getGenericStrategyPackage() {
		return genericStrategyPackage;
	}

	public void setGenericStrategyPackage(String genericStrategyPackage) {
		this.genericStrategyPackage = genericStrategyPackage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getConcreteStrategyPackage() {
		return concreteStrategyPackage;
	}

	public void setConcreteStrategyPackage(String concreteStrategyPackage) {
		this.concreteStrategyPackage = concreteStrategyPackage;
	}

	@Override
	public int hashCode() {
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
	public boolean equals(Object obj) {
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
	public String toString() {
		return "\n\nStrategyPattern [patternId=" + super.patternId
				+ ", genericStrategyPackage=" + genericStrategyPackage
				+ ", className=" + className + ", actions=" + actions
				+ ", concreteStrategyPackage=" + concreteStrategyPackage
				+ ", concreteStrategies=" + concreteStrategies + "]\n\n";
	}

	@Override
	public String getAllImports() {
		String result = "";

		List<String> imported = new ArrayList<String>();
		String aux = "";

		aux = "\nimport " + this.genericStrategyPackage + "." + this.className
				+ ";";

		if (!imported.contains(aux)) {
			result += aux + "\n";
			imported.add(aux);
		}

		for (ConcreteStrategy cs : this.concreteStrategies) {
			aux = "\nimport " + this.concreteStrategyPackage + "."
					+ cs.getClassName() + ";";

			if (!imported.contains(aux)) {
				result += aux;
				imported.add(aux);
			}

		}

		result += "\n";

		for (StrategyAction sa : this.actions) {
			if (!sa.getReturnTypePackage().isEmpty()) {
				aux = "\nimport " + sa.getReturnTypePackage() + ";";

				if (!imported.contains(aux)) {
					result += aux;
					imported.add(aux);
				}

			}
		}

		result += "\n";

		for (StrategyAction sa : this.actions) {

			if (sa.getComparison().getClass() == ByComparator.class) {
				ByComparator comp = (ByComparator) sa.getComparison();
				if (!comp.getClassName().isEmpty()) {
					aux = "\nimport " + comp.getPackageValue() + "."
							+ comp.getClassName() + ";";

					if (!imported.contains(aux)) {
						result += aux;
						imported.add(aux);
					}
				}
			}

			if (!sa.getReturnTypePackage().isEmpty()) {
				aux = "\nimport " + sa.getReturnTypePackage() + ";";

				if (!imported.contains(aux)) {
					result += aux + "\n";
					imported.add(aux);
				}
			}
		}

		result += "\n";

		return result;
	}

	@Override
	public String getMinImports() {
		String result = "";

		result += "\nimport " + this.genericStrategyPackage + "."
				+ this.className + ";";
		result += "\n";

		for (ConcreteStrategy cs : this.concreteStrategies) {
			result += "\nimport " + this.concreteStrategyPackage + "."
					+ cs.getClassName() + ";";
		}

		result += "\n";

		return result;
	}

	@Override
	public List<PatternTest> getPatternTests() {
		List<PatternTest> tests = new ArrayList<PatternTest>();

		tests.add(new StrategyPatternTestsPFEST1(this));

		return tests;
	}

}
