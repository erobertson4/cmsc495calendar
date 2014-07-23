/**
 * 
 */
package model.bean;

/**
 * Corresponding class to the User table in the database. 
 * information
 * 
 * @author elijahr
 *
 */
public class UserBean {

  private int userID;
  private String firstName;
  private String lastName;
  private String username;
  private String password;

  // no args constructor
  public UserBean() {
  }

  // full recordset constructor
  public UserBean(int userID, String firstName, String lastName, String username, String password) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }
  
  // min recordset constructor
  public UserBean(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public int getUserID() {
      return userID;
  }
  
  public void setUserID(int userID) {
      this.userID = userID;
  }
  
  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }

}
