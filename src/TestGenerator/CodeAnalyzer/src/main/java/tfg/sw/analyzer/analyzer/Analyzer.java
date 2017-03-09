package tfg.sw.analyzer.analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.transform.Result;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tfg.sw.analyzer.exception.ParserException;
import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.pattern.Pattern;
import tfg.sw.analyzer.pattern.PatternWriter;
import tfg.sw.analyzer.util.SystemUtil;


public class Analyzer {
	
	public static void main (String[] args) throws SistemUtilException {
		
		// If the parameters are not correct, error message and finish. if
		if (args.length != 2) {
			String errorMessage = SystemUtil
					.getMessageById("main_incorrect_params");
			System.out.println(errorMessage);
			return;
		}
		
		try {
			execute(args[0], args[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void execute (String root, String destiny)
			throws ParserException, TransformerException,
			ParserConfigurationException, FileNotFoundException,
			SistemUtilException {
		
		List<File> files;
		
		files = Crowler.recursiveCrowling(root);
		
		List<Pattern> patterns = Parser.parseFileList(files);
		
		Document document = getXMLDocumentOfPatterns(patterns);
		
		writeXMLToFile(document, destiny);
		
	}
	
	private static Document getXMLDocumentOfPatterns (List<Pattern> patterns)
			throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		// root elements
		Document document = docBuilder.newDocument();
		Element rootElement = document.createElement("patterns");
		document.appendChild(rootElement);
		Element patternElement;
		PatternWriter writter;
		
		if (patterns.size() == 0)
			rootElement.appendChild(document.createTextNode(""));
		
		
		for (Pattern p : patterns) {
			writter = p.getWriter();
			
			patternElement = writter.patternToDomElement(p, document);
			
			rootElement.appendChild(patternElement);
		}
		
		return document;
	}
	
	private static void writeXMLToFile (Document document, String destinyPath)
			throws TransformerException {
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "no");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "4");
		Source source = new DOMSource(document);
		File file = new File(destinyPath);
		file.getParentFile().mkdirs();
		Result  result = new StreamResult (file);
		
		transformer.transform(source, result);
	}
}
