package com.sample;


import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Inject
    ConfigurableApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JDBCCustomerDAO dao = (JDBCCustomerDAO) context
                .getBean("jdbcCustomerDAO");

        dao.createTable();
        Customer c = new Customer();
        c.setAge(45);
        c.setName("ABC");
        c.setSurname("DEF");
        dao.insert(c);

        List<Customer> list = dao.findAll();
        for (Customer customer:list)
            System.out.println(customer);

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JDBCCustomerDAO dao = (JDBCCustomerDAO) context
                .getBean("jdbcCustomerDAO");

        dao.dropTable();

    }
}