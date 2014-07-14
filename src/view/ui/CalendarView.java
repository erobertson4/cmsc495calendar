/**
 * 
 */
package view.ui;

import java.util.GregorianCalendar;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bean.UserBean;
import view.type.Calendar;

/**
 * @author elijahr
 *
 */
public class CalendarView implements Calendar {
  
  private CalendarListener listener;
  
  private GregorianCalendar gregorianCalendar;
  
  public static final String[] MONTHS_OF_YEAR = { "January", "February", "March", "April", "May", "June", "July",
                                                   "August", "September", "October", "November", "December" };
  public static final String[] DAYS_OF_WEEK = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
  private final Integer[] YEARS;
  
  private Stage stage;
  private Month month;
  
  private Label monthYearDisplayedLabel;
  private VBox layout;
  
  private ComboBox<String> monthComboBox;
  private ComboBox<Integer> yearComboBox;
  
  private Button previousMonthButton;
  private Button nextMonthButton;
  
  
  public CalendarView(final UserBean user, final GregorianCalendar gregorianCalendar) {
    
    this.gregorianCalendar = gregorianCalendar;
    
    stage = new Stage();
    
    // create a GregorianCalendar to help set up our calendar
    gregorianCalendar.set(GregorianCalendar.DATE, 1);
    final int currentMonth = gregorianCalendar.get(GregorianCalendar.MONTH);
    final int currentYear = gregorianCalendar.get(GregorianCalendar.YEAR);
    String currentMonthString = MONTHS_OF_YEAR[currentMonth];
    
    // calculate the years to be displayed in the combo box.
    int startingYear = currentYear - 5;
    YEARS = new Integer[11];
    for (int index = 0; index < YEARS.length; index++) {
      YEARS[index] = startingYear + index;
    }
    
    // create the month and year combo boxes
    monthComboBox = new ComboBox<String>();
    monthComboBox.getItems().addAll(MONTHS_OF_YEAR);
    monthComboBox.setValue(currentMonthString);
    monthComboBox.setVisibleRowCount(MONTHS_OF_YEAR.length);
    
    yearComboBox = new ComboBox<Integer>();
    yearComboBox.getItems().addAll(YEARS);
    yearComboBox.setValue(currentYear);
    yearComboBox.setVisibleRowCount(YEARS.length);
    
    Button setCalendarButton = new Button("Set");
    setCalendarButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(yearComboBox.getValue(), convertMonthToInt(monthComboBox.getValue()), 1);
        setNewMonth(gregorianCalendar);
      }
    });
    
    previousMonthButton = new Button("<<");
    previousMonthButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(GregorianCalendar.MONTH, gregorianCalendar.get(GregorianCalendar.MONTH) - 1);
        setNewMonth(gregorianCalendar);
        setNavigationControls();
      }
    });
    
    nextMonthButton = new Button(">>");
    nextMonthButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(GregorianCalendar.MONTH, gregorianCalendar.get(GregorianCalendar.MONTH) + 1);
        setNewMonth(gregorianCalendar);
        setNavigationControls();
      }
    });
    
    StackPane stack = new StackPane();
    Button logoutButton = new Button("Logout");
    stack.getChildren().addAll(logoutButton);
    stack.setAlignment(Pos.CENTER_RIGHT);
    logoutButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        listener.logout();
      }
    });
    
    HBox controlsLayout = new HBox();
    controlsLayout.getChildren().addAll(monthComboBox, yearComboBox, setCalendarButton, previousMonthButton, nextMonthButton);
    //Ensures the logout button stays to the far right of the HBox
    controlsLayout.getChildren().add(stack);
    HBox.setHgrow(stack, Priority.ALWAYS);
    
    month = new Month(gregorianCalendar);
    
    monthYearDisplayedLabel = new Label(currentMonthString + " " + currentYear);
    monthYearDisplayedLabel.setStyle("-fx-font-size: 20px");
    
    layout = new VBox();
    layout.setPadding(new Insets(10));
    layout.getChildren().addAll(monthYearDisplayedLabel, controlsLayout, month.getCalendar());
    
    // create and show screen
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.setTitle("Calendar");
  }

  
  /**
   * Update the Calendar with the values of the new month.
   * @param gregorianCalendar
   */
  private void setNewMonth(GregorianCalendar gregorianCalendar) {
    layout.getChildren().remove(month.getCalendar());
    
    int newMonth = gregorianCalendar.get(GregorianCalendar.MONTH);
    int newYear = gregorianCalendar.get(GregorianCalendar.YEAR);
    
    monthYearDisplayedLabel.setText(MONTHS_OF_YEAR[newMonth] + " " + newYear);
    
    monthComboBox.setValue(MONTHS_OF_YEAR[newMonth]);
    yearComboBox.setValue(newYear);
    
    month = new Month(gregorianCalendar);
    layout.getChildren().add(month.getCalendar());
    layout.getChildren().set(0, monthYearDisplayedLabel);
  }
  
  
  /**
   * Convert the String for the month into an int.
   * @param monthText
   * @return
   */
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
  
  
  /**
   * Determine if the next and previous month buttons should be disabled or enabled.
   */
  private void setNavigationControls() {
    
    if (gregorianCalendar.get(GregorianCalendar.MONTH) == GregorianCalendar.JANUARY &&
        gregorianCalendar.get(GregorianCalendar.YEAR) == YEARS[0]) {
      previousMonthButton.setDisable(true);
    }
    else {
      previousMonthButton.setDisable(false);
    }
    
    if (gregorianCalendar.get(GregorianCalendar.MONTH) == GregorianCalendar.DECEMBER &&
        gregorianCalendar.get(GregorianCalendar.YEAR) == YEARS[YEARS.length-1]) {
      nextMonthButton.setDisable(true);
    }
    else {
      nextMonthButton.setDisable(false);
    }
    
  }


  @Override
  public void setCalendarListener(CalendarListener listener) {
    this.listener = listener;
  }


  @Override
  public void showCalendar() {
    stage.show();
  }


  @Override
  public void hideCalendar() {
    stage.hide();
  }
  
}
