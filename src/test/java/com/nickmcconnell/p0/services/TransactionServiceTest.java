package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.TransactionDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class TransactionServiceTest {

    private TransactionService sut;
    private TransactionDAO mockTransactionDao;

    @Before
    public void SetUp() {
        mockTransactionDao = mock(TransactionDAO.class);

        sut = new TransactionService(mockTransactionDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockTransactionDao = null;
    }

    @Test
    public void test_withdrawalValidationFail() throws InvalidRequestException {

        try {
            sut.validateWithdrawal("same", "same", 1, 0);
        } catch (InvalidRequestException e) {
            assertTrue(e instanceof InvalidRequestException);
        }
    }

    @Test
    public void test_withdrawalValidationPass() {
        float expected = 5;
        float balance = sut.validateWithdrawal("same", "same", 5, 10);
        assertEquals(expected, balance, 0);
    }

    @Test
    public void test_validateTransactionAmtFail() throws InvalidRequestException {
        float transactionAmount = -1;
        try {
            sut.validateTransactionAmt(transactionAmount);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidRequestException);
        }
    }

    @Test
    public void test_validateTransactionAmtPass() {
        float transactionAmount = 1;

        sut.validateTransactionAmt(transactionAmount);

    }


}
