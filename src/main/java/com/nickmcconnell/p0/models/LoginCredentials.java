package com.nickmcconnell.p0.models;

public class LoginCredentials {
    private String username;
    private String password;

    public LoginCredentials(String username, String password){
        System.out.println("LoginCredentials constructor invoked");
        this.username = username;
        this.password = password;
    }

    public LoginCredentials(){super();}

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
