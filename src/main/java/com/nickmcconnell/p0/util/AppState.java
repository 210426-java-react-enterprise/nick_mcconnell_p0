package com.nickmcconnell.p0.util;

import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.screens.LoginScreen;
import com.nickmcconnell.p0.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing application");

        appRunning = true;

        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
            .addScreen(new LoginScreen(consoleReader))
                .addScreen(new Regis)


    }
}
