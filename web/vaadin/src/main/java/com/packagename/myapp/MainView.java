package com.packagename.myapp;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    @Inject
    private GreetService greetService;

    @PostConstruct
    public void init() {

        setJustifyContentMode(JustifyContentMode.CENTER);
        setWidth("100%");



        List<Person> personList = new ArrayList<>();

        personList.add(new Person(100, "Lucas", "Kane", 68));
        personList.add(new Person(101, "Peter", "Buchanan", 38));
        personList.add(new Person(102, "Samuel", "Lee", 53));


        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.removeColumnByKey("id");

// The Grid<>(Person.class) sorts the properties and in order to
// reorder the properties we use the 'setColumns' method.
        grid.setColumns("name", "surname", "age");

        add(grid);
        setSizeFull();
    }

}
