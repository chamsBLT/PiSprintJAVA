/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class HomeMainController implements Initializable {

    @FXML
    private Pane backgroundPane;
    @FXML
    private Pane HomeBackgroundPane;
    @FXML
    private Pane logoPane;
    @FXML
    private ImageView LogoHomeDashboard;
    @FXML
    private Button Contactus;
    @FXML
    private Button FacebookPage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ContactUsTransition(ActionEvent event) {
    }

    @FXML
    private void FacebookTransition(ActionEvent event) {
    }
    
}
