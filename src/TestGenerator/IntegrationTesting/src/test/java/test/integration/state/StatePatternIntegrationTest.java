package test.integration.state;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import tfg.sw.patternTestGenerator.generator.PatternTestGenerator;

public class StatePatternIntegrationTest {

	private String examplesProyectPath = "..\\PatternImplementationExamples";
	private String sourcePath = "..\\PatternImplementationExamples\\src\\main\\java\\tfg\\sw\\implementationExample\\pattern";
	private String resultPath = "..\\PatternImplementationExamples\\src\\test\\java\\tfg\\sw\\test\\integration\\pattern";
	private String resultPackage = "tfg.sw.test.integration.pattern";

	private String allExpectedErrorCount = "Tests run: 84, Failures: 7, Errors: 9, Skipped: 0";
	private String stateExpectedErrorCount = "Tests run: 66, Failures: 5, Errors: 6, Skipped: 0";
	private String strategyExpectedErrorCount = "Tests run: 18, Failures: 2, Errors: 3, Skipped: 0";

	private String stateExpectedFailureMessage1 = "orderDependencyTest(tfg.sw.test.integration.pattern.ExampleNotOrderDependentWrongStatePatternOrderDependentTest)";
	private String stateExpectedFailureMessage2 = "orderDependencyTest(tfg.sw.test.integration.pattern.ExampleOrederDependentWrongStatePatternOrderDependentTest)";
	private String stateExpectedFailureMessage3 = "test0_Transition_State1_action1_State2(tfg.sw.test.integration.pattern.ExampleWrongTransitionsStatePatternTransitionsTest)";
	private String stateExpectedFailureMessage4 = "test4_Transition_State2_action2_State3(tfg.sw.test.integration.pattern.ExampleWrongTransitionsStatePatternTransitionsTest)";
	private String stateExpectedFailureMessage5 = "test1_Transition_State1_action2_State1(tfg.sw.test.integration.pattern.ExampleWrongTransitionsStatePatternTransitionsTest)";

	private String stateExpectedErrorMessage1 = "testTransitionSecuenceToFinalState(tfg.sw.test.integration.pattern.ExampleCorrectStatePatternAceptedTransitionSecuenceTest): The user must finish this test. This exception must be removed after that.";
	private String stateExpectedErrorMessage2 = "testTransitionSecuenceToFinalState(tfg.sw.test.integration.pattern.ExampleNotOrderDependentCorrectStatePatternAceptedTransitionSecuenceTest): The user must finish this test. This exception must be removed after that.";
	private String stateExpectedErrorMessage3 = "testTransitionSecuenceToFinalState(tfg.sw.test.integration.pattern.ExampleNotOrderDependentWrongStatePatternAceptedTransitionSecuenceTest): The user must finish this test. This exception must be removed after that.";
	private String stateExpectedErrorMessage4 = "testTransitionSecuenceToFinalState(tfg.sw.test.integration.pattern.ExampleOrederDependentCorrectStatePatternAceptedTransitionSecuenceTest): The user must finish this test. This exception must be removed after that.";
	private String stateExpectedErrorMessage5 = "testTransitionSecuenceToFinalState(tfg.sw.test.integration.pattern.ExampleOrederDependentWrongStatePatternAceptedTransitionSecuenceTest): The user must finish this test. This exception must be removed after that.";
	private String stateExpectedErrorMessage6 = "testTransitionSecuenceToFinalState(tfg.sw.test.integration.pattern.ExampleWrongTransitionsStatePatternAceptedTransitionSecuenceTest): The user must finish this test. This exception must be removed after that.";

	private String strategyExpectedFailureMessage1 = "test8_action2_ConcreteStrategy2_ConcreteStrategy3_0(tfg.sw.test.integration.pattern.Strategy1StrategyPatternComparisonsTest): expected:<0> but was:<1>";
	private String strategyExpectedFailureMessage2 = "test6_action2_ConcreteStrategy1_ConcreteStrategy2_0(tfg.sw.test.integration.pattern.Strategy1StrategyPatternComparisonsTest): expected:<0> but was:<1>";

	private String strategyExpectedErrorMessage1 = "test14_action4_ConcreteStrategy2_ConcreteStrategy3_0(tfg.sw.test.integration.pattern.Strategy1StrategyPatternComparisonsTest): The user must finish this test. This exception must be removed after that.";
	private String strategyExpectedErrorMessage2 = "test12_action4_ConcreteStrategy1_ConcreteStrategy2_0(tfg.sw.test.integration.pattern.Strategy1StrategyPatternComparisonsTest): The user must finish this test. This exception must be removed after that.";
	private String strategyExpectedErrorMessage3 = "test13_action4_ConcreteStrategy1_ConcreteStrategy3_0(tfg.sw.test.integration.pattern.Strategy1StrategyPatternComparisonsTest): The user must finish this test. This exception must be removed after that.";

	// private String resulCompiledPath =
	// "D:\\Grado\\TFG\\SW\\Code\\TestGenerator\\PatternImplementationExamples\\target\\test-classes\\tfg\\sw\\test\\integration\\pattern";

	@Test
	public void testState() throws Exception {

		String sourcePathState = sourcePath + "\\state";
		String resultPathState = resultPath + "\\state";
		String resultPackageState = resultPackage;

		Object[] waitedResults = new Object[] {
				new Object[] { stateExpectedErrorCount, false },

				new Object[] { stateExpectedFailureMessage1, false },
				new Object[] { stateExpectedFailureMessage2, false },
				new Object[] { stateExpectedFailureMessage3, false },
				new Object[] { stateExpectedFailureMessage4, false },
				new Object[] { stateExpectedFailureMessage5, false },

				new Object[] { stateExpectedErrorMessage1, false },
				new Object[] { stateExpectedErrorMessage2, false },
				new Object[] { stateExpectedErrorMessage3, false },
				new Object[] { stateExpectedErrorMessage4, false },
				new Object[] { stateExpectedErrorMessage5, false },
				new Object[] { stateExpectedErrorMessage6, false } };

		deleteOldResults();

		// Execute the system with the state pattern examples
		PatternTestGenerator.execute(sourcePathState, resultPathState,
				resultPackageState);

		StringBuffer output = new StringBuffer();
		String command = "mvn test -fae -f "
				+ (new File(examplesProyectPath).getAbsolutePath());

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		String line = "";
		while ((line = reader.readLine()) != null) {
			output.append("\n" + line);

			for (Object o : waitedResults) {
				Object[] oa = (Object[]) o;
				if (!(Boolean) oa[1]) {
					if (line.contains((String) oa[0]))
						oa[1] = true;
				}
			}
		}

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + output
				+ "\n\n\n\n\n\n\n\n\n\n\n");

		for (Object o : waitedResults) {
			Object[] oa = (Object[]) o;
			boolean founded = (Boolean) oa[1];

			if (founded)
				System.out.println("\nFounded: " + ((String) oa[0]) + "\n");
			assertTrue(founded);
		}
	}

	@Test
	public void testStrategy() throws Exception {

		String sourcePathState = sourcePath + "\\strategy";
		String resultPathState = resultPath + "\\strategy";
		String resultPackageState = resultPackage;

		Object[] waitedResults = new Object[] {
				new Object[] { strategyExpectedErrorCount, false },

				new Object[] { strategyExpectedFailureMessage1, false },
				new Object[] { strategyExpectedFailureMessage2, false },

				new Object[] { strategyExpectedErrorMessage1, false },
				new Object[] { strategyExpectedErrorMessage2, false },
				new Object[] { strategyExpectedErrorMessage3, false } };

		deleteOldResults();

		// Execute the system with the state pattern examples
		PatternTestGenerator.execute(sourcePathState, resultPathState,
				resultPackageState);

		StringBuffer output = new StringBuffer();
		String command = "mvn test -fae -f "
				+ (new File(examplesProyectPath).getAbsolutePath());

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		String line = "";
		while ((line = reader.readLine()) != null) {
			output.append("\n" + line);

			for (Object o : waitedResults) {
				Object[] oa = (Object[]) o;
				if (!(Boolean) oa[1]) {
					if (line.contains((String) oa[0]))
						oa[1] = true;
				}
			}
		}

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + output
				+ "\n\n\n\n\n\n\n\n\n\n\n");

		for (Object o : waitedResults) {
			Object[] oa = (Object[]) o;
			boolean founded = (Boolean) oa[1];

			if (founded)
				System.out.println("\nFounded: " + ((String) oa[0]) + "\n");
			assertTrue(founded);
		}
	}

	@Test
	public void testAll() throws Exception {

		String sourcePathState = sourcePath;
		String resultPathState = resultPath;
		String resultPackageState = resultPackage;

		Object[] waitedResults = new Object[] {
				new Object[] { allExpectedErrorCount, false },

				new Object[] { stateExpectedFailureMessage1, false },
				new Object[] { stateExpectedFailureMessage2, false },
				new Object[] { stateExpectedFailureMessage3, false },
				new Object[] { stateExpectedFailureMessage4, false },
				new Object[] { stateExpectedFailureMessage5, false },

				new Object[] { stateExpectedErrorMessage1, false },
				new Object[] { stateExpectedErrorMessage2, false },
				new Object[] { stateExpectedErrorMessage3, false },
				new Object[] { stateExpectedErrorMessage4, false },
				new Object[] { stateExpectedErrorMessage5, false },
				new Object[] { stateExpectedErrorMessage6, false },

				new Object[] { strategyExpectedFailureMessage1, false },
				new Object[] { strategyExpectedFailureMessage2, false },

				new Object[] { strategyExpectedErrorMessage1, false },
				new Object[] { strategyExpectedErrorMessage2, false },
				new Object[] { strategyExpectedErrorMessage3, false } };

		deleteOldResults();

		// Execute the system with the state pattern examples
		PatternTestGenerator.execute(sourcePathState, resultPathState,
				resultPackageState);

		StringBuffer output = new StringBuffer();
		String command = "mvn test -fae -f "
				+ (new File(examplesProyectPath).getAbsolutePath());

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		String line = "";
		while ((line = reader.readLine()) != null) {
			output.append("\n" + line);

			for (Object o : waitedResults) {
				Object[] oa = (Object[]) o;
				if (!(Boolean) oa[1]) {
					if (line.contains((String) oa[0]))
						oa[1] = true;
				}
			}
		}

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + output
				+ "\n\n\n\n\n\n\n\n\n\n\n");

		for (Object o : waitedResults) {
			Object[] oa = (Object[]) o;
			boolean founded = (Boolean) oa[1];

			if (founded)
				System.out.println("\nFounded: " + ((String) oa[0]) + "\n");
			assertTrue(founded);
		}
	}

	public void deleteOldResults() throws IOException {

		File directory = new File(resultPath);

		// make sure directory exists
		if (directory.exists()) {
			delete(directory);
		}
	}

	public void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			// if file, then delete it
			file.delete();
		}
	}

	@Test
	public void testDemo() throws Exception {
		String sourcePath = "D:\\Grado\\TFG\\Demostracion\\demostration\\src\\main\\java";
		String resultPath = "D:\\Grado\\TFG\\Demostracion\\demostration\\src\\test\\java\\demo";
		String resultPackage = "demo";

		// Execute the system with the state pattern examples
		PatternTestGenerator.execute(sourcePath, resultPath, resultPackage);
	}

}
