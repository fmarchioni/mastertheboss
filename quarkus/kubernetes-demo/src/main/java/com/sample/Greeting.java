package com.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Greeting {
    private Long id;
    private String message;

    @Id
    @SequenceGenerator(name = "greetSeq", sequenceName = "greet_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "greetSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

  
}
