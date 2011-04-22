package org.petank.gwt.client.model;

public class NamedValue {

	private String name;
	private String value;
	
	public NamedValue(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	@Override
	public String toString() {
		return name + " : " + value;
	}
	
}
