package br.com.rafawhite.comparators;

import br.com.rafawhite.dto.FilterBy;

public class EqualsComparator extends ComparatorImpl {

	private static final long serialVersionUID = 1L;

	public EqualsComparator(FilterBy filter) {
		super(filter);
	}

	@Override
	public boolean compare(Object currentValue, Object invoked) {
		return invoked.equals(currentValue);
	}

}
