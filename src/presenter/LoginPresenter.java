/**
 * 
 */
package presenter;

import dbConnection.DBConnect;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.UserBean;
import view.type.Calendar;
import view.type.LoginScreen;
import view.type.LoginScreen.LoginListener;
import view.type.NewUserScreen;
import view.ui.CalendarView;
import view.ui.NewUserScreenView;


/**
 * @author elijahr
 *
 */
public class LoginPresenter implements LoginListener {
  
  private LoginScreen loginScreen;
  private Connection conn;
  private String SQL;
  private ResultSet rset;
  private Statement stmt;
  private String username1; 
  private String password1;
  
  // database value variables
  private int dbUserID;
  private String dbFirstName;
  private String dbLastName;
  private String dbUserName;
  private String dbPassword;

  
  // create collection object for user data
  private UserBean userObj;
  private ArrayList<UserBean> userArray = new ArrayList<UserBean>();
  private int i = 0;
  
  
  public LoginPresenter(LoginScreen loginScreen) {
    this.loginScreen = loginScreen;
  }

  @Override
  public void showNewUserScreen() {
    NewUserScreen newUserScreen = new NewUserScreenView();
    NewUserPresenter newUserPresenter = new NewUserPresenter(newUserScreen);
    newUserScreen.setNewUserListener(newUserPresenter);
    
    newUserScreen.showNewUserScreen();
    loginScreen.hideLoginScreen();
  }

  @Override
  public void login(String username, String password) throws NullPointerException {
      
    //======================================================================
    // verify there is a user in the DB with the given username and password
    //======================================================================
    // assign user credentials parameter values to variables
    username1 = username;
    password1 = password;
    
    
    // connect to data source calling DBConnect.java > connect()
    try {
        conn = DBConnect.connect();        
        stmt = conn.createStatement();
        SQL = "SELECT * FROM USER_T WHERE USERNAME = '" + username1 + "' AND Password = '" + password1 + "' ";
        rset = stmt.executeQuery(SQL);
        
        
        // add resultSet values to userArray collection object
        while(rset.next()) {
            dbUserID = rset.getInt("USERID");
            dbFirstName = rset.getString("FIRSTNAME");
            dbLastName = rset.getString("LASTNAME");
            dbUserName = rset.getString("USERNAME");
            dbPassword = rset.getString("PASSWORD");
            
            // create userBean object
            userObj = new UserBean();
            
            // assign database values to userObj
            userObj.setUserID(dbUserID);
            userObj.setFirstName(dbFirstName);
            userObj.setLastName(dbLastName);
            userObj.setUsername(dbUserName);
            userObj.setPassword(dbPassword);
            
            // assign userObj's to userArray
            userArray.add(i, userObj);
            i++; // increase count to iterate through array
        } // end while loop
        

            showMyCalendar(); // call to method below

        
        // close all db connections
        rset.close();
        conn.close();
        stmt.close();
        
    } // end try block

    catch(SQLException ex) {
        //TODO [ER] & [MM] present following message in error dialog box when credentials don't match
        System.out.println("SQLException: " + ex.getMessage() + "\nError: can not connect to database");
        loginScreen.showLoginScreen();
    } // end catch   
        //fail-all close database resources
        finally {    
            try {
                if(rset != null) rset.close();
            }
            catch(SQLException ex) {
                //TODO  [ER] & [MM] use below to write error to log if needed.
                //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
                //TODO  [ER] & [MM] present following message in error dialog box
                System.out.println("Error: unable to close ResultSet");
            } // end catch
            
            try {
                if(conn!=null) conn.close();
            }
            catch(SQLException ex) {
                //TODO  [ER] & [MM] use below to write error to log if needed.
                //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
                //TODO  [ER] & [MM] present following message in error dialog box
                System.out.println("Error: unable to close database Connection");
            } // end catch
            
            try {
                if(stmt != null) stmt.close();
            }
            catch(SQLException ex) {
                //TODO  [ER] & [MM] use below to write error to log if needed.
                //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
                //TODO  [ER] & [MM] present following message in error dialog box
                System.out.println("Error: unable to close data Statement");
            } // end catch
        } // end finally =============================================================
    
    
    // ====================================================================
    // TODO [MJ] make sure it is a genuine login before creating a calendar
    // ====================================================================
    
}

  public void showMyCalendar() {
    // test user name entered by user against value retreived from data base
    try {
        
        if ((dbUserName.equals(username1)) && (dbPassword.equals(password1))) {
            System.out.println("The credentials match the database valued- Proceed!");
            // create calendar           
            Calendar calendar = new CalendarView(userObj, new GregorianCalendar());
            CalendarPresenter calendarPresenter = new CalendarPresenter(calendar);
            calendar.setCalendarListener(calendarPresenter);
    
            calendar.showCalendar();
    
            // if login is successful, close the login screen
            loginScreen.hideLoginScreen();
        } else {
            throw new NullPointerException("num 2");

        }
    }
    catch(NullPointerException ex) {
        //TODO  [ER] & [MM] use below to write error to log if needed.
            //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            //TODO  [ER] & [MM] present following message in error dialog box
            System.out.println("An invalid username or password was entered. Please try again." + ex);
            loginScreen.showLoginScreen();
    }
    // ====================================================================
  }  
  
  
  @Override
  public void quit() {
    loginScreen.hideLoginScreen();
    System.exit(0);
  }
  
  
}
