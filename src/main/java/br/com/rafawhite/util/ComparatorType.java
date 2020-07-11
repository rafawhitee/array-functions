package br.com.rafawhite.util;


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
