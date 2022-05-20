package com.example;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class RestEasyClientDemo {
    static String BASE_URL ="http://localhost:8080/person";
    public static void main(String args[]) {

        RestEasyClientDemo demo = new RestEasyClientDemo();
        demo.addPerson("Bruce","Banner");
        demo.findPerson("Banner");
        demo.deletePerson("Peter","Parker");
        demo.findPersons();
    }

    public void findPersons() {

        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL);

        Invocation.Builder request = target.request();
        Response response = null;
        try
        {
            response = request.get();
            List<Person> list = response.readEntity(new GenericType<List<Person>>() {});
            System.out.println(list);
        }
        finally
        {
            response.close();
            client.close();
        }
    }
    public void findPerson(String surname) {

        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL+"/single");
        Invocation.Builder request = target.request();
        Response response = null;
        try
        {
            response = request.get();
            Person person = target.queryParam("surname", surname).request().get(Person.class);
            System.out.println(person);
        }
        finally
        {
            response.close();
            client.close();
        }
    }
    public void addPerson(String name, String surname) {

        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request();
        Response response = null;
        try
        {

            Person person = new Person(name,surname);
            response =   target.request().post(Entity.json(person));
            System.out.println(response.getStatusInfo());
        }
        finally
        {
            response.close();
            client.close();
        }
    }

    public void deletePerson(String name, String surname) {

        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL);

        Invocation.Builder request = target.request();
        Response response = null;
        try
        {

            Person person = new Person(name, surname);

            response =   target.request("application/json")
                    .build("DELETE", Entity.entity(person, MediaType.APPLICATION_JSON))
                    .invoke();

            System.out.println(response.getStatusInfo());
        }
        finally
        {
            response.close();
            client.close();
        }
    }
}
