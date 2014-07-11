/**
 * 
 */
package view.type;


/**
 * @author elijahr
 *
 */
public interface NewUserScreen {
  
  public interface NewUserListener {

    void createNewUser(String firstName, String lastName, String username, String password);

    void showLoginScreen();
    
  }
  
  
  void setNewUserListener(NewUserListener listener);
  
  void showNewUserScreen();
  
}
