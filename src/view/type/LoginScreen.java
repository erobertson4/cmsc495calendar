/**
 * 
 */
package view.type;

/**
 * Interface to the LoginScreenView GUI class the controls the interaction
 * between View and Presenter.
 * 
 * @author elijahr
 *
 */
public interface LoginScreen {

  /**
   * Actions that the LoginScreenPresenter will execute on a LoginScreen.
   * 
   * @author elijahr
   *
   */
  public interface LoginListener {

    /**
     * Show the screen for users to create an account.
     */
    void showNewUserScreen();


    /**
     * Attempt to login using the given username and password.
     * 
     * @param username
     *          the username for the account.
     * @param password
     *          the password for the account.
     */
    void login(String username, String password);


    /**
     * Close the Calendar application.
     */
    void quit();
  }


  /**
   * Set the listener for the LoginScreen.
   * 
   * @param listener
   *          the listener for the LoginScreen GUI object.
   */
  void setLoginListener(LoginListener listener);


  /**
   * Show the LoginScreen.
   */
  void showLoginScreen();


  /**
   * Hide the LoginScreen.
   */
  void hideLoginScreen();

}
