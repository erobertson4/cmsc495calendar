/**
 * 
 */
package presenter;

import view.type.Calendar;
import view.type.LoginScreen;
import view.type.Calendar.CalendarListener;
import view.ui.LoginScreenView;

/**
 * @author elijahr
 *
 */
public class CalendarPresenter implements CalendarListener {
  
  private Calendar calendar;
  
  public CalendarPresenter(Calendar calendar) {
    this.calendar = calendar;
  }

  @Override
  public void logout() {
    LoginScreen loginScreen = new LoginScreenView();
    LoginPresenter loginPresenter = new LoginPresenter(loginScreen);
    loginScreen.setLoginListener(loginPresenter);
    
    loginScreen.showLoginScreen();
    calendar.hideCalendar();
  }
  
}
