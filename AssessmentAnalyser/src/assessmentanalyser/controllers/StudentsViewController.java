/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessmentanalyser.controllers;

import assessmentanalyser.beans.Student;
import assessmentanalyser.database.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class StudentsViewController implements Initializable
{

    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, Integer> id;
    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, String> className;
    @FXML
    private JFXButton load;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void loadStudents(ActionEvent event) throws SQLException
    {
        String lName, fName, class_name;
        int identity;
        
        studentsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        className.setCellValueFactory(new PropertyValueFactory<>("className"));
        
        studentsTable.getItems().clear();
        
        try (Connection conn = DatabaseConnection.createConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM STUDENTS"))
        {
            
            while(rs.next())
            {
                lName = rs.getString("lname");
                fName = rs.getString("fname");
                identity = rs.getInt("id");
                class_name = rs.getString("class");
                
                
                Student s5 = new Student();
                s5.setFirstName(fName);
                s5.setLastName(lName);
                s5.setId(identity);
                s5.setClass(class_name);
                studentsTable.getItems().addAll(s5);
                
                
            }
            conn.commit();
            
        }
        
     
    }
    
}
