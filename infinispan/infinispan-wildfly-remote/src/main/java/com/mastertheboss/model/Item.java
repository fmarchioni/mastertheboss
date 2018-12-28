package com.mastertheboss.model;

import java.io.Serializable;

public class Item implements Serializable {
String key;
String value;
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
}
