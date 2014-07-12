/**
 * 
 */
package presenter;

import view.type.LoginScreen;
import view.ui.LoginScreenView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author elijahr
 *
 */
public class Main extends Application {
  public static void main(String[] args) {
      launch(args);
  }
  
  
  @Override
  public void start(Stage primaryStage) {
    
    LoginScreen loginScreen = new LoginScreenView();
    LoginPresenter loginPresenter = new LoginPresenter(loginScreen);
    loginScreen.setLoginListener(loginPresenter);
    
    loginScreen.showLoginScreen();
  }
  
}