package com.nickmcconnell.p0.util;

import com.nickmcconnell.p0.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    @Test
    public void test_getConnection(){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            Assert.assertNotNull(conn);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
