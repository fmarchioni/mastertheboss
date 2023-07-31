package com.itbuzzpress.jsf.bean;

import java.io.Serializable;
 

import jakarta.enterprise.inject.Model;
@Model
public class ConverterBean implements Serializable {

    private int intValue;
    private double doubleValue;
    private Date dateValue;

	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public Date getDateValue() {
		return dateValue;
	}
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public void submit() {
         System.out.println(this.toString());
    }
	@Override
	public String toString() {
		return "ConverterBean [intValue=" + intValue + ", doubleValue=" + doubleValue + ", dateValue=" + dateValue
				+ "]";
	}
}
