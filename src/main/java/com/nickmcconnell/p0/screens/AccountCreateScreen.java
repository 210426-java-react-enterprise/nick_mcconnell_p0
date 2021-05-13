package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.services.AccountService;
import com.nickmcconnell.p0.ScreenRouter;

import java.io.BufferedReader;

public class AccountCreateScreen extends Screen {
    private BufferedReader consoleReader;
    private AccountService accountService;
    private ScreenRouter router;

    public AccountCreateScreen(BufferedReader consoleReader, AccountService accountService, ScreenRouter router) {
        super("AccountCreateScreen", "/createaccount");
        this.consoleReader = consoleReader;
        this.accountService = accountService;
        this.router = router;
    }

    @Override
    public void render() {

        AppUser currentUser = router.getCurrentUser();

        try {

            accountService.checkExistingAccount(currentUser);

            System.out.println("Choose an account type to open:");
            System.out.println("1) Checking");
            System.out.println("2) Savings");
            System.out.println("3) Account Home");
            System.out.println("> ");

            String userSelection = consoleReader.readLine();

            boolean success = true;
            String accountType = "";

            switch (userSelection) {
                case "1":
                    accountType = "Checking";
                    break;
                case "2":
                    accountType = "Savings";
                    break;
                case "3":
                    router.navigate("/accounthome");
                    break;
                default:
                    System.out.println("Invalid selection.");
                    router.navigate("/createscreen");
            }

            accountService.validateAccountCreate(accountType, currentUser.getId());

            UserAccount userAccount = accountService.validateGetAccount(router.getCurrentUser());
            success = accountService.validateInitialBalance(userAccount.getId());

            if (success) {
                System.out.println("Account creation: Success!");
                System.out.println("+---------------------------+");

                router.navigate("/viewaccounts");
            }

        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
            System.out.println("+---------------------------+");
            router.navigate("/viewaccounts");
        } catch (Exception e) {
            router.navigate("/viewaccounts");
        }
    }
}
