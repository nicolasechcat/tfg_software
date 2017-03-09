package tfg.sw.maker.pattern;

import java.util.List;

import tfg.sw.maker.maker.PatternTest;



public abstract class Pattern {
	
	
	protected String patternId;
	
	public String getPatternId () {
		return patternId;
	}
	
	public void setPatternId (String patternId) {
		this.patternId = patternId;
	}
	
	@Override
	public boolean equals (Object obj) {
		
		if (obj == null)
			return false;
		
		if (obj.getClass() != this.getClass())
			return false;
		
		Pattern aux = (Pattern) obj;
		
		if (aux.getPatternId().compareTo(this.patternId) != 0)
			return false;
		
		return true;
	}
	
	public abstract String getAllImports ();
	
	public abstract String getMinImports ();
	
	public abstract List<PatternTest> getPatternTests ();
	
}
