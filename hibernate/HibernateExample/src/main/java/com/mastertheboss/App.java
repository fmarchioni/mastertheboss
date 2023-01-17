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
        session.save(department);

        session.save(new Employee("Jakab Gipsz",department));
        session.save(new Employee("Captain Nemo",department));

        session.getTransaction().commit();

        Query q = session.createQuery("From Employee ");

        List<Employee> resultList = q.list();
        logger.debug("num of employess:" + resultList.size());
        for (Employee next : resultList) {
                logger.debug("next employee: " + next);
        }

    }

}
