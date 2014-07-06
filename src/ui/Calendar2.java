/**
 * 
 */
package ui;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author elijahr
 *
 */
public class Calendar2 {
  
  private static final String[] MONTHS_OF_YEAR = { "January", "February", "March", "April", "May", "June", "July",
                                                   "August", "September", "October", "November", "December" };
  
  private static final String[] DAYS_OF_WEEK = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
  
  private static final int CALENDAR_COLUMNS = 7;
  private static final int CALENDAR_ROWS    = 6;
  
  
  private Stage stage;
  
  private Label monthYearDisplayedLabel;
  
  public Calendar2(final GregorianCalendar gregorianCalendar) {
    
    stage = new Stage();
    
    // create a GregorianCalendar to help set up our calendar
    gregorianCalendar.set(GregorianCalendar.DATE, 1);
    final int currentMonth = gregorianCalendar.get(GregorianCalendar.MONTH);
    final int currentYear = gregorianCalendar.get(GregorianCalendar.YEAR);
    String currentMonthString = MONTHS_OF_YEAR[currentMonth];
    
    // calculate the years to be displayed in the combo box.
    int startingYear = currentYear - 5;
    Integer[] YEARS = new Integer[10];
    for (int index = 0; index < YEARS.length; index++) {
      YEARS[index] = startingYear + index;
    }
    
    // create the month and year combo boxes
    final ComboBox<String> monthComboBox = new ComboBox<String>();
    monthComboBox.getItems().addAll(MONTHS_OF_YEAR);
    monthComboBox.setValue(currentMonthString);
    monthComboBox.setVisibleRowCount(MONTHS_OF_YEAR.length);
    
    final ComboBox<Integer> yearComboBox = new ComboBox<Integer>();
    yearComboBox.getItems().addAll(YEARS);
    yearComboBox.setValue(currentYear);
    yearComboBox.setVisibleRowCount(YEARS.length);
    
    Button setCalendarButton = new Button("Set");
    setCalendarButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(yearComboBox.getValue(), convertMonthToInt(monthComboBox.getValue()), 1);
        new Calendar2(gregorianCalendar);
      }
    });
    
    Button previousMonthButton = new Button("<<");
    previousMonthButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(currentYear, currentMonth - 1, 1);
        new Calendar2(gregorianCalendar);
      }
    });
    
    Button nextMonthButton = new Button(">>");
    nextMonthButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(currentYear, currentMonth + 1, 1);
        new Calendar2(gregorianCalendar);
      }
    });

    
    HBox controlsLayout = new HBox();
    controlsLayout.getChildren().addAll(monthComboBox, yearComboBox, setCalendarButton, previousMonthButton, nextMonthButton);
    
    List<Day> days = new ArrayList<Day>();
    
    int firstDayOfMonth = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);
    System.out.println(firstDayOfMonth);
    
    for (int index = 1; index <= CALENDAR_ROWS * CALENDAR_COLUMNS; index++) {
      days.add(new Day(index-1, new GregorianCalendar(currentYear, currentMonth, index - firstDayOfMonth + 1)));
    }
    
    GridPane calendar = new GridPane();
    calendar.setHgap(10);
    calendar.setVgap(10);
    
    for (int dayOfWeek = 0; dayOfWeek < CALENDAR_COLUMNS; dayOfWeek++) {
      Label day = new Label(DAYS_OF_WEEK[dayOfWeek]);
      HBox box = new HBox();
      box.setAlignment(Pos.CENTER);
      box.getChildren().add(day);
      day.setAlignment(Pos.CENTER);
      calendar.add(box, dayOfWeek, 0);
    }
    
    int index = 0;
    
    for (int row = 1; row <= CALENDAR_ROWS; row++) {
      for (int column = 0; column < CALENDAR_COLUMNS; column++) {
        
        calendar.add(days.get(index).getLayout(), column, row);
        index++;
      }
      
    }
    
    monthYearDisplayedLabel = new Label(currentMonthString + " " + currentYear);
    monthYearDisplayedLabel.setStyle("-fx-font-size: 20px");
    
    VBox layout = new VBox();
    layout.setPadding(new Insets(10));
    layout.getChildren().addAll(monthYearDisplayedLabel, controlsLayout, calendar);
    
    // create and show screen
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.setTitle("Calendar");
    stage.show();
  }
  
  
  private int convertMonthToInt(String monthText) {
    int month = -1;
    
    for (int index = 0; index < MONTHS_OF_YEAR.length; index++) {
      if (monthText.equals(MONTHS_OF_YEAR[index])) {
        month = index;
        break;
      }
    }
    return month;
  }
  
}
