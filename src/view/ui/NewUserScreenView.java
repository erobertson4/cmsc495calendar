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

import org.controlsfx.dialog.Dialogs;

import view.type.NewUserScreen;

/**
 * @author elijahr
 *
 */
public class NewUserScreenView implements NewUserScreen {
  
  private NewUserListener listener;
  
  private Stage stage;
  
  private TextField firstNameTextField;
  private TextField lastNameTextField;
  private TextField usernameTextField;
  private PasswordField password1TextField;
  private PasswordField password2TextField;
  private Button createButton;
  
  public NewUserScreenView() {
    stage = new Stage();
    Label instructionsLabel = new Label("Create an account by filling out this form:");
    
    // create form fields
    Label firstNameLabel = new Label("First Name*");
    firstNameTextField = new TextField();
    firstNameTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setCreateButtonEnabled();
      }
    });
    
    Label lastNameLabel = new Label("Last Name*");
    lastNameTextField = new TextField();
    lastNameTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setCreateButtonEnabled();
      }
    });
    
    Label usernameLabel = new Label("Username*");
    usernameTextField = new TextField();
    usernameTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setCreateButtonEnabled();
      }
    });
    
    Label passwordLabel1 = new Label("Password*");
    password1TextField = new PasswordField();
    password1TextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setCreateButtonEnabled();
      }
    });
    
    Label passwordLabel2 = new Label("Confirm Password*");
    password2TextField = new PasswordField();
    password2TextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setCreateButtonEnabled();
      }
    });
    
    // create action buttons
    createButton = new Button("Create");
    createButton.setDisable(true);
    createButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // attempt validation
        if (!password1TextField.getText().equals(password2TextField.getText())) {
          Dialogs.create()
            .owner(stage)
            .title("Invalid Passwords")
            .message("Passwords did not match!")
            .showError();
        }
        else {
          String firstName = firstNameTextField.getText();
          String lastName = lastNameTextField.getText();
          String username = usernameTextField.getText();
          String password = password1TextField.getText();
          listener.createNewUser(firstName, lastName, username, password);
        }
      }
    });
    
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        new LoginScreenView();
        listener.showLoginScreen();
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
    stage.setScene(scene);
    stage.setTitle("Create Account");
  }
  
  
  /**
   * Only enable create button if there is text in all fields.
   */
  private void setCreateButtonEnabled() {
    if (firstNameTextField.getText().isEmpty() ||
        lastNameTextField.getText().isEmpty() ||
        usernameTextField.getText().isEmpty() ||
        password1TextField.getText().isEmpty() ||
        password2TextField.getText().isEmpty()) {
      createButton.setDisable(true);
    }
    else {
      createButton.setDisable(false);
    }
  }


  @Override
  public void setNewUserListener(NewUserListener listener) {
    this.listener = listener;
  }


  @Override
  public void showNewUserScreen() {
    stage.show();
  }


  @Override
  public void hideNewUserScreen() {
    stage.hide();
  }
  
}
