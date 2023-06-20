package com.example.starter.base;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Key;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
 
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
 
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
public class MainView extends VerticalLayout {

    @Inject
    PersonService greetService;

 
    
    public MainView() {
 

        H1 pageTitle = new H1("Welcome to Quarkus Vaadin!");
        add(pageTitle);
        ComboBox<String> comboBox = new ComboBox<>("Country");
        comboBox.setAllowCustomValue(true);
        List<String> countries = new ArrayList();
        countries.add("US");
        countries.add("UK");
        comboBox.setItems(countries);

        Grid<Person> grid = new Grid<>(Person.class);
        grid.removeColumnByKey("id");
        grid.setColumns("name", "surname", "age", "country");
        grid.setSizeFull();
        grid.setWidth("600px");

      

        Button button = new Button("Search", e -> {
            List<Person> persons = greetService.getPersons(comboBox.getValue());
            System.out.println(persons);
        
            grid.setItems(persons);
            grid.getDataProvider().refreshAll();
            
        });

       
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

       
        button.addClickShortcut(Key.ENTER);

        
        add(comboBox, button,grid);
        setWidth("100%");
        setSizeFull();
        
         

         
    }
}
