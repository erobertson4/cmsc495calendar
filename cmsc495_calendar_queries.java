/* 
* ===================================================
* file name: cmsc495_calendar_dataQueries.java 
* script purpose: code snippits for accessing, adding, updating, deleting database records 
* script author: matthew wingate jacobs 
* date: july 7, 2014 
* ===================================================
*/

/*
* ===================================================
* query user credentials for login
* USING JDBC CONNECTIVITY
* this is not a complete java document
* the following code is intended to be cut and pasted into your documents
* ===================================================
*/

/* imports for connectivity if needed*/
import java.sql.*;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;
import java.util.ArrayList;

/* connection variables */
Connection conn = null;
Statement stmt = null;
ResultSet rset = null;

/* user data variables */
ArrayList<userBean> userArray = new ArrayList<userBean>(); // userBean not created. included for contextual use if you desire!
int userID = 0; //userID from database
String userName = request.getparameter("userName"); // username from input form
String password = request.getParameter("password"); // password from input form
String usernameCode = null; // user name from database
String passwordCode = null; // password from database
String firstName = null; // first name from database
String lastName = null; // last name from database
int i = 0; // used to loop through userArray in while statement below


try {
    // set variables and connet to DB and query user record
    OracleDataSource ds = new OracleDataSource();
    ds.setURL("jdbc:oracle:thin:@nova.umuc.edu:1521:acad");
    conn = ds.getConnection("cs495b04", "h4m5t4n9");
    stmt = conn.createStatement();
    rset = stmt.executeQuery("SELECT * FROM USER_T WHERE USERNAME = '" + userName + "' AND PASSWORD = '" + password + "' ");

    while (rset.next()) {
        userID = rset.getInt("USERID");
        firstName = rset.getString("FIRSTNAME");
        lastName = rset.getString("LASTNAME");
        usernameCode = rset.getString("USERNAME");
        passwordCode = rset.getString("PASSWORD");

        /* CREATE NEW USER OBJECT */
        userBean userObj = new UserBean();
        userObj.setUserID(userID); // assign value to userObj
        userObj.setUserFirstName(firstName); // assign value to userObj
        userObj.setUserLastName(lastName); // assign value to userObj
        userObj.setUserUserName(usernameCode); // assign value to userObj
        userObj.setUserPassword(passwordCode); // assign value to userObj
        userArray.add(i, userObj); // add userObj's to userArray
        i++ // iterate userArray
    } // end while loop

        /* if no data returned or no record match throw NullPointerException and send error */
        if ((!usernameCode.equals(userName)) && (!passwordCode.equals(password))) {
            throw new NullPointerException();
        }
    } // end try block

    catch (SQLException ex){
        System.out.println("SQLException: " + ex.getMessage() + "\n"); // you can change this to display err msg of your choice
    }

    // if not matching records retrieved, send error
    catch (NullPointerException ex) {
        /* enter NullPointerException code here if any */
     }
    }

    finally { // disconnect from data base 
        try {
        if(rset!=null) rset.close();
        if(stmt!=null) stmt.close();
        if(conn!=null) conn.close();
        }
        catch(SQLException e) { // catch sqlException for finally block
            /* enter SQLException code here if any */
        }

    /* ---- test user name entered by data in data base. values should match here then proceed with program---- */
    if ((usernameCode.equals(userName)) && (passwordCode.equals(password))) { 
        /* enter code to display calendar here*/

    }

/*
-- ===================================================
-- End query user credentials for login
-- ===================================================
*/



