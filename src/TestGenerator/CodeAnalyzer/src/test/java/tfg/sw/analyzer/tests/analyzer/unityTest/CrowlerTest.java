package tfg.sw.analyzer.tests.analyzer.unityTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.analyzer.analyzer.Crowler;
import tfg.sw.analyzer.exception.SistemUtilException;

public class CrowlerTest {
	
	@Test
	public void test () throws SistemUtilException {
		String root = "src/test/resources/testExamples/crowler";
		
		List<File> result = Crowler.recursiveCrowling(root);
		
		List<String> waitedResult = new ArrayList<String>();
		waitedResult.add("j1.java");
		waitedResult.add("ja1.java");
		waitedResult.add("jbc1.java");
		waitedResult.add("jbc2.java");
		
		assertEquals(waitedResult.size(), result.size());
		
		for (File f : result) {
			assertTrue(waitedResult.contains(f.getName()));
		}
	}
}
