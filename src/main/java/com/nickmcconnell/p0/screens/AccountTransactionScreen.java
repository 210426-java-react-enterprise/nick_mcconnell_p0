package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountTransactionScreen extends Screen{
    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;

    public AccountTransactionScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router){
        super("AccountTransactionScreen", "/accounttransaction");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
    }

    @Override
    public void render(){
        System.out.println("in acounts trans screen");
    }
}
