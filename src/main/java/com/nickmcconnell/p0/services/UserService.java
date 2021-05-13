package com.nickmcconnell.p0.services;

import com.nickmcconnell.p0.daos.UserDAO;
import com.nickmcconnell.p0.exceptions.InvalidRequestException;
import com.nickmcconnell.p0.exceptions.ResourcePersistenceException;
import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.LoginCredentials;

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        if(!userDao.isUsernameAvailable(newUser.getUsername())){
            throw new ResourcePersistenceException(("The provided username is already taken!"));
        }

        if(!userDao.isEmailAvailable(newUser.getEmail())){
            throw new ResourcePersistenceException("The provided email is already taken!");
        }

        userDao.save(newUser);
    }

    public AppUser login(String username, String password){
        if(!isLoginValid(username, password)){
            throw new InvalidRequestException("Invalid login credentials provided");
        }

       AppUser newUser =  userDao.findUserByUsernameAndPassword(username, password);
        return newUser;
    }

    public boolean isLoginValid(String username, String password) {

        if (username == null || username.trim().isEmpty() || username.length() > 20) return false;
        if (password == null || password.trim().isEmpty() || password.length() > 255) return false;

        return true;
    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;

        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255)
            return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 255) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25)
            return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25)
            return false;
        if (user.getAge() < 0) return false;

        return true;
    }
}
