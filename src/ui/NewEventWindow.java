package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NewEventWindow {
  private Stage stage;
  
  private ComboBox<Integer> startHourSelector;
  private ComboBox<Integer> startMinuteSelector;
  
  private ComboBox<Integer> endHourSelector;
  private ComboBox<Integer> endMinuteSelector;
  
  public NewEventWindow() {
    stage = new Stage();
    
    
    TextField eventTitleTextField = new TextField();
    
    DatePicker datePicker = new DatePicker();
    
    
    TextArea eventDescriptionTextArea = new TextArea();

    
    startHourSelector = new ComboBox<Integer>();
    for (int hour = 1; hour <= 12; hour++) {
      startHourSelector.getItems().add(hour);
    }
    startHourSelector.setVisibleRowCount(startHourSelector.getItems().size());
    
    startMinuteSelector = new ComboBox<Integer>();
    for (int minute = 0; minute < 60; minute+=5) {
      startMinuteSelector.getItems().add(minute);
    }
    startMinuteSelector.setVisibleRowCount(startMinuteSelector.getItems().size());
    
    ComboBox<String> startAm_PmSelector = new ComboBox<String>();
    startAm_PmSelector.getItems().addAll("AM", "PM");
    startAm_PmSelector.setValue(startAm_PmSelector.getItems().get(0));
    
    
    HBox startTimeSelectionLayout = new HBox();
    startTimeSelectionLayout.getChildren().addAll(startHourSelector, startMinuteSelector, startAm_PmSelector);
    
    
    endHourSelector = new ComboBox<Integer>();
    for (int hour = 1; hour <= 12; hour++) {
      endHourSelector.getItems().add(hour);
    }
    endHourSelector.setVisibleRowCount(endHourSelector.getItems().size());
    
    endMinuteSelector = new ComboBox<Integer>();
    for (int minute = 0; minute < 60; minute+=5) {
      endMinuteSelector.getItems().add(minute);
    }
    endMinuteSelector.setVisibleRowCount(endMinuteSelector.getItems().size());
    
    ComboBox<String> endAm_PmSelector = new ComboBox<String>();
    endAm_PmSelector.getItems().addAll("AM", "PM");
    endAm_PmSelector.setValue(startAm_PmSelector.getItems().get(0));
    
    
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
        stage.close();
        stage.hide();
      }
    });
    
    
    
    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().addAll(createButton, cancelButton);
    
    int row = 0;
    
    GridPane layout = new GridPane();
    layout.setPadding(new Insets(20));
    layout.setVgap(5);
    layout.setHgap(10);
    layout.addRow(row++, new Label("Title"), eventTitleTextField);
    layout.addRow(row++, new Label("Date"), datePicker);
    layout.addRow(row++, new Label("Description"), eventDescriptionTextArea);
    layout.addRow(row++, new Label("Start Time"), startTimeSelectionLayout);
    layout.addRow(row++, new Label("End Time"), endTimeSelectionLayout);
    layout.add(buttonsBox, 0, row++, 3, 1);
    
    
    
    // create and show screen
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.setTitle("New Event");
    stage.show();
  }
  
}
