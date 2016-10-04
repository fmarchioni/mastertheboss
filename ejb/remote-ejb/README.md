ejb-remote: Remote EJB Client Example for WildFly
=====================================
Author: Francesco Marchioni  
Level: Intermediate  
Technologies: EJB, JNDI  
Source: <http://http://www.mastertheboss.com/jboss-server/jboss-as-7/jboss-as-7-remote-ejb-client-tutorial/>  

How to run it
-----------

1. Build and deploy the server project with:    

    * Navigate to the server-side subdirectory:
            cd ejb-remote-server
    * Build the EJB and client interfaces JARs and install them in your local Maven repository.

            mvn clean install        
    * Deploy the EJB JAR to your server. 

            mvn wildfly:deploy
2. Build and run the client application
    * Navigate to the client subdirectory:

            cd ../client-basic
    * Compile the client code

            mvn clean install
    * Execute the client application within Maven

            mvn exec:exec
