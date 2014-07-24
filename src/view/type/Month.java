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
 * @author elijahr
 *
 */
public interface Month {
  
  public interface MonthListener {
    void getEvents(LocalDate startDate, LocalDate endDate);
  }
  
  GridPane getCalendar();
  
  void setMonthListener(MonthListener listener);

  void setEvents(Map<LocalDate, List<EventBean>> events);

  Map<LocalDate, List<EventBean>> getEvents();

  List<Day> getDays();
}
