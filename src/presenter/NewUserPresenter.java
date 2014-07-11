/**
 * 
 */
package presenter;

import view.type.LoginScreen;
import view.type.NewUserScreen.NewUserListener;
import view.ui.LoginScreenView;

/**
 * @author elijahr
 *
 */
public class NewUserPresenter implements NewUserListener {
  
  public NewUserPresenter() {
    
  }

  @Override
  public void createNewUser(String firstName, String lastName, String username, String password) {
    System.out.println("Attempting to create user");
    
    // TODO see if username exists. If so, indicate this in a dialog box.
    
    
    //TODO If username does not exist, create the account and proceed to Calendar.
//    UserBean newUser = new UserBean(firstName, lastName, username, password);
  }

  @Override
  public void showLoginScreen() {
    LoginScreen loginScreen = new LoginScreenView();
    LoginPresenter loginPresenter = new LoginPresenter();
    loginScreen.setLoginListener(loginPresenter);
    
    loginScreen.showLoginScreen();
  }
}
