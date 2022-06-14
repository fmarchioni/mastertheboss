package com.sample.action;


import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
    private String username;
    private String password;

    public String authenticate() {

        if (this.username.equals("admin")
                && this.password.equals("admin")) {
            return "success";
        } else {
            addActionError(getText("error.login"));
            return "error";
        }
    }

    public String logout() {
        return "logout";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}