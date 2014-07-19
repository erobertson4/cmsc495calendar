/**
 * 
 */
package presenter;

import model.bean.EventBean;
import view.type.EventScreen;
import view.type.EventScreen.EventListener;

/**
 * Presenter class for EventScreen. Controls interaction between this GUI
 * element and other elements.
 * 
 * @author elijahr
 *
 */
public class EventScreenPresenter implements EventListener {

  private EventScreen eventScreen;


  public EventScreenPresenter(EventScreen eventScreen) {
    this.eventScreen = eventScreen;
  }


  @Override
  public void hide() {
    eventScreen.hideEventScreen();
  }


  @Override
  public void save(EventBean event) {
    // [MJ] save the event in the database
  }


  @Override
  public void delete(EventBean event) {
    // [MJ] delete the event from the database
  }
}
