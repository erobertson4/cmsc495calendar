/**
 * 
 */
package view.type;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;

import javafx.scene.layout.VBox;
import model.bean.EventBean;
import model.bean.UserBean;

/**
 * Interface to the DayView GUI class the controls the interaction between View
 * and Presenter.
 * 
 * @author elijahr
 *
 */
public interface Day {

  /**
   * Actions that the DayPresenter will execute on a Day.
   * 
   * @author elijahr
   *
   */
  public interface DayListener {


    /**
     * Show the details of an event.
     * 
     * @param eventBean
     *          the event whose details will be displayed.
     * @param userBean
     *          the user who is opening the EventScreen.
     */
    void showEventScreen(EventBean eventBean, UserBean userBean);

  }


  /**
   * Get the layout for the Day.
   * 
   * @return the layout for the Day
   */
  VBox getLayout();


  /**
   * Set the listener for the Day.
   * 
   * @param listener
   *          the listener for the Day GUI object.
   */
  void setDayListener(DayListener listener);


  /**
   * Set the events of the Day.
   * 
   * @param events
   *          the events of the Day.
   */
  void setEvents(List<EventBean> events);


  /**
   * Get the GregorianCalendar for this Day.
   * 
   * @return the GregorianCalendar whose value is set to the Day.
   */
  GregorianCalendar getGregorianCalendar();


  LocalDate getLocalDate();


  void removeEvent(EventBean event);


  void saveEvent(EventBean event);

}
