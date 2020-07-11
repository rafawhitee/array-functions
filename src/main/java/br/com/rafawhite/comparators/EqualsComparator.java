package br.com.rafawhite.comparators;

import br.com.rafawhite.interfaces.Comparator;

public class EqualsComparator implements Comparator {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean compare(Object currentValue, Object invoked) {
		return invoked.equals(currentValue);
	}

}
