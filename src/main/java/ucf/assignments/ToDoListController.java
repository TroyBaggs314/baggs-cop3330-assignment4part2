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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TitledPaneSkin;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import java.awt.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ArrayList;
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
    private Button editItemTitleButton;

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

    public int maxAccCount = 0;


    @FXML
    Pair<TextField,TextArea> addToListClicked()
    {
        //create default index title pane
        //increase size of index by one
        javafx.scene.control.CheckBox cb = new javafx.scene.control.CheckBox("");
        cb.setId("CB" + maxAccCount);
        //System.out.println("Created #CB" + maxAccCount);
        cb.setLayoutX(14);
        cb.setLayoutY(25);
        TextField tf = new TextField();
        tf.setId("TF" + maxAccCount);
        AnchorPane ap = new AnchorPane();
        ap.getChildren().add(cb);
        ap.getChildren().add(tf);
        tf.promptTextProperty().set("YYYY/MM/DD");
        tf.setLayoutX(45);
        tf.setLayoutY(19);
        tf.setPrefHeight(32);
        tf.setPrefWidth(149);
        tf.setOnKeyPressed(this::editDateOfItemClicked);
        //System.out.println("Created #TF" + maxAccCount);
        TitledPane pane = new TitledPane();
        javafx.scene.control.TextArea ta = new TextArea();
        ap.getChildren().add(ta);
        ta.promptTextProperty().set("Enter a description here.");
        ta.setLayoutX(45);
        ta.setLayoutY(51);
        ta.setPrefHeight(134);
        ta.setPrefWidth(627);
        pane.setText("New Entry");
        pane.setContent(ap);
        maxAccCount++;
        accordion.getPanes().add(pane);
        ToDoLists.refreshAccordion(accordion,1);
        ToDoLists.scrollWheelSetup(sc,accordion);
        return new Pair<>(tf,ta);
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
        accordion.getPanes().clear();
        ToDoLists.refreshAccordion(accordion,0);
        maxAccCount = 0;
    }
    void clearListClicked()
    {
        //remove all indexes
        accordion.getPanes().clear();
        ToDoLists.refreshAccordion(accordion,0);
        maxAccCount = 0;
    }
    @FXML
    void addRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Adds a new entry.");
    }
    @FXML
    void removeRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Enter index to remove.");
    }
    @FXML
    void editRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Enter format: Index/Name");
    }
    @FXML
    void clearRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Removes every entry.");
    }
    @FXML
    void importRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Imports a saved list.");
    }
    @FXML
    void exportRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Exports a list for later use.");
    }
    @FXML
    void allRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Displays all lists");
    }
    @FXML
    void completeRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Displays only completed list");
    }
    @FXML
    void incompleteRegistryEntered(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Displays only incomplete list");    }
    @FXML
    void editRegistryExited(MouseEvent mouseEvent)
    {
        userInput.promptTextProperty().set("Press a button.");
    }

    @FXML
    void editNameOfItemClicked()
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.desc to user prompt
        //System.out.println(userInput.getText() + " " + accordion.getPanes().size());
        String sub = userInput.getText();
        String post = userInput.getText();
        int i = 0;
        i = userInput.getText().indexOf('/') + 1;
        int index = -1;
        if(i > 1)
        {
            sub = sub.substring(0, i - 1);
            post = post.substring(i);
            index = Integer.parseInt(sub);
        }
        if(index > 0 && index <= accordion.getPanes().size() && accordion.getPanes().size() > 0)
        {
            accordion.getPanes().get(index - 1).setText(post);
            userInput.promptTextProperty().set("Press a button.");
        }
        else
        {
            userInput.clear();
            userInput.promptTextProperty().set("Invalid input, try again.");
        }
    }
    void editNameOfItemClicked(int sentIndex,String input)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.desc to user prompt
        //System.out.println(userInput.getText() + " " + accordion.getPanes().size());
        if(sentIndex >= 0 && sentIndex <= accordion.getPanes().size() && accordion.getPanes().size() > 0)
        {
            accordion.getPanes().get(sentIndex).setText(input);
            userInput.clear();
            userInput.promptTextProperty().set("Press a button.");
        }
        else
        {
            userInput.clear();
            userInput.promptTextProperty().set("Invalid input, try again.");
        }
    }


    @FXML
    void editDescOfItemClicked(String input, TextArea ta)
    {
        //change userInput prompt description to "index of entry to edit"
        //get user prompt  for entry index to edit
        //if index exists, ask user for new title
        //if doesn't exist prompt again
        //set class.desc to user prompt
        ta.setText(input);
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
    void editDateOfItemClicked(String input, TextField tf)
    {
        //check if date is valid gregorian date then continue
        //if isn't valid prompt again
        //set class.date to user prompt at prompted index
        //System.out.println(actionEvent.getCode());
        tf.setText(input);
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
        for(int i = 0; i < maxAccCount; i++)
        {
            final Node source = (Node)actionEvent.getSource();
            Scene scene = source.getScene();
            CheckBox tempCB = (CheckBox) scene.lookup("#CB" + i);
            if(tempCB != null)
            {
                tempCB.getParent().getParent().getParent().visibleProperty().set(true);
                tempCB.getParent().getParent().getParent().setManaged(true);
            }
        }
    }

    @FXML
    void completeItemsClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry class.complete = false then disable it
        //if disabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
        for(int i = 0; i < maxAccCount; i++)
        {
            final Node source = (Node)actionEvent.getSource();
            Scene scene = source.getScene();
            CheckBox tempCB = (CheckBox) scene.lookup("#CB" + i);
            if(tempCB != null && tempCB.selectedProperty().get() == false)
            {
                tempCB.getParent().getParent().getParent().visibleProperty().set(false);
                tempCB.getParent().getParent().getParent().setManaged(false);
            }
            if(tempCB != null && tempCB.selectedProperty().get() == true)
            {
                tempCB.getParent().getParent().getParent().visibleProperty().set(true);
                tempCB.getParent().getParent().getParent().setManaged(true);
            }
        }
    }
    @FXML
    void incompleteItemsClicked(MouseEvent actionEvent)
    {
        //increment through all entries of expanded accordion pane
        //if entry class.complete = true then disable it
        //if disabling an entry in an accordion doesn't revaluate the height then use refreshAccordion()
        for(int i = 0; i < maxAccCount; i++)
        {
            final Node source = (Node)actionEvent.getSource();
            Scene scene = source.getScene();
            CheckBox tempCB = (CheckBox) scene.lookup("#CB" + i);
            if(tempCB != null && tempCB.selectedProperty().get() == false)
            {
                tempCB.getParent().getParent().getParent().visibleProperty().set(true);
                tempCB.getParent().getParent().getParent().setManaged(true);
            }
            if(tempCB != null && tempCB.selectedProperty().get() == true)
            {
                tempCB.getParent().getParent().getParent().visibleProperty().set(false);
                tempCB.getParent().getParent().getParent().setManaged(false);
            }
        }
    }

    @FXML
    void importListClicked(MouseEvent actionEvent)
    {
        //get to path through file explorer (or if have to; ask for path)
        //load file as File and send to parse
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Saved List");
        final Node source = (Node)actionEvent.getSource();
        Scene scene = source.getScene();
        File file = fileChooser.showOpenDialog(scene.getWindow());
        try
        {
            clearListClicked();
            arrayToList(ToDoLists.parse(file,actionEvent),actionEvent);
        }
        catch (Exception e)
        {
            userInput.promptTextProperty().set("Error retrieving file.");
        }
    }

    void arrayToList(ArrayList<itemFormat> arr, MouseEvent actionEvent)
    {
        for(int i = 0; i < arr.size(); i++)
        {
            Pair<TextField,TextArea> pair = addToListClicked();
            Scene scene = accordion.getScene();
            //CheckBox tempCB = (CheckBox) scene.lookup("#CB" + i);
            AnchorPane ap = (AnchorPane) accordion.getPanes().get(i).getContent();
            CheckBox tempCB = (CheckBox) ap.getChildren().get(0);
            if(arr.get(i).complete == true)
            {
                tempCB.selectedProperty().set(true);
            }
            else
            {
                tempCB.selectedProperty().set(false);
            }

            editNameOfItemClicked(i,arr.get(i).title);
            editDateOfItemClicked(arr.get(i).date,pair.getKey());
            editDescOfItemClicked(arr.get(i).desc,pair.getValue());
        }
    }

    void listToFile(File file)
    {
        //System.out.println("Starting");
        try
        {
            FileWriter writer = new FileWriter(file);
            writer.write(accordion.getPanes().size() + "\n");
            for(int i = 0; i < accordion.getPanes().size(); i++)
            {
                Scene scene = accordion.getPanes().get(i).getChildrenUnmodifiable().get(0).getScene();
                CheckBox tempCB = (CheckBox) scene.lookup("#CB" + i);
                AnchorPane ap = (AnchorPane) accordion.getPanes().get(i).getContent();
                TextField tf = (TextField) ap.getChildren().get(1);
                TextArea ta = (TextArea) ap.getChildren().get(2);
                if(tempCB.selectedProperty().get() == false)
                {
                    writer.write("x");
                }
                else
                {
                    writer.write("y");
                }
                writer.write(accordion.getPanes().get(i).getText() +"_\n"); //titles
                writer.write(tf.getText() + "_\n");
                writer.write(ta.getText() + "_\n");
            }
            writer.close();
        }
        catch (Exception e)
        {
            userInput.promptTextProperty().set("Error saving file.");
        }

    }

    @FXML
    void exportListClicked(MouseEvent actionEvent)
    {
        //get to save path through file explorer (or if have to; ask for path)
        //save as txt or json
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to drive");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        final Node source = (Node)actionEvent.getSource();
        Scene scene = source.getScene();
        File file = fileChooser.showSaveDialog(scene.getWindow());
        listToFile(file);
    }
}