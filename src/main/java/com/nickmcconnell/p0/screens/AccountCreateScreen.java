package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
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

    public AccountCreateScreen(BufferedReader consoleReader, AccountService accountService, ScreenRouter router, AccountDAO accountDao) {
        super("AccountCreateScreen", "/createaccount");
        this.consoleReader = consoleReader;
        this.accountService = accountService;
        this.router = router;
        this.accountDAO = accountDao;
    }

    @Override
    public void render() {

        AppUser currentUser = router.getCurrentUser();

//        try {
//            accountService.checkExistingAccount(currentUser);
////        UserAccount currentAccount = accountDAO.getAccount(currentUser);
//
////            if (currentAccount.getAccountType() != null) {
////                System.out.println("You have already created an account.");
////                System.out.println("------------------------------------");
////                router.navigate("/accounthome");
////            }
//        } catch (ResourcePersistenceException e) {
//            e.printStackTrace();
//            router.navigate("/accounthome");
//        } catch (Exception e) {
//            e.printStackTrace();
//            router.navigate("/accounthome");
//        }

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


//            System.out.println("accountType "+ accountType);
            //re create accoutns table -- acounts not set to unique
            accountService.validateAccountCreate(accountType, currentUser.getId());
//            success = accountDAO.createAccount(accountType, currentUser.getId());
            //move to services
//            if (!success) {
//                System.out.println("Account creation failed.");
//                router.navigate("/accounthome");
//            }

//            UserAccount userAccount = accountDAO.getAccount(router.getCurrentUser());
            UserAccount userAccount = accountService.validateGetAccount(router.getCurrentUser());
            //DOWN TO HERE
            success = accountDAO.createInitialBalance(userAccount.getId());
            if (!success) {
                System.out.println(userAccount.getAccountType() + " account balance initialization failed.");
                router.navigate("/accounthome");
            }

            if (success) {
                System.out.println("Account creation: Success!");
                router.navigate("/viewaccounts");
            }

        } catch (ResourcePersistenceException | InvalidRequestException e) {
            e.printStackTrace();
            router.navigate("/accounthome");
        } catch (Exception e) {
            e.printStackTrace();
            router.navigate("/accounthome");
        }
    }
}
