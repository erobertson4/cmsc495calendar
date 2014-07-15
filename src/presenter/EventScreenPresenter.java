/**
 * 
 */
package presenter;

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
}
