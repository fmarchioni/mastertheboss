package com.mastertheboss.graphql.model;

public class Country {
	String name;
	String symbol;
	public Country(String name, String symbol) {
		this.name=name;
		this.symbol=symbol;
	}
	@Override
	public String toString() {
		return "Country [name=" + name + ", symbol=" + symbol + "]";
	}
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}