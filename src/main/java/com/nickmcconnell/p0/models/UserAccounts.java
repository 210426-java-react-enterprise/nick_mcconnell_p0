package com.nickmcconnell.p0.models;

public class UserAccounts {

    private int id;
    private String accountType;
    private int customerId;

    public UserAccounts(int id, String accountType, int customerId){
        this.id = id;
        this.accountType = accountType;
        this.customerId = customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
