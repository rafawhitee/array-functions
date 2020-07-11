package br.com.rafawhite.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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

	// Do a for in VALUES
	// Check if ComparatorType is Number Type
	// If is, the values required to be Number too
	public boolean valuesAreValidAccordingComparatorType() {
		boolean areValid = true;
		boolean comparatorIsTypeNumber = comparatorTypeIsNumber();
		if (values != null && values.size() > 0) {
			for (Object currentObj : values) {
				if (comparatorIsTypeNumber) {
					if (!(currentObj instanceof Number)) {
						areValid = false;
					}
				}
			}
		}
		return areValid;
	}

	// Return true if ComparatorType is comparator > , < , >= or <=
	private boolean comparatorTypeIsNumber() {
		if (comparator.equals(ComparatorType.GreaterThan) || comparator.equals(ComparatorType.GreaterThanOrEquals)
				|| comparator.equals(ComparatorType.LessThan) || comparator.equals(ComparatorType.LessThanOrEquals))
			return true;

		return false;
	}

}
