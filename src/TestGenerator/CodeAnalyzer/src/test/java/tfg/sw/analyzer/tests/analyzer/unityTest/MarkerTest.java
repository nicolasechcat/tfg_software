package tfg.sw.analyzer.tests.analyzer.unityTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tfg.sw.analyzer.analyzer.Marker;


public class MarkerTest {
	
	@Test
	public void testCorrect1 () {
		Marker m = new Marker("file1", 21,
				"* @patternElement Builder <new ConexionTCP (8080, \"pruebas\")>");
		
		List<String> params = new ArrayList<String>();
		params.add("Builder");
		params.add("<new ConexionTCP (8080, \"pruebas\")>");
		
		assertTrue(m.isMarker());
		assertEquals(m.getMarkerId(), "patternElement");
		assertEquals(m.getFilePath(), "file1");
		assertEquals(m.getLineNumber(), 21);
		assertEquals(m.getParams(), params);
	}
	
	
	@Test
	public void testCorrect2 () {
		Marker m = new Marker("", 0, "@pattern State");
		
		List<String> params = new ArrayList<String>();
		params.add("State");
		
		assertTrue(m.isMarker());
		assertEquals(m.getMarkerId(), "pattern");
		assertEquals(m.getFilePath(), "");
		assertEquals(m.getLineNumber(), 0);
		assertEquals(m.getParams(), params);
	}
	
	@Test
	public void testCorrect3 () {
		Marker m = new Marker("", 0,
				"@patternAction Transition TCPCerrada <context, true, \"Cerrada\">");
		
		List<String> params = new ArrayList<String>();
		params.add("Transition");
		params.add("TCPCerrada");
		params.add("<context, true, \"Cerrada\">");
		
		assertTrue(m.isMarker());
		assertEquals(m.getMarkerId(), "patternAction");
		assertEquals(m.getFilePath(), "");
		assertEquals(m.getLineNumber(), 0);
		assertEquals(m.getParams(), params);
	}
	
	@Test
	public void testTabuledMarker () {
		String waitedResult = "<context, true, \"Cerrada\">";
		
		Marker m = new Marker("", 0,
				"@patternAction Transition TCPCerrada <context, \t true, \"Cerrada\">");
		
		assertTrue(m.isMarker());
		assertEquals(3, m.getParams().size());
		assertEquals(waitedResult, m.getParams().get(2));
	}
	
	@Test
	public void testnotMarker1 () {
		Marker m = new Marker("", 0,
				"patternAction Transition TCPCerrada <context, true, \"Cerrada\">");
		
		assertTrue(!m.isMarker());
	}
	
	@Test
	public void testnotMarker2 () {
		Marker m = new Marker("", 0,
				"h @patternAction Transition TCPCerrada <context, true, \"Cerrada\">");
		
		// System.out.print(m);
		assertTrue(!m.isMarker());
		
	}
	
	@Test
	public void testnotMarker3 () {
		Marker m = new Marker("", 0,
				"h@patternAction Transition TCPCerrada <context, true, \"Cerrada\">");
		
		assertTrue(!m.isMarker());
	}
	
	@Test
	public void testnotMarker4 () {
		Marker m = new Marker("", 0,
				"** @patternAction Transition TCPCerrada <context, true, \"Cerrada\">");
		
		assertTrue(!m.isMarker());
	}
	
	@Test
	public void testnotMarker5 () {
		Marker m = new Marker("", 0,
				"* @@patternAction Transition TCPCerrada <context, true, \"Cerrada\">");
		
		assertTrue(!m.isMarker());
	}
	
}
