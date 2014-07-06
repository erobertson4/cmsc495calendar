/**
 * 
 */
package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author elijahr
 *
 */
public class InformationDialogBox extends Stage {
  
  
  public InformationDialogBox(String dialogTitle, String dialogText) {
    
    Label dialogTextLabel = new Label(dialogText);
    dialogTextLabel.setWrapText(true);
    
    
    Button okayButton = new Button("Okay");
    okayButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        hide();
      }
    });
    
    VBox layout = new VBox();
    layout.getChildren().addAll(dialogTextLabel, okayButton);
    
    // create and show screen
    Scene scene = new Scene(layout);
    setWidth(500);
    setHeight(200);
    setScene(scene);
    setTitle(dialogTitle);
    show();
  }
}
