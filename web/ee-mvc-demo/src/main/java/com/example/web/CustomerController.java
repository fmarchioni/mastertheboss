package com.example.web;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.*;

import java.util.List;

@Path("customers")
@Controller
@RequestScoped
public class CustomerController {

    @Inject
    private Models models;

    @Inject
    CustomerRepository repository;

    @GET
    public String listCustomers() {
          List<Customer> list = repository.findAllCustomers();
          models.put("customers",list);
          System.out.println("Called form!");
          return "list.xhtml";
    }

    @Path("new")
    @Controller
    @GET
    public String preAdd() {
        return "insert.xhtml";
    }

    @Path("new")
    @POST
    public String createCustomer(@BeanParam Customer customer) {
        repository.createCustomer(customer);
        List<Customer> list = repository.findAllCustomers();
        models.put("customers",list);
        return "list.xhtml";

    }

    @Path("edit/{id}")
    @GET
    public String preEdit(@PathParam("id") Long id) {
        Customer customer = repository.findCustomerById(id);
        models.put("customer",customer);

        return "edit.xhtml";
    }

    @Path("edit/{id}")
    @POST
    public String edit(@PathParam("id") Long id, @BeanParam Customer customer) {
        customer.setId(id);
        repository.updateCustomer(customer);
        List<Customer> list = repository.findAllCustomers();
        models.put("customers",list);
        return "list.xhtml";
    }


    @Path("delete/{id}")
    @GET
    public String delete(@PathParam("id") Long id) {
        repository.deleteCustomer(id);
        List<Customer> list = repository.findAllCustomers();
        models.put("customers",list);
        return "list.xhtml";
    }


}
