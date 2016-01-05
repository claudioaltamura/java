package de.claudioaltamura.json.annotation;

import org.codehaus.jackson.annotate.JsonProperty;

public class Book {

	private String _name;

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}
		
}
