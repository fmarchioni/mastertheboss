package com.mastertheboss.springboot.SpringBootJMS;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class RESTController {

    @Autowired private JmsTemplate jmsTemplate;

    @RequestMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        try {
            jmsTemplate.convertAndSend("springbootQueue",message);
            return "message sent!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in sending message!";
        }


    }

    @RequestMapping(path="/sendCustomer", method = RequestMethod.POST, consumes = {"application/json"})
    public void sendCustomer(@RequestBody Customer customer) {
        System.out.println("Sending a transaction.");
        // Post message to the message queue named "OrderTransactionQueue"
        jmsTemplate.convertAndSend("springbootQueue", customer);
    }
}