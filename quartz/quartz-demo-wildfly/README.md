Quartz Demo on WildFly 10
===================================================
Author: Francesco Marchioni  
Level: Intermediate  
Technologies: Servlet, Quartz  

What is it?
-----------

This demo shows how to trigger the Quartz scheduler on WildFly application server. Launch the TestQuartz Servlet to schedule a Quartz Job.

More info at the following URL <http://www.mastertheboss.com/jboss-frameworks/jboss-quartz/quartz-2-tutorial-on-jboss-as-7/>.

Start the WildFly Server
-------------------------

1. Open a command prompt and navigate to the root of the WildFly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/standalone.sh
        For Windows: WILDFLY_HOME\bin\standalone.bat


Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/quartz-demo.war` to the running instance of the server.


Access the application 
---------------------

The application will be running at the following URL <http://localhost:8080/quartz-demo/TestQuartz/>.


Undeploy the Archive
--------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


