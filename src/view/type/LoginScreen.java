/**
 * 
 */
package view.type;

/**
 * @author elijahr
 *
 */
public interface LoginScreen {
  
  public interface LoginListener {

    void showNewUserScreen();

    void login(String username, String password);

    void quit();
  }
  
  
  void setLoginListener(LoginListener listener);
  
  void showLoginScreen();
  
  void hideLoginScreen();
  
  
}
