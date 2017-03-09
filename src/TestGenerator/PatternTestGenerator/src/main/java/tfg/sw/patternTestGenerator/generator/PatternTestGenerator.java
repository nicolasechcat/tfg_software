package tfg.sw.patternTestGenerator.generator;

import tfg.sw.analyzer.analyzer.Analyzer;
import tfg.sw.maker.maker.TestMaker;
import tfg.sw.patternTestGenerator.exception.SistemUtilException;
import tfg.sw.patternTestGenerator.util.SistemUtil;

public class PatternTestGenerator {

	private static final String tempPath = "D:\\Grado\\TFG\\SW\\Code\\TemporalFiles\\Definition.xml";

	public static void main(String[] args) throws SistemUtilException {
		// If the parameters are not correct, error message and finish. if
		if (args.length != 3) {
			String errorMessage = SistemUtil
					.getMessageById("main_incorrect_params");
			System.out.println(errorMessage);
			return;
		}

		try {
			execute(args[0], args[1], args[2]);

			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void execute(String sourceCodeOriginPath,
			String sourceCodeResultPath, String resultPackage) throws Exception {

		Analyzer.execute(sourceCodeOriginPath, tempPath);
		TestMaker.execute(tempPath, sourceCodeResultPath, resultPackage);

	}
}
