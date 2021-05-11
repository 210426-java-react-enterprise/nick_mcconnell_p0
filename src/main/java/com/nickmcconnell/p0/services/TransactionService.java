package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.TransactionDAO;

public class TransactionService {

    private TransactionDAO transactionDao;

    public TransactionService(TransactionDAO transactionDao){this.transactionDao=transactionDao;}

    public void validateDeposit(int id, float deposit){

        boolean update = transactionDao.updateBalance(id, deposit);
        System.out.println("Deposit status: " + update);
    }

    public void validateWithdrawl(int id, float deposit){

        boolean update = transactionDao.updateBalance(id, deposit);
        System.out.println("Deposit status: " + update);
    }



}
