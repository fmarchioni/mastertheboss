package com.mastertheboss.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import com.mastertheboss.model.Customer;

import java.util.List;

public class SoftDeleteExample {

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

    List<Customer> nativeCustomers = entityManager.createNativeQuery("SELECT * FROM Customer c", Customer.class)
        .getResultList();

    System.out.println("Native query");
    for (Customer customer : nativeCustomers) {
      System.out.println(customer);
    }
    entityManager.close();
    emf.close();

  }
}
