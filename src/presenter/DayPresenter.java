/**
 * 
 */
package presenter;

import java.util.GregorianCalendar;
import java.util.List;

import model.bean.EventBean;
import model.bean.UserBean;
import view.type.Day;
import view.type.Day.DayListener;
import view.type.EventScreen;
import view.ui.EventScreenView;

/**
 * Presenter class for Day. Controls interaction between this GUI element and
 * other elements.
 * 
 * @author elijahr
 *
 */
public class DayPresenter implements DayListener {

  private Day day;


  public DayPresenter(Day day) {
    this.day = day;
  }


  @Override
  public void getEvents() {
    GregorianCalendar gregorianCalendar = day.getGregorianCalendar();

    // TODO [MJ] retrieve the list of events with the given GregorianCalendar
    List<EventBean> events = null;

    // Since listener methods should return null, we have to set the list of
    // events for the day rather than returning the list.
    day.setEvents(events);
  }


  @Override
  public void showEventScreen(EventBean eventBean, UserBean userBean) {
    EventScreen eventScreen = new EventScreenView(eventBean, userBean);
    EventScreenPresenter eventScreenPresenter = new EventScreenPresenter(eventScreen);
    eventScreen.setEventListener(eventScreenPresenter);

    eventScreen.showEventScreen();
  }

}
