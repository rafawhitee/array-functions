package br.com.rafawhite.factory;

import java.io.Serializable;

import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.interfaces.Validator;
import br.com.rafawhite.validators.NumberValidator;
import br.com.rafawhite.validators.StringValidator;

public abstract class ValidatorFactory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static Validator getValidator(FilterBy filter) {
		boolean firstValueIsString = filter.firstValueIsString();	
		boolean firstValueIsNumber = filter.firstValueIsNumber();
		
		if(firstValueIsString)
			return new StringValidator();
		
		else if(firstValueIsNumber)
			return new NumberValidator();
		
		return null;
	}

}
