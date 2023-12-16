package org.vaadin.example;

import com.vaadin.cdi.annotation.CdiComponent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;


@Route("")
@CdiComponent
public class MainView extends VerticalLayout {

    @Inject
    private CustomerService service;
    private Binder<Customer> binder = new Binder<>(Customer.class);

    @PostConstruct
    public void init() {

        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField email = new TextField("Email");
        ComboBox<String> countryComboBox = new ComboBox<>("Country");
        countryComboBox.setItems("USA", "Canada"); // Add your country options here

        binder.bind(firstName, Customer::getFirstName, Customer::setFirstName);
        binder.bind(lastName, Customer::getLastName, Customer::setLastName);
        binder.bind(email, Customer::getEmail, Customer::setEmail);
        binder.bind(countryComboBox, Customer::getCountry, Customer::setCountry);

        Button registerButton = new Button("Register", event -> {

            Customer customer = new Customer();
            binder.writeBeanIfValid(customer);
            service.register(customer);

            // Perform registration logic here
            String message = "Registration successful for: " +
                    firstName.getValue() + " " +
                    lastName.getValue() + ", Email: " +
                    email.getValue();
            Notification.show(message);
            // Clear all fields after registration is completed
            binder.removeBean(); // Remove current bean from the binder
            binder.setBean(new Customer()); // Set a new instance of Customer to the binder
        });

      
        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in
        // shared-styles.css.
        addClassName("centered-content");
        add(firstName, lastName, email, countryComboBox, registerButton);
    }

}
