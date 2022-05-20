package org.acme;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class ContactService {

    List<Contact> contacts =  new ArrayList();

    public List<Contact> getPersons() {
        return contacts;

    }
    public void addPerson(Contact p){
        contacts.add(p);
    }

    public ContactService( ) {
        contacts.add(new Contact("Bruce","Wayne","Batman"));
        contacts.add(new Contact("Peter","Parker","Spiderman"));
        contacts.add(new Contact("Steve","Rogers","Captain America"));
        contacts.add(new Contact("Bruce","Banner","Hulk"));
    }



}
