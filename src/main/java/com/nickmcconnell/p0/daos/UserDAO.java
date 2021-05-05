package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.util.ConnectionFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    //TODO (Associate task) Implement!
    public void save(AppUser newUswer) {

    }

    //Methods needed
        //createNewUser => account => checking or saving
        //createAccount => from account screen
            //Check if column already exists first then
                //then ALTER table to add account type column
        //widthdraw funds
            //check funds => if sum desired greated than account, send overdraft messate
            //else reduce funds by

        //once logged in => how to stay logged in??
            // always to select bassed on username and id?
            //keep pointing at new user?

    //


    public AppUser findUserByUsernameAndPassword(String username, String password) {

        AppUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from quizzard.users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new AppUser();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
