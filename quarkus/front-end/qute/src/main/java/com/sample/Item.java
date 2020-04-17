package com.sample;

import java.math.BigDecimal;

public class Item {

    public final BigDecimal price;
    public final String name;
    public final Integer id=0;

    public Item(BigDecimal price, String name) {
        this.price = price;
        this.name = name;
    }

}