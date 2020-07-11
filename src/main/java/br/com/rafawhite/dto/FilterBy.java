package br.com.rafawhite.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import br.com.rafawhite.enums.ComparatorType;

public class FilterBy implements Serializable {

	private static final long serialVersionUID = 1L;

	// Field to do Get
	private String field;

	// Comparator Type (equals, greaterThan, lessThan...)
	private ComparatorType comparator;

	// List of values to use Comparator (with OR Clause)
	private List<Object> values;

	// Unique Constructor
	public FilterBy(String field, ComparatorType comparator, Object... values) {
		this.field = field;
		this.comparator = comparator;
		setFields(values);
	}

	private void setFields(Object... values) {
		boolean isNotEmpty = valuesFromParameterIsNotEmpty(values);
		if (isNotEmpty)
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

	public ComparatorType getComparator() {
		return comparator;
	}

	public void setComparator(ComparatorType comparator) {
		this.comparator = comparator;
	}
	
	// Check if First Value is Number Instance
	public boolean firstValueIsNumber() {
		if(values != null && values.size() > 0) {
			return (values.get(0) instanceof Number);
		}
		return false;
	}
	
	// Check if First Value is String Instance
	public boolean firstValueIsString() {
		if(values != null && values.size() > 0) {
			return (values.get(0) instanceof String);
		}
		return false;
	}
	
	// Check if All Values are Number instance
	public boolean valuesAreNumber() {
		if(values != null && values.size() > 0) {
			for(Object obj : values) {
				if(!(obj instanceof Number)) {
					return false;
				}
			}
		}
		return true;
	}
	
	// Check if All Values are String instance
	public boolean valuesAreString() {
		if(values != null && values.size() > 0) {
			for(Object obj : values) {
				if(!(obj instanceof String)) {
					return false;
				}
			}
		}
		return true;
	}

	// Return true if ComparatorType is comparator > , < , >= or <=
	public boolean comparatorTypeIsNumber() {
		if (comparator.equals(ComparatorType.GreaterThan) || comparator.equals(ComparatorType.GreaterThanOrEquals)
				|| comparator.equals(ComparatorType.LessThan) || comparator.equals(ComparatorType.LessThanOrEquals))
			return true;

		return false;
	}

}