
package assessmentanalyser.controllers;

import assessmentanalyser.database.DatabaseConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;

/**
 *
 * @author AUGUSTINE
 */
public class PerfomanceChartController implements Initializable
{

    @FXML
    private LineChart<String, Number> perfomanceTrend;
    @FXML
    private NumberAxis yAxix;
    @FXML
    private CategoryAxis xAxix;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
            Connection conn = DatabaseConnection.createConnection();
            
            String id =ResultsAnalysisController.student_id;
            String class_name = ResultsAnalysisController.class_name;
            String categoryValue = ResultsAnalysisController.categoryValue;
            String subject_name = ResultsAnalysisController.subject_name;
            
            
            String singleStudentQuerry = "SELECT fname, lname, grade, subject FROM STUDENTS JOIN GRADES ON id = student_id WHERE id=" + id ;
            String subjectQuerry = "SELECT fname, lname, grade, subject FROM STUDENTS JOIN GRADES ON id = student_id WHERE subject LIKE \'%"+subject_name+"%\'" ;
            String classQuerry = "SELECT class, subject, grade FROM STUDENTS JOIN GRADES ON id = student_id WHERE class LIKE \'%"+class_name+"%\'" ;
            
            
            ObservableList<XYChart.Series<String, Number>> mainData = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
            XYChart.Series<String, Number> studentData = new XYChart.Series<>();
          
              String name = " ";
            
            if(categoryValue.equals("SINGLE STUDENT"))
            {
                
                try
                {
                    Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery(singleStudentQuerry);
                    while(rs.next())
                    {   
                        String subjectValue = rs.getString("subject");
                        int grade = rs.getInt("grade");
                        name = rs.getString("fname")+"  "+rs.getString("lname");


                        XYChart.Data<String, Number> data= new XYChart.Data<>(subjectValue, grade);
                        studentData.getData().addAll(data);

                    }

                     mainData.addAll(studentData); 
                     perfomanceTrend.setData(mainData);
                     perfomanceTrend.setTitle("PERFOMANCE OF " + name);

                }
                catch(SQLException ex)
                {}
            }
           
            if(categoryValue.equals("CLASS"))
            {
                
                try
                {
                    Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery(classQuerry);
                    while(rs.next())
                    {   
                        String subjectValue = rs.getString("subject");
                        int grade = rs.getInt("grade");
                        name = rs.getString("class");


                        XYChart.Data<String, Number> data= new XYChart.Data<>(subjectValue, grade);
                        studentData.getData().addAll(data);

                    }

                     mainData.addAll(studentData); 
                     perfomanceTrend.setData(mainData);
                     perfomanceTrend.setTitle("PERFOMANCE OF " + name);

                }
                catch(SQLException ex)
                {}
            }
          
            
            if(categoryValue.equals("SUBJECT"))
            {
                
                try
                {
                    Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery(subjectQuerry);
                    while(rs.next())
                    {   
                        String subjectValue = rs.getString("subject");
                        int grade = rs.getInt("grade");
                        name = rs.getString("subject");


                        XYChart.Data<String, Number> data= new XYChart.Data<>(subjectValue, grade);
                        studentData.getData().addAll(data);

                    }

                     mainData.addAll(studentData); 
                     perfomanceTrend.setData(mainData);
                     perfomanceTrend.setTitle("PERFOMANCE OF " + name);

                }
                catch(SQLException ex)
                {}
            }
           
            
             
    }
    
}
