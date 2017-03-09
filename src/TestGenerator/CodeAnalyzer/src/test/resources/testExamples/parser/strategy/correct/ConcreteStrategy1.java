package testExamples.parser.strategy.correct.concrete;

/*
 * @pattern Strategy
 * @patternElement ConcreteStrategy
 * @patternElement Builder < new ConcreteStrategy1 () >
 */
public class ConcreteStrategy1 implements Strategy1 {

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