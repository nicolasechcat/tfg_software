package tfg.sw.implementationExample.pattern.strategy.ExampleCorrect;

import java.util.Date;

import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparable;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparator1;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparator2;

/*
 * @pattern Strategy
 * @patternElement Strategy
 */
public interface Strategy1 {

	/*
	 * @patternElement Action
	 */
	public int action0();

	/*
	 * @patternElement Action
	 * 
	 * @patternElement Comparison Equals
	 * 
	 * @patternElement Execution < new Date (2017, 01, 01), true >
	 * 
	 * @patternElement Execution < new Date (2017, 12, 31), true >
	 */
	public Date action1(Date date, boolean future);

	/*
	 * @patternElement Action
	 * 
	 * @patternElement Comparison Comparable
	 */
	public UsersClassComparable action2();

	/*
	 * @patternElement Action
	 * 
	 * @patternElement Comparison Comparator
	 * <tfg.sw.implementationExample.pattern.strategy.shared> <UsersComparator>
	 * 
	 * @patternElement Execution < new UsersClassComparable () >
	 */
	public UsersClassComparator1 action3(UsersClassComparable input);

	/*
	 * @patternElement Action
	 * 
	 * @patternElement Comparison Comparator
	 */
	public UsersClassComparator2 action4();
}