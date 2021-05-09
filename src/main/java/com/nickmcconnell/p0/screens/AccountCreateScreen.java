package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountCreateScreen extends Screen {
    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;

    public AccountCreateScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router){
        super("AccountCreateScreen", "/createaccount");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
    }

    @Override
    public void render(){
        System.out.println("in create account screen");

    }
}
