package testExamples.parser.strategy.correct;

/*
 * @pattern Strategy
 * @patternElement Strategy
 */
public interface Strategy1 {

	/*
	 * @patternElement Action
	 * @patternElement Comparison Equals
	 * @patternElement Execution < new Date (2017, 01, 01), true >
	 * @patternElement Execution < new Date (2017, 12, 31), true >
	 */
	public Date action1(Date date, boolean future);

	/*
	 * @patternElement Action
	 * @patternElement Comparison Comparable 
	 */
	public UsersClassComparable action2();

	/*
	 * @patternElement Transition
	 * @patternElement Comparison Comparator
	 * <testExamples.parser.strategy.correct.auxiliar> <UsersComparator> < new
	 * UsersComparator (3) >
	 * @patternElement Execution < new UsersClassComparable () >
	 */
	public UsersClassComparator1 action3(UsersClassComparable input);

	/*
	 * @patternElement Action
	 * @patternElement Comparison Comparator
	 */
	public UsersClassComparator2 action4();
}