package tfg.sw.maker.tests.maker;

import org.junit.Test;

import tfg.sw.maker.maker.TestMaker;

public class TestMakerTest {

	private String sourcePathState = "src\\test\\resources\\DefinitionState.xml";
	private String sourcePathStrategy = "src\\test\\resources\\DefinitionStrategy.xml";
	private String resultPath = "..\\..\\TemporalFiles\\unity\\pattern";
	private String resultPackage = "tfg.sw.test.unity.pattern";

	@Test
	public void it1_ft54_testGeneralSistemExecution() throws Exception {
		TestMaker.execute(sourcePathState, resultPath, resultPackage);
	}

	@Test
	public void it2_ft26_testGeneralSistemExecution() throws Exception {
		TestMaker.execute(sourcePathStrategy, resultPath, resultPackage);
	}

}
