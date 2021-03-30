/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.io.IOException;
import projet.entities.FxmlLoader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ProjectDashboardController implements Initializable {

    @FXML
    private BorderPane mainPane;
    @FXML
    private ImageView AppLogo;
    @FXML
    private Button Home;
    @FXML
    private Button WorkoutandDiet;
    @FXML
    private Pane buttonsPane;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button3;
    @FXML
    private Button button7;
    @FXML
    private Button Close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println("Home!");
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage(getClass().getResource("HomePageDashboard.fxml"));
            mainPane.setCenter(view);
        } catch (IOException ex) {
            Logger.getLogger(ProjectDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CloseDashboard(ActionEvent event) {
        Stage stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void LoadHome(ActionEvent event) throws IOException {
        System.out.println("Home!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage(getClass().getResource("HomePageDashboard.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    private void LoadWorkoutandDiet(ActionEvent event) throws IOException {
        System.out.println("Workout and Diet!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage(getClass().getResource("Home.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    private void LoadReservationandterrain(ActionEvent event) {
    }

    @FXML
    private void LoadProduits(ActionEvent event) {
    }

    @FXML
    private void LoadCommentandsubject(ActionEvent event) {
    }

    @FXML
    private void LoadUsers(ActionEvent event) {
    }


}
