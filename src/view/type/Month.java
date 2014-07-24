/**
 * 
 */
package view.type;

import java.util.GregorianCalendar;
import java.util.List;

import javafx.scene.layout.GridPane;
import model.bean.EventBean;

/**
 * @author elijahr
 *
 */
public interface Month {
  
  public interface MonthListener {
    void getEvents(GregorianCalendar startDate, GregorianCalendar endDate);
  }
  
  GridPane getCalendar();
  
  void setEvents(List<EventBean> events);

  void setMonthListener(MonthListener listener);
}
