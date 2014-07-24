/**
 * 
 */
package view.ui;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.bean.EventBean;
import model.bean.UserBean;
import view.type.Day;

/**
 * GUI class for the Day. Shows the day of the month, and a list of events on
 * the Day. Each Day knows what month and year it belongs to, so overlapping
 * Days that belong to a month different from the month displayed will be grayed
 * out. Weekends and other holidays will be identified as well.
 * 
 * @author elijahr
 * 
 */
public class DayView implements Day {

  private DayListener listener;
  private final UserBean user;
  private final GregorianCalendar gregorianCalendar;
  private LocalDate date;

  private VBox eventsLayout;
  private VBox layout;
  private Map<EventBean, Label> eventsMap;
  private List<EventBean> events;


  public DayView(final UserBean user, int currentMonth, final GregorianCalendar gregorianCalendar) {

    this.user = user;
    this.gregorianCalendar = gregorianCalendar;
    date = LocalDate.of(gregorianCalendar.get(GregorianCalendar.YEAR),
        gregorianCalendar.get(GregorianCalendar.MONTH)+1,
        gregorianCalendar.get(GregorianCalendar.DATE));
    
    eventsMap = new HashMap<EventBean, Label>();
    
    Label dayLabel = new Label(String.valueOf(gregorianCalendar.get(GregorianCalendar.DATE)));

    eventsLayout = new VBox(3);
    eventsLayout.setStyle("-fx-font-size: 10px");

    // create the box
    layout = new VBox();
    layout.setPadding(new Insets(2));
    layout.getChildren().addAll(dayLabel, eventsLayout);

    // styling for the day
    String style = "-fx-border: 2px solid; -fx-border-color: blue;";

    int dayOfWeek = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);

    // identify the weekends
    if (dayOfWeek == GregorianCalendar.SUNDAY || dayOfWeek == GregorianCalendar.SATURDAY) {
      style += "-fx-background-color: #E8E8E8;";
    }

    // shade the overlapping days from the other months
    if (currentMonth != gregorianCalendar.get(GregorianCalendar.MONTH)) {
      dayLabel.setTextFill(Color.web("#A8A8A8"));
    }

    layout.setStyle(style);
    layout.setMinSize(120, 85);
    layout.setMaxSize(120, 85);

  }


  /**
   * Tells the presenter to fetch the events for this day and adds them as
   * labels to the day.
   */
  private void addEvents() {
    // only add events if there are some
    if (events != null) {
      for (EventBean event : events) {
        addEvent(event);
      }
    }
  }
  
  
  /**
   * Add the event to the map of events and to the GUI.
   * @param eventBean the event to be added
   */
  private void addEvent(final EventBean eventBean) {
    Label eventLabel = new Label(eventBean.getTitle());
    eventsMap.put(eventBean, eventLabel);
    Tooltip.install(eventLabel, new Tooltip(eventLabel.getText()));
    eventLabel.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        // Call to DayPresenter to open the event window for this event and user
        listener.showEventScreen(eventBean, user);
      }
    });
    eventsLayout.getChildren().add(eventLabel);
  }


  @Override
  public VBox getLayout() {
    return layout;
  }


  @Override
  public void setDayListener(DayListener listener) {
    this.listener = listener;
    addEvents();
  }


  @Override
  public void setEvents(List<EventBean> events) {
    this.events = events;
  }


  @Override
  public GregorianCalendar getGregorianCalendar() {
    return gregorianCalendar;
  }


  @Override
  public LocalDate getLocalDate() {
    return date;
  }
  
  
  @Override
  public void saveEvent(EventBean event) {
    addEvent(event);
  }
  
  
  @Override
  public void removeEvent(EventBean event) {
    if (events != null) {
      events.remove(event);
    }
    
    Label deletedLabel = eventsMap.get(event);
    eventsMap.remove(event);
    
    eventsLayout.getChildren().remove(deletedLabel);
    
  }

}
