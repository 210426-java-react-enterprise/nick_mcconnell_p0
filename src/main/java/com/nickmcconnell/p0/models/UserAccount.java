package com.nickmcconnell.p0.models;

public class UserAccount {

    private int id;
    private String accountType;

    public UserAccount(){super();}

    public UserAccount(int id, String accountType){
        this.id = id;
        this.accountType = accountType;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
