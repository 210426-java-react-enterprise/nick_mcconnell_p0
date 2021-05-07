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

    //tables
        //user - PK id
        //accounts - fk id fromuser
        //login credentials - fk id from user

    //Methods needed
        //createNewUser => account => checking or saving
        //createAccount => from account screen
            //Check if column already exists first then
        //widthdraw funds
            //check funds => if sum desired greated than account, send overdraft messate
            //else reduce funds by

        //once logged in => how to stay logged in??
            // always to select bassed on username and id?
            //keep pointing at new user?

    //login
        //on success - get user info and account's and balances => use join
        //then display menu to create account, deposit or withdraw
            //after eaach re display above menu with exit option

    //cf

    //TODO (Associate task) Implement!
    public void save(AppUser newUser) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sqlInsertUser = "insert into customers (username, password, email, first_name, last_name, age) values (?,?,?,?,?,?,)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] {"customer_id"});
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getFirstName());
            pstmt.setString(5, newUser.getLastName());
            pstmt.setInt(6, newUser.getAge());
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public boolean isUsernameAvailable(String username){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from customers where username = ?";
            PreparedStatement pstmt = conn.prepareStatement((sql));
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean isEmailAvailable(String email){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from customers where email = ?";
            PreparedStatement pstmt = conn.prepareStatement((sql));
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }


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
