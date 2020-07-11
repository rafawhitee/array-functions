package br.com.rafawhite.enums;

// Types to Comparator Used on FilterBy
public enum ComparatorType {

	Equals("Equals"), 
	LessThan("lessThan"), 
	GreaterThan("greaterThan"), 
	GreaterThanOrEquals("greaterThanOrEquals"),
	LessThanOrEquals("lessThanOrEquals");

	private ComparatorType(final String type) {
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}

}