/**
 * 
 */
package view.type;


/**
 * @author elijahr
 *
 */
public interface Calendar {

  public interface CalendarListener {

    void logout();
    
  }
  
  
  void setCalendarListener(CalendarListener listener);
  
  void showCalendar();
  
}
