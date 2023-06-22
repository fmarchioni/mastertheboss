package com.sample.hibernatesearch.repository;

import com.sample.hibernatesearch.model.Book;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends SearchRepository<Book, Long> {
}
