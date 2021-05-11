package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.daos.TransactionDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.models.UserAccountAndBalance;
import com.nickmcconnell.p0.services.AccountService;
import com.nickmcconnell.p0.services.TransactionService;
import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

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
                default:
                    System.out.println("Invalid entry");
                    router.navigate("/accounthome");
            }

            System.out.printf("How much would you like to %s?\n", transactionType);
            transactionAmount = Float.parseFloat(consoleReader.readLine());
            //call service function
            if (transactionAmount < 0) {
                System.out.println("You must enter an amount greater than 0.");
                router.navigate("/accounthome");
            } else if (transactionType.equals(withdrawal) && transactionAmount > userAccountAndBalance.getBalance()) {
                System.out.println("Invalid amount. Withdrawal amount greater than account balance.");
                router.navigate("/accounthome");
            }

            System.out.println("trans type and trans amount " + transactionType + " " + transactionAmount);

            if (transactionType.equals("Deposit")) {
                System.out.println("deposit if");
                Float depositBalanceSum = Float.sum(transactionAmount, userAccountAndBalance.getBalance());
                transactionService.validateDeposit(userAccountAndBalance.getId(), depositBalanceSum);

            } else {
                System.out.println("withdrawal if");
                float withdrawalBalanceSumDiff = userAccountAndBalance.getBalance() - transactionAmount;
                System.out.println("withhdrwalbalancesum diff: " + withdrawalBalanceSumDiff);
                transactionService.validateWithdrawl(userAccountAndBalance.getId(), withdrawalBalanceSumDiff);

            }
            router.navigate("/accounthome");
        } catch (NumberFormatException e) {
            System.err.println("You provided an incorrect value for your transaction.");
            this.render(); // => apparently not the best practice, just put router in here and => "/register"
        }catch(InvalidRequestException | ResourcePersistenceException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
