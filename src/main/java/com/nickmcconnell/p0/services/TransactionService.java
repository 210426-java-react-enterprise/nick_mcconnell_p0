package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.TransactionDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;

public class TransactionService {

    private TransactionDAO transactionDao;

    public TransactionService(TransactionDAO transactionDao){this.transactionDao=transactionDao;}
    public void executeDeposit(int id, float deposit){
        transactionDao.updateBalance(id, deposit);
    }

    public void executeWithdrawal(int id, float deposit){

        transactionDao.updateBalance(id, deposit);
    }

    public float validateWithdrawal(String transactionType, String withdrawal, float transactionAmount, float balance) throws InvalidRequestException{
        if (transactionType.equals(withdrawal) && transactionAmount > balance){
            throw new InvalidRequestException("Your withdrawal amount cannot exceed the balance of your account.");
        };
        float withdrawalBalanceSumDiff = balance - transactionAmount;
        return withdrawalBalanceSumDiff;
    }

    public void validateTransactionAmt(float transactionAmount) throws InvalidRequestException {
        if(transactionAmount < 0) {
            throw new InvalidRequestException("Your transaction amount cannot be less than $0.00!");
        }
    }



}
