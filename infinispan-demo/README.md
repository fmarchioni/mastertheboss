Example application using Infinispan cache in Library mode 
===================================
Author: Francesco Marchioni 
Technologies: Infinispan, CDI
Summary: This application demonstrates how to configure and access Infinispan cache in a simple JSF application.
Product Versions: Infinispan 8.2, WildFly 10 

What is it?
-----------

This is a sample application that uses Infinispan Cache in Library mode to store some Java Objects

System requirements
-------------------

All you need to build this project is Java SDK 1.8 or better, Maven 3.0 or better.

The application this project produces is designed to be run on WildFly 8/9/10

 

Build and Deploy the Application 
------------------------------------------------


   mvn clean package wildfly:deploy
        
This will deploy `target/infinispan-demo.war` to the running instance of the server.
 

Access the application
---------------------

The application will be running at the following URL: <http://localhost:8080/infinispan-demo/home.xhtml/>

