package tfg.sw.analyzer.tests.sistemUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import tfg.sw.analyzer.exception.SistemUtilException;
import tfg.sw.analyzer.util.SystemUtil;

public class SistemUtilTest {
	
	@Test
	public void testOk () throws SistemUtilException {
		
		String waitedResult = "Directory does not exist or is not readable";
		String result = SystemUtil
				.getMessageById("crowler_directory_not_found");
		
		assertEquals(waitedResult, result);
	}
	
	@Test (expected = SistemUtilException.class)
	public void testNotExistent () throws SistemUtilException {
		
		SystemUtil.getMessageById("test_no_existen_message???");
		
		fail();
	}
	
	@Test (expected = SistemUtilException.class)
	public void testEmptyId () throws SistemUtilException {
		
		SystemUtil.getMessageById("");
		
		fail();
	}
}
