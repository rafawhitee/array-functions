package br.com.rafawhite.comparators;

import br.com.rafawhite.interfaces.Comparator;
import br.com.rafawhite.util.Util;

public class LessThanOrEqualsComparator implements Comparator {
	
	private static final long serialVersionUID = 1L;

	@Override
	public boolean compare(Object currentValue, Object invoked) {
		double currentDoubleValue = Util.parseObjectToDouble(currentValue).doubleValue();
		double invokedDouble = Util.parseObjectToDouble(invoked).doubleValue();
		return invokedDouble <= currentDoubleValue;
	}

}
