#JMS Browser

An example Web application which can be used to browse, consume and send test message to a JMS queue deployed on WildFly

```mvn clean install wildfly:deploy```

Contains a configuration file named jms.properties which can be used to customize the browser:

#Default configuration:

mode=standalone
host=127.0.0.1
port=9990

If you want to switch to domain mode, just change the mode and set the profile to be used:

mode=domain
profile=full
host=127.0.0.1
port=9990

#REST API
The Web application exposes also a REST API to browse messages on a Queue
Usage: http://localhost:8080/Browser/rest/list?queue=[JNDI_BINDING]

Example:

http://localhost:8080/Browser/rest/list?queue=java:/ExampleQueue