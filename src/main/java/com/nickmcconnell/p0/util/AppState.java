package com.nickmcconnell.p0.util;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.daos.TransactionDAO;
import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.screens.*;
import com.nickmcconnell.p0.services.AccountService;
import com.nickmcconnell.p0.services.TransactionService;
import com.nickmcconnell.p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private AccountService accountService;
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application");

        appRunning = true;

        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();
        final UserService userService = new UserService(userDao);
        final AccountDAO accountDao = new AccountDAO();
        final AccountService accountService = new AccountService(accountDao);
        final TransactionDAO transactionDao = new TransactionDAO();
        final TransactionService transactionService = new TransactionService(transactionDao);
        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, userService, router))
                .addScreen(new RegisterScreen(consoleReader, userService, router))
                .addScreen(new AccountHomeScreen(consoleReader, userService, router))
                .addScreen(new AccountsViewScreen(consoleReader, router, accountDao))
                .addScreen(new AccountCreateScreen(consoleReader, accountService, router))
                .addScreen(new AccountTransactionScreen(consoleReader, accountService, router, accountDao, transactionService));

        System.out.println("Application Initialized");
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }


}
