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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class ResultsAnalysisController implements Initializable
{

    @FXML
    private ChoiceBox<String> category;
    @FXML
    private ChoiceBox<String> studentId;
    @FXML
    private ChoiceBox<String> className;
    @FXML
    private ChoiceBox<String> subject;
    @FXML
    private JFXButton analyse;
    @FXML
    private StackPane graphPAne;
    @FXML
    private HBox statistics;
    @FXML
    private LineChart<String, Number> perfomanceTrend;
    @FXML
    private NumberAxis yAxix;
    @FXML
    private CategoryAxis xAxix;
    
    private String id, cla, sub, cat;
    
    public static String  student_id; 
    public  static String  class_name;
    public  static String  subject_name;    
    public static String categoryValue;
    /**
     * Initializes the controller class.
     */
    
    public ResultsAnalysisController()
    {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        
        
        category.getSelectionModel().selectedItemProperty().addListener(this::categorySelected);
        
        Connection conn = DatabaseConnection.createConnection();
        
        
        try
        {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT id, class FROM STUDENTS");
            while(rs.next())
            {
                String stuId = rs.getString("id");
                String stuCla = rs.getString("class");
              
                
                studentId.getItems().add(stuId);
                className.getItems().add(stuCla);
                
            }
            
            ResultSet rsG = st.executeQuery("SELECT DISTINCT subject FROM GRADES");
            while(rsG.next())
            {
                 String stuSub = rsG.getString("subject");
                 subject.getItems().add(stuSub);
                
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ResultsAnalysisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentId.getSelectionModel().selectedItemProperty().addListener(this::idSelected);
        subject.getSelectionModel().selectedItemProperty().addListener(this::subjectSelected);
        className.getSelectionModel().selectedItemProperty().addListener(this::classSelected);
        category.getSelectionModel().selectedItemProperty().addListener(this::categorySelected);
        category.getItems().addAll("SINGLE STUDENT", "CLASS", "SUBJECT");
        category.getSelectionModel().selectFirst();
        studentId.getSelectionModel().selectFirst();
        subject.getSelectionModel().selectFirst();
        className.getSelectionModel().selectFirst();
                 
    }    
    
    public void idSelected(ObservableValue<? extends String> observable,  String oldValue, String newValue)
    {
        id = newValue;
    }
    
    public void classSelected(ObservableValue<? extends String> observable,  String oldValue, String newValue)
    {
        cla = newValue;
    }
    
    public void subjectSelected(ObservableValue<? extends String> observable,  String oldValue, String newValue)
    {
        sub = newValue;
    }
    public void categorySelected(ObservableValue<? extends String> observable,  String oldValue, String newValue)
    {
        cat =  newValue;
        
        //allowing only meaningfull variables    
        if(cat.equals("SINGLE STUDENT"))
        {
            className.setDisable(true);
            studentId.setDisable(false);
            subject.setDisable(true);
        }
        if(cat.equals("CLASS"))
        {   studentId.setDisable(true);
            className.setDisable(false);
            subject.setDisable(false);
        }
        if(cat.equals("SUBJECT"))
        {   studentId.setDisable(false);
            className.setDisable(true);
            subject.setDisable(false);
   
        }
    }
    @FXML
    private void analyseResults(ActionEvent event) throws IOException
    {
        if(cat == null)
        {
            categoryValue = " ";
        }
        else
        {
            categoryValue = cat;
        }
        
        if(id == null)
        {
            student_id = "";
            
        }
        else
        {
            student_id = id;
        }
        
        if(sub == null)
        {
            subject_name = "";
        }
        else
        {
            subject_name = sub;
        }
        
        if(cla == null)
        {
            class_name = " ";
        }
        else
        {
            class_name = cla;
        }
       
        AnchorPane p = (AnchorPane)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/PerfomanceChart.fxml"));
        p.setMinSize(420, 500);
        graphPAne.getChildren().clear();
        graphPAne.getChildren().add(p);
       
    }
    
}
