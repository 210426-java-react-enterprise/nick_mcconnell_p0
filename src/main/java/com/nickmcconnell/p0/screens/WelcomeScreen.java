package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/welcome");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        System.out.println("Welcome to Bank!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch(userSelection) {
                case "1":
                    System.out.println("Navigating to login screen");
                    router.navigate("login");
                    break;
                case"2":
                    System.out.println("Navigating to register screen");
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    app()
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }

}
