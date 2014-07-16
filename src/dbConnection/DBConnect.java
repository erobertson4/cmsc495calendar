/*
    Document   : DBConnect.java
    Created on : July 12, 2014
    Author     : Matt Jacobs
 */

package dbConnection;


import java.sql.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnect {
    
    // connection variables
    private static String url = null;
    private static Properties info = null;
    private static Connection conn = null;
    
    // method to connect to database
    public static Connection connect() throws SQLException {

        try {
            
            //register JDBC driver
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch(ClassNotFoundException ex) {
                System.out.println("Error: unable to load driver class");
                System.exit(1);
            }
            
            // create connection object with db url and properties object
            url = "jdbc:oracle:thin:@nova.umuc.edu:1521:acad";
            info = new Properties();
            info.put("user", "cs495b04");
            info.put("password", "h4m5t4n9");
            conn = DriverManager.getConnection(url, info);

        } // end try
        
        // catch any SQL exception
        catch(SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        } // end catch: SQLException
        
        
        return conn;
    } // end method: connect()
    
    public static Connection getConnection() throws SQLException {
        if (conn != null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
    
} // end class: DBConnect
