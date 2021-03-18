package com.mastertheboss.reactive;


public class Operation   {

    public static final int SELL = 0;
    public static final int BUY = 1;
    private int amount;
    private String company;
    private int type;

    public Operation(int type, String company, int amount) {
        this.amount = amount;
        this.company = company;
        this.type = type;
    }



    public Operation() {

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
