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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import java.awt.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ToDoLists.scrollWheelSetup(sc,accordion);
        System.out.println(accordion.translateYProperty().getValue());
    }

    @FXML
    private Accordion accordion;

    @FXML
    private ScrollBar sc;

    @FXML
    private TextField userInput;

    @FXML
    private Button addToListButton;

    @FXML
    private Button removeFromListButton;

    @FXML
    private MenuItem editItemTitleButton;

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
    private Button clearItemButton;


    /*@FXML
    void editListTitleClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
            //if doesn't exist prompt again
        //set class.title to user prompt at prompted index
    }*/
    @FXML
    void addToListClicked(MouseEvent actionEvent)
    {
        //prompt user for item name, date, and desc
        //increase size of index by one
        //accordion.getPanes().add()
        ToDoLists.refreshAccordion(accordion,1);
        ToDoLists.scrollWheelSetup(sc,accordion);
    }
    @FXML
    void removeFromListClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to remove"
        //remove index if valid
        System.out.println(userInput.getText() + " " + accordion.getPanes().size());
        accordion.getPanes().remove(Integer.parseInt(userInput.getText()) - 1);
        ToDoLists.refreshAccordion(accordion,-1);
    }
    @FXML
    void clearListClicked(MouseEvent actionEvent)
    {
        //remove all indexes
    }

    @FXML
    void editRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Enter input here.");
    }
    @FXML
    void editRegistryExited(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Press a button.");
    }

    @FXML
    void editNameOfItemClicked(ActionEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.desc to user prompt
        //System.out.println(userInput.getText() + " " + accordion.getPanes().size());
        String sub = userInput.getText();
        String post = userInput.getText();
        int i;
        i = userInput.getText().indexOf('/') + 1;
        sub = sub.substring(0,i-1);
        post = post.substring(i);
        int index = Integer.parseInt(sub);
        accordion.getPanes().get(index-1).setText(post);
        userInput.promptTextProperty().set("Press a button.");
    }

    @FXML
    void editDescOfItemClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.desc to user prompt
    }
    @FXML
    void editDateOfItemClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new date
        //if date is valid gregorian date then continue
        //if isn't valid prompt again
        //set class.date to user prompt at prompted index
    }
    @FXML
    void markCompleteClicked(MouseEvent actionEvent)
    {
        //when checkbox is clicked
        //flip the status of the complete boolean so as to represent its status
    }
    @FXML
    void allItemsClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //enable it
        //if enabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
    }

    @FXML
    void completeItemsClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry class.complete = false then disable it
        //if disabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
    }
    @FXML
    void incompleteItemsClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry class.complete = true then disable it
        //if disabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
    }

    @FXML
    void importListClicked(MouseEvent actionEvent)
    {
        //get to path through file explorer (or if have to; ask for path)
        //load file as File and send to parse
    }
    @FXML
    void exportListClicked(MouseEvent actionEvent)
    {
        //get to save path through file explorer (or if have to; ask for path)
        //save as txt or json
    }
}