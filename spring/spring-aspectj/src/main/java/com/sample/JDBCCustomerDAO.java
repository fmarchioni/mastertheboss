package com.sample;
import java.util.List;


public interface JDBCCustomerDAO {

    public void insert(Customer customer);

    public List<Customer> findAll();

    public void createTable();
    public void dropTable();
}
