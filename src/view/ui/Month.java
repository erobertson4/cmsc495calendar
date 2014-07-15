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
import view.type.Day;

/**
 * GUI class for the Month. Contains a 6 x 7 grid of Days.
 * 
 * @author elijahr
 *
 */
public class Month {

  private static final int CALENDAR_COLUMNS = 7;
  private static final int CALENDAR_ROWS = 6;

  private GridPane calendar;


  public Month(GregorianCalendar gregorianCalendar) {

    final int currentMonth = gregorianCalendar.get(GregorianCalendar.MONTH);
    final int currentYear = gregorianCalendar.get(GregorianCalendar.YEAR);
    int firstDayOfMonth = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);

    calendar = new GridPane();
    calendar.setHgap(10);
    calendar.setVgap(10);

    // create a list of 42 days to be displayed in this calendar
    List<Day> days = new ArrayList<Day>();
    for (int index = 1; index <= CALENDAR_ROWS * CALENDAR_COLUMNS; index++) {
      GregorianCalendar date = new GregorianCalendar(currentYear, currentMonth,
          index - firstDayOfMonth + 1);
      days.add(new DayView(currentMonth, date));
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


  public GridPane getCalendar() {
    return calendar;
  }

}
