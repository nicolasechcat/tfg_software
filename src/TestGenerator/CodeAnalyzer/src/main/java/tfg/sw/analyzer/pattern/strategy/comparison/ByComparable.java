package tfg.sw.analyzer.pattern.strategy.comparison;

import tfg.sw.analyzer.pattern.strategy.Comparison;

public class ByComparable implements Comparison {
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return true;
	}
	
	@Override
	public String toString () {
		return "ByComparable []";
	}
	
	
}
