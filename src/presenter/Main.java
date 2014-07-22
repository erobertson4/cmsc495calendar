/**
 * 
 */
package presenter;

import view.type.LoginScreen;
import view.ui.LoginScreenView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Starting class of the Calendar application. Launches the application.
 * 
 * Matt's test comment is now a class commment.
 * 
 * @author elijahr
 *
 */
public class Main extends Application {
  public static void main(String[] args) {
      launch(args);
  }
  
  
  @Override
  public void start(Stage primaryStage) {
    // initialize the login screen and display it by using its presenter
    LoginScreen loginScreen = new LoginScreenView();
    LoginPresenter loginPresenter = new LoginPresenter(loginScreen);
    loginScreen.setLoginListener(loginPresenter);
    
    loginScreen.showLoginScreen();
  }
  
}