package testExamples.parser.strategy.correct.concrete;

/*
 * @pattern Strategy <Strategy1>
 * @patternElement ConcreteStrategy
 */
public class ConcreteStrategy3 implements Strategy1 {

	public Date action1(Date date, boolean future) {
		return null;
	}

	public UsersClassComparable action2() {
		return null;
	}

	public UsersClassComparator1 action3(UsersClassComparable input) {
		return null;
	}

	public UsersClassComparator2 action4() {
		return null;
	}

}