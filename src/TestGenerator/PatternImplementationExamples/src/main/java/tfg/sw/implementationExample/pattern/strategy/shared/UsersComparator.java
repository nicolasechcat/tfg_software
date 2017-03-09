package tfg.sw.implementationExample.pattern.strategy.shared;

import java.util.Comparator;

public class UsersComparator implements Comparator<UsersClassComparator1> {

	@Override
	public int compare(UsersClassComparator1 o1, UsersClassComparator1 o2) {

		return o1.getA() == o2.getA() ? 0 : 1;
	}

}
