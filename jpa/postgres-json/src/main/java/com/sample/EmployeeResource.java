package com.sample;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return Response.ok(employees).build();
    }
 
    @POST
    public Response createEmployee(Employee employee) {
 
        employeeService.insertEmployee(employee);
        
        return Response.ok("Employee created").build();
    }

    @GET
    @Path("/byrole")
    public List<Employee> findByRole(@QueryParam("role") String role) {
         return employeeService.findByRole(role);
    }    
}
