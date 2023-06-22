package com.mastertheboss;

import java.util.List;


import com.mastertheboss.domain.Employee;
import com.mastertheboss.domain.Department;
import com.mastertheboss.util.*;

import org.hibernate.*;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class App {
        private static final Logger logger = LoggerFactory.getLogger(App.class);
public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Department department = new Department("java");
        session.persist(department);

        session.persist(new Employee("Jakab Gipsz",department));
        session.persist(new Employee("Captain Nemo",department));

        session.getTransaction().commit();

        Query q = session.createQuery("From Employee ", Employee.class);

        List<Employee> resultList = q.list();
        System.out.println("num of employees:" + resultList.size());
        for (Employee next : resultList) {
                System.out.println("next employee: " + next);
        }

    }

}
