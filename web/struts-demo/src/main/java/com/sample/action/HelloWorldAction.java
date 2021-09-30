package com.sample.action;


 

import com.opensymphony.xwork2.ActionSupport;
import com.sample.model.MessageStore;

public class HelloWorldAction extends ActionSupport {
    private MessageStore messageStore;

    public String execute() {
        messageStore = new MessageStore() ;
        
        return SUCCESS;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }
}