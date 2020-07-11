package br.com.rafawhite.factory;

import java.io.Serializable;

import br.com.rafawhite.comparators.EqualsComparator;
import br.com.rafawhite.comparators.GreaterThanComparator;
import br.com.rafawhite.comparators.GreaterThanOrEqualsComparator;
import br.com.rafawhite.comparators.LessThanComparator;
import br.com.rafawhite.comparators.LessThanOrEqualsComparator;
import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.interfaces.Comparator;

public abstract class ComparatorFactory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static Comparator create(FilterBy filter) {
		boolean firstValueIsString = filter.firstValueIsString();	
		boolean firstValueIsNumber = filter.firstValueIsNumber();
		
		if(firstValueIsString)
			return new EqualsComparator(filter);
		
		else if(firstValueIsNumber)
			return ComparatorFactory.getNumberComparator(filter);
		
		return null;
	}
	
	// Number can have EqualsComparator to (double equals another double value)
	private static Comparator getNumberComparator(FilterBy filter) {
		switch (filter.getComparator()) {
			case Equals: return new EqualsComparator(filter);		
			case GreaterThan: return new GreaterThanComparator(filter);
			case LessThan: return new LessThanComparator(filter);
			case GreaterThanOrEquals: return new GreaterThanOrEqualsComparator(filter);
			case LessThanOrEquals: return new LessThanOrEqualsComparator(filter);			
			default: return new EqualsComparator(filter);
		}
	}
	
}