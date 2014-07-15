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
//    private Statement stmt = null; 
//    ResultSet rset = null;
    
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
//            stmt = conn.createStatement();
//            rset = stmt.executeQuery("SELECT * FROM USERS_T");
//            
//            //extract data from result set
//            while(rset.next()) {
//                int dbUserID = rset.getInt("USERID");
//                String dbFirstName = rset.getString("FIRSTNAME");
//                String dbLastName = rset.getString("LASTNAME");
//                String dbUserName = rset.getString("USERNAME");
//                String dbPassword = rset.getString("PASSWORD");
//                
//                // display data values
//                System.out.println(dbUserID);
//                System.out.println(dbFirstName);
//                System.out.println(dbLastName);
//                System.out.println(dbUserName);
//                System.out.println(dbPassword);
//                
//                
//            }
//            
//            // close all db connections
//            rset.close();
//            stmt.close();
//            conn.close();

        } // end try
        
        // catch any SQL exception
        catch(SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        } // end catch: SQLException
        
        
        // fail-all close database resources
//        finally {    
//            try {
//                if(rset != null) rset.close();
//            }
//            catch(SQLException ex) {
//                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//            } // end catch
            
//            try {
//                if(conn!=null) conn.close();
//            }
//            catch(SQLException ex) {
//                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//            } // end catch
            
//            try {
//                if(stmt != null) stmt.close();
//            }
//            catch(SQLException ex) {
//                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//            } // end catch
//        } // end finally
        
        return conn;
    } // end method: connect()
    
    public static Connection getConnection() throws SQLException {
        if (conn != null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
    
} // end class: DBConnect
