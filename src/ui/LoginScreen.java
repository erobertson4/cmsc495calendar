/**
 * 
 */
package ui;

import java.util.GregorianCalendar;

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

/**
 * @author elijahr
 *
 */
public class LoginScreen {
  private Stage stage; 

  private TextField usernameTextField;
  private PasswordField passwordField;
  
  private Button loginButton;
  private Button newUserButton;
  
  public LoginScreen() {
    stage = new Stage();
    Label instructions = new Label("Please enter your username and password.");
    
    // create login form fields
    Label usernameLabel = new Label("Username*");
    usernameTextField = new TextField();
    usernameTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setLoginButtonEnabled();
      }
    });
    
    Label passwordLabel = new Label("Password*");
    passwordField = new PasswordField();
    passwordField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setLoginButtonEnabled();
      }
    });
    
    // create action buttons
    loginButton = new Button("Login");
    // TODO uncomment once we get logging in working.
//    loginButton.setDisable(true);
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // attempt to login with supplied credentials
        // TODO make sure it is a genuine login before creating a calendar
        new Calendar(new GregorianCalendar());
        stage.hide();
      }
    });
    
    newUserButton = new Button("New User?");
    newUserButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        new NewUserScreen();
        stage.hide();
      }
    });
    
    Button exitButton = new Button("Exit");
    exitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.exit(0);
        stage.hide();
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
    
    // create and show screen
    Scene scene = new Scene(loginGridPane);
    stage.setScene(scene);
    stage.setTitle("Login");
    stage.show();
  }
  
  
  /**
   * Only enable login button if there is text in both username and password fields.
   */
  private void setLoginButtonEnabled() {
    if (usernameTextField.getText().isEmpty() ||
        passwordField.getText().isEmpty()) {
      loginButton.setDisable(true);
    }
    else {
      loginButton.setDisable(false);
    }
  }
  
}
