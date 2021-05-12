package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.ConnectionFactory;
import com.nickmcconnell.p0.Driver;
import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.services.UserService;
import org.junit.*;
import org.mockito.verification.VerificationMode;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao;
    private Connection mockConnection;
    private ConnectionFactory mockConnectionFactory;
    private AppUser appUser;
//    private MockedStatic<ConnectionFactory> staticMockConnectionFactory;
//    private Logger mockLogger;
//    private MockedStatic<Loger>

    @Before
    public void SetUp() {
//        Driver.app().setLoggingToConsole(false);
        mockUserDao = mock(UserDAO.class);
        mockConnection = mock(Connection.class);
        mockConnectionFactory = mock(ConnectionFactory.class);

//        when(ConnectionFactory.getInstance()).thenReturn(mockConnectionFactory);
        when(mockConnectionFactory.getConnection()).thenReturn(mockConnection);

        sut = new UserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;
        mockConnection = null;
        mockConnectionFactory = null;
    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword() throws SQLException {
        //Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(true);
        //Act
        sut.register(new AppUser("un", "pw", "email", "fn", "ln", 18));
        //Assert
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername(){
        // don't mok the thing you are testing
//        moke the dependencies not what you are testing
        //arange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);
        //act
        try{
            sut.register(new AppUser("un", "pw", "email", "fn", "ln", 18));
        }catch (Exception e){
            assertTrue(e instanceof ResourcePersistenceException);
        }finally{
            verify(mockUserDao,times(0)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerwithInvalidUser(){
        //Arrange
        AppUser invalidUser = new AppUser("","","","","",30);
        //Act
        sut.register(invalidUser);
        //Assert
        verify(mockUserDao,times(0)).isUsernameAvailable(anyString());
        verify(mockUserDao, times(0)).isEmailAvailable((anyString()));
        verify(mockUserDao, times(0)).save(any());
    }





}
//
//    //arange
//    when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);
//        //act
//        try{
//        sut.register(new AppUser("sdf", "pw", "email", "fn", "ln", 18));
//        } catch(Exception e){
//        assertTrue(e instanceof ResourcePersistenceException);
//        }finally {
//        verify(mockConnectionFactory, times)
//        }
////assert
