package com.mastertheboss.rest;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class WebContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        if (!new File(Config.UPLOAD_FOLDER).exists()) {
            new File(Config.UPLOAD_FOLDER).mkdir();
        }

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}