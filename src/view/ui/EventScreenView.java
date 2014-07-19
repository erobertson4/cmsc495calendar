/**
 * 
 */
package view.ui;

import java.time.LocalDateTime;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bean.EventBean;
import model.bean.UserBean;
import view.type.EventScreen;

/**
 * GUI class for the EventScreen.
 * 
 * TODO still working on this class, so more comments will be added later.
 * 
 * @author elijahr
 *
 */
public class EventScreenView implements EventScreen {

  private EventListener listener;

  private Stage stage;

  private TextField titleTextField;
  private TextArea descriptionTextArea;
  private DatePicker datePicker;

  private CheckBox allDayCheckBox;
  private ComboBox<Integer> startHourSelector;
  private ComboBox<Integer> startMinuteSelector;
  private ComboBox<String> startAm_PmSelector;
  private HBox startTimeSelectionLayout;

  private ComboBox<Integer> endHourSelector;
  private ComboBox<Integer> endMinuteSelector;
  private ComboBox<String> endAm_PmSelector;
  private HBox endTimeSelectionLayout;

  private Button closeButton;
  private Button saveButton;
  private Button cancelButton;
  private Button deleteButton;

  private final EventBean event;
  private boolean isNewEvent;
  private boolean isUsersEvent;

  private final String AM = "AM";
  private final String PM = "PM";

  public EventScreenView(final EventBean event, UserBean user) {
    stage = new Stage();

    // if the event passed in is null, this is a new event
    this.isNewEvent = (event == null);
    this.isUsersEvent = (!isNewEvent && event.getOwner().getUsername()
        .equals(user.getUsername()));
    this.event = event;

    titleTextField = new TextField();
    titleTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setControls();
      }
    });

    datePicker = new DatePicker();
    datePicker.setEditable(false);
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
    for (int minute = 0; minute < 60; minute += 5) {
      startMinuteSelector.getItems().add(minute);
    }
    startMinuteSelector.setVisibleRowCount(startMinuteSelector.getItems()
        .size());
    startMinuteSelector.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });

    startAm_PmSelector = new ComboBox<String>();
    startAm_PmSelector.getItems().addAll(AM, PM);
    startAm_PmSelector.setValue(startAm_PmSelector.getItems().get(0));

    startTimeSelectionLayout = new HBox();
    startTimeSelectionLayout.getChildren().addAll(startHourSelector,
        startMinuteSelector, startAm_PmSelector, allDayCheckBox);

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
    for (int minute = 0; minute < 60; minute += 5) {
      endMinuteSelector.getItems().add(minute);
    }
    endMinuteSelector.setVisibleRowCount(endMinuteSelector.getItems().size());
    endMinuteSelector.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });

    endAm_PmSelector = new ComboBox<String>();
    endAm_PmSelector.getItems().addAll(AM, PM);
    endAm_PmSelector.setValue(startAm_PmSelector.getItems().get(0));

    endTimeSelectionLayout = new HBox();
    endTimeSelectionLayout.getChildren().addAll(endHourSelector,
        endMinuteSelector, endAm_PmSelector);

    GridPane timeSelectorPane = new GridPane();
    timeSelectorPane.setVgap(5);
    timeSelectorPane.setHgap(30);

    timeSelectorPane.addRow(0, startTimeSelectionLayout);
    timeSelectorPane.addRow(1, endTimeSelectionLayout);
    timeSelectorPane.add(allDayCheckBox, 1, 0, 1, 2);

    descriptionTextArea = new TextArea();

   /*
    * +========================================================+
    * |        Possible Button Arrangements / Scenarios        |
    * |--------------------------------------------------------|
    * |        Close              Viewing Someone Else's Event |
    * |--------------------------------------------------------|
    * |     Save   Cancel         Creating a New Event         |
    * |--------------------------------------------------------|
    * | Save   Cancel   Delete    Viewing Your Event           |
    * +========================================================+
    */
    
    // create action buttons
    closeButton = new Button("Close");
    closeButton.setDisable(true);
    closeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        listener.hide();
      }
    });
    
    saveButton = new Button("Save");
    saveButton.setDisable(true);
    saveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        // TODO Still working on this logic, but wanted to get the other code checked
        // in for you guys.
//        event.setName(titleTextField.getText());
//        event.setDate(datePicker.getValue());
//        event.setAllDayIndicator(allDayCheckBox.isSelected());
//        event.setDescription(descriptionTextArea.getText());
//        
//        if (!event.getAllDayIndicator()) {
//          
//          LocalDateTime endDateTime = LocalDateTime.now();
//          endDateTime.
//          
//          if (endAm_PmSelector.getValue().equals(PM))
//          
//          event.setEndDateTime(LocalDateTime.of(year, month, dayOfMonth, end, endMinuteSelector.getValue()));
//        }
//        
//        System.out.println(event.getDate());
//        System.out.println(event.getAllDayIndicator());
//        
//        listener.save(event);
      }
    });

    cancelButton = new Button("Cancel");
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        listener.hide();
      }
    });

    deleteButton = new Button("Delete");
    deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        listener.delete(event);
      }
    });

    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    
    // someone else's event
    if (!isNewEvent && !isUsersEvent) {
      buttonsBox.getChildren().add(closeButton);
    }
    // new event
    else if (isNewEvent) {
      buttonsBox.getChildren().addAll(saveButton, cancelButton);
    }
    // your event
    else if (isUsersEvent) {
      buttonsBox.getChildren().addAll(saveButton, cancelButton, deleteButton);
    }

    int row = 0;

    // add the elements to a layout
    GridPane eventFieldsLayout = new GridPane();
    eventFieldsLayout.setVgap(5);
    eventFieldsLayout.setHgap(10);
    eventFieldsLayout.addRow(row++, new Label("Title*"), titleTextField);
    eventFieldsLayout.addRow(row++, new Label("Date*"), dateControlsLayout);
    eventFieldsLayout.addRow(row++, new Label("Start Time*"));
    eventFieldsLayout.addRow(row++, new Label("End Time*"));
    eventFieldsLayout.add(timeSelectorPane, 1, row - 2, 1, 2);
    eventFieldsLayout.addRow(row++, new Label("Description"), descriptionTextArea);

    VBox layout = new VBox(20);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(eventFieldsLayout, buttonsBox);
    buttonsBox.setAlignment(Pos.CENTER);

    if (!isNewEvent) {
      populateEventScreenValues();
    }

    // if it is not your event, make all fields uneditable
    if (!isNewEvent && !isUsersEvent) {
      setEventFieldsUneditable();
    }
    
    setControls();

    // create and show screen
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    
    if (isNewEvent) {
      stage.setTitle("New Event");
    }
    else {
      stage.setTitle(event.getName());
    }
  }


  /**
   * Prevent any changes from being made to the event. Used if the event was
   * created by someone else.
   */
  private void setEventFieldsUneditable() {
    titleTextField.setDisable(true);
    datePicker.setDisable(true);
    startHourSelector.setDisable(true);
    startMinuteSelector.setDisable(true);
    startAm_PmSelector.setDisable(true);
    endHourSelector.setDisable(true);
    endMinuteSelector.setDisable(true);
    endAm_PmSelector.setDisable(true);
    allDayCheckBox.setDisable(true);
    descriptionTextArea.setDisable(true);
  }


  /**
   * Populate the values in the dialog with the values of the event.
   */
  private void populateEventScreenValues() {
    LocalDateTime startDateTime = event.getStartDateTime();
    LocalDateTime endDateTime = event.getEndDateTime();
    
    titleTextField.setText(event.getName());
    descriptionTextArea.setText(event.getDescription());
    
    datePicker.setValue(event.getDate());

    if (startDateTime != null) {
      if (startDateTime.getHour() < 11) {
        startHourSelector.setValue(event.getStartDateTime().getHour());
        startAm_PmSelector.setValue(AM);
      }
      else {
        startHourSelector.setValue(event.getStartDateTime().getHour() - 12);
        startAm_PmSelector.setValue(PM);
      }
      startMinuteSelector.setValue(event.getStartDateTime().getMinute());      
    }

    if (endDateTime != null) {
      if (endDateTime.getHour() < 11) {
        endHourSelector.setValue(event.getEndDateTime().getHour());
        endAm_PmSelector.setValue(AM);
      }
      else {
        endHourSelector.setValue(event.getEndDateTime().getHour() - 12);
        endAm_PmSelector.setValue(PM);
      }
      endMinuteSelector.setValue(event.getEndDateTime().getMinute());
    }

    if (event.getAllDayIndicator() != null) {
      allDayCheckBox.setSelected(event.getAllDayIndicator());
    }
  }


  /**
   * Determine if the time selectors and Save button should be enabled.
   */
  private void setControls() {
    boolean allDayChecked = allDayCheckBox.isSelected();

    startTimeSelectionLayout.setDisable(allDayChecked);
    endTimeSelectionLayout.setDisable(allDayChecked);

    boolean isTitle = (!titleTextField.getText().isEmpty());
    boolean isDate = (datePicker.getValue() != null);

    boolean isStartTime = (startHourSelector.getValue() != null)
        && (startMinuteSelector.getValue() != null);
    boolean isEndTime = (endHourSelector.getValue() != null)
        && (endMinuteSelector.getValue() != null);
    boolean isTime = (allDayChecked || (isStartTime && isEndTime));

    boolean isCreateButtonEnabled = isTitle && isDate && isTime;
    saveButton.setDisable(!isCreateButtonEnabled);
  }


  @Override
  public void setEventListener(EventListener listener) {
    this.listener = listener;
  }


  @Override
  public void showEventScreen() {
    stage.show();
  }


  @Override
  public void hideEventScreen() {
    stage.hide();
  }

}
