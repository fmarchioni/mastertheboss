package com.mastertheboss.rest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class WebContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("UPLOAD folder "+Config.UPLOAD_FOLDER);
        if (!new File(Config.UPLOAD_FOLDER).exists()) {
            new File(Config.UPLOAD_FOLDER).mkdir();
            System.out.println("CREATA!");
        }

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //. . .
    }
}