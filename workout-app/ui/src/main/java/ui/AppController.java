package ui;

import core.Workout;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This abstract class is implemented in both the local and the remote controller.
 * It contains all the fxml imports and parts of the methods that are to be used in 
 * both controllers to avoid duplicating code.
 * The implementations remote and local app controllers mostly only have code related to 
 * saving the workoutlog to appropriate places.
 */

abstract class AppController {

  @FXML
  protected TextArea inputWorkout;

  @FXML
  protected DatePicker inputDate;

  @FXML
  protected TableView<Workout> workoutsList;

  @FXML
  protected TableColumn<Workout, String> workoutsColumn;

  @FXML
  protected TableColumn<Workout, String> dateColumn;

  @FXML
  protected Label errorLabel;

  @FXML
  protected Button deleteButton;

  @FXML
  protected Button editButton;
 
  /**
   * initialize: initializes all fields that needs to be set to certain values.
   * before the application is to be used
   */
  @FXML
  public void initialize() {
    inputDate.setEditable(false); // Makes the user unable to write in the date picker field
      
    //Sets up the TableColumn to display the input property
    workoutsColumn.setCellValueFactory(new PropertyValueFactory<>("workoutInput"));
    workoutsColumn.setOnEditStart(e -> { //on doubleclick the edit function will run
      handleEdit(e.getRowValue()); });
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        
    workoutsList.setEditable(true);
    workoutsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    deleteButton.setDisable(true);
    editButton.setDisable(true);

    //Listener to check if user selected row
    workoutsList.getSelectionModel().selectedItemProperty().addListener((obs, 
        oldSelection, newSelection) -> {
      //If no row is selected, disable the buttons
      deleteButton.setDisable(newSelection == null);
      editButton.setDisable(newSelection == null);
    });
  }

  /**
   * handleRegister: running when user have written in the description area and clicked 
   * on register. 
   * If the user has not chosen a date the date will be sat to today. If the 
   * user has chosen a future date/invalid date, there will be an error displayed.
   *
   * @return Workout (inheritance function is implemented to use this workout to add to workoutlog)
   */
  @FXML
  public Workout handleRegister() {
    String session = inputWorkout.getText();
    LocalDate date = inputDate.getValue();

    errorLabel.setText("");

    if (!session.isEmpty()) {
      if (date == null) {
        date = LocalDate.now();
      } else if (date.isAfter(LocalDate.now())) {
        errorLabel.setText("Date can not be in the future");
        return null;
      }
      return new Workout(session, date); 
    } 
    return null;
  }

  /**
   * handleEdit: fired when user is doubleclicking on an element in the workoutList
   * the element is removed from the list and the date and input is added to the input field.
   * If there is written something in the field this need to be added first
   *
   * @param w workout to edit
   * @return boolean to represent weather this method is editing or not
   * 
   */
  public boolean handleEdit(Workout w) { 
    if (inputWorkout.getText().equals("")) {
      inputWorkout.setText(w.getWorkoutInput()); 
      inputDate.setValue(w.getDate());
      return true;
    }
    return false;
  }

  /**
   * Method fired on the edit button.
   * This method calls the handle edit after finding the active workout
   */
  public void handleEditButton() {
    Workout selectedWorkout = workoutsList.getSelectionModel().getSelectedItem();
    handleEdit(selectedWorkout);
  }

  /**
   * Method fired on the delete button after activating an element.
   *
   * @return a list that the implemented class can use to find the element to remove
   */
  public ObservableList<Workout> handleDelete() {
    ObservableList<Workout> selectedRows = workoutsList.getSelectionModel().getSelectedItems();
    return selectedRows;
  }

  /**
   * Abstract method to implement, fired on clear button.
   */
  @FXML
  public abstract void handleClear();

  /**
   * updateTableView: abstract method to implement, updates the table according to the workoutLog.
   * Also need to sort all the workouts in order of date.
   */
  public abstract void updateTableView();
}

