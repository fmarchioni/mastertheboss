/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastertheboss.jmsbrowser.bean;

/**
 *
 * @author francesco
 */
public class MessageDTO {
    public String id;
    public String message;
    public int priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public MessageDTO(String id, String message, int priority) {
        this.id = id;
        this.message= message;
        this.priority = priority;
    }
     public MessageDTO() {
         
     }
}
