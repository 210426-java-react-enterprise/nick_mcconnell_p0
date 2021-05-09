package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.models.AppUser;
import com.nickmcconnell.p0.models.UserAccount;
import com.nickmcconnell.p0.util.ConnectionFactory;

import java.sql.*;

public class AccountDAO {

    public UserAccount getUserAccounts(AppUser currentUser) {
        System.out.println("in accountDao get user accounts " + currentUser);

        UserAccount userAccount = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select balances.balance_id, accounts.account_type, balances.balance from customers inner join accounts on ? = accounts.customer_id inner join balances on accounts.account_id = balances.account_id;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUser.getId());

            ResultSet rs = pstmt.executeQuery();

            userAccount = new UserAccount();

            while (rs.next()) {
                userAccount.setId(rs.getInt("balance_id"));
                userAccount.setAccountType(rs.getString("account_type"));
                userAccount.setBalance(rs.getFloat("balance"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccount;
    }
}
