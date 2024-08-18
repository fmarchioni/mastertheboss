package com.example.demo.demo2_1.repository;

import com.example.demo.demo2_1.model.Customer;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerSpecification {

    public static Specification<Customer> withDynamicFilter(Map<String, Object> fields) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            fields.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        predicates.add(cb.equal(root.get("name"), value));
                        break;
                    case "surname":
                        predicates.add(cb.equal(root.get("surname"), value));
                        break;
                    case "email":
                        predicates.add(cb.equal(root.get("email"), value));
                        break;
                }
            });

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
