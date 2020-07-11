package br.com.rafawhite.interfaces;

import java.io.Serializable;

import br.com.rafawhite.dto.FilterBy;

public interface Validator extends Serializable {
	
	boolean validate(FilterBy filter);

}
