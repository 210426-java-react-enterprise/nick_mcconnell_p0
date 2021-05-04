package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.models.AppUser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDAO {

    String userDataFile;

    public UserDAO() {

        userDataFile = "src/main/resources/users.txt";

    }

    public void saveUserToFile(AppUser newUser) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter((userDataFile, true))) {
            writer.write(newUser.toFileString() + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
