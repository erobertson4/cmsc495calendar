/**
 * 
 */
package presenter;

import model.bean.EventBean;
import model.bean.UserBean;
import view.type.Day;
import view.type.Day.DayListener;
import view.type.EventScreen;
import view.type.Month;
import view.ui.EventScreenView;

/**
 * Presenter class for Day. Controls interaction between this GUI element and
 * other elements.
 * 
 * @author elijahr
 * @author mattj
 */
public class DayPresenter implements DayListener {

  private Day day;
  private Month month;
  
  
  public DayPresenter(Day day, Month month) {
    this.day = day;
    this.month = month;
  }


  @Override
  public void showEventScreen(EventBean eventBean, UserBean userBean) {
    EventScreen eventScreen = new EventScreenView(eventBean, userBean);
    EventScreenPresenter eventScreenPresenter = new EventScreenPresenter(eventScreen, month, day);
    eventScreen.setEventListener(eventScreenPresenter);

    eventScreen.showEventScreen();
  }

}
