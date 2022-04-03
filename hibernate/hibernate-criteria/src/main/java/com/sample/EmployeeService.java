package com.sample;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void saveEmployee(Employee e) {
		em.persist(e);
	}

	public List<Employee> findEmployees() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery criteria = cb.createQuery(Employee.class);
		criteria.from(Employee.class);

		List<Employee> result = em.createQuery(criteria).getResultList();

		return result;
	}

	public List<Employee> findEmployeesById() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery criteria = cb.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		criteria.select(root).where(cb.between(root.get("id"), 1, 2));
		List<Employee> result = em.createQuery(criteria).getResultList();

		return result;
	}

	public List<Employee> findByName(String name) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);

		Root<Employee> root = criteria.from(Employee.class);

		criteria.where(cb.equal(root.get("firstName"), name));

		return em.createQuery(criteria).getResultList();
	}

	public List<Employee> findByNameLike() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);

		Root<Employee> root = criteria.from(Employee.class);

		criteria.select(root).where(cb.like(root.<String>get("firstName"), "J%"));

		return em.createQuery(criteria).getResultList();
	}

	public List<Employee> findMultipleConditions() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);

		Root<Employee> root = criteria.from(Employee.class);

		criteria.select(root).where(cb.like(root.<String>get("firstName"), "J%"),
				// AND
				cb.between(root.get("id"), 1, 2)
		        // AND ...
		);

		return em.createQuery(criteria).getResultList();
	}

	public List<Object[]> groupByDepartment() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);

		Root<Employee> root = criteria.from(Employee.class);

		criteria.multiselect(root.get("dept"), cb.count(root));
		criteria.groupBy(root.get("dept"));

		Query q = em.createQuery(criteria);
		List<Object[]> result = q.getResultList();

		result.forEach(item -> System.out.println("Dept : " + item[0] + "\t count : " + item[1]));
		return result;
	}
	public List<Object[]> groupByDepartmentHaving() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);

		Root<Employee> root = criteria.from(Employee.class);

		criteria.multiselect(root.get("dept"), cb.count(root));
		criteria.groupBy(root.get("dept")).having(cb.ge(cb.count(root),2));

		Query q = em.createQuery(criteria);
		List<Object[]> result = q.getResultList();

		result.forEach(item -> System.out.println("Dept : " + item[0] + "\t count : " + item[1]));
		return result;
	}
	public List<Employee> findUsingPredicate() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee>  criteria = cb.createQuery(Employee.class);

		Root<Employee> root = criteria.from(Employee.class);
		
		Predicate[] predicates = new Predicate[2];
		predicates[0] = cb.equal(root.get("firstName"), "John");
		predicates[1] = cb.between(root.get("id"), 1, 2);
		
		criteria.select(root).where(predicates);	
		return em.createQuery(criteria).getResultList();
		
	}
	public List<Employee> findUsingPredicateTypeSafe() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee>  criteria = cb.createQuery(Employee.class);

		Root<Employee> root = criteria.from(Employee.class);
		
		Predicate[] predicates = new Predicate[2];
		predicates[0] = cb.equal(root.get("firstName"), "John");
		predicates[1] = cb.between(root.get("id"), 1, 2);
		
		TypedQuery<Employee> q = em.createQuery(criteria); 
		return q.getResultList();	
 		
	}
}
