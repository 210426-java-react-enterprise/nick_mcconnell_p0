package com.nickmcconnell.p0.models;

public class UserAccountAndBalance {
    private int id;
    private String accountType;
    private float balance;

    public UserAccountAndBalance(){super();}

    public UserAccountAndBalance(int id, String accountType, float balance){

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
