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
public class NewUserScreen extends Stage {
  
  public NewUserScreen() {
    Label instructionsLabel = new Label("Create an account by filling out this form:");
    
    // create form fields
    Label firstNameLabel = new Label("First Name");
    TextField firstNameTextField = new TextField();
    
    Label lastNameLabel = new Label("Last Name");
    TextField lastNameTextField = new TextField();
    
    Label usernameLabel = new Label("Username");
    TextField usernameTextField = new TextField();
    
    Label passwordLabel1 = new Label("Password");
    PasswordField password1TextField = new PasswordField();
    
    Label passwordLabel2 = new Label("Confirm Password");
    PasswordField password2TextField = new PasswordField();
    
    // create action buttons
    Button createButton = new Button("Create");
    createButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // attempt validation
      }
    });
    
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        new LoginScreen();
        hide();
      }
    });
    
    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().addAll(createButton, cancelButton);
    
    // create layout to hold form fields
    GridPane newUserGridPane = new GridPane();
    newUserGridPane.setAlignment(Pos.CENTER);
    newUserGridPane.setPadding(new Insets(20));
    newUserGridPane.setVgap(5);
    newUserGridPane.setHgap(10);
    GridPane.setMargin(instructionsLabel, new Insets(0, 0, 20, 0));
    GridPane.setMargin(buttonsBox, new Insets(20, 0, 0, 0));
    
    // add form fields to layout
    newUserGridPane.add(instructionsLabel, 0, 0, 2, 1);
    newUserGridPane.add(firstNameLabel, 0, 1);
    newUserGridPane.add(firstNameTextField, 1, 1);
    newUserGridPane.add(lastNameLabel, 0, 2);
    newUserGridPane.add(lastNameTextField, 1, 2);
    newUserGridPane.add(usernameLabel, 0, 3);
    newUserGridPane.add(usernameTextField, 1, 3);
    newUserGridPane.add(passwordLabel1, 0, 4);
    newUserGridPane.add(password1TextField, 1, 4);
    newUserGridPane.add(passwordLabel2, 0, 5);
    newUserGridPane.add(password2TextField, 1, 5);
    newUserGridPane.add(buttonsBox, 0, 6, 2, 1);
    
    // create and show screen
    Scene scene = new Scene(newUserGridPane);
    setScene(scene);
    show();
    
  }
  
}
