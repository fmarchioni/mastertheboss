package com.example.demo.demo2_1.repository;

import com.example.demo.demo2_1.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CriteriaRepository  {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Customer> findBySurname(String surname) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);

        Predicate surnamePredicate = cb.equal(customer.get("surname"), surname);
        query.where(surnamePredicate);

        return entityManager.createQuery(query).getResultList();
    }


    public List<Customer> findByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);

        Predicate emailPredicate = cb.equal(customer.get("email"), email);
        query.where(emailPredicate);

        return entityManager.createQuery(query).getResultList();
    }


    public List<Customer> findByFields(Map<String, Object> fields) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> field : fields.entrySet()) {
            predicates.add(cb.equal(customer.get(field.getKey()), field.getValue()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }


    public List<Object[]> findWithProjection(List<String> columns, Map<String, Object> fields) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Customer> customer = query.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            predicates.add(cb.equal(customer.get(field.getKey()), field.getValue()));
        }
        query.where(predicates.toArray(new Predicate[0]));

        List<jakarta.persistence.criteria.Selection<?>> selections = new ArrayList<>();
        for (String column : columns) {
            selections.add(customer.get(column));
        }
        query.multiselect(selections);

        return entityManager.createQuery(query).getResultList();
    }
}