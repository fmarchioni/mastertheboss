package com.mastertheboss.jakartadata;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
 

 
@ApplicationScoped
public class CustomerService {

 
    @Inject
    CustomerRepository repository;

    @Transactional
    public void createCustomer(Customer customer) {
        repository.addCustomer(customer);
    }

    @Transactional
    public Response deleteCustomerById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            repository.removeCustomer(customer.get());
            return Response.noContent().build(); // HTTP 204 for successful deletion
        }
        return Response.status(404).build(); // HTTP 404 if customer not found
    }

   public Response findCustomerById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        return customer.map(c -> Response.ok(c).build())
                       .orElse(Response.status(404).build());
    }

    @Transactional
    public Response updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = repository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setSurname(updatedCustomer.getSurname());
            repository.updateCustomer(customer);
            return Response.ok(customer).build(); // Return updated customer
        }
        return Response.status(404).build(); // Return 404 if customer not found
    }

    public List<Customer> findAllCustomers() {
        return repository.all();
    }

    public List<Customer> findAllCustomersByName(String name) {
        return repository.byName(name);
    }
}