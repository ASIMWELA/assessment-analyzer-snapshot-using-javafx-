
package assessmentanalyser.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author AUGUSTINE
 */
public class MainController implements Initializable
{

    @FXML
    private JFXButton rigister;
    @FXML
    private JFXButton analyse;
    @FXML
    private JFXButton view;
    @FXML
    private JFXButton enterGrades;
    @FXML
    private AnchorPane pane;
    @FXML
    private StackPane stackPane;
    
    VBox register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void registerStudent(ActionEvent event)
    {
        try
        {   
            register = (VBox)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/Register.fxml"));
            stackPane.getChildren().clear();
            stackPane.getChildren().add(register);
            analyse.setDisable(false);
            view.setDisable(false);
            rigister.setDisable(true);
            enterGrades.setDisable(false);
        } catch (IOException ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void analyseResults(ActionEvent event) throws IOException
    {    
        AnchorPane p = (AnchorPane)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/ResultsAnalysis.fxml"));
       
        stackPane.getChildren().clear();
        stackPane.getChildren().add(p);
        analyse.setDisable(true);
        view.setDisable(false);
        rigister.setDisable(false);
        enterGrades.setDisable(false);
    }

    @FXML
    private void viewStudentsPerfomance(ActionEvent event) throws IOException
    {
        AnchorPane viewPane = (AnchorPane)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/View.fxml"));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(viewPane);
        view.setDisable(true);
        analyse.setDisable(false);
        rigister.setDisable(false);
        enterGrades.setDisable(false);
    }

    @FXML
    private void enterGradesAssessment(ActionEvent event) throws IOException
    {
        AnchorPane viewPane = (AnchorPane)FXMLLoader.load(getClass().getResource("/assessmentanalyser/resources/EnterGrades.fxml"));
        stackPane.getChildren().clear();
        stackPane.getChildren().add(viewPane);
        view.setDisable(false);
        analyse.setDisable(false);
        rigister.setDisable(false);
        enterGrades.setDisable(true);
    }
    
}
