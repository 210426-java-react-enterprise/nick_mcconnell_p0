package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.models.UserAccountAndBalance;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountsViewScreen extends Screen {
    private AccountDAO accountDao;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public AccountsViewScreen(BufferedReader consoleReader, ScreenRouter router, AccountDAO accountDao) {
        super("AccountsViewScreen", "/viewaccounts");
        this.consoleReader = consoleReader;
        this.router = router;
        this.accountDao = accountDao;
    }

    @Override
    public void render() {

        AppUser currentUser = router.getCurrentUser();
        UserAccount currentAccount = accountDao.getAccount(currentUser);

        try{
            if(currentAccount.getAccountType() == null){
                System.out.println("You have not created an account.");

            } else {
                UserAccountAndBalance userAccountAndBalance = accountDao.getAccountAndBalance(currentUser);
                System.out.printf("Account - %s: $%.2f\n",userAccountAndBalance.getAccountType(),userAccountAndBalance.getBalance());
            }
            router.navigate("/accounthome");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
