/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Troy Baggs
 */

package ucf.assignments;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        sc.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
            {
                accordion.setTranslateY(accordion.getHeight() * (.39f - (sc.valueProperty().doubleValue()/255)));
            }
        });
    }

    @FXML
    private Accordion accordion;

    @FXML
    private ScrollBar sc;

    @FXML
    private TextField userInput;

    @FXML
    private Button addListButton;

    @FXML
    private Button removeListButton;

    @FXML
    private Button editListTitleButton;

    @FXML
    private Button importButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button showAllButton;

    @FXML
    private Button showCompleteButton;

    @FXML
    private Button showIncompleteButton;

    @FXML
    public void addListClicked(MouseEvent actionEvent)
    {
        //get user prompt for title
        //create new entry in arraylist and accordion
        //adding entry shouldn't effect accordion height unless scrollbar needs to be reassessed but it should be fine
    }
    @FXML
    void removeListClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of list to remove"
        //get user prompt for entry index to edit
        //if index exists, ask for confirmation
            //if doesn't exist prompt again or cancellation
        //if confirmed remove entry from arraylist and remove entry in accordion
        //if removing entry in accordion doesn't revaluate the height, create a new accordion with correlating height
    }
    @FXML
    void editListTitleClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
            //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }
    @FXML
    void addToListClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }
    @FXML
    void removeFromListClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }
    @FXML
    void editDescOfItemClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }
    @FXML
    void editDateOfItemClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }
    @FXML
    void markCompleteClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }
    @FXML
    void importListClicked(MouseEvent actionEvent)
    {
        //get user prompt in new window if they want to import one or several
        //get to path through file explorer (or if have to; ask for path)
        //load file as File and send to parse
    }
    @FXML
    void exportListClicked(MouseEvent actionEvent)
    {
        //get user prompt in new window if they want to export one or several
        //get to save path through file explorer (or if have to; ask for path)
        //save as txt or json
    }
    @FXML
    void allListsClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry is disabled, re-enable it
    }
    @FXML
    void completeListClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry class.complete = false then disable it
        //if disabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
    }
    @FXML
    void incompleteListClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry class.complete = true then disable it
        //if disabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
    }
}