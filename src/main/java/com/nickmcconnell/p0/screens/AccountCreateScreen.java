package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.services.AccountService;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountCreateScreen extends Screen {
    private BufferedReader consoleReader;
    private AccountService accountService;
    private ScreenRouter router;
    private AccountDAO accountDAO;

    public AccountCreateScreen(BufferedReader consoleReader, AccountService accountService, ScreenRouter router, AccountDAO accountDao){
        super("AccountCreateScreen", "/createaccount");
        this.consoleReader = consoleReader;
        this.accountService = accountService;
        this.router = router;
        this.accountDAO= accountDao;
    }

    @Override
    public void render(){
        AppUser currentUser = router.getCurrentUser();
        UserAccount currentAccount = accountDAO.getUserAccounts(currentUser);

        if(currentAccount.getAccountType() != null){
            System.out.println("You have already created an account.");
            System.out.println("------------------------------------");
            router.navigate("/accounthome");
        }
        try{
            System.out.println("Choose an account type to open:");
            System.out.println("1) Checking");
            System.out.println("2) Savings");
            String userSelection = consoleReader.readLine();

        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
