/**
 * 
 */
package ui;

import java.util.GregorianCalendar;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author elijahr
 *
 */
public class Calendar {
  
  public static final String[] MONTHS_OF_YEAR = { "January", "February", "March", "April", "May", "June", "July",
                                                   "August", "September", "October", "November", "December" };
  public static final String[] DAYS_OF_WEEK = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
  
  private Stage stage;
  private Month month;
  
  private Label monthYearDisplayedLabel;
  private VBox layout;
  
  private ComboBox<String> monthComboBox;
  private ComboBox<Integer> yearComboBox;
  
  
  public Calendar(final GregorianCalendar gregorianCalendar) {
    
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
    
    //Sets up error dialogs
    final Stage errorDialog = new Stage();
    errorDialog.initStyle(StageStyle.UTILITY);
    final Scene backScene = new Scene(new Group(new Text(25, 25, "Sorry!  The calendar cannot go any further back.")));
    final Scene nextScene = new Scene(new Group(new Text(25, 25, "Sorry!  The calendar cannot go any further forward.")));

    
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
    
    Button previousMonthButton = new Button("<<");
    previousMonthButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(GregorianCalendar.MONTH, gregorianCalendar.get(GregorianCalendar.MONTH) - 1);
        //Should stop the user from going back past January 2009; however it doesn't work yet
        if (gregorianCalendar.MONTH == 0 && gregorianCalendar.YEAR == 2009) {
            errorDialog.setScene(backScene);
            errorDialog.show();
        } else {
            setNewMonth(gregorianCalendar);
        }
      }
    });
    
    Button nextMonthButton = new Button(">>");
    nextMonthButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        gregorianCalendar.set(GregorianCalendar.MONTH, gregorianCalendar.get(GregorianCalendar.MONTH) + 1);
        //Should stop the user from going boyond December 2018; however it doesn't work yet
        if (gregorianCalendar.MONTH == 11 && gregorianCalendar.YEAR == 2018) {
            errorDialog.setScene(nextScene);
            errorDialog.show();
        } else {
            setNewMonth(gregorianCalendar);
        }
      }
    });
    
    StackPane stack = new StackPane();
    Button logoutButton = new Button("Logout");
    stack.getChildren().addAll(logoutButton);
    stack.setAlignment(Pos.CENTER_RIGHT);
    logoutButton.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
          LoginScreen ls = new LoginScreen();
          stage.close();
      }
    });
    
    HBox controlsLayout = new HBox();
    controlsLayout.getChildren().addAll(monthComboBox, yearComboBox, setCalendarButton, previousMonthButton, nextMonthButton);
    //Ensures the logout button stays to the far right of the HBox
    controlsLayout.getChildren().add(stack);
    controlsLayout.setHgrow(stack, Priority.ALWAYS);
    
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
    stage.show();
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
  
}
