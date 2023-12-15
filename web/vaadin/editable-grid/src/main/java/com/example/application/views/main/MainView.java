package com.example.application.views.main;

import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private final CustomerRepository repo;
    final Grid<Customer> grid;

    public MainView(CustomerRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Customer.class, false);

        grid.addColumn(Customer::getFirstName).setHeader("First Name").setSortable(true);
        grid.addColumn(Customer::getLastName).setHeader("Last Name").setSortable(true);

        grid.setItems(repo.findAll());

        add(grid);

    }

 

}
