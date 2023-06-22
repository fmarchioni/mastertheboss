package com.sample.hibernatesearch.controller;

import com.sample.hibernatesearch.model.Book;
import com.sample.hibernatesearch.model.SearchRequestDTO;
import com.sample.hibernatesearch.service.BookService;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

 
@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public List<Book> searchBooks(SearchRequestDTO searchRequestDTO) {

        System.out.println("Request for Book search received with data : " + searchRequestDTO);

        return bookService.searchBooks(searchRequestDTO.getText(), searchRequestDTO.getFields(), searchRequestDTO.getLimit());
    }
}
