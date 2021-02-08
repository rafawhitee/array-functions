package br.com.rafawhite.interfaces;

import java.io.Serializable;

import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.factory.ValidatorFactory;

public interface Comparator extends Serializable {
	
	default boolean validateAndCompareValues(Object currentValue, Object invoked, FilterBy filter) {
		Validator validator = ValidatorFactory.getValidator(filter);
		boolean areValids = validator.validate(filter);
		
		if(areValids) 
			return compare(currentValue, invoked);
		
		return false;
	}
	
	boolean compare(Object currentValue, Object invoked);

}