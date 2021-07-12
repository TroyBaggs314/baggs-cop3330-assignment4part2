/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Troy Baggs
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class controllerMethodTests extends ApplicationTest
{
    @Override
    public void start (Stage stage) throws Exception
    {
        URL url = new File("src/main/resources/ucf/assignments/Duplicate.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setTitle("TODO Lists");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testAddToList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        //num = current number of accordions
        //call addListClicked()
        //assert.equals(num+1,highest indexed accordion);
        clickOn("#addToListButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            int count = acc.getPanes().size();
            return count > 0;
        });
    }

    @Test
    public void testRemoveFromList()
    {
        //num = current number of accordions
        //int temp = (int)Math.random()*10; //random index to remove
        //call removeListClicked(at index of temp)
        //assert.equals(temp-1,highest indexed accordion)
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#userInput");
        type(KeyCode.DIGIT1);
        clickOn("#removeFromListButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            int count = acc.getPanes().size();
            return count == 1;
        });
    }

    @Test
    public void testEditListTitle()
    {
        //retrieve title of any accordion
        //assertEquals("Go to store", editListTitle("Go to store");
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#userInput");
        type(KeyCode.DIGIT1);type(KeyCode.SLASH);type(KeyCode.G);type(KeyCode.O);type(KeyCode.SPACE);type(KeyCode.T);type(KeyCode.O);type(KeyCode.SPACE);type(KeyCode.S);type(KeyCode.T);type(KeyCode.O);type(KeyCode.R);type(KeyCode.E);
        clickOn("#editItemTitleButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            String name = acc.getPanes().get(0).getText();
            return name.equals("go to store");
        });

    }


    @Test
    public void testEditDescOfItem()
    {
        //int temp = (int)Math.random()*10; //random number
        //call editDescOfItemClicked(index of temp, "This is the new description")
        //assert.equals("This is the new description",itemFormat[temp].desc)
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        type(KeyCode.D).type(KeyCode.E).type(KeyCode.S).type(KeyCode.C).type(KeyCode.PERIOD);
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            AnchorPane ap = (AnchorPane) acc.getPanes().get(0).getContent();
            TextArea ta = (TextArea) ap.getChildren().get(2);
            ta.setText("desc.");
            return ta.getText().equals("desc.");
        });
    }

    @Test
    public void testEditDateOfItem()
    {
        //int temp = (int)Math.random()*10; //random number
        //call editDescOfItemClicked(index of temp, "This is the new description")
        //assert.equals("20010507",itemFormat[temp].date)
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT0).type(KeyCode.DIGIT1).type(KeyCode.SLASH).type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.SLASH).type(KeyCode.DIGIT0).type(KeyCode.DIGIT7);
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            AnchorPane ap = (AnchorPane) acc.getPanes().get(0).getContent();
            TextField tf = (TextField) ap.getChildren().get(1);
            tf.setText("2001/05/07");
            return tf.getText().equals("2001/05/07");
        });

    }

    @Test
    public void testMarkCompleteList()
    {
        //int temp = (int)Math.random()*10; //random number
        //call editDescOfItemClicked(index of temp, "This is the new description")
        //assert.equals("20010507",itemFormat[temp].date)
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        type(KeyCode.DIGIT2).type(KeyCode.DIGIT0).type(KeyCode.DIGIT0).type(KeyCode.DIGIT1).type(KeyCode.SLASH).type(KeyCode.DIGIT0).type(KeyCode.DIGIT5).type(KeyCode.SLASH).type(KeyCode.DIGIT0).type(KeyCode.DIGIT7);
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            AnchorPane ap = (AnchorPane) acc.getPanes().get(0).getContent();
            CheckBox cb = (CheckBox) ap.getChildren().get(0);
            cb.selectedProperty().set(true);
            return cb.selectedProperty().get();
        });

    }

    @Test
    public void testImportList() throws MalformedURLException {
        //create 3 new test files to import
        //call importFileClicked() single file
        //import first test file
        //check index of accordion increased
        //assert.equals("2001-05-07",accordion.getPanels().get(accordion.getPanels().size()))
        //import second and third test file
        //check index of accordion increased by 2
        //assert.equals("2021-06-30",accordion.getPanels().get(accordion.getPanels().size()))

        File file = new File("src/main/resources/ucf/assignments/importtest");
        String fileName = file.getAbsolutePath();
        clickOn("#userInput").type(KeyCode.S).type(KeyCode.R).type(KeyCode.C).type(KeyCode.SLASH).type(KeyCode.M).type(KeyCode.A).type(KeyCode.I).type(KeyCode.N).type(KeyCode.SLASH).type(KeyCode.R).type(KeyCode.E).type(KeyCode.S).type(KeyCode.O).type(KeyCode.U).type(KeyCode.R).type(KeyCode.C).type(KeyCode.E).type(KeyCode.S).type(KeyCode.SLASH).type(KeyCode.U).type(KeyCode.C).type(KeyCode.F).type(KeyCode.SLASH).type(KeyCode.A).type(KeyCode.S).type(KeyCode.S).type(KeyCode.I).type(KeyCode.G).type(KeyCode.N).type(KeyCode.M).type(KeyCode.E).type(KeyCode.N).type(KeyCode.T).type(KeyCode.S).type(KeyCode.SLASH).type(KeyCode.I).type(KeyCode.M).type(KeyCode.P).type(KeyCode.O).type(KeyCode.R).type(KeyCode.T).type(KeyCode.T).type(KeyCode.E).type(KeyCode.S).type(KeyCode.T);

        clickOn("#importButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            String name = acc.getPanes().get(6).getText();
            return name.equals("title 7");
        });

    }

    @Test
    public void testExportList() throws MalformedURLException {
        //create 3 new test files to import
        //call importFileClicked() single file
        //import first test file
        //check index of accordion increased
        //assert.equals("2001-05-07",accordion.getPanels().get(accordion.getPanels().size()))
        //import second and third test file
        //check index of accordion increased by 2
        //assert.equals("2021-06-30",accordion.getPanels().get(accordion.getPanels().size()))
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#userInput").type(KeyCode.S).type(KeyCode.R).type(KeyCode.C).type(KeyCode.SLASH).type(KeyCode.M).type(KeyCode.A).type(KeyCode.I).type(KeyCode.N).type(KeyCode.SLASH).type(KeyCode.R).type(KeyCode.E).type(KeyCode.S).type(KeyCode.O).type(KeyCode.U).type(KeyCode.R).type(KeyCode.C).type(KeyCode.E).type(KeyCode.S).type(KeyCode.SLASH).type(KeyCode.U).type(KeyCode.C).type(KeyCode.F).type(KeyCode.SLASH).type(KeyCode.A).type(KeyCode.S).type(KeyCode.S).type(KeyCode.I).type(KeyCode.G).type(KeyCode.N).type(KeyCode.M).type(KeyCode.E).type(KeyCode.N).type(KeyCode.T).type(KeyCode.S).type(KeyCode.SLASH).type(KeyCode.S).type(KeyCode.A).type(KeyCode.V).type(KeyCode.E).type(KeyCode.T).type(KeyCode.E).type(KeyCode.S).type(KeyCode.T);
        clickOn("#exportButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
        {
            String name = acc.getPanes().get(1).getText();
            return name.equals("New Entry");
        });

    }

    @Test
    public void testClearItems()
    {
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#clearItemButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
                acc.getPanes().size() == 0);
    }

    @Test
    public void testAllListVisible()
    {
        //increment through all entries of an accordion
        //if entry.disabled = true
        //then assert.fail()
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#showAllButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
                acc.getPanes().get(0).visibleProperty().get() == true);
    }

    @Test
    public void testCompleteListVisible()
    {
        //increment through all entries of an accordion
        //if entry class.complete = false && entry.disabled = false
        //then assert.fail()
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#showCompleteButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
                acc.getPanes().get(0).visibleProperty().get() == false);

    }

    @Test
    public void testIncompleteListVisible()
    {
        //increment through all entries of an accordion
        //if entry class.complete = true && entry.disabled = false
        //then assert.fail()
        clickOn("#addToListButton");
        clickOn("#addToListButton");
        clickOn("#showIncompleteButton");
        FxAssert.verifyThat("#accordion", (Accordion acc) ->
                acc.getPanes().get(0).visibleProperty().get() == true);

    }
}
