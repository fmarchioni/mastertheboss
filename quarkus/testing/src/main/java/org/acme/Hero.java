package org.acme;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Heros.findAll", query = "SELECT h FROM Hero h ORDER BY h.id")
public class Hero {
    private Long id;
    private String name;

    public Hero(String name) {
        this.name = name;
    }
    public Hero() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "heroSequence")
    @SequenceGenerator(name = "heroSequence", sequenceName = "heroSeq", allocationSize = 1, initialValue = 10)
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