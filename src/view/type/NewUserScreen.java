/**
 * 
 */
package view.type;

/**
 * Interface to the NewUserScreenView GUI class the controls the interaction
 * between View and Presenter.
 * 
 * @author elijahr
 *
 */
public interface NewUserScreen {

  /**
   * Actions that the NewUserPresenter will execute on a NewUserScreen.
   * @author elijahr
   *
   */
  public interface NewUserListener {

    /**
     * Attempt to create a new user based on the given first name, last name,
     * username, and password.
     * 
     * @param firstName
     *          the first name of a potential new user.
     * @param lastName
     *          the last name of a potential new user.
     * @param username
     *          the username of a potential new user.
     * @param password
     *          the password of a potential new user.
     */
    void createNewUser(String firstName, String lastName, String username,
        String password);


    /**
     * Show the LoginScreen.
     */
    void showLoginScreen();

  }


  /**
   * Set the listener for the NewUserScreen.
   * 
   * @param listener
   *          the listener for the NewUserScreen GUI object.
   */
  void setNewUserListener(NewUserListener listener);


  /**
   * Show the NewUserScreen.
   */
  void showNewUserScreen();


  /**
   * Hide the NewUserScreen.
   */
  void hideNewUserScreen();

}
