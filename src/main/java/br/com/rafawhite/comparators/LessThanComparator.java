package br.com.rafawhite.comparators;

import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.util.Util;

public class LessThanComparator extends ComparatorImpl {

	private static final long serialVersionUID = 1L;
	
	public LessThanComparator(FilterBy filter) {
		super(filter);
	}

	@Override
	public boolean compare(Object currentValue, Object invoked) {
		double currentDoubleValue = Util.parseObjectToDouble(currentValue).doubleValue();
		double invokedDouble = Util.parseObjectToDouble(invoked).doubleValue();
		return invokedDouble < currentDoubleValue;
	}

}
