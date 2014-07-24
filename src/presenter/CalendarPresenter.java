/**
 * 
 */
package presenter;

import model.bean.UserBean;
import view.type.Calendar;
import view.type.Calendar.CalendarListener;
import view.type.EventScreen;
import view.type.LoginScreen;
import view.ui.EventScreenView;
import view.ui.LoginScreenView;

/**
 * Presenter class for Calendar. Controls interaction between this GUI element
 * and other elements.
 * 
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


  @Override
  public void createNewEvent(UserBean user) {
    EventScreen eventScreen = new EventScreenView(null, user);
    EventScreenPresenter eventScreenPresenter = new EventScreenPresenter(eventScreen, calendar.getMonth(), null);
    eventScreen.setEventListener(eventScreenPresenter);
    
    eventScreen.showEventScreen();
  }

}
