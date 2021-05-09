package com.nickmcconnell.p0.models;

public class UserAccount {

    private int id;
    private String accountType;
    private float balance;

    public UserAccount(){super();}

    public UserAccount(int id, String accountType){
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
