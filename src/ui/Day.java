/**
 * 
 */
package ui;

import java.util.GregorianCalendar;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author elijahr
 * 
 */
public class Day {

  private GregorianCalendar gregorianCalendar;
  private int day;
  private VBox layout;


  public Day(int day, GregorianCalendar gregorianCalendar) {
    
    final int thisDay = day;
    this.day = day;

    Label dayLabel = new Label(String.valueOf(gregorianCalendar.get(GregorianCalendar.DATE)));

    layout = new VBox();
    
    layout.getChildren().add(dayLabel);
    layout.setStyle("-fx-border: 2px solid; -fx-border-color: blue;");
    
    int dayOfWeek = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);
    
    if (dayOfWeek == GregorianCalendar.SUNDAY || dayOfWeek == GregorianCalendar.SATURDAY) {
      layout.setStyle("-fx-background-color: gray");
    }
    
    layout.setMinSize(80, 80);
    layout.setMaxSize(80, 80);
    
    layout.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        System.out.println("Day clicked at {" + thisDay + "}");
      }
    });
  }


  public GregorianCalendar getGregorianCalendar() {
    return gregorianCalendar;
  }


  public void setGregorianCalendar(GregorianCalendar gregorianCalendar) {
    this.gregorianCalendar = gregorianCalendar;
  }


  public int getDay() {
    return day;
  }


  public void setDay(int day) {
    this.day = day;
  }


  public VBox getLayout() {
    return layout;
  }


  public void setLayout(VBox layout) {
    this.layout = layout;
  }
  

}
