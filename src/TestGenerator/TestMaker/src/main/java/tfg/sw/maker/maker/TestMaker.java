package tfg.sw.maker.maker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import tfg.sw.maker.exception.PatternBuilderException;
import tfg.sw.maker.exception.SistemUtilException;
import tfg.sw.maker.pattern.Builder;
import tfg.sw.maker.pattern.Pattern;
import tfg.sw.maker.util.SystemUtil;

public class TestMaker {

	public static void main(String[] args) throws SistemUtilException {
		// If the parameters are not correct, error message and finish. if
		if (args.length != 3) {
			String errorMessage = SystemUtil
					.getMessageById("main_incorrect_params");
			System.out.println(errorMessage);
			return;
		}

		try {
			execute(args[0], args[1], args[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void execute(String definitionFile, String destinyPath,
			String destinyPackage) throws FileNotFoundException, SAXException,
			IOException, ParserConfigurationException, PatternBuilderException {

		// getting theXML
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new File(definitionFile));

		// document.getDocumentElement().normalize();

		// Parsing the XML
		List<Pattern> patterns = Builder.getPatternFromXML(document);

		// Generation the tests
		List<PatternTest> tests;
		List<String> resultantTests = new ArrayList<String>();
		List<String> resultantTestsFileNames = new ArrayList<String>();

		for (Pattern p : patterns) {
			tests = p.getPatternTests();

			for (PatternTest pt : tests) {
				resultantTests.add(pt.getTests(destinyPackage));
				resultantTestsFileNames.add(pt.getTestsFileName());
			}
		}

		// Writing the tests
		for (int i = 0; i < resultantTests.size(); i++) {
			String filePath = destinyPath + "\\"
					+ resultantTestsFileNames.get(i) + ".java";

			File file = new File(filePath);

			// Make sure to have a new empty file
			if (file.exists())
				file.delete();

			file.getParentFile().mkdirs();

			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			out.write(resultantTests.get(i));
			out.close();

			// PrintWriter writer = new PrintWriter(file, "UTF-8");
			// writer.write(resultantTests.get(i));
			// writer.close();
		}

	}
}
