/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastertheboss.jmsbrowser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mastertheboss.jmsbrowser.bean.MessageDTO;
import com.mastertheboss.jmsbrowser.bean.QueueDTO;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.ejb.Stateless;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.OperationBuilder;
import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.dmr.ModelNode;

@Stateless
public class EJBBrowser {


    @Inject ConnectionFactory cf;
    Properties properties;
    
    public List<MessageDTO> browseMessage(String q) {
        List<MessageDTO> list = new ArrayList();
       
        Queue queue = null;
        try {
      
            queue = (Queue) new InitialContext().lookup(q);
        } catch (NamingException ex) {
            Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection connection = null;
        Session session = null;

        try {
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser browser = session.createBrowser(queue);

            Enumeration messageEnum = browser.getEnumeration();
            while (messageEnum.hasMoreElements()) {
                TextMessage message = (TextMessage) messageEnum.nextElement();
                list.add(new MessageDTO(message.getJMSMessageID(), message.getText(), message.getJMSPriority()));

            }

            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;

    }
  
    public void sendTestMessages(String strQueue, int testmessages) {
        Queue queue = null;
        try {
            queue = (Queue) new InitialContext().lookup(strQueue);
        } catch (NamingException ex) {
            Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        TextMessage message = null;

        try {

            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            producer = session.createProducer(queue);

            connection.start();

            // Send the specified number of messages
            for (int i = 0; i < testmessages; i++) {
                message = session.createTextMessage("Message sent by Browser at " + new java.util.Date());
                producer.send(message);
                
                Logger.getLogger(EJBBrowser.class.getName()).log(Level.INFO, null, "sent message " + (i + 1));
            }
        } catch (Exception exc) {
            Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, exc);
        } finally {
            try {
                connection.close();
            } catch (JMSException ex) {
                Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public List<QueueDTO> getListQueues() {

        String host = properties.getProperty("host");
        int port = Integer.parseInt(properties.getProperty("port"));
        System.out.println("PORT---->"+port);
        final ModelNode req = new ModelNode();
        req.get(ClientConstants.OP).set("read-children-resources");
        req.get("child-type").set("jms-queue");
 
        String mode = properties.getProperty("mode");
        if (mode.equals("domain")) {
            req.get(ClientConstants.OP_ADDR).add("profile", properties.getProperty("profile"));
        }
        req.get(ClientConstants.OP_ADDR).add("subsystem", "messaging");
        req.get(ClientConstants.OP_ADDR).add("hornetq-server", "default");

        ModelControllerClient client = null;
        List<QueueDTO> listQueues = new ArrayList();
         
        try {
            client = ModelControllerClient.Factory.create(InetAddress.getByName(host), port);
            final ModelNode resp = client.execute(new OperationBuilder(req).build());

            List<ModelNode> list = resp.get(ClientConstants.RESULT).asList();

            for (ModelNode node : list) {

                String json = node.toJSONString(true);
                JsonObject res = new JsonParser().parse(json).getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> es = res.entrySet();
                for (Map.Entry<String, JsonElement> entry : es) {
                    QueueDTO queue = new QueueDTO();
                    queue.setName(entry.getKey());

                    JsonElement element = entry.getValue();
                    JsonObject obj = element.getAsJsonObject();
                    JsonElement binding = obj.get("entries");
                    queue.setEntry(binding.getAsString());

                    listQueues.add(queue);
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
       return listQueues;
    }
	
    public void consumeMessages(String strQueue) {
         try {
             
             
             Connection connection = null;
             Session session = null;
             MessageConsumer consumer = null;
             
             connection = cf.createConnection();
             session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             Queue queueConsume=null;
             try {
                 queueConsume = (Queue)new InitialContext().lookup(strQueue);
             } catch (NamingException ex) {
                 Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
             }
             consumer = session.createConsumer(queueConsume);
             
             connection.start();
            
             javax.jms.Message msg = consumer.receiveNoWait();
           
             Logger.getLogger(EJBBrowser.class.getName()).log(Level.INFO, "Received "+msg, "");
             connection.close();
             
         } catch (JMSException ex) {
             Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        @PostConstruct
	private void readPropertyFile()   {
        try {
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("jms.properties");
            
            properties = new Properties();
            
            
            
            // load the inputStream using the Properties
            properties.load(inputStream);
            // get the value of the property

            Logger.getLogger(EJBBrowser.class.getName()).log(Level.INFO, "Read Properties ", "");
        } catch (IOException ex) {
            Logger.getLogger(EJBBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
	        
		
	}
}
