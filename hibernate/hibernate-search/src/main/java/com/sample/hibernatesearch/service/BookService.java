package com.sample.hibernatesearch.service;

import com.sample.hibernatesearch.model.Book;
import com.sample.hibernatesearch.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("title","author");

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(String text, List<String> fields, int limit) {

        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream(). anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if(containsInvalidField) {
            throw new IllegalArgumentException();
        }

        return bookRepository.searchBy(
                text, limit, fieldsToSearchBy.toArray(new String[0]));
    }
}
