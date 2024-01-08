package com.sample;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

	@PersistenceContext
	private EntityManager em;


    @Transactional
    public void insertEmployee(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> findByRole(String role) {
        return em.createNativeQuery(
            "SELECT * FROM Employee WHERE properties ->> 'role' = :role", Employee.class)
            .setParameter("role", role)
            .getResultList();
    }
    public List<Employee> findAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }
}

