package tfg.sw.implementationExample.pattern.strategy.ExampleCorrect;

import java.util.Date;

import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparable;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparator1;
import tfg.sw.implementationExample.pattern.strategy.shared.UsersClassComparator2;

/*
 * @pattern Strategy
 * @patternElement ConcreteStrategy
 * @patternElement Builder < new ConcreteStrategy1 () >
 */
public class ConcreteStrategy1 implements Strategy1 {

	public int action0() {
		return 0;
	}

	public Date action1(Date date, boolean future) {
		return new Date();
	}

	public UsersClassComparable action2() {
		return new UsersClassComparable();
	}

	public UsersClassComparator1 action3(UsersClassComparable input) {
		return new UsersClassComparator1();
	}

	public UsersClassComparator2 action4() {
		return new UsersClassComparator2();
	}
}