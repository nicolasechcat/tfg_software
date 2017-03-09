package tfg.sw.test.integration.pattern;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Comparator;

import tfg.sw.implementationExample.pattern.strategy.ExampleCorrect.Strategy1;

import tfg.sw.implementationExample.pattern.strategy.ExampleCorrect.ConcreteStrategy1;
import tfg.sw.implementationExample.pattern.strategy.ExampleCorrect.ConcreteStrategy2;
import tfg.sw.implementationExample.pattern.strategy.ExampleCorrect.ConcreteStrategy3;

import java.util.Date;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparable;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparator1;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparator2;

import tfg.sw.implementationExample.pattern.strategy.shared.UsersComparator;


public class Strategy1StrategyPatternComparisonsTest {



	private class action4Comparator implements Comparator<UsersClassComparator2> {
		public int compare(UsersClassComparator2 o1, UsersClassComparator2 o2) {
			throw new UnsupportedOperationException ("The user must finish this test. This exception must be removed after that.");
		}
	}

	@Test
	public void test0_action0_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		int result1 = strategy1.action0 ();
		int result2 = strategy2.action0 ();
		
		assertEquals (result1, result2);
	}



	@Test
	public void test1_action0_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		int result1 = strategy1.action0 ();
		int result2 = strategy2.action0 ();
		
		assertEquals (result1, result2);
	}



	@Test
	public void test2_action0_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		int result1 = strategy1.action0 ();
		int result2 = strategy2.action0 ();
		
		assertEquals (result1, result2);
	}



	@Test
	public void test3_action1_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 01, 01), true);
		Date result2 = strategy2.action1 (new Date (2017, 01, 01), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test3_action1_ConcreteStrategy1_ConcreteStrategy2_1 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 12, 31), true);
		Date result2 = strategy2.action1 (new Date (2017, 12, 31), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test4_action1_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 01, 01), true);
		Date result2 = strategy2.action1 (new Date (2017, 01, 01), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test4_action1_ConcreteStrategy1_ConcreteStrategy3_1 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 12, 31), true);
		Date result2 = strategy2.action1 (new Date (2017, 12, 31), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test5_action1_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 01, 01), true);
		Date result2 = strategy2.action1 (new Date (2017, 01, 01), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test5_action1_ConcreteStrategy2_ConcreteStrategy3_1 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		Date result1 = strategy1.action1 (new Date (2017, 12, 31), true);
		Date result2 = strategy2.action1 (new Date (2017, 12, 31), true);
		
		assertEquals (result1, result2);
	}



	@Test
	public void test6_action2_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		UsersClassComparable result1 = strategy1.action2 ();
		UsersClassComparable result2 = strategy2.action2 ();
		
		int comparisonResult = result1.compareTo(result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test7_action2_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparable result1 = strategy1.action2 ();
		UsersClassComparable result2 = strategy2.action2 ();
		
		int comparisonResult = result1.compareTo(result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test8_action2_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparable result1 = strategy1.action2 ();
		UsersClassComparable result2 = strategy2.action2 ();
		
		int comparisonResult = result1.compareTo(result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test9_action3_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy2 ();
		
		UsersClassComparator1 result1 = strategy1.action3 (new UsersClassComparable ());
		UsersClassComparator1 result2 = strategy2.action3 (new UsersClassComparable ());
		
		Comparator comparator = new UsersComparator ();
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test10_action3_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy1 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparator1 result1 = strategy1.action3 (new UsersClassComparable ());
		UsersClassComparator1 result2 = strategy2.action3 (new UsersClassComparable ());
		
		Comparator comparator = new UsersComparator ();
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test11_action3_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
	{
		Strategy1 strategy1 = new ConcreteStrategy2 ();
		Strategy1 strategy2 = new ConcreteStrategy3 ();
		
		UsersClassComparator1 result1 = strategy1.action3 (new UsersClassComparable ());
		UsersClassComparator1 result2 = strategy2.action3 (new UsersClassComparable ());
		
		Comparator comparator = new UsersComparator ();
		int comparisonResult = comparator.compare(result1, result2);
		assertEquals (0, comparisonResult);
	}



	@Test
	public void test12_action4_ConcreteStrategy1_ConcreteStrategy2_0 () throws Exception
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
	public void test13_action4_ConcreteStrategy1_ConcreteStrategy3_0 () throws Exception
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
	public void test14_action4_ConcreteStrategy2_ConcreteStrategy3_0 () throws Exception
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