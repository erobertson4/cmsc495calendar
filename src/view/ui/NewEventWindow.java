package view.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
  
  private TextField eventTitleTextField;
  private DatePicker datePicker;
  
  private CheckBox allDayCheckBox;
  private ComboBox<Integer> startHourSelector;
  private ComboBox<Integer> startMinuteSelector;
  private HBox startTimeSelectionLayout;
  
  private ComboBox<Integer> endHourSelector;
  private ComboBox<Integer> endMinuteSelector;
  private HBox endTimeSelectionLayout;
  
  private Button createButton;
  
  public NewEventWindow() {
    stage = new Stage();
    
    
    eventTitleTextField = new TextField();
    eventTitleTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setControls();
      }
    });
    
    datePicker = new DatePicker();
    // FIXME This action listener does not recognize typing until focus has shifted.
    datePicker.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });
    
    allDayCheckBox = new CheckBox("All Day Event");
    allDayCheckBox.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setControls();
      }
    });
    
    HBox dateControlsLayout = new HBox(20);
    dateControlsLayout.getChildren().addAll(datePicker);
    
    startHourSelector = new ComboBox<Integer>();
    for (int hour = 1; hour <= 12; hour++) {
      startHourSelector.getItems().add(hour);
    }
    startHourSelector.setVisibleRowCount(startHourSelector.getItems().size());
    startHourSelector.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });
    
    // TODO make the minutes 2 digits
    startMinuteSelector = new ComboBox<Integer>();
    for (int minute = 0; minute < 60; minute+=5) {
      startMinuteSelector.getItems().add(minute);
    }
    startMinuteSelector.setVisibleRowCount(startMinuteSelector.getItems().size());
    startMinuteSelector.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });
    
    ComboBox<String> startAm_PmSelector = new ComboBox<String>();
    startAm_PmSelector.getItems().addAll("AM", "PM");
    startAm_PmSelector.setValue(startAm_PmSelector.getItems().get(0));
    
    
    startTimeSelectionLayout = new HBox();
    startTimeSelectionLayout.getChildren().addAll(startHourSelector, startMinuteSelector, startAm_PmSelector, allDayCheckBox);
    
    endHourSelector = new ComboBox<Integer>();
    for (int hour = 1; hour <= 12; hour++) {
      endHourSelector.getItems().add(hour);
    }
    endHourSelector.setVisibleRowCount(endHourSelector.getItems().size());
    endHourSelector.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });

    // TODO make the minutes 2 digits
    endMinuteSelector = new ComboBox<Integer>();
    for (int minute = 0; minute < 60; minute+=5) {
      endMinuteSelector.getItems().add(minute);
    }
    endMinuteSelector.setVisibleRowCount(endMinuteSelector.getItems().size());
    endMinuteSelector.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });
    
    ComboBox<String> endAm_PmSelector = new ComboBox<String>();
    endAm_PmSelector.getItems().addAll("AM", "PM");
    endAm_PmSelector.setValue(startAm_PmSelector.getItems().get(0));
    
    endTimeSelectionLayout = new HBox();
    endTimeSelectionLayout.getChildren().addAll(endHourSelector, endMinuteSelector, endAm_PmSelector);
    
    GridPane timeSelectorPane = new GridPane();
    timeSelectorPane.setVgap(5);
    timeSelectorPane.setHgap(30);
    
    timeSelectorPane.addRow(0, startTimeSelectionLayout);
    timeSelectorPane.addRow(1, endTimeSelectionLayout);
    timeSelectorPane.add(allDayCheckBox, 1, 0, 1, 2);
    
    TextArea eventDescriptionTextArea = new TextArea();
    
    // create action buttons
    createButton = new Button("Create");
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
    layout.addRow(row++, new Label("Title*"), eventTitleTextField);
    layout.addRow(row++, new Label("Date*"), dateControlsLayout);
    layout.addRow(row++, new Label("Start Time*"));
    layout.addRow(row++, new Label("End Time*"));
    layout.add(timeSelectorPane, 1, row-2, 1, 2);
    layout.addRow(row++, new Label("Description"), eventDescriptionTextArea);
    layout.add(buttonsBox, 0, row++, 3, 1);
    buttonsBox.setAlignment(Pos.CENTER);
    
    
    // create and show screen
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.setTitle("New Event");
    stage.show();
  }
  
  
  /**
   * Determine if the time selectors and Create button should be enabled.
   */
  private void setControls() {
    boolean allDayChecked = allDayCheckBox.isSelected();
    
    startTimeSelectionLayout.setDisable(allDayChecked);
    endTimeSelectionLayout.setDisable(allDayChecked);
    
    boolean isTitle = (!eventTitleTextField.getText().isEmpty());
    boolean isDate = (datePicker.getValue() != null);
    
    boolean isStartTime = (startHourSelector.getValue() != null) && (startMinuteSelector.getValue() != null);
    boolean isEndTime = (endHourSelector.getValue() != null) && (endMinuteSelector.getValue() != null);
    boolean isTime = (allDayChecked || (isStartTime && isEndTime));
    
    boolean isCreateButtonEnabled = isTitle && isDate && isTime;
    createButton.setDisable(!isCreateButtonEnabled);
  }
  
}
