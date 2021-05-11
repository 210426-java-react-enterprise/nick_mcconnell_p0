package com.nickmcconnell.p0.daos;

import com.nickmcconnell.p0.util.ConnectionFactory;

import java.sql.*;


public class TransactionDAO {
    //withdrawal
        //do math
        //update lin


    //
    public boolean accountDeposit(int id, float deposit){

        int rowsUpdated = 0;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update balances set balance=? where balance_id=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, deposit);
            pstmt.setInt(2, id);

//            ResultSet rs = pstmt.executeQuery();

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
