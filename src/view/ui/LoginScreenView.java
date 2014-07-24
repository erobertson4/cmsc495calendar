/**
 * 
 */
package view.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.type.LoginScreen;

/**
 * GUI class for the LoginScreen. Contains two required fields (username and
 * password) and three buttons (login, create new user, and quit).
 * 
 * @author elijahr
 *
 */
public class LoginScreenView implements LoginScreen {

  private LoginListener listener;

  private Stage stage;

  private TextField usernameTextField;
  private PasswordField passwordField;
  
  
  private Button loginButton;
  private Button newUserButton;


  public LoginScreenView() {
    stage = new Stage();
    Label instructions = new Label("Please enter your username and password.");

    // create login form fields
    Label usernameLabel = new Label("Username*");
    usernameTextField = new TextField();
    usernameTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        checkLoginButtonEnabled();
      }
    });

    Label passwordLabel = new Label("Password*");
    passwordField = new PasswordField();
    passwordField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        checkLoginButtonEnabled();
      }
    });

    // create action buttons
    loginButton = new Button("Login");
    // [MJ] disables login button until value is entered in both usernameTextField and passwordField 
    loginButton.setDisable(true);
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // attempt to login with supplied credentials
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        listener.login(username, password);
      }
    });

    newUserButton = new Button("New User?");
    newUserButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        listener.showNewUserScreen();
      }
    });

    Button exitButton = new Button("Exit");
    exitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        listener.quit();
      }
    });

    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().addAll(loginButton, newUserButton, exitButton);

    // create layout to hold login form fields
    GridPane loginGridPane = new GridPane();
    loginGridPane.setAlignment(Pos.CENTER);
    loginGridPane.setPadding(new Insets(20));
    loginGridPane.setVgap(5);
    loginGridPane.setHgap(10);
    GridPane.setMargin(buttonsBox, new Insets(20, 0, 0, 0));

    // add login form fields to layout
    loginGridPane.add(instructions, 0, 0, 2, 1);
    loginGridPane.add(usernameLabel, 0, 1);
    loginGridPane.add(usernameTextField, 1, 1);
    loginGridPane.add(passwordLabel, 0, 2);
    loginGridPane.add(passwordField, 1, 2);
    loginGridPane.add(buttonsBox, 0, 3, 2, 1);
    buttonsBox.setAlignment(Pos.CENTER);

    // create scene
    Scene scene = new Scene(loginGridPane);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Login");
  }


  /**
   * Only enable login button if there is text in both username and password
   * fields.
   */
  private void checkLoginButtonEnabled() {
    if (usernameTextField.getText().isEmpty()
        || passwordField.getText().isEmpty()) {
      loginButton.setDisable(true);
    }
    else {
      loginButton.setDisable(false);
    }
  }


  @Override
  public void setLoginListener(LoginListener listener) {
    this.listener = listener;
  }


  @Override
  public void showLoginScreen() {
    stage.show();
  }


  @Override
  public void hideLoginScreen() {
    stage.hide();
  }

}
