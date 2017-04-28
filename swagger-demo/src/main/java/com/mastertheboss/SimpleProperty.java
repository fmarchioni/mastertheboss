package com.mastertheboss;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleProperty {
	public SimpleProperty() {

	}
 
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public SimpleProperty(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}