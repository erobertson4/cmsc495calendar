/**
 * 
 */
package presenter;

import java.util.GregorianCalendar;

import model.bean.UserBean;
import view.type.Calendar;
import view.type.NewUserScreen;
import view.type.LoginScreen.LoginListener;
import view.ui.CalendarView;
import view.ui.NewUserScreenView;

/**
 * @author elijahr
 *
 */
public class LoginPresenter implements LoginListener {
  
  public LoginPresenter() {
    
  }

  @Override
  public void showNewUserScreen() {
    NewUserScreen newUserScreen = new NewUserScreenView();
    NewUserPresenter newUserPresenter = new NewUserPresenter();
    newUserScreen.setNewUserListener(newUserPresenter);
    
    newUserScreen.showNewUserScreen();
  }

  @Override
  public void login(String username, String password) {
    //TODO verify there is a user in the DB with the given username and password
    UserBean user = new UserBean(username, password);
    
    
    // TODO make sure it is a genuine login before creating a calendar
    Calendar calendar = new CalendarView(user, new GregorianCalendar());
    CalendarPresenter calendarPresenter = new CalendarPresenter();
    calendar.setCalendarListener(calendarPresenter);
    
    calendar.showCalendar();
  }

  @Override
  public void quit() {
    System.exit(0);
  }
  
  
}
