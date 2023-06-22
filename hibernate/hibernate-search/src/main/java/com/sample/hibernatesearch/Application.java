package com.sample.hibernatesearch;

import com.sample.hibernatesearch.index.Indexer;
import com.sample.hibernatesearch.model.Book;
 
import com.sample.hibernatesearch.repository.BookRepository;
 
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ApplicationRunner buildIndex(Indexer indexer) {
		return (ApplicationArguments args) -> {
			indexer.indexPersistedData("com.sample.hibernatesearch.model.Book");
		};
	}
}

// curl -X GET 'http://localhost:8080/book/search?text=The&limit=5&fields=title' | jq
// curl -X GET 'http://localhost:8080/book/search?text=Tolkien&limit=5&fields=author' | jq

