package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewEventWindow extends Stage {
  private ComboBox<Integer> startHourSelector;
  private ComboBox<Integer> startMinuteSelector;
  
  private ComboBox<Integer> endHourSelector;
  private ComboBox<Integer> endMinuteSelector;
  
  public NewEventWindow() {
    
    startHourSelector = new ComboBox<Integer>();
    
    for (int hour = 1; hour <= 12; hour++) {
      startHourSelector.getItems().add(hour);
    }
    
    startMinuteSelector = new ComboBox<Integer>();
    
    for (int minute = 0; minute < 60; minute+=5) {
      startMinuteSelector.getItems().add(minute);
    }
    
    ComboBox<String> am_pmSelector = new ComboBox<String>();
    am_pmSelector.getItems().addAll("AM", "PM");
    am_pmSelector.setValue(am_pmSelector.getItems().get(0));
    
    
    HBox startTimeSelectionLayout = new HBox();
    startTimeSelectionLayout.getChildren().addAll(startHourSelector, startMinuteSelector, am_pmSelector);
    
    
    endHourSelector = new ComboBox<Integer>();
    
    for (int hour = 1; hour <= 12; hour++) {
      endHourSelector.getItems().add(hour);
    }
    
    endMinuteSelector = new ComboBox<Integer>();
    
    for (int minute = 0; minute < 60; minute+=5) {
      endMinuteSelector.getItems().add(minute);
    }
    
    ComboBox<String> endAm_PmSelector = new ComboBox<String>();
    endAm_PmSelector.getItems().addAll("AM", "PM");
    endAm_PmSelector.setValue(am_pmSelector.getItems().get(0));
    
    
    HBox endTimeSelectionLayout = new HBox();
    endTimeSelectionLayout.getChildren().addAll(endHourSelector, endMinuteSelector, endAm_PmSelector);
    
    
    // create action buttons
    Button createButton = new Button("Create");
    createButton.setDisable(true);
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
        close();
        hide();
      }
    });
    
    
    
    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().addAll(createButton, cancelButton);
    
    VBox layout = new VBox();
    layout.getChildren().addAll(startTimeSelectionLayout, endTimeSelectionLayout, buttonsBox);
    
    // create and show screen
    Scene scene = new Scene(layout);
    setScene(scene);
    show();
  }
  
}
