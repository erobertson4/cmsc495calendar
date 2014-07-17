/**
 * 
 */
package presenter;

import dbConnection.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import model.bean.UserBean;
import org.controlsfx.dialog.Dialogs;
import view.type.LoginScreen;
import view.type.NewUserScreen;
import view.type.NewUserScreen.NewUserListener;
import view.ui.LoginScreenView;
import javafx.stage.Stage;
import view.type.Calendar;
import view.ui.CalendarView;

/**
 * Presenter class for NewUserScreen. Controls interaction between this GUI
 * element and other elements.
 * 
 * @author elijahr
 * @author Matthew Wigate Jacob
 */
public class NewUserPresenter implements NewUserListener {

  private NewUserScreen newUserScreen;
  private UserBean newUser;
  
  private Stage stage;
  
  // data connection variables
  private Connection conn;
  private String SQL;
  private ResultSet rset;
  private Statement stmt;
  private String firstname1;
  private String lastname1;
  private String username1;
  private String password1;
  
  // database value variables
  private int dbUserID;
  private String dbfirstName;
  private String dblastName;
  private String dbuserName;
  private String dbpassword;

  public NewUserPresenter(NewUserScreen newUserScreen) {
    this.newUserScreen = newUserScreen;
  }


  @Override
  public void createNewUser(String firstName, String lastName, String username,
      String password) throws NullPointerException {
    
    //System.out.println("Attempting to create user");

    // assign user credentials parameter values to variables
    firstname1 = firstName;
    lastname1 = lastName;
    username1 = username;
    password1 = password;
    
    
    // =======================================================================
    // see if username exists. If so, indicate this in a dialog box.
    // =======================================================================
    
    // connect to data source calling DBConnect.java > connect()
    try {
        conn = DBConnect.connect();        
        stmt = conn.createStatement();
        SQL = "SELECT * FROM USER_T WHERE USERNAME = '" + username1 + "' ";
        rset = stmt.executeQuery(SQL);
        
        
        // add resultSet value to database variable
        while(rset.next()) {
            dbuserName = rset.getString("USERNAME");
        } // end while loop
        
        if (dbuserName.equals(username1)) {
            // if username exists, display error dialog box
            Dialogs.create().owner(stage).title("Invalid Username")
              .message("The username \"" + dbuserName + "\" belongs to another user."
                    + "\nPlease select a different username.").showError();
        } else {
            createNewAccount(); // call to method below: creates new user record
        }
        // close all db connections
        rset.close();
        conn.close();
        stmt.close();
        
    } // end try block
    
    catch(NullPointerException ex) {
        /*
        when no data record is matched, null pointer exception will be caught
        here. process continues with call to createNewAccount method below
        */
        // [TESTING ONLY] System.out.println("null pointer thrown @ check for existing username");
        createNewAccount(); // call to method below: creates new user record
        
    }
    
    catch(SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage() 
                + "\nError: can not connect to database");
        //loginScreen.showLoginScreen();
    } // end catch
    
    //fail-all close database resources
        finally {    
            try {
                if(rset != null) rset.close();
                if(conn!=null) conn.close();
                if(stmt != null) stmt.close();
            }
            catch(SQLException ex) {
                System.out.println("Error: An error was detected while "
                        + "closing the data source!");
            } // end catch
        } // end finally =============================================================
    
  } // end method: createNewUser

  
    
    
    
    // ========================================================================
    // If username does not exist, create the account and proceed to Calendar.
    // ========================================================================
    
    public void createNewAccount() {
        
        // connect to data source calling DBConnect.java > connect()
        try {
            conn = DBConnect.connect();        
            stmt = conn.createStatement();
            SQL = "INSERT INTO USER_T (USERID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) "
                    + "VALUES(USER_SEQ.NEXTVAL, '" + firstname1 
                    + "', '" + lastname1 + "', '" + username1 
                    + "', '" + password1 + "')";
            stmt.executeUpdate(SQL);

            // close all db connections
            conn.close();
            stmt.close();

        } // end try block

        catch(NullPointerException ex) {
            System.out.println("Error: NullPointerException thrown form "
                    + "createNewAccount method " + ex.getMessage());
        }

        catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage() + "\nError: "
                    + "can not connect to database");
        } // end catch

        //fail-all close database resources
        finally {    
            try {
                if(stmt != null) stmt.close();
                if(conn!=null) conn.close();
            }
            catch(SQLException ex) {
                System.out.println("Error: An error was detected while closing "
                        + "the data source!");
            } // end catch
            
        // confirm new user created
        confirmNewUser();
        } // end finally =============================================================
    }
    
    
    
    // confirm new record created in database, create userBean, display loginScreen
    public void confirmNewUser() {
        
         // connect to data source calling DBConnect.java > connect()
        try {
            conn = DBConnect.connect();        
            stmt = conn.createStatement();
            SQL = "SELECT * FROM USER_T WHERE FIRSTNAME = '" + firstname1 
                    + "' AND LASTNAME = '" + lastname1
                    + "' AND USERNAME = '" + username1
                    + "' AND PASSWORD = '" + password1 + "' ";
            rset = stmt.executeQuery(SQL);


            // add resultSet value to database variable
            while(rset.next()) {
                dbUserID = rset.getInt("USERID");
                dbfirstName = rset.getString("FIRSTNAME");
                dblastName = rset.getString("LASTNAME");
                dbuserName = rset.getString("USERNAME");
                dbpassword = rset.getString("PASSWORD");
            } // end while loop
        
            /*
            * create userBean with values from
            * database, NOT with values inserted by user
            */
            newUser = new UserBean(dbUserID, dbfirstName, dblastName, 
                    dbuserName, dbpassword);
            /*
            [TESTING ONLY - prints values to output]
            System.out.println(newUser.getUserID() + "\n"
            + newUser.getFirstName() + "\n"
            + newUser.getLastName() + "\n"
            + newUser.getUsername() + "\n"
            + newUser.getPassword());
            */
            
            // close all db connections
            rset.close();
            conn.close();
            stmt.close();

        } // end try block

        catch(NullPointerException ex) {
            System.out.println("Error: NullPointerException - The requested "
                    + "record does not exists! " + ex.getMessage());
        }

        catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage() 
                    + "\nError: can not connect to database");
        } // end catch

        //fail-all close database resources
            finally {    
                try {
                    if(rset != null) rset.close();
                    if(conn!=null) conn.close();
                    if(stmt != null) stmt.close();
                }
                catch(SQLException ex) {
                    System.out.println("Error: An error was detected while "
                            + "closing the data source!");
                } // end catch
            } // end finally =============================================================
        
        // display calendar
        // Need to add users greeting to calendar
        Calendar calendar = new CalendarView(newUser, new GregorianCalendar());
        CalendarPresenter calendarPresenter = new CalendarPresenter(calendar);
        calendar.setCalendarListener(calendarPresenter);

        newUserScreen.hideNewUserScreen();
        calendar.showCalendar();
    } // end method: confirmNewUser
    
    
  @Override
  public void showLoginScreen() {
    LoginScreen loginScreen = new LoginScreenView();
    LoginPresenter loginPresenter = new LoginPresenter(loginScreen);
    loginScreen.setLoginListener(loginPresenter);

    loginScreen.showLoginScreen();
    newUserScreen.hideNewUserScreen();
  }
}
