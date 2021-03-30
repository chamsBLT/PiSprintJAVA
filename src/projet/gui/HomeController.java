/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnWorkout;
    @FXML
    private Button btnDiet;
    @FXML
    private ImageView WorkoutLogo;
    @FXML
    private ImageView DietLogo;
    @FXML
    private ImageView SportunusLogo;
    @FXML
    private AnchorPane background;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AfficherWorkoutNavigation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageWorkout.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Sport");
            stage.getIcons().add(new Image("/Graphics/AppLogo.png"));
            Scene sceneHPW = new Scene(root,1000,525);           
            stage.setScene(sceneHPW);
            sceneHPW.getStylesheets().add(getClass().getResource("/Graphics/AppCss/HomePageWorkout.css").toExternalForm());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AfficherDietNavigation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageDiet.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Régime");
            stage.getIcons().add(new Image("/Graphics/AppLogo.png"));
            Scene sceneHPD = new Scene(root,1000,525);
            stage.setScene(sceneHPD);
            sceneHPD.getStylesheets().add(getClass().getResource("/Graphics/AppCss/HomePageDiet.css").toExternalForm());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
