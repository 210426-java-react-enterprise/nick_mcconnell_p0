package com.nickmcconnell.p0.models;

public class UserBalance {
    private int id;
    private float balance;

    public UserBalance(int id, float balance){
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
