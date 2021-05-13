package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.AccountDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.services.AccountService;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    private AccountService sut;
    private AccountDAO mockAccountDao;
    private AppUser appUser;

    @Before
    public void SetUp() {
        mockAccountDao = mock(AccountDAO.class);
        sut = new AccountService(mockAccountDao);
    }

    @After
    public void TearDown() {
        sut = null;
        mockAccountDao = null;
    }

    @Test
    public void test_checkExistingAccountPass() {
        UserAccount invalidAccount = new UserAccount(0, null);
        AppUser appUser = new AppUser(1, "un", "pw", "em", "fn", "ln", 30);

        when(mockAccountDao.getAccount(appUser)).thenReturn(invalidAccount);

        sut.checkExistingAccount(appUser);
    }

    @Test
    public void test_checkExistingAccountFail() throws InvalidRequestException {
        UserAccount invalidAccount = new UserAccount(0, "Account");
        AppUser appUser = new AppUser(1, "un", "pw", "em", "fn", "ln", 30);

        when(mockAccountDao.getAccount(appUser)).thenReturn(invalidAccount);
        try {
            sut.checkExistingAccount(appUser);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidRequestException);
        }
    }

    @Test
    public void test_validateAccountCreatePass() {
        String account = "account";
        int id = 1;
        when(mockAccountDao.createAccount(account, id)).thenReturn(true);
        sut.validateAccountCreate(account, id);
    }

    @Test
    public void test_validateAccountCreateFail() throws InvalidRequestException {
        String account = "account";
        int id = 1;
        when(mockAccountDao.createAccount(account, id)).thenReturn(false);

        try {
            sut.validateAccountCreate(account, id);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidRequestException);
        }
    }

    @Test
    public void test_validateGetAccountPass() {
        UserAccount userAccount = new UserAccount(1, "Account");
        AppUser appUser = new AppUser(1, "un", "pw", "em", "fn", "ln", 30);
        when(mockAccountDao.getAccount(appUser)).thenReturn(userAccount);

        sut.validateGetAccount(appUser);
    }

    @Test
    public void test_validateGetAccountFail() throws InvalidRequestException {
        AppUser appUser = new AppUser(1, "un", "pw", "em", "fn", "ln", 30);
        when(mockAccountDao.getAccount(appUser)).thenReturn(null);
        try {
            sut.validateGetAccount(appUser);
        } catch (Exception e) {
            assertTrue(e  instanceof InvalidRequestException);
        }
    }

    @Test
    public void test_validateInitialBalancePass(){
        int id = 1;
        when(mockAccountDao.createInitialBalance(id)).thenReturn(true);
        sut.validateInitialBalance(id);
    }

    @Test
    public void test_validateInitialBalanceFail() throws InvalidRequestException{
        int id = 1;
        when(mockAccountDao.createInitialBalance(id)).thenReturn(true);
        try{
            sut.validateInitialBalance(id);
        }catch(Exception e){
            assertTrue(e instanceof InvalidRequestException);
        }
    }


}
