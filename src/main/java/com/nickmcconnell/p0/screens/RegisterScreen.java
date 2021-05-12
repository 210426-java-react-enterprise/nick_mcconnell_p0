package com.nickmcconnell.p0.screens;

import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.services.UserService;
import com.nickmcconnell.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private UserService userService;
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public RegisterScreen(BufferedReader consoleReader, UserService userService, ScreenRouter router) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
        this.router = router;
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

            AppUser newUser = new AppUser(username, password, email, firstName,lastName, age);
            userService.register(newUser);

        } catch (NumberFormatException e) {
            System.err.println("You provided an incorrect value for your age!  Please try again!");
           router.navigate("/welcome");
        }catch(InvalidRequestException | ResourcePersistenceException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
