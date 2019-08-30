
package assessmentanalyser.controllers;

import assessmentanalyser.beans.Grades;
import assessmentanalyser.database.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class GradesViewController implements Initializable
{
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private TableView<Grades> gradesTable;
    @FXML
    private TableColumn<Grades, String> studentName;
    @FXML
    private TableColumn<Grades, String> subject;
    @FXML
    private TableColumn<Grades, Double> score;
    @FXML
    private TableColumn<Grades, String> className;
    @FXML
    private TableColumn<Grades, Integer> id;
    @FXML
    private ChoiceBox<String> selectionBox;
    @FXML
    private JFXButton viewButton;

    @FXML
    private ChoiceBox<String> searchClue;
    
    private String searchValue;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        
        selectionBox.getItems().addAll("ALL","CLASS", "SINGLE STUDENT", "SUBJECT");
        selectionBox.getSelectionModel().selectFirst();
        
        searchClue.getItems().add("ALL");
        searchClue.getSelectionModel().selectFirst();
        searchClue.getSelectionModel().selectedItemProperty().addListener(this::itemSelected);
        
        selectionBox.getSelectionModel().selectedItemProperty().addListener(this::itemChanged);
        
        
    } 
    
    public void itemSelected(ObservableValue<? extends String> observable,  String oldValue, String newValue)
    {
        searchValue =  newValue;
    }
    
    public void itemChanged(ObservableValue<? extends String> observable,  String oldValue, String newValue)
    {
        if (newValue.equals("ALL"))
        {   
            searchClue.getItems().clear();
             searchClue.getItems().add("ALL");
            searchClue.getSelectionModel().selectFirst();
            
        }
        
        else if(newValue.equals("CLASS"))
        {   
         
            try {
                Connection conn = DatabaseConnection.createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT DISTINCT class FROM STUDENTS");
                
                searchClue.getItems().clear();
                while(rs.next())
                {
                   String studentClasses = rs.getString("class");
                   searchClue.getItems().addAll(studentClasses);
                }
                searchClue.getItems().addAll("4", "3", "2", "1");
                
                searchClue.getSelectionModel().selectFirst();
            } catch (SQLException ex) {
                Logger.getLogger(GradesViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(newValue.equals("SINGLE STUDENT"))
        {
               try {
                Connection conn = DatabaseConnection.createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT DISTINCT id FROM STUDENTS");
                searchClue.getItems().clear();
                while(rs.next())
                {
                   String studentClasses = String.valueOf(rs.getInt("id"));
                   searchClue.getItems().addAll(studentClasses);
                }
                
                searchClue.getSelectionModel().selectFirst();
            } catch (SQLException ex) {
                Logger.getLogger(GradesViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        else
        {   
                 
            try {
                Connection conn = DatabaseConnection.createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT DISTINCT subject FROM GRADES");
                searchClue.getItems().clear();
                while(rs.next())
                {
                   String studentClasses = rs.getString("subject");
                   searchClue.getItems().addAll(studentClasses);
                }
                
                searchClue.getSelectionModel().selectFirst();
            } catch (SQLException ex) {
                Logger.getLogger(GradesViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
   
    
    @FXML
    private void viewResults(ActionEvent event)
    {
        gradesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        score.setCellValueFactory(new PropertyValueFactory<>("grade"));
        className.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        Connection conn = DatabaseConnection.createConnection();
        
        PreparedStatement allStudents ;
        
        
        
        ResultSet rs ;
        
        String selection = selectionBox.getValue();
       
        try
        {    
             if(selection.equals("ALL"))
             {
                 String allQuery = "SELECT DISTINCT id, fname, lname, class, subject, grade FROM STUDENTS "
                            +"JOIN GRADES ON id = student_id";
                 
                  allStudents = conn.prepareStatement(allQuery);
                  rs = allStudents.executeQuery();
                 
                 gradesTable.getItems().clear();
                 while(rs.next())
                 {   
                     int idStu = rs.getInt("id");
                     String name = rs.getString("fname")  + "   " + rs.getString("lname");
                     String classNme = rs.getString("class");
                     double grade = rs.getDouble("grade");
                     String subjectStu = rs.getString("subject");
                     
                     Grades grades = new Grades(idStu,name, classNme, subjectStu, grade);
                     
                     gradesTable.getItems().addAll(grades);
                     
                     
                     
                 }
                 
             }
             
             if(selection.equals("CLASS"))
             {  
                 String searchClass = searchValue;
               
                 String classQuery = "SELECT DISTINCT id,fname, lname, class, subject, grade FROM STUDENTS "
                            +"JOIN GRADES ON id = student_id WHERE class LIKE \'%"+searchClass+"%\'";
                 gradesTable.getItems().clear();
                 allStudents = conn.prepareStatement(classQuery);
                  rs = allStudents.executeQuery();
                 
                 while(rs.next())
                 {   
                     int idStu = rs.getInt("id");
                     String name = rs.getString("fname")  + "   " + rs.getString("lname");
                     String classNme = rs.getString("class");
                     double grade = rs.getDouble("grade");
                     String subjectStu = rs.getString("subject");
                     
                     Grades grades = new Grades(idStu,name, classNme, subjectStu, grade);
                     gradesTable.getItems().addAll(grades);
                     
                }
                 
                 
             }
           
             if(selection.equals("SINGLE STUDENT"))
             {
                 
                 
                 String searchStudent = searchValue;
                 String singleStudentQuery = "SELECT id,fname, lname, class, subject, grade FROM STUDENTS "
                            +"JOIN GRADES ON id = student_id WHERE id = "+searchStudent;
                 gradesTable.getItems().clear();
                 allStudents = conn.prepareStatement(singleStudentQuery);
                  rs = allStudents.executeQuery();
                 String name = " " ;
                 while(rs.next())
                 {   
                     int idStu = rs.getInt("id");
                     name  = rs.getString("fname")  + "   " + rs.getString("lname");
                     String classNme = rs.getString("class");
                     double grade = rs.getDouble("grade");
                     String subjectStu = rs.getString("subject");
                     
                     Grades grades = new Grades(idStu,name,classNme, subjectStu, grade);
                    
                     gradesTable.getItems().addAll(grades);
                    
                  }
                 
                 
             }
             
             
              
             if(selection.equals("SUBJECT"))
             {  
                 String searchSubject = searchValue;
                 String subjectQuery = "SELECT id,fname, lname, class, subject, grade FROM STUDENTS "
                            +"JOIN GRADES ON id = student_id WHERE subject LIKE \'"+searchSubject+"%\'";
                 gradesTable.getItems().clear();
                 allStudents = conn.prepareStatement(subjectQuery);
                  rs = allStudents.executeQuery();
                 
                 while(rs.next())
                 {   
                     int idStu = rs.getInt("id");
                     String name = rs.getString("fname")  + "   " + rs.getString("lname");
                     String classNme = rs.getString("class");
                     double grade = rs.getDouble("grade");
                     String subjectStu = rs.getString("subject");
                     
                     Grades grades = new Grades(idStu,name, classNme, subjectStu, grade);
                     gradesTable.getItems().addAll(grades);
                     
                }
                 
                 
             }
             
             
        }
        catch(SQLException ex)
        {}
        
        
    }
    
}
