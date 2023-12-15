package com.example.application.views.main;

import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Route("editable")
public class EditableView extends VerticalLayout {

    private final CustomerRepository repo;
    final Grid<Customer> grid;
    private Optional<Grid.Column<Customer>> currentColumn = Optional.empty();
    private Optional<Customer> currentItem = Optional.empty();

    public EditableView(CustomerRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Customer.class, false);

        var binder = new BeanValidationBinder<>(Customer.class);

        // Create Grid Editor
        var editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        // Save Listener to save the changed SamplePerson
        editor.addSaveListener(event -> {
            Customer c = event.getItem();
            repo.save(c);

            System.out.println("Updated DB with " + c);
        });

        // Configure Grid
        var txtFirstName = new TextField();
        txtFirstName.setWidthFull();
        binder.forField(txtFirstName).bind("firstName");
        grid.addColumn("firstName").setEditorComponent(txtFirstName).setAutoWidth(true);

        var txtLastName = new TextField();
        txtLastName.setWidthFull();
        binder.forField(txtLastName).bind("lastName");
        grid.addColumn("lastName").setEditorComponent(txtLastName).setAutoWidth(true);

        grid.setItems(repo.findAll());

        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(samplePerson -> {
            editor.save();

            if (!editor.isOpen()) {
                grid.getEditor().editItem(samplePerson);

                currentColumn.ifPresent(column -> {
                    if (column.getEditorComponent() instanceof Focusable<?> focusable) {
                        focusable.focus();
                    }
                });
            }
        }));

        grid.addCellFocusListener(event -> {
            // Store the item on cell focus. Used in the ENTER ShortcutListener
            currentItem = event.getItem();
            // Store the current column. Used in the SelectionListener to focus the editor
            // component
            currentColumn = event.getColumn();
        });
        add(grid);

    }

}
