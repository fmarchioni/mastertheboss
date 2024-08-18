package com.example.demo.demo2_1.repository;

import com.example.demo.demo2_1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}


/*
CrudRepository:

Purpose: Provides basic CRUD (Create, Read, Update, Delete) operations for any domain object.
Persistence Provider Agnostic: Works with various persistence providers (databases) like JPA, MongoDB, Cassandra, etc., through Spring Data abstractions.
Limited Functionality: Offers essential CRUD methods (save, findOne, findAll, deleteById, etc.), but lacks JPA-specific features.

JpaRepository:

Purpose: Extends CrudRepository and specifically designed for JPA-based persistence.
JPA Specific: Leverages JPA features like flushing, lazy loading, and custom JPA queries.
Additional Functionality: Provides methods like flush(), getOne(), deleteAll(), and support for creating custom JPA queries using @Query.
 */