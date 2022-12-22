package com.mastertheboss.jaxrs.model;


import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;


public class ParamBean {
    @FormParam("username")
    String username;

    @FormParam("email")
    String email;

    @HeaderParam("Content-Type")
    String contentType;


    public String dumpData() {
        return "ParamBean{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

}
