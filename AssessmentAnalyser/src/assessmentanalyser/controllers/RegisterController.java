/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessmentanalyser.controllers;

import assessmentanalyser.database.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class RegisterController implements Initializable
{

    
    @FXML
    private VBox registrationForm;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField lname;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXButton register;
    @FXML
    private Label successOrNot;
    @FXML
    private JFXTextField className;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    } 
    
    @FXML
    private void registerStudent(ActionEvent event) throws SQLException
    {  
        Connection conn = DatabaseConnection.createConnection();
        String studentTable = DatabaseConnection.createStudentTable(conn);
        
        String identity = id.getText();
        String firstName = fname.getText().toUpperCase();
        String lastName = lname.getText().toUpperCase();
        String class_name = className.getText().toUpperCase(); 
        
        
        String querry = "INSERT INTO "+studentTable+"(id, fname, lname, class) VALUES(?, ?, ?, ?)";
        
        if(identity.equals("") || firstName.equals("") || lastName.equals("") || class_name.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ALL FIELDS ARE REQUIRED");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else
        {
            int stuId = Integer.parseInt(identity);
            
            try (PreparedStatement prst = conn.prepareStatement(querry))
            {
                prst.setInt(1, stuId);
                prst.setString(2, firstName);
                prst.setString(3, lastName);
                prst.setString(4, class_name);
                prst.execute();
                
                fname.setText("");
                lname.setText("");
                id.setText("");
                className.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("SUCCESSFULLY REGISTERD");
                alert.setHeaderText(null);
                alert.showAndWait();
                
                
                
                conn.commit();
                conn.close();
            }
        }
        
        
        
        
        
       
    }
    
}
