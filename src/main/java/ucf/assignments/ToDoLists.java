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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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

    public static ArrayList<itemFormat> parse(File file, MouseEvent actionEvent) throws FileNotFoundException {
        //use string parse of format length\ntitle string goes here in the first line then _\n yyyy/mm/dd_\nitem description goes here and is one long string that nextLine() can eat up easily\n
        //fill in new ArrayList<itemFormat> iF;
        ArrayList<itemFormat> iF = new ArrayList<>();
        Scanner sc = new Scanner(file);
        int loopTimes = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < loopTimes; i++)
        {
            itemFormat temp = new itemFormat();
            temp.desc = "";
            String s = "";
            for(int j = 0; j < 3; j++)
            {
                do
                {
                    s = sc.nextLine();


                    if(j == 0)
                    {
                        if(s.charAt(0) == 'x')
                        {
                            temp.complete = false;
                        }
                        else if(s.charAt(0) =='y')
                        {
                            temp.complete = true;
                        }
                        temp.title = s.substring(1,s.length() - 1);
                    }
                    else if(j ==1)
                    {
                        temp.date = s.substring(0,s.length() - 1);
                    }
                    else if(j == 2)
                    {
                        if(s.charAt(s.length() -1) == '_')
                        {
                            temp.desc += s.substring(0, s.length() - 1);
                        }
                        else
                        {
                            temp.desc += s.substring(0,s.length()) + "\n";
                        }
                    }
                }while(s.charAt(s.length() - 1) != '_');
            }
            iF.add(i,temp);
        }
        return iF;
    }

}