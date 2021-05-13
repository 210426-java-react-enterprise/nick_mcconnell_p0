package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.ScreenRouter;

import java.io.BufferedReader;

/**
 * Screen where users enter login credentials which are then verified and allow access to user accounts.
 */

public class LoginScreen extends Screen{
    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;

    public LoginScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router){
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
    }

    @Override
    public void render(){

        try{
            String username;
            String password;

            System.out.println("Log into your account!");
            System.out.println("+---------------------------+");
            System.out.println("Username: ");
            username = consoleReader.readLine();

            System.out.println("Password: ");
            password = consoleReader.readLine();

            AppUser newUser = userService.login(username, password);

            if (newUser.getUsername() != null){
                router.setCurrentUser(newUser);
                router.navigate("/accounthome");
            } else {
                System.out.println("Login Failed.");
                router.navigate("/welcome");
            }
        } catch(NullPointerException e){
            System.out.println("Invalid login credentials");
            System.out.println("+---------------------------+");
            router.navigate("/welcome");
        } catch (Exception e){
            System.out.println("Login Failed.");
            System.out.println("+---------------------------+");
            router.navigate("/welcome");
        }
    }
}
