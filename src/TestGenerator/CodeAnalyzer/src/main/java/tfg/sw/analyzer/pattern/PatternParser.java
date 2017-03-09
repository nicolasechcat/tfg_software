package tfg.sw.analyzer.pattern;

import tfg.sw.analyzer.analyzer.PatternParsedFiles;
import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;


public interface PatternParser {
	
	/**
	 * 
	 * @param info
	 * @return
	 * @throws ParserException
	 * @throws SistemUtilException
	 */
	public Pattern parse (PatternParsedFiles info) throws ParserException,
			SistemUtilException;
	
}
