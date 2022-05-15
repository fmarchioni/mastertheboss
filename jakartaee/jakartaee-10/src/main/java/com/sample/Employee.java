package com.sample;

import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;

@JsonbTypeInfo(key = "@employee", value = 
 {@JsonbSubtype(alias = "manager", type = Manager.class),@JsonbSubtype(alias = "consultant", type = Consultant.class)})
public class Employee {

	public Employee() {
		super();
	}
}