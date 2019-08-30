
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

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class EnterGradesController implements Initializable
{

    @FXML
    private JFXButton register;
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXTextField grade;
    @FXML
    private JFXTextField studentId;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void saveGrade(ActionEvent event) throws SQLException
    {
        Connection conn = DatabaseConnection.createConnection();
        DatabaseConnection.createGradeTable(conn);
        
        String stuSubject = this.subject.getText().toUpperCase();
        String stuGrade = this.grade.getText();
        String stuId = this.studentId.getText();
        
        
        if(stuSubject.equals("") || stuGrade.equals("") || stuId.equals(""))
        {   
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ALL FIELDS ARE REQUIRED");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else
        {
            double studGrade = Double.parseDouble(stuGrade);
            int student_id = Integer.parseInt(stuId);
            
            String gradeTable = DatabaseConnection.createGradeTable(conn);
            
            PreparedStatement prst = conn.prepareStatement("INSERT INTO "+gradeTable+"(subject, grade, student_id) VALUES(?, ?, ?)");
            
            prst.setString(1, stuSubject);
            prst.setDouble(2, studGrade);
            prst.setInt(3, student_id);
         
            
            prst.execute();
            
            
                grade.setText("");
                subject.setText("");
                studentId.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("SUCCESSFULLY SAVED");
                alert.setHeaderText(null);
                alert.showAndWait();
            
        }
     }
    
}
