package tfg.sw.test.unity.pattern;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Comparator;

import testExamples.parser.strategy.correct.Strategy1;

import testExamples.parser.strategy.correct.concrete.ConcreteStrategy1;
import testExamples.parser.strategy.correct.concrete.ConcreteStrategy2;
import testExamples.parser.strategy.correct.concrete.ConcreteStrategy3;


import testExamples.parser.strategy.correct.auxiliar.UsersComparator;


public class Strategy1StrategyPatternComparisonsTest {



	private class action4Comparator implements Comparator<UsersClassComparator2> {
		public int compare(UsersClassComparator2 o1, UsersClassComparator2 o2) {
			throw new UnsupportedOperationException ("The user must finish this test. This exception must be removed after that.");
		}
	}

	@Test
	public void test0_action1_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 01, 01), true);
		Date result2 = strategy2.action1 (new Date (2017, 01, 01), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test0_action1_ConcreteStrategy1_ConcreteStrategy2_1 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 12, 31), true);
		Date result2 = strategy2.action1 (new Date (2017, 12, 31), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test1_action1_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 01, 01), true);
		Date result2 = strategy2.action1 (new Date (2017, 01, 01), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test1_action1_ConcreteStrategy1_ConcreteStrategy3_1 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 12, 31), true);
		Date result2 = strategy2.action1 (new Date (2017, 12, 31), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test2_action1_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 01, 01), true);
		Date result2 = strategy2.action1 (new Date (2017, 01, 01), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test2_action1_ConcreteStrategy2_ConcreteStrategy3_1 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 12, 31), true);
		Date result2 = strategy2.action1 (new Date (2017, 12, 31), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test3_action2_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		UsersClassComparable result1 = strategy1.action2 ();
		UsersClassComparable result2 = strategy2.action2 ();
		
		int comparisonResult = result1.compareTo(result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test4_action2_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparable result1 = strategy1.action2 ();
		UsersClassComparable result2 = strategy2.action2 ();
		
		int comparisonResult = result1.compareTo(result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test5_action2_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparable result1 = strategy1.action2 ();
		UsersClassComparable result2 = strategy2.action2 ();
		
		int comparisonResult = result1.compareTo(result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test6_action3_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		UsersClassComparator1 result1 = strategy1.action3 (new UsersClassComparable ());
		UsersClassComparator1 result2 = strategy2.action3 (new UsersClassComparable ());
		
		Comparator comparator = new UsersComparator (3);
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test7_action3_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparator1 result1 = strategy1.action3 (new UsersClassComparable ());
		UsersClassComparator1 result2 = strategy2.action3 (new UsersClassComparable ());
		
		Comparator comparator = new UsersComparator (3);
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test8_action3_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparator1 result1 = strategy1.action3 (new UsersClassComparable ());
		UsersClassComparator1 result2 = strategy2.action3 (new UsersClassComparable ());
		
		Comparator comparator = new UsersComparator (3);
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test9_action4_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		UsersClassComparator2 result1 = strategy1.action4 ();
		UsersClassComparator2 result2 = strategy2.action4 ();
		
		Comparator comparator = new action4Comparator ();
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test10_action4_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparator2 result1 = strategy1.action4 ();
		UsersClassComparator2 result2 = strategy2.action4 ();
		
		Comparator comparator = new action4Comparator ();
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test11_action4_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparator2 result1 = strategy1.action4 ();
		UsersClassComparator2 result2 = strategy2.action4 ();
		
		Comparator comparator = new action4Comparator ();
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}


}