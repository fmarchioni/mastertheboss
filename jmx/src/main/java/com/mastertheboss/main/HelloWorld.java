package com.mastertheboss.main;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HelloWorld {
	public static void main(String[] args) throws Exception {
		status();
	}

	public static void status() throws Exception{
		String host = "localhost";
		int port = 9990;  // management-http port
		String urlString = "service:jmx:remote+http://" + host + ":" + port;
		System.out.println("\n\n\t****  urlString: "+urlString);;
		JMXServiceURL serviceURL = new JMXServiceURL(urlString);

		Map map = new HashMap();
		String[] credentials = new String[] { "admin", "admin" };
		map.put("jmx.remote.credentials", credentials);
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, map);

		MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

        Set<ObjectInstance> instances = connection.queryMBeans(null, null);
        Iterator<ObjectInstance> iterator = instances.iterator();

        while (iterator.hasNext()) {
            ObjectInstance instance = iterator.next();
            if (instance.getObjectName().toString().startsWith("jboss.as:subsystem=datasources,data-source=") &&
                    (!instance.getObjectName().toString().contains(",statistics")))
            System.out.println("Object Name:" + instance.getObjectName());

        }
		jmxConnector.close();

	}

}



