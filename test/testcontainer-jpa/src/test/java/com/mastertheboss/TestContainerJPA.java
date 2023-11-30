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
  void contextLoads() {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    Customer c = new Customer();
    c.setFirstName("John");
    c.setLastName("Doe");
    c.setEmail("jdoe@gmail.com");
    entityManager.persist(c);

    entityManager.getTransaction().commit();

    List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
            .getResultList();

    for (Customer customer : customers) {
      System.out.println(customers);

    Assertions.assertEquals(1, customers.size());
    }
  }
}