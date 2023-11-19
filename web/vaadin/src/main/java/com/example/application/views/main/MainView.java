package com.example.application.views.main;


import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;


@Route("")
 
public class MainView extends VerticalLayout {
    private PersonRepository personRepository;
    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void init() {

        setJustifyContentMode(JustifyContentMode.CENTER);
        setWidth("100%");

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personRepository.findAll());

        grid.removeColumnByKey("id");
        grid.setColumns("name", "surname", "age");

        add(grid);
        setSizeFull();
    }

    
}
