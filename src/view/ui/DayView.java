/**
 * 
 */
package view.ui;

import java.util.GregorianCalendar;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.bean.EventBean;
import view.type.Day;

/**
 * @author elijahr
 * 
 */
public class DayView implements Day {

  private DayListener listener;
  private final GregorianCalendar gregorianCalendar;
  
  private VBox layout;
  private List<EventBean> events;


  public DayView(int currentMonth, final GregorianCalendar gregorianCalendar) {
    
    this.gregorianCalendar = gregorianCalendar;
    
    Label dayLabel = new Label(String.valueOf(gregorianCalendar.get(GregorianCalendar.DATE)));
    
    // TODO [ER] I need to finish the linking to get the list of events for a day
//    listener.getEvents();
//    
//    VBox eventsLayout = new VBox(3);
//    eventsLayout.setStyle("-fx-font-size: 10px");
//    
//    for (final EventBean eventBean : events) {
//      Label eventLabel = new Label(eventBean.getName());
//      eventLabel.setOnMouseClicked(new EventHandler<Event>() {
//        @Override
//        public void handle(Event event) {
//          listener.showEventScreen(eventBean);
//        }
//      });
//      
//      eventsLayout.getChildren().add(eventLabel);
//    }
    
    
    
    layout = new VBox();
    layout.getChildren().addAll(dayLabel);
    
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


  @Override
  public void setDayListener(DayListener listener) {
    this.listener = listener;
  }


  @Override
  public void setEvents(List<EventBean> events) {
    this.events = events;
  }

  
  @Override
  public GregorianCalendar getGregorianCalendar() {
    return gregorianCalendar;
  }


}
