package org.acme;

import java.util.Objects;

public   class Contact {
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private  String firstName;
    private  String lastName;
    private String hero;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public String getHero() {
        return hero;
    }

    public Contact(String fName, String lName, String hero ) {
        this.firstName = fName;
        this.lastName = lName;
        this.hero = hero;

    }


}
