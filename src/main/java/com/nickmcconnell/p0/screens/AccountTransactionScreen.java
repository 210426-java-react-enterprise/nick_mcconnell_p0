package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.models.UserAccountAndBalance;
import com.nickmcconnell.p0.services.AccountService;
import com.nickmcconnell.p0.services.TransactionService;
import com.nickmcconnell.p0.ScreenRouter;

import java.io.BufferedReader;

public class AccountTransactionScreen extends Screen {
    private BufferedReader consoleReader;
    //    private UserService userService;

    private AccountService accountService;
    private ScreenRouter router;
    private AccountDAO accountDao;
    private TransactionService transactionService;

    public AccountTransactionScreen(BufferedReader consoleReader, AccountService accountService, ScreenRouter router, AccountDAO accountDao, TransactionService transactionService) {
        super("AccountTransactionScreen", "/accounttransaction");
        this.consoleReader = consoleReader;
        this.accountService = accountService;
        this.router = router;
        this.accountDao = accountDao;
        this.transactionService = transactionService;

    }

    @Override
    public void render() {
        System.out.println("top of trans screen");
        AppUser currentUser = router.getCurrentUser();
        UserAccount currentAccount = accountDao.getAccount(currentUser);
        UserAccountAndBalance userAccountAndBalance = accountDao.getAccountAndBalance(currentUser);

        try {
            if (currentAccount.getAccountType() == null) {
                System.out.println("You have not created an account.");
                router.navigate("/accounthome");
            } else {
                System.out.printf("Account - %s: $%.2f\n", userAccountAndBalance.getAccountType(), userAccountAndBalance.getBalance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Choose transaction type:");
        System.out.println("------------------------");
        System.out.println("1) Deposit");
        System.out.println("2) Withdrawal");
        System.out.println("3) Account Home");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();
            String transactionType = "";
            String withdrawal = "Withdrawal";
            String deposit = "Deposit";
            float transactionAmount = 0;

            switch (userSelection) {
                case "1":
                    transactionType = deposit;
                    break;
                case "2":
                    transactionType = withdrawal;
                    break;
                case "3":
                    router.navigate("/accounthome");
                default:
                    System.out.println("Invalid entry");
                    router.navigate("/accounttransaction");
            }

            System.out.printf("How much would you like to %s?\n", transactionType);
            transactionAmount = Float.parseFloat(consoleReader.readLine());
            transactionService.validateTransactionAmt(transactionAmount);

            float withdrawalAmount = transactionService.validateWithdrawal(transactionType, withdrawal, transactionAmount, userAccountAndBalance.getBalance());

            if (transactionType.equals("Deposit")) {
                Float depositBalanceSum = Float.sum(transactionAmount, userAccountAndBalance.getBalance());
                transactionService.validateDeposit(userAccountAndBalance.getId(), depositBalanceSum);

            } else {
                transactionService.executeWithdrawal(userAccountAndBalance.getId(), withdrawalAmount);

            }
            router.navigate("/viewaccounts");
        } catch (NumberFormatException e) {
            System.err.println("You provided an incorrect value for your transaction.");
            router.navigate("/accounttransaction");
        } catch (InvalidRequestException e) {
            e.printStackTrace();
            router.navigate("/accounttransaction");
        } catch (ResourcePersistenceException e) {
            e.printStackTrace();
            router.navigate("/accounttransaction");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
