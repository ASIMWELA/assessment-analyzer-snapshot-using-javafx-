/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assessmentanalyser.main;

import assessmentanalyser.database.DatabaseConnection;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author AUGUSTINE
 */
public class AssessmentAnalyser extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        
      
        Parent root = FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/Main.fxml"));
        
        Scene scene = new Scene(root);
        
        
        
        stage.setScene(scene);
        stage.setMinHeight(1000);
        stage.setMinWidth(900);
        stage.setTitle("ASSESSMENT RESULTS ANALYSER");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {  
        Connection conn = DatabaseConnection.createConnection();
        String stuTable = DatabaseConnection.createGradeTable(conn);
       
       
        launch(args);
        
    }
    
}
