package br.com.rafawhite.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String INITIAL_DESCRIPTION_GROUPED_BY = "Grouped by ";
	public static final String INITIAL_DESCRIPTION_FILTERED_BY = "Filtered by ";

	private String description;
	private List<?> values;
	
	public Result() {
		initializeList();
	}
	
	public Result(String description) {
		this.description = description;
		initializeList();
	}

	public Result(String description, List<?> values) {
		this.description = description;
		this.values = values;
	}
	
	private void initializeList() {
		this.values = new ArrayList<>();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<?> getValues() {
		return values;
	}

	public void setValues(List<?> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return description + " - List Size: " + ((values != null) ? values.size() : 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
