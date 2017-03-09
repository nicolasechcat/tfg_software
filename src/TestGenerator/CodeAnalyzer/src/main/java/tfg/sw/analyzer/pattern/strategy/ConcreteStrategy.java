package tfg.sw.analyzer.pattern.strategy;

public class ConcreteStrategy {
	
	private String className;
	private String builder;
	
	public ConcreteStrategy (String className, String builder) {
		super();
		this.className = className;
		this.builder = builder;
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
		ConcreteStrategy other = (ConcreteStrategy) obj;
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
		return true;
	}
	
	
	
	@Override
	public String toString () {
		return "ConcreteStrategy [className=" + className + ", builder="
				+ builder + "]";
	}
	
	
	
	
}
