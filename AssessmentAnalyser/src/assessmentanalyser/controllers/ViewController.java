/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessmentanalyser.controllers;

import assessmentanalyser.database.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class ViewController implements Initializable
{

    @FXML
    private StackPane viewStackPane;
    @FXML
    private JFXButton grades;
    @FXML
    private JFXButton students;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void viewGrades(ActionEvent event)
    {
        
        try
        {
            AnchorPane studentsPane = (AnchorPane)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/GradesView.fxml"));
            viewStackPane.getChildren().clear();
            viewStackPane.getChildren().add(studentsPane);
            grades.setDisable(true);
            students.setDisable(false);
        } catch (IOException ex)
        {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void viewStudents(ActionEvent event) throws IOException
    {
        AnchorPane studentsPane = (AnchorPane)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/StudentsView.fxml"));
        viewStackPane.getChildren().clear();
        viewStackPane.getChildren().add(studentsPane);
        students.setDisable(true);
        grades.setDisable(false);
    }
    
}
