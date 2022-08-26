package org.acme;

import javax.persistence.*;


public class Hero {
    private Long id;
    private String name;

    public Hero(String name) {
        this.name = name;
    }
    public Hero() {
    }
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + "]";
	}
 
}