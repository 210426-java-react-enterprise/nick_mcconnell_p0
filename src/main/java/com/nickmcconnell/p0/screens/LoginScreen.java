package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.models.AppUser;

import java.io.BufferedReader;

public class LoginScreen extends Screen{
    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader){
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
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

            if(username != null && !username.isEmpty() && password != null && !password.isEmpty()){
                AppUser authenticateUser = userDAO.findUserByUsernameAndPassword(username, password);
                if(authenticateUser != null){
                    System.out.println("Login successful!");
                }else {
                       /*
                        The below code is not necessary, because if the login fails, we will fall
                        out of this method
                     */
//                    router.navigate("/welcome");
                    System.out.println("Login failed!");
                }
            } else{
                System.out.println("It looks like you didn't provide any credentials!");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
