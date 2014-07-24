/**
 * 
 */
package view.ui;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.bean.EventBean;
import model.bean.UserBean;
import presenter.DayPresenter;
import view.type.Day;
import view.type.Month;

/**
 * GUI class for the Month. Contains a 6 x 7 grid of Days.
 * 
 * @author elijahr
 *
 */
public class MonthView implements Month {

  private static final int CALENDAR_COLUMNS = 7;
  private static final int CALENDAR_ROWS = 6;

  private MonthListener listener;
  
  private UserBean user;
  private GregorianCalendar startDate;
  private GregorianCalendar endDate;
  
  private GridPane calendar;
  private List<EventBean> events;
  
  private int currentMonth;
  private int currentYear;
  private int firstDayOfMonth;

  public MonthView(UserBean user, GregorianCalendar gregorianCalendar) {

    this.user = user;
    
    currentMonth = gregorianCalendar.get(GregorianCalendar.MONTH);
    currentYear = gregorianCalendar.get(GregorianCalendar.YEAR);
    firstDayOfMonth = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);

    calendar = new GridPane();
    calendar.setHgap(10);
    calendar.setVgap(10);
    
    startDate = new GregorianCalendar(currentYear, currentMonth,
        1 - firstDayOfMonth + 1);
    endDate = new GregorianCalendar(currentYear, currentMonth,
        1 - firstDayOfMonth + (CALENDAR_ROWS * CALENDAR_COLUMNS));
    
  }
  
  
  private void createDays() {
    listener.getEvents(startDate, endDate);
    
    // create a list of 42 days to be displayed in this calendar
    List<Day> days = new ArrayList<Day>();
    for (int index = 1; index <= CALENDAR_ROWS * CALENDAR_COLUMNS; index++) {
      GregorianCalendar date = new GregorianCalendar(currentYear, currentMonth,
          index - firstDayOfMonth + 1);
      
      // add each Day and its Listener to the list
      Day day = new DayView(user, currentMonth, date);
      DayPresenter dayPresenter = new DayPresenter(day);
      day.setDayListener(dayPresenter);
      
      List<EventBean> eventsForDay = new ArrayList<EventBean>();
      
      for (EventBean eventBean : events) {
        if (eventBean.getDate().equals(day.getLocalDate())) {
          eventsForDay.add(eventBean);
        }
      }
      
      day.setEvents(eventsForDay);
      
      days.add(day);
    }

    // add the days of the week
    for (int dayOfWeek = 0; dayOfWeek < CALENDAR_COLUMNS; dayOfWeek++) {
      Label day = new Label(CalendarView.DAYS_OF_WEEK[dayOfWeek]);
      HBox box = new HBox();
      box.setAlignment(Pos.CENTER);
      box.getChildren().add(day);
      day.setAlignment(Pos.CENTER);
      calendar.add(box, dayOfWeek, 0);
    }

    // add the days to the calendar
    int index = 0;

    for (int row = 1; row <= CALENDAR_ROWS; row++) {
      for (int column = 0; column < CALENDAR_COLUMNS; column++) {
        calendar.add(days.get(index).getLayout(), column, row);
        index++;
      }

    }
  }


  @Override
  public GridPane getCalendar() {
    return calendar;
  }


  @Override
  public void setEvents(List<EventBean> events) {
    this.events = events;
  }


  @Override
  public void setMonthListener(MonthListener listener) {
    this.listener = listener;
    createDays();
  }

}
