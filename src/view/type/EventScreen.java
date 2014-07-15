/**
 * 
 */
package view.type;

/**
 * Interface to the EventScreenView GUI class the controls the interaction
 * between View and Presenter.
 * 
 * @author elijahr
 *
 */
public interface EventScreen {

  /**
   * Actions that the EventPresenter will execute on an EventScreen.
   * 
   * @author elijahr
   *
   */
  public interface EventListener {

    /**
     * Instruct the EventScreen to close itself.
     */
    void hide();

  }


  /**
   * Set the listener for the EventScreen.
   * 
   * @param listener
   *          the listener for the EventScreen GUI object.
   */
  void setEventListener(EventListener listener);


  /**
   * Show the EventScreen.
   */
  void showEventScreen();


  /**
   * Hide the EventScreen.
   */
  void hideEventScreen();
}
