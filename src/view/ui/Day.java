/**
 * 
 */
package view.ui;

import java.util.GregorianCalendar;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author elijahr
 * 
 */
public class Day {

  private VBox layout;


  public Day(int currentMonth, final GregorianCalendar gregorianCalendar) {
    
    Label dayLabel = new Label(String.valueOf(gregorianCalendar.get(GregorianCalendar.DATE)));

    layout = new VBox();
    layout.getChildren().add(dayLabel);
    
    // some initial styling, to complete later
    String style = "-fx-border: 2px solid; -fx-border-color: blue;";
    
    int dayOfWeek = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);
    
    // identify the weekends
    if (dayOfWeek == GregorianCalendar.SUNDAY || dayOfWeek == GregorianCalendar.SATURDAY) {
      style+="-fx-background-color: #E8E8E8;";
    }
    
    // shade the overlapping days from the other months
    if (currentMonth != gregorianCalendar.get(GregorianCalendar.MONTH)) {
      dayLabel.setTextFill(Color.web("#A8A8A8"));
    }
    
    layout.setStyle(style);
    layout.setMinSize(80, 80);
    layout.setMaxSize(80, 80);
    
    layout.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        System.out.println("Day clicked is " +
            (gregorianCalendar.get(GregorianCalendar.MONTH)+1) + " / " +
            gregorianCalendar.get(GregorianCalendar.DATE) + " / " +
            gregorianCalendar.get(GregorianCalendar.YEAR)
            );
      }
    });
  }
  

  public VBox getLayout() {
    return layout;
  }


}
