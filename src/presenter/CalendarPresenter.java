/**
 * 
 */
package presenter;

import view.type.LoginScreen;
import view.type.Calendar.CalendarListener;
import view.ui.LoginScreenView;

/**
 * @author elijahr
 *
 */
public class CalendarPresenter implements CalendarListener {
  
  public CalendarPresenter() {
  }

  @Override
  public void logout() {
    LoginScreen loginScreen = new LoginScreenView();
    LoginPresenter loginPresenter = new LoginPresenter();
    loginScreen.setLoginListener(loginPresenter);
    
    loginScreen.showLoginScreen();
  }
  
}
