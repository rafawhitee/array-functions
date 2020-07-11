package br.com.rafawhite.validators;

import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.interfaces.Validator;

public class StringValidator implements Validator {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean validate(FilterBy filter) {
		return filter.valuesAreString();
	}

}
