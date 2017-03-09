package tfg.sw.maker.pattern;

import org.w3c.dom.Element;

import tfg.sw.maker.exception.PatternBuilderException;

public interface PatternBuilder {
	
	
	public Pattern getPatternFromXML (Element rootElement)
			throws PatternBuilderException;
	
}
