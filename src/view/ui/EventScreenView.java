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

  private TextField eventTitleTextField;
  private TextArea eventDescriptionTextArea;
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

  private Button okayButton;
  private Button cancelButton;
  private Button deleteButton;

  private EventBean event;
  private boolean isNewEvent;
  private boolean isUsersEvent;


  public EventScreenView(EventBean event, UserBean user) {
    stage = new Stage();

    // if the event passed in is null, this is a new event
    this.isNewEvent = (event == null);
    this.isUsersEvent = (!isNewEvent && event.getOwner().getUsername()
        .equals(user.getUsername()));
    this.event = event;

    eventTitleTextField = new TextField();
    eventTitleTextField.setOnKeyReleased(new EventHandler<Event>() {
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
    startAm_PmSelector.getItems().addAll("AM", "PM");
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
    endAm_PmSelector.getItems().addAll("AM", "PM");
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

    eventDescriptionTextArea = new TextArea();

    // create action buttons
    okayButton = new Button("Okay");
    okayButton.setDisable(true);
    okayButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // TODO attempt validation
      }
    });

    cancelButton = new Button("Cancel");
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        listener.hide();
      }
    });

    deleteButton = new Button("Delete");
    deleteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // TODO attempt to delete the event
      }
    });

    // create layout to hold buttons
    HBox buttonsBox = new HBox(10);
    buttonsBox.getChildren().add(okayButton);

    if (isNewEvent || isUsersEvent) {
      buttonsBox.getChildren().add(cancelButton);
    }

    if (isUsersEvent) {
      buttonsBox.getChildren().add(deleteButton);
    }

    int row = 0;

    // add the elements to a layout
    GridPane eventFieldsLayout = new GridPane();
    eventFieldsLayout.setVgap(5);
    eventFieldsLayout.setHgap(10);
    eventFieldsLayout.addRow(row++, new Label("Title*"), eventTitleTextField);
    eventFieldsLayout.addRow(row++, new Label("Date*"), dateControlsLayout);
    eventFieldsLayout.addRow(row++, new Label("Start Time*"));
    eventFieldsLayout.addRow(row++, new Label("End Time*"));
    eventFieldsLayout.add(timeSelectorPane, 1, row - 2, 1, 2);
    eventFieldsLayout.addRow(row++, new Label("Description"), eventDescriptionTextArea);

    VBox layout = new VBox(20);
    layout.setPadding(new Insets(20));
    layout.getChildren().addAll(eventFieldsLayout, buttonsBox);
    buttonsBox.setAlignment(Pos.CENTER);

    if (!isNewEvent) {
      populateEventScreenValues();
    }

    // if it is not your event, make all fields uneditable
    if (!isUsersEvent) {
      okayButton.setDisable(false);
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
    eventTitleTextField.setDisable(true);
    datePicker.setDisable(true);
    startHourSelector.setDisable(true);
    startMinuteSelector.setDisable(true);
    startAm_PmSelector.setDisable(true);
    endHourSelector.setDisable(true);
    endMinuteSelector.setDisable(true);
    endAm_PmSelector.setDisable(true);
    allDayCheckBox.setDisable(true);
    eventDescriptionTextArea.setDisable(true);
  }


  /**
   * Populate the values in the dialog with the values of the event.
   */
  private void populateEventScreenValues() {
    LocalDateTime startDateTime = event.getStartDateTime();
    LocalDateTime endDateTime = event.getEndDateTime();
    
    eventTitleTextField.setText(event.getName());
    eventDescriptionTextArea.setText(event.getDescription());
    
    datePicker.setValue(event.getDate());

    if (startDateTime != null) {
      if (startDateTime.getHour() < 11) {
        startHourSelector.setValue(event.getStartDateTime().getHour());
        startAm_PmSelector.setValue("AM");
      }
      else {
        startHourSelector.setValue(event.getStartDateTime().getHour() - 12);
        startAm_PmSelector.setValue("PM");
      }
      startMinuteSelector.setValue(event.getStartDateTime().getMinute());      
    }

    if (endDateTime != null) {
      if (endDateTime.getHour() < 11) {
        endHourSelector.setValue(event.getEndDateTime().getHour());
        endAm_PmSelector.setValue("AM");
      }
      else {
        endHourSelector.setValue(event.getEndDateTime().getHour() - 12);
        endAm_PmSelector.setValue("PM");
      }
      endMinuteSelector.setValue(event.getEndDateTime().getMinute());
    }

    if (event.getAllDayIndicator() != null) {
      allDayCheckBox.setSelected(event.getAllDayIndicator());
    }
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

    boolean isStartTime = (startHourSelector.getValue() != null)
        && (startMinuteSelector.getValue() != null);
    boolean isEndTime = (endHourSelector.getValue() != null)
        && (endMinuteSelector.getValue() != null);
    boolean isTime = (allDayChecked || (isStartTime && isEndTime));

    boolean isCreateButtonEnabled = isTitle && isDate && isTime;
    okayButton.setDisable(!isCreateButtonEnabled);
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
