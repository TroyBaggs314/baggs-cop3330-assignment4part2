/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Troy Baggs
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import java.awt.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ToDoLists.scrollWheelSetup(sc,accordion);
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
        //create default index title pane
        //increase size of index by one
        javafx.scene.control.CheckBox cb = new javafx.scene.control.CheckBox("");
        AnchorPane ap = new AnchorPane();
        ap.getChildren().add(cb);
        cb.setLayoutX(14);
        cb.setLayoutY(6);
        TextField tf = new TextField();
        ap.getChildren().add(tf);
        tf.promptTextProperty().set("YYYY/MM/DD");
        tf.setLayoutX(45);
        tf.setOnKeyPressed(this::editDateOfItemClicked);
        tf.setId("TF" + accordion.getPanes().size());
        TitledPane pane = new TitledPane();
        pane.setText("New Entry");
        pane.setContent(ap);
        accordion.getPanes().add(pane);
        ToDoLists.refreshAccordion(accordion,1);
        ToDoLists.scrollWheelSetup(sc,accordion);
    }
    @FXML
    void removeFromListClicked(MouseEvent actionEvent)
    {
        //change userInput prompt description to "index of entry to remove"
        //remove index if valid
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
    void editDateOfItemClicked(KeyEvent actionEvent)
    {
        //check if date is valid gregorian date then continue
        //if isn't valid prompt again
        //set class.date to user prompt at prompted index
        //System.out.println(actionEvent.getCode());
        final Node source = (Node)actionEvent.getSource();
        String id = source.getId();
        Scene scene = source.getScene();
        TextField tf = (TextField) scene.lookup("#" + id);
        //System.out.println(tf.getId() + " " + tf.getText() + "\t" + replaceHolder);
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                if(newValue.length() > 0)
                {
                    if (!Character.isDigit(newValue.charAt(newValue.length() - 1)) && newValue.charAt(newValue.length() - 1) != '/' && newValue.length() <=10) {
                        Platform.runLater(() ->
                        {
                            tf.clear();
                        });
                    }
                    else if(newValue.length() > 10)
                    {
                        tf.deletePreviousChar();
                    }
                    for(int i = 0; i < newValue.length(); i++)
                    {
                        if(i <= 3 && !Character.isDigit(newValue.charAt(i)))
                        {
                            Platform.runLater(() ->
                            {
                                tf.clear();
                            });
                        }
                        else if((i == 4 || i == 7) && newValue.charAt(i) != '/')
                        {
                            Platform.runLater(() ->
                            {
                                tf.clear();
                            });
                        }
                        else if((i > 4 && i !=7) && !Character.isDigit(newValue.charAt(i)))
                        {
                            Platform.runLater(() ->
                            {
                                tf.clear();
                            });                        }
                        else if(i == 5 && Integer.parseInt(String.valueOf(newValue.charAt(5))) > 1)
                        {
                            Platform.runLater(() ->
                            {
                                tf.clear();
                            });
                        }
                        else if(i == 6 && Integer.parseInt(String.valueOf(newValue.charAt(5))) == 1  && Integer.parseInt(String.valueOf(newValue.charAt(6))) > 2)
                        {
                            Platform.runLater(() ->
                            {
                                tf.clear();
                            });
                        }
                        else if(i == 9 && Character.isDefined(newValue.charAt(9)))
                        {
                            int day = Integer.parseInt(String.valueOf(newValue.charAt(8))) * 10 + Integer.parseInt(String.valueOf(newValue.charAt(9)));
                            if(day > 31)
                            {
                                Platform.runLater(() ->
                                {
                                    tf.clear();
                                });

                            }
                        }
                    }
                }
            }
        });


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