/**
 * 
 */
package presenter;

import java.util.GregorianCalendar;

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
  public void login(String username, String password) {
    //TODO [MJ] verify there is a user in the DB with the given username and password
    UserBean user = new UserBean(username, password);
    
    
    // TODO [MJ] make sure it is a genuine login before creating a calendar
    Calendar calendar = new CalendarView(user, new GregorianCalendar());
    CalendarPresenter calendarPresenter = new CalendarPresenter(calendar);
    calendar.setCalendarListener(calendarPresenter);
    
    calendar.showCalendar();
    
    // TODO [MJ] if login is successful, close the login screen
    loginScreen.hideLoginScreen();
  }

  @Override
  public void quit() {
    loginScreen.hideLoginScreen();
    System.exit(0);
  }
  
  
}
