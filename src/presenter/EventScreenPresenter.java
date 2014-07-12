/**
 * 
 */
package presenter;

import view.type.EventScreen;
import view.type.EventScreen.EventListener;

/**
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
