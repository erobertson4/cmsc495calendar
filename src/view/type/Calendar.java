/**
 * 
 */
package view.type;

/**
 * Interface to the CalendarView GUI class that controls the interaction between
 * View and Presenter.
 * 
 * @author elijahr
 *
 */
public interface Calendar {

  /**
   * Actions that the CalendarPresenter will execute on a Calendar.
   * 
   * @author elijahr
   *
   */
  public interface CalendarListener {

    /**
     * Logout and return to the login screen.
     */
    void logout();

  }


  /**
   * Set the listener for the Calendar.
   * 
   * @param listener
   *          the listener for the Calendar GUI object.
   */
  void setCalendarListener(CalendarListener listener);


  /**
   * Display the calendar on the screen.
   */
  void showCalendar();


  /**
   * Hide the calendar from the screen.
   */
  void hideCalendar();

}
