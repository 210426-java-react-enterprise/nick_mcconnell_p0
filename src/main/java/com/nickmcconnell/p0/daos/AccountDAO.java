package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.models.UserAccountAndBalance;
import com.nickmcconnell.p0.ConnectionFactory;

import java.sql.*;

/**
 * The account data access object => contains database queries for get initializing,
 * and getting accounts and account balances.
 */

public class AccountDAO {
    //sets userAccountandBalance class fields via sql query
    public UserAccountAndBalance getAccountAndBalance(AppUser currentUser) {

        UserAccountAndBalance userAccountAndBalance = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select balances.balance, accounts.account_type, balances.balance_id from customers inner join accounts on ? = accounts.customer_id inner join balances on accounts.account_id = balances.account_id;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUser.getId());

            ResultSet rs = pstmt.executeQuery();

            userAccountAndBalance = new UserAccountAndBalance();

            while (rs.next()) {
                userAccountAndBalance.setId(rs.getInt("balance_id"));
                userAccountAndBalance.setAccountType(rs.getString("account_type"));
                userAccountAndBalance.setBalance(rs.getFloat("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccountAndBalance;
    }
    //sets userAccount class fields via sql query
    public UserAccount getAccount(AppUser currentUser){
        UserAccount userAccount = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from accounts where customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUser.getId());

            ResultSet rs = pstmt.executeQuery();

            userAccount = new UserAccount();

            while(rs.next()){
                userAccount.setId(rs.getInt("account_id"));
                userAccount.setAccountType(rs.getString("account_type"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return userAccount;
    }
    //saves user registration data to database via sql query
    public boolean createAccount(String accountType, int currentUserId) {

        int rowsInserted = 0;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into accounts(account_type, customer_id) values (?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountType);
            pstmt.setInt(2, currentUserId);

            rowsInserted = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rowsInserted == 1) {
            return true;
        } else {
            return false;
        }
    }
    //set user account balance to an initial balance of '0'
    public boolean createInitialBalance(int accountId) {
        int rowsInserted = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into balances(balance, account_id) values (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, 0);
            pstmt.setInt(2, accountId);

            rowsInserted = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowsInserted == 1) {
            return true;
        } else {
            return false;
        }
    }
}
