/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rahima
 */
public class DB {
 

    private static Connection connect;
    private static Statement st;

    public static Connection Connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/activity", "root", "");
            st = connect.createStatement();
            System.out.println("Connected to database!");
            return connect;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Can't Connect To Server");
        return null;
    }

    public static ResultSet Query(String query, boolean isUpdate) throws SQLException {
        Statement stmnt = Connect().createStatement();
        System.out.println("Running all queries: " + query);
        if (isUpdate) {
            stmnt.executeUpdate(query);
        } else {
            return stmnt.executeQuery(query);
        }
        stmnt.close();
        close();

        return null;

    }

    public static ResultSet Query(String query) throws SQLException {
        return Query(query, false);
    }

    public static void close() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    
    }
}

