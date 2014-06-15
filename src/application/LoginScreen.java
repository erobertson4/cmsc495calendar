/**
 * 
 */
package application;

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
    
    Label usernameLabel = new Label("Username");
    TextField username = new TextField();
    
    Label passwordLabel = new Label("Password");
    PasswordField password = new PasswordField();
    
    Button loginButton = new Button("Login");
    Button newUserButton = new Button("New User?");
    
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().addAll(loginButton, newUserButton);
    
    GridPane loginScreen = new GridPane();
    loginScreen.setVgap(5);
    loginScreen.setHgap(10);
    
    loginScreen.add(instructions, 0, 0, 2, 1);
    loginScreen.add(usernameLabel, 0, 1);
    loginScreen.add(username, 1, 1);
    loginScreen.add(passwordLabel, 0, 2);
    loginScreen.add(password, 1, 2);
    loginScreen.add(buttonsBox, 0, 3, 2, 1);
    
    
    Scene scene = new Scene(loginScreen, 300, 150);
    loginScreen.setAlignment(Pos.CENTER);
    
    setScene(scene);
    show();
    
  }
  
}
