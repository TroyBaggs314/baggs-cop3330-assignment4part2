/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Troy Baggs
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollBar;
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

    public static Accordion refreshAccordion(Accordion a, int i)
    {
        if(i == 1)
        {
            a.setPrefHeight(a.getPrefHeight() + 25);
        }
        else if(i == 0)
        {
            a.setPrefHeight(167);
        }
        else if( i == -1)
        {
            a.setPrefHeight(a.getPrefHeight() - 25);
        }
        return a;
    }

    public static void scrollWheelSetup(ScrollBar sc, Accordion accordion)
    {
        sc.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
            {
                accordion.setTranslateY(25 + -(accordion.getHeight()) * ((sc.valueProperty().doubleValue() / 100)));
            }
        });
    }

    /*public ArrayList<itemFormat> parse(File file)
    {
        //use string parse of format title string goes here in the firstline\n yyyymmdd item description goes here and is one long string that nextLine() can eat up easily\n yyyymmdd etc.
        fill in new ArrayList<itemFormat> iF;
    }*/





}