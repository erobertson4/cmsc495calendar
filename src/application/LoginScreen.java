/**
 * 
 */
package application;

import javafx.event.ActionEvent;
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
public class LoginScreen extends Stage {
  
  
  public LoginScreen() {
    Label instructions = new Label("Please enter your username and password.");
    
    // create login form fields
    Label usernameLabel = new Label("Username");
    TextField username = new TextField();
    
    Label passwordLabel = new Label("Password");
    PasswordField password = new PasswordField();
    
    // create action buttons
    Button loginButton = new Button("Login");
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // attempt to login with supplied credentials
      }
    });
    
    Button newUserButton = new Button("New User?");
    newUserButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        new NewUserScreen();
        hide();
      }
    });
    
    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().addAll(loginButton, newUserButton);
    
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
    loginGridPane.add(username, 1, 1);
    loginGridPane.add(passwordLabel, 0, 2);
    loginGridPane.add(password, 1, 2);
    loginGridPane.add(buttonsBox, 0, 3, 2, 1);
    
    // create and show screen
    Scene scene = new Scene(loginGridPane);
    setScene(scene);
    show();
  }
  
}
