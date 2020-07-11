package br.com.rafawhite.comparators;

import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.interfaces.Comparator;

public abstract class ComparatorImpl implements Comparator {

	private static final long serialVersionUID = 1L;
	
	private FilterBy filter;
		
	public ComparatorImpl(FilterBy filter) {
		this.filter = filter;
	}

	public FilterBy getFilter() {
		return filter;
	}

	public void setFilter(FilterBy filter) {
		this.filter = filter;
	}

}
