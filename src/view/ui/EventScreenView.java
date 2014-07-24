/**
 * 
 */
package view.ui;

import java.time.LocalDate;

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
 * GUI class for the EventScreen. Has fields for the event's title, description,
 * date, time, and whether or not it is an all day event. If the event coming in
 * is null, it means this is to create a new event. If the user opening the
 * event created the event, the user will be able to delete the event. If the
 * user did not create the event, the user will only be able to view the event.
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
  private ComboBox<String> startMinuteSelector;
  private ComboBox<String> startAm_PmSelector;
  private HBox startTimeSelectionLayout;

  private ComboBox<Integer> endHourSelector;
  private ComboBox<String> endMinuteSelector;
  private ComboBox<String> endAm_PmSelector;
  private HBox endTimeSelectionLayout;

  private Button closeButton;
  private Button saveButton;
  private Button cancelButton;
  private Button deleteButton;

  private EventBean newEvent;
  private boolean isNewEvent;
  private boolean isUsersEvent;

  private final String AM = "AM";
  private final String PM = "PM";


  public EventScreenView(final EventBean event, final UserBean user) {
    stage = new Stage();

    // if the event passed in is null, this is a new event.
    this.isNewEvent = (event == null);
    this.isUsersEvent = (!isNewEvent && event.getCreatorId() == user.getUserID());
    this.newEvent = event;

    // TextField for the event's title.
    titleTextField = new TextField();
    titleTextField.setOnKeyReleased(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setControls();
      }
    });

    // DatePicker for the event's date.
    datePicker = new DatePicker();
    datePicker.setEditable(false);
    datePicker.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        setControls();
      }
    });

    // CheckBox to determine if this is an all day event.
    allDayCheckBox = new CheckBox("All Day Event");
    allDayCheckBox.setOnMouseClicked(new EventHandler<Event>() {
      @Override
      public void handle(Event event) {
        setControls();
      }
    });

    // series of ComboBoxes for the starting time of the event.
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

    startMinuteSelector = new ComboBox<String>();
    for (int minute = 0; minute < 60; minute += 5) {
      startMinuteSelector.getItems().add(String.format("%02d", minute));
    }
    startMinuteSelector.setVisibleRowCount(startMinuteSelector.getItems().size());
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
    startTimeSelectionLayout.getChildren().addAll(startHourSelector, startMinuteSelector,
        startAm_PmSelector, allDayCheckBox);

    // series of ComboBoxes for the ending time of the event.
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

    endMinuteSelector = new ComboBox<String>();
    for (int minute = 0; minute < 60; minute += 5) {
      endMinuteSelector.getItems().add(String.format("%02d", minute));
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
    endTimeSelectionLayout.getChildren().addAll(endHourSelector, endMinuteSelector,
        endAm_PmSelector);

    // GridPane to organize all of the time-related elements
    GridPane timeSelectorPane = new GridPane();
    timeSelectorPane.setVgap(5);
    timeSelectorPane.setHgap(30);

    timeSelectorPane.addRow(0, startTimeSelectionLayout);
    timeSelectorPane.addRow(1, endTimeSelectionLayout);
    timeSelectorPane.add(allDayCheckBox, 1, 0, 1, 2);

    // TextArea to hold the description of the event
    descriptionTextArea = new TextArea();

    /*
     * +========================================================+ 
     * |        Possible Button Arrangements / Scenarios        |
     * |--------------------------------------------------------|
     * |       Close               Viewing Someone Else's Event |
     * |--------------------------------------------------------|
     * |    Save Cancel            Creating a New Event         |
     * |--------------------------------------------------------|
     * | Save Cancel Delete        Viewing Your Event           |
     * +========================================================+
     */

    // create action buttons
    closeButton = new Button("Close");
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
        // save the values in the form into an event
        LocalDate eventDate = datePicker.getValue();

        if (isNewEvent) {
          newEvent = new EventBean();
        }
        newEvent.setCreatorId(user.getUserID());
        newEvent.setTitle(titleTextField.getText());
        newEvent.setDate(eventDate);
        newEvent.setAllDayIndicator(allDayCheckBox.isSelected());
        newEvent.setDescription(descriptionTextArea.getText());

        // insert empty string value if allDay selected
        // reason: prohibits nullPointerException when no values are entered
        // as a result of allDay selection
        if (allDayCheckBox.isSelected()) {
        	newEvent.setStartHour(0);
            newEvent.setStartMinute(0);
            newEvent.setStartAMIndicator(false);
            newEvent.setEndHour(0);
            newEvent.setEndMinute(0);
            newEvent.setEndAMIndicator(false);
        } else {
        	newEvent.setStartHour(startHourSelector.getValue());
        	newEvent.setStartMinute(Integer.valueOf(startMinuteSelector.getValue()));
        	newEvent.setStartAMIndicator(startAm_PmSelector.getValue().equals(AM));
        	newEvent.setEndHour(endHourSelector.getValue());
        	newEvent.setEndMinute(Integer.valueOf(endMinuteSelector.getValue()));
        	newEvent.setEndAMIndicator(endAm_PmSelector.getValue().equals(AM));
        }

        listener.save(newEvent);
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

    // create layout to hold buttons and determine which buttons should be added
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

    // add the GUI elements to a layout
    GridPane eventFieldsLayout = new GridPane();
    eventFieldsLayout.setVgap(5);
    eventFieldsLayout.setHgap(10);
    eventFieldsLayout.addRow(row++, new Label("Title*"), titleTextField);
    eventFieldsLayout.addRow(row++, new Label("Date*"), datePicker);
    eventFieldsLayout.addRow(row++, new Label("Start Time*"));
    eventFieldsLayout.addRow(row++, new Label("End Time*"));
    eventFieldsLayout.add(timeSelectorPane, 1, row - 2, 1, 2);
    eventFieldsLayout.addRow(row++, new Label("Description"), descriptionTextArea);

    VBox layout = new VBox(20);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(eventFieldsLayout, buttonsBox);
    buttonsBox.setAlignment(Pos.CENTER);

    if (!isNewEvent) {
      addEventScreenValues();
      stage.setTitle(event.getTitle());
    }
    else {
      stage.setTitle("New Event");
    }

    // if it is not your event, make all fields uneditable--otherwise, determine
    // which fields should be editable
    if (!isNewEvent && !isUsersEvent) {
      setEventFieldsUneditable();
    }
    else {
      setControls();
    }

    // create and show screen
    Scene scene = new Scene(layout);
    stage.setScene(scene);
  }


  /**
   * Prevent any changes from being made to the event. Used if the event was
   * created by someone else. Set the TextField and TextArea so that you can't
   * tab to reach them. It also makes the Close button the only thing
   * highlighted.
   */
  private void setEventFieldsUneditable() {
    titleTextField.setEditable(false);
    titleTextField.setFocusTraversable(false);
    datePicker.setDisable(true);
    startHourSelector.setDisable(true);
    startMinuteSelector.setDisable(true);
    startAm_PmSelector.setDisable(true);
    endHourSelector.setDisable(true);
    endMinuteSelector.setDisable(true);
    endAm_PmSelector.setDisable(true);
    allDayCheckBox.setDisable(true);
    descriptionTextArea.setEditable(false);
    descriptionTextArea.setFocusTraversable(false);
  }


  /**
   * Add the values from the event in the dialog. Requires extracting the hour
   * and minute from the start and end times so they can be displayed in the
   * ComboBoxes. Also requires converting the time from a 0-based 24 hours to a
   * 1-based 12 hours.
   */
  private void addEventScreenValues() {
    titleTextField.setText(newEvent.getTitle());
    descriptionTextArea.setText(newEvent.getDescription());

    datePicker.setValue(newEvent.getDate());

    // set the all day indicator or time fields
    if (newEvent.getAllDayIndicator()) {
      allDayCheckBox.setSelected(true);
    }
    else {
      startHourSelector.setValue(newEvent.getStartHour());
      startMinuteSelector.setValue(String.format("%02d", newEvent.getStartMinute()));
      startAm_PmSelector.setValue((newEvent.getStartAMIndicator() ? AM : PM));
      
      endHourSelector.setValue(newEvent.getEndHour());
      endMinuteSelector.setValue(String.format("%02d", newEvent.getEndMinute()));
      endAm_PmSelector.setValue((newEvent.getEndAMIndicator() ? AM : PM));
    }
    
  }


  /**
   * Determine if the time selectors and Save button should be enabled.
   */
  private void setControls() {
    // if allDayCheckBox is selected, make the time ComboBoxes disabled
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

    // save button is enabled if all required fields are filled out
    boolean isSaveButtonEnabled = isTitle && isDate && isTime;
    saveButton.setDisable(!isSaveButtonEnabled);
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
