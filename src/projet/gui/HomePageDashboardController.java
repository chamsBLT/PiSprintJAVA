/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class HomePageDashboardController implements Initializable {

    @FXML
    private Pane logoPane;
    @FXML
    private ImageView LogoHomeDashboard;
    @FXML
    private Pane HomeBackgroundPane;
    @FXML
    private Pane backgroundPane;
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
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactUs.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            Scene Cus = new Scene(root1);
            stage.setScene(Cus);
            stage.initStyle(StageStyle.UNDECORATED);
            Cus.getStylesheets().add(getClass().getResource("/Graphics/AppCss/ContactUs.css").toExternalForm());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void FacebookTransition(ActionEvent event) throws URISyntaxException, IOException {
         Desktop.getDesktop().browse(new URI("https://www.facebook.com/SportUnus"));
    }
    
}
