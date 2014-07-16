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
  private final GregorianCalendar gregorianCalendar;
  private final UserBean user;

  private VBox eventsLayout;
  private VBox layout;
  private List<EventBean> events;


  public DayView(final UserBean user, int currentMonth, final GregorianCalendar gregorianCalendar) {

    this.user = user;
    this.gregorianCalendar = gregorianCalendar;

    Label dayLabel = new Label(String.valueOf(gregorianCalendar.get(GregorianCalendar.DATE)));

    eventsLayout = new VBox(3);
    eventsLayout.setStyle("-fx-font-size: 10px");

    layout = new VBox();
    layout.getChildren().addAll(dayLabel, eventsLayout);

    // some initial styling, to complete later
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
    layout.setMinSize(80, 80);
    layout.setMaxSize(80, 80);

    layout.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        System.out.println("Day clicked is " + (gregorianCalendar.get(GregorianCalendar.MONTH) + 1)
            + " / " + gregorianCalendar.get(GregorianCalendar.DATE) + " / "
            + gregorianCalendar.get(GregorianCalendar.YEAR));
      }
    });
  }


  /**
   * Tells the presenter to fetch the events for this day and adds them as
   * labels to the day.
   */
  private void addEvents() {
    listener.getEvents();

    // only add events if there are some
    if (events != null) {
      for (final EventBean eventBean : events) {
        Label eventLabel = new Label(eventBean.getName());
        eventLabel.setOnMouseClicked(new EventHandler<Event>() {
          @Override
          public void handle(Event event) {
            // Call to DayPresenter to open the event window for this event and user
            listener.showEventScreen(eventBean, user);
          }
        });
        eventsLayout.getChildren().add(eventLabel);
      }
    }
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

}
