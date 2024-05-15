package com.mastertheboss;

import java.util.ArrayList;
 
import java.util.List;

 
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
 
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
 
import net.datafaker.Faker;

@Path("/persons")
public class GettingStartedEndpoint {

  Faker faker = new Faker();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> getPosts() {
 
    List<Person> persons = new ArrayList<>();
    for (int ii=1;ii<10;ii++)
    persons.add(new Person(ii, faker.name().fullName()));
     
    return persons;
  }
}

class Person {
  public int id;
  public String fullname;

  public Person(int id, String fullname) {
    this.id = id;
    this.fullname = fullname;
  }

   
}