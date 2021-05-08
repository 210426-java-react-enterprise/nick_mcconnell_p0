package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.util.AppState;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountsViewScreen extends Screen{

    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;

    public AccountsViewScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router){
        super("AccountsViewScreen", "/viewaccounts");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
    }

    @Override
    public void render() {

        System.out.println("in view acounts screen");
        AppUser currentUser = router.getCurrentUser();
        System.out.println("current user "+ currentUser);

    }
}
