package tfg.sw.maker.pattern.state;


public class Parameter {
	
	private String value;
	private int order;
	
	
	
	public Parameter (String value, int order) {
		super();
		this.value = value;
		this.order = order;
	}
	
	
	
	public Parameter () {
		super();
		this.value = "";
		this.order = -1;
	}
	
	
	
	public String getValue () {
		return value;
	}
	
	public void setValue (String value) {
		this.value = value;
	}
	
	public int getOrder () {
		return order;
	}
	
	public void setOrder (int order) {
		this.order = order;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		Parameter other = (Parameter) obj;
		
		if (this.value.compareTo(other.value) != 0)
			return false;
		
		if (this.order != other.order)
			return false;
		
		
		return true;
	}
	
	@Override
	public String toString () {
		return "Parameter [value=" + value + "\torder=" + order + "]";
	}
	
	
}
