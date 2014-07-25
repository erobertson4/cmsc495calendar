/**
 * 
 */
package view.type;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.GridPane;
import model.bean.EventBean;

/**
 * Interface to the MonthView GUI class the controls the interaction between
 * View and Presenter.
 * 
 * @author elijahr
 *
 */
public interface Month {

  /**
   * Actions that the MonthPresenter will execute on a month.
   * 
   * @author elijahr
   *
   */
  public interface MonthListener {

    /**
     * Get the events for a month, which is all 42 days that will be displayed.
     * 
     * @param startDate
     *          the first day that is displayed.
     * @param endDate
     *          the last day that is displayed.
     */
    void getEvents(LocalDate startDate, LocalDate endDate);
  }


  /**
   * Get the Grid of all Days being displayed.
   * 
   * @return the Grid of all Days being displayed.
   */
  GridPane getCalendar();


  /**
   * Set the listener for the Month.
   * 
   * @param listener
   *          the listener for the Month GUI object.
   */
  void setMonthListener(MonthListener listener);


  /**
   * Set the Map of all events of the displayed Month.
   * 
   * @param events
   *          the Map of all events of the displayed Month.
   */
  void setEvents(Map<LocalDate, List<EventBean>> events);


  /**
   * Get the Map of all events of the displayed Month.
   * 
   * @return the Map of all events of the displayed Month.
   */
  Map<LocalDate, List<EventBean>> getEvents();


  /**
   * Get the List of all days being displayed.
   * 
   * @return the List of all days.
   */
  List<Day> getDays();
}
