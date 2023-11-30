package com.mastertheboss.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.mastertheboss.model.Customer;

import java.util.List;

public class App {

  public static void main(String[] args) {
 

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence01");

    Customer c = new Customer();
    c.setFirstName("John");
    c.setLastName("Doe");
    c.setEmail("jdoe@gmail.com");

    EntityManager entityManager = emf.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(c);
    entityManager.getTransaction().commit();

    entityManager.getTransaction().begin();
    entityManager.remove(c);
    entityManager.getTransaction().commit();

    List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
        .getResultList();

    for (Customer customer : customers) {
      System.out.println(customers);
    }


    entityManager.close();
    emf.close();

  }
}
