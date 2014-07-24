/**
 * 
 */
package presenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.EventBean;
import view.type.Month;
import view.type.Month.MonthListener;

/**
 * @author elijahr
 *
 */
public class MonthPresenter implements MonthListener {

  private Month month;
  
  public MonthPresenter(Month month) {
    this.month = month;
  }

  
  @Override
  public void getEvents(GregorianCalendar startDate, GregorianCalendar endDate) {
    List<EventBean> resultSet = new ArrayList<EventBean>();
    // [MJ] query the DB to get all events between the two dates (inclusive)
    
    
    
    Map<LocalDate, List<EventBean>> events = new HashMap<LocalDate, List<EventBean>>();
    for (EventBean event : resultSet) {
      List<EventBean> eventsForDay = events.get(event.getDate());
      if (eventsForDay == null) {
        eventsForDay = new ArrayList<EventBean>();
      }
      eventsForDay.add(event);
      events.put(event.getDate(), eventsForDay);
    }
    
    month.setEvents(events);
  }

}
