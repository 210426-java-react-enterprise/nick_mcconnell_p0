package com.nickmcconnell.p0.util;

import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.screens.LoginScreen;
import com.nickmcconnell.p0.screens.RegisterScreen;
import com.nickmcconnell.p0.screens.WelcomeScreen;
import com.nickmcconnell.p0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application");

        appRunning = true;

        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();
        final UserService userService = new UserService(userDao);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, userService))
                .addScreen(new RegisterScreen(consoleReader, userService));
        System.out.println("Application Initialized");
    }

    public ScreenRouter getRouter(){
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning){
        this.appRunning = appRunning;
    }


}
