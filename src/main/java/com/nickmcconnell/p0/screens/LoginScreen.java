package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.LoginCredentials;
import com.nickmcconnell.p0.services.UserService;

import java.io.BufferedReader;

public class LoginScreen extends Screen{
//    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;
    private UserService userService;

    public LoginScreen(BufferedReader consoleReader, UserService userService){
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    @Override
    public void render(){

        try{
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("------------------------");

            System.out.println("Username: ");
            username = consoleReader.readLine();

            System.out.println("Password: ");
            password = consoleReader.readLine();

//            AppUser userLogin = new AppUser(username, password);
            AppUser newUser = userService.login(username, password);
            System.out.println("line 39 login screen " + newUser);

        } catch(NullPointerException e){
            System.out.println("line 56 of login screen");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
