package tfg.sw.analyzer.pattern;

import tfg.sw.analyzer.exception.SistemUtilException;


public abstract class Pattern {
	
	
	
	protected String patternId;
	
	public abstract PatternWriter getWriter ();
	
	public abstract String validate () throws SistemUtilException;
	
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
		
		if (aux.getPatternId() == null && this.patternId == null)
			return true;
		
		String id1 = this.patternId.trim();
		String id2 = aux.getPatternId().trim();
		
		return id1.equals(id2);
		
	}
	
}
