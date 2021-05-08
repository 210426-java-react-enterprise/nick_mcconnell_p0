package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.util.AppState;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountsViewScreen extends Screen{
    private AccountDAO accountDao;
    private BufferedReader consoleReader;
//    private UserService userService;
    private ScreenRouter router;

    public AccountsViewScreen(BufferedReader consoleReader,ScreenRouter router, AccountDAO accountDao){
        super("AccountsViewScreen", "/viewaccounts");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountDao = accountDao;
    }

    @Override
    public void render() {

        //get accounts
            // sql call
            AccountDAO userAccounts = new AccountDAO();

            userAccounts.getUserAccounts();
            //if no accounts navigate to create account
                // mayb ask to create y or not
                    // y to createscreen
                    // no to account home screen
            //if there are accounts
                // display
            //then y/not to return to accounts home

        System.out.println("in view acounts screen");
        AppUser currentUser = router.getCurrentUser();
        System.out.println("current user "+ currentUser);

    }
}
