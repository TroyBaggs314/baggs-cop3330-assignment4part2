/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Troy Baggs
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;

import java.io.IOException;


public class ToDoLists extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Duplicate.fxml"));

        Scene scene = new Scene(root);



        primaryStage.setTitle("TODO Lists");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*public Accordion refreshAccordion(Accordion a) //only use if changing the size of an existing accordion doesn't affect its arrangement correctly
    {
        Accordion temp = new Accordion();
        for(int i = 0; i < a.getPanes().size(); i++)
        {
            temp.getPanes().set(i,a.getPanes().get(i));
        }
        return temp;
    }*/


    /*public ArrayList<itemFormat> parse(File file)
    {
        //use string parse of format title string goes here in the firstline\n yyyymmdd item description goes here and is one long string that nextLine() can eat up easily\n yyyymmdd etc.
        fill in new ArrayList<itemFormat> iF;
    }*/




}