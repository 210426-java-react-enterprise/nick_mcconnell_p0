package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.TransactionDAO;

public class TransactionService {

    private TransactionDAO transactionDao;

    public TransactionService(TransactionDAO transactionDao){this.transactionDao=transactionDao;}

    public void checkWithdrawal(int id, float deposit){

        boolean update = transactionDao.accountDeposit(id, deposit);
        System.out.println("transaction service: in checkwithdrawl: " + update);
    }

}
