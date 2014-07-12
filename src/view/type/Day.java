/**
 * 
 */
package view.type;

import java.util.GregorianCalendar;
import java.util.List;

import model.bean.EventBean;

/**
 * @author elijahr
 *
 */
public interface Day {
  
  public interface DayListener {
  
    void getEvents();
    
    void showEventScreen(EventBean eventBean);
    
  }
  
  
  void setDayListener(DayListener listener);
  
  void setEvents(List<EventBean> events);

  GregorianCalendar getGregorianCalendar();

}
