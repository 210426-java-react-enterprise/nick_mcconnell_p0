package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.daos.UserDAO;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
    }

    @Override
    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;

        try {
            System.out.println("Register for a new account!");
            System.out.println("----------------------------");

            System.out.println("First name: ");
            firstName = consoleReader.readLine();

            System.out.println("Last name: ");
            lastName = consoleReader.readLine();

            System.out.println("Email: ");
            email = consoleReader.readLine();

            System.out.println("Username: ");
            username = consoleReader.readLine();

            System.out.println("Password: ");
            password = consoleReader.readLine();

            System.out.println("Age: ");
            age = Integer.parseInt(consoleReader.readLine());

        }catch(NumberFormatException e) {
            System.err.println("You provided an incorrect value for your age!  Please try again!");
//            this.render() // => apparently not the best practice, just put router in here and => "/register"
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
