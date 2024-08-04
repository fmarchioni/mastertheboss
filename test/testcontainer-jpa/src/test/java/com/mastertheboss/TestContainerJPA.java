package com.mastertheboss;


import com.mastertheboss.model.Customer;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Assertions;
import java.util.List;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestContainerJPA {

  private EntityManagerFactory emf;

  @Container
  private static final MySQLContainer MYSQL_CONTAINER = (MySQLContainer) new MySQLContainer()
          .withDatabaseName("testdb")
          .withUsername("root")
          .withPassword("password")
          .withReuse(true);

  @BeforeAll
  void setup() {
    System.setProperty("db.port", MYSQL_CONTAINER.getFirstMappedPort().toString());
    emf = Persistence.createEntityManagerFactory("my-persistence");
  }

  @Test
  void testCreateCustomer() {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    Customer c = new Customer();
    c.setFirstName("John");
    c.setLastName("Doe");
    c.setEmail("jdoe@gmail.com");
    entityManager.persist(c);
    entityManager.getTransaction().commit();
    entityManager.close();

    // Verify
    EntityManager em = emf.createEntityManager();
    List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    Assertions.assertEquals(1, customers.size());
    Assertions.assertEquals("John", customers.get(0).getFirstName());
    em.close();
  }



  @Test
  void testDeleteCustomer() {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    Customer c = new Customer();
    c.setFirstName("Charlie");
    c.setLastName("Black");
    c.setEmail("charlie@gmail.com");
    entityManager.persist(c);
    entityManager.getTransaction().commit();

    // Delete
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.contains(c) ? c : entityManager.merge(c));
    entityManager.getTransaction().commit();
    entityManager.close();

    // Verify
    EntityManager em = emf.createEntityManager();
    Customer deletedCustomer = em.find(Customer.class, c.getId());
    Assertions.assertNull(deletedCustomer);
    em.close();
  }
}