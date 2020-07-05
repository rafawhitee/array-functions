package br.com.rafawhite.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class FilterBy implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private List<Object> values;

	public FilterBy(String field, Object... values) {
		this.field = field;
		setFields(values);
	}
	
	private void setFields(Object... values) {
		boolean isNotEmpty = valuesFromParameterIsNotEmpty(values);
		if(isNotEmpty)
			setValues(Arrays.asList(values));
	}
	
	private boolean valuesFromParameterIsNotEmpty(Object... values) {
		if (values != null && values.length > 0)
			return true;

		return false;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

}
