package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.util.ConnectionFactory;

import java.sql.*;

public class AccountDAO {

//    public UserAccount getUserAccounts(AppUser currentUser) {
//        UserAccount userAccount = null;
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            String sql = "select accounts.account_type, account_id from customers inner join accounts on ? = accounts.customer_id;";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, currentUser.getId());
//
//            ResultSet rs = pstmt.executeQuery();
//
//            userAccount = new UserAccount();
//
//            while (rs.next()) {
//                userAccount.setId(rs.getInt("account_id"));
//                userAccount.setAccountType(rs.getString("account_type"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userAccount;

//        UserAccount userAccount = null;
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            String sql = "select balances.balance, accounts.account_type, balances.balance_id from customers inner join accounts on ? = accounts.customer_id inner join balances on accounts.account_id = balances.account_id;";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, currentUser.getId());
//
//            ResultSet rs = pstmt.executeQuery();
//
//            userAccount = new UserAccount();
//
//            while (rs.next()) {
//                userAccount.setId(rs.getInt("balance_id"));
//                userAccount.setAccountType(rs.getString("account_type"));
//                userAccount.setBalance(rs.getFloat("balance"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userAccount;
//    }

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

//    public UserAccount getBalance(AppUser currentUser) {
//        UserAccount userAccount = null;
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            String sql = "select balances.balance, accounts.account_type, balances.balance from customers inner join accounts on ? = accounts.customer_id inner join balances on accounts.account_id = balances.account_id;";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, currentUser.getId());
//
//            ResultSet rs = pstmt.executeQuery();
//
//            userAccount = new UserAccount();
//
//            while (rs.next()) {
//                userAccount.setId(rs.getInt("balance_id"));
//                userAccount.setAccountType(rs.getString("account_type"));
//                userAccount.setBalance(rs.getFloat("balance"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userAccount;
//    }

    public boolean createAccount(String accountType, int currentUserId) {

        int rowsInserted = 0;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into accounts(account_type, customer_id) values (?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountType);
            pstmt.setInt(2, currentUserId);

            rowsInserted = pstmt.executeUpdate();
            System.out.println("ros inser " + rowsInserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rowsInserted == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createInitialBalance(int accountId) {
        System.out.println("in create innitial balance " + accountId);
        int rowsInserted = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into balances(balance, account_id) values (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, 0);
            pstmt.setInt(2, accountId);

            rowsInserted = pstmt.executeUpdate();
            System.out.println("initial palance rows " + rowsInserted);
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
