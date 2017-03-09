package tfg.sw.analyzer.pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public interface PatternWriter {
	
	public Element patternToDomElement (Pattern pattern, Document doc);
	
}
