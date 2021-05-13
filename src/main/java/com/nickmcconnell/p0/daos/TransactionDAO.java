package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.ConnectionFactory;

import java.sql.*;

/**
 * The transaction data access object => contains database queries for updating account balances.
 */
public class TransactionDAO {

    public boolean updateBalance(int id, float deposit){

        int rowsUpdated = 0;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update balances set balance=? where balance_id=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, deposit);
            pstmt.setInt(2, id);

            rowsUpdated = pstmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowsUpdated == 1) {
            return true;
        } else {
            return false;
        }
    }

}
