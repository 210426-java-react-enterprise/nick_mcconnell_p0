package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.ConnectionFactory;
import com.nickmcconnell.p0.Driver;
import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import org.junit.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao;


    @Before
    public void SetUp() {
        mockUserDao = mock(UserDAO.class);
        sut = new UserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;
    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword(){

        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(true);

        sut.register(new AppUser(0,"un", "pw", "email", "fn", "ln", 18));
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername(){

        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);
        try{
            sut.register(new AppUser(0,"un", "pw", "email", "fn", "ln", 18));
        }catch (ResourcePersistenceException e){
            assertTrue(e instanceof ResourcePersistenceException);
        }finally{
            verify(mockUserDao,times(0)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser(){
        //Arrange
        AppUser invalidUser = new AppUser(0,"","","","","",0);
        //Act
        sut.register(invalidUser);
        //Assert
        verify(mockUserDao,times(0)).isUsernameAvailable(anyString());
        verify(mockUserDao, times(0)).isEmailAvailable((anyString()));
        verify(mockUserDao, times(0)).save(any());
    }

    @Test
    public void test_loginCredentialsInvalid() {

         boolean validate = sut.isLoginValid(null, null);
        assertFalse(validate);
    }

    @Test
    public void test_loginCredentialValid() {

       boolean validate = sut.isLoginValid("username", "password");
        assertTrue(validate);
    }
}

