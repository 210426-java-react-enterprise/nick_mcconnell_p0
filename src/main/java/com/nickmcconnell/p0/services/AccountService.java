package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;

public class AccountService {

    private AccountDAO accountDao;

    public AccountService(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    public UserAccount checkExistingAccount(AppUser currentUser) throws InvalidRequestException {

        UserAccount userAccount = accountDao.getAccount(currentUser);
        System.out.println("In validateGetACcount " + userAccount.getAccountType());
        if(userAccount.getAccountType() != null){
            System.out.println("in iff ot check existing account");
            throw new InvalidRequestException("You have already created an account.");
        }
        return userAccount;
    }

    public void validateAccountCreate(String accountType, int id) throws InvalidRequestException {
        System.out.println("in servcies arroucn create");
        boolean success = true;
        success = accountDao.createAccount(accountType, id);

        if(!success){
            throw new InvalidRequestException("Account creation failed.");
        }
    }

    public UserAccount validateGetAccount(AppUser currentUser) throws InvalidRequestException{
        System.out.println("in services get account");
        UserAccount userAccount = accountDao.getAccount(currentUser);

        if(userAccount == null){
            throw new InvalidRequestException("An error occurred while retrieving your account.");
        }
        return userAccount;

    }



//    public

}
