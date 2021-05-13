package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;

/**
 * Service class handling validation pertaining to account creation.
 */
public class AccountService {

    private AccountDAO accountDao;

    public AccountService(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    //Validates  if an account already exists
    public void checkExistingAccount(AppUser currentUser) throws InvalidRequestException {

        UserAccount userAccount = accountDao.getAccount(currentUser);
        if (userAccount.getAccountType() != null) {
            throw new InvalidRequestException("You have already created an account.");
        }
    }

    //validates an account has been created
    public void validateAccountCreate(String accountType, int id) throws InvalidRequestException {
        boolean success = true;
        success = accountDao.createAccount(accountType, id);

        if (!success) {
            throw new InvalidRequestException("Account creation failed.");
        }
    }

    //validates an account was found in db
    public UserAccount validateGetAccount(AppUser currentUser) throws InvalidRequestException {

        UserAccount userAccount = accountDao.getAccount(currentUser);

        if (userAccount == null) {
            throw new InvalidRequestException("An error occurred while retrieving your account.");
        }
        return userAccount;
    }

    //validates initialization of account balance
    public boolean validateInitialBalance(int id) throws InvalidRequestException {
        boolean success = true;
        success = accountDao.createInitialBalance(id);
        if (!success) {
            throw new InvalidRequestException("Your account initialization failed.");
        }
        return true;
    }
}
