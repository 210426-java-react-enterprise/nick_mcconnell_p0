package com.nickmcconnell.p0.models;

public class AppUser {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int age;

    public AppUser(String username, String password, String email, String firstName, String lastName, int age){
        System.out.println("AppUser constructor invoked!");

        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toFileString(){
        return String.format("%s;%s;%s;%s;%s;%d;", username, password, email, firstName, lastName, age);
    }

}
