package tfg.sw.implementationExample.pattern.strategy.shared;

public class UsersClassComparable implements Comparable<UsersClassComparable> {

	private int a = 0;

	public UsersClassComparable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsersClassComparable(int a) {
		super();
		this.a = a;
	}

	@Override
	public int compareTo(UsersClassComparable o) {

		return a == o.a ? 0 : 1;
	}

}
