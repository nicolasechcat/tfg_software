package tfg.sw.analyzer.pattern.strategy.comparison;

import tfg.sw.analyzer.pattern.strategy.Comparison;

public class ByComparator implements Comparison {
	
	private String packageValue;
	private String className;
	private String builder;
	
	public ByComparator () {
		super();
		this.packageValue = "";
		this.className = "";
		this.builder = "";
	}
	
	public ByComparator (String packageValue, String className, String builder) {
		super();
		this.packageValue = packageValue;
		this.className = className;
		this.builder = builder;
	}
	
	
	public String getPackageValue () {
		return packageValue;
	}
	
	public void setPackageValue (String packageValue) {
		this.packageValue = packageValue;
	}
	
	public String getClassName () {
		return className;
	}
	
	public void setClassName (String className) {
		this.className = className;
	}
	
	public String getBuilder () {
		return builder;
	}
	
	public void setBuilder (String builder) {
		this.builder = builder;
	}
	
	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((builder == null) ? 0 : builder.hashCode());
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ ((packageValue == null) ? 0 : packageValue.hashCode());
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
		ByComparator other = (ByComparator) obj;
		if (builder == null) {
			if (other.builder != null)
				return false;
		} else if (!builder.equals(other.builder))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (packageValue == null) {
			if (other.packageValue != null)
				return false;
		} else if (!packageValue.equals(other.packageValue))
			return false;
		return true;
	}
	
	@Override
	public String toString () {
		return "ByComparator [packageValue=" + packageValue + ", className="
				+ className + ", builder=" + builder + "]";
	}
	
	
	
	
}
