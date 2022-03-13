package org.acme.kafka;


 
public class Quote   {
    String company;
    Double value;
	Double change;
    String time;
    
    public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}


    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Quote(String company) {
        this.company = company;
 
    }

    public Quote() {
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
