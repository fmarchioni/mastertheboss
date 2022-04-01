package com.sample;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

@ApplicationScoped
public class EmployeeService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveEmployee(Employee e)  {
        em.persist(e);
    }
    
    public List<Employee> findEmployees()  {
    	Query query = em.createQuery("FROM Employee");

		List <Employee> list = query.getResultList();
		return list;
    }
    @Transactional
    public void saveEmployeeHibernate(Employee e) throws Exception {
        
        // using Hibernate session(Native API) and JPA entitymanager
        Session session = (Session) em.getDelegate();       
        session.persist(e);
         
    }
}
