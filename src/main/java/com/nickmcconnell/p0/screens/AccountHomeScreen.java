package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.ScreenRouter;

import java.io.BufferedReader;

/**
 * An accounts option screen only visible to logged in users.
 */
public class AccountHomeScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public AccountHomeScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router) {
        super("AccountHomeScreen", "/accounthome");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        try {
            System.out.println("Accounts Screen");
            System.out.println("+---------------------------+");
            System.out.println("1) View Accounts");
            System.out.println("2) Create Account");
            System.out.println("3) Account Transaction");
            System.out.println("4) Return to Welcome Screen");
            System.out.print("> ");

            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to View Account screen");
                    router.navigate("/viewaccounts");
                    break;
                case "2":
                    System.out.println("Navigating to Create Account screen");
                    router.navigate("/createaccount");
                    break;
                case "3":
                    System.out.println("Navigating to Account Transaction Screen");
                    router.navigate("/accounttransaction");
                    break;
                case "4":
                    System.out.println("Returning to Welcome Screen");
                    router.navigate("/welcomescreen");
                    break;
                default:
                    System.out.println("Invalid selection.");
                    router.navigate("/accounthome");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("+---------------------------+");
        }

    }
}
