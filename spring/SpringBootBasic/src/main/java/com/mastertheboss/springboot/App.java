package com.mastertheboss.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<App> applicationClass = App.class;
}

@RestController
class HelloController {

	@RequestMapping("/hello/{name}")
	String hello(@PathVariable String name) {

		return "Hi " + name + " !";

	}
}
