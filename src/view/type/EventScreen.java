/**
 * 
 */
package view.type;


/**
 * @author elijahr
 *
 */
public interface EventScreen {
  
  public interface EventListener {

    void hide();
    
  }
  
  
  void setEventListener(EventListener listener);
  
  
  void showEventScreen();
  
  
  void hideEventScreen();
}
