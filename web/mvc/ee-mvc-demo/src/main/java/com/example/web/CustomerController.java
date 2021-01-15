package com.example.web;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.ws.rs.*;
import java.util.List;
import javax.mvc.View;

@Path("customers")
@Controller
@RequestScoped
public class CustomerController {

   // @Inject
   // private Greeting greeting;

    @GET
    @Path("hello")
    @Controller
    public String hello() {
        models.put("greeting", new Greeting("Hello there!"));
        return "hello.jsp";
    }

    @Inject
    private Models models;

    @Inject
    CustomerRepository repository;

    @GET
    public String listCustomers() {
          List<Customer> list = repository.findAllCustomers();
          models.put("customers",list);
          System.out.println("Called form!");
          return "list.jsp";
    }

    @Path("new")
    @Controller
    @GET
    public String preAdd() {
        return "insert.jsp";
    }

    @Path("new")
    @POST
    public String createCustomer(@BeanParam Customer customer) {
        repository.createCustomer(customer);
        List<Customer> list = repository.findAllCustomers();
        models.put("customers",list);
        return "list.jsp";

    }

    @Path("edit/{id}")
    @GET
    public String preEdit(@PathParam("id") Long id) {
        Customer customer = repository.findCustomerById(id);
        models.put("customer",customer);

        return "edit.jsp";
    }

    @Path("edit/{id}")
    @POST
    public String edit(@PathParam("id") Long id, @BeanParam Customer customer) {
        customer.setId(id);
        repository.updateCustomer(customer);
        List<Customer> list = repository.findAllCustomers();
        models.put("customers",list);
        return "list.jsp";
    }


    @Path("delete/{id}")
    @GET
    public String delete(@PathParam("id") Long id) {
        repository.deleteCustomer(id);
        List<Customer> list = repository.findAllCustomers();
        models.put("customers",list);
        return "list.jsp";
    }


}
