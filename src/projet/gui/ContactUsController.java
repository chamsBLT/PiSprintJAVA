/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ContactUsController implements Initializable {

    @FXML
    private TextField tfSender;
    @FXML
    private TextField tfSubject;
    @FXML
    private TextArea tfMessage;
    @FXML
    private Button btnSend;
    @FXML
    private AnchorPane ContactUsAnchor;
    @FXML
    private Pane ContactUsPane;
    @FXML
    private Button btnClose;
    @FXML
    private PasswordField tfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfSender.setText(null);
        tfPassword.setText(null);
        tfMessage.setText(null);
        tfSubject.setText(null);

    }

    public void SendingEmail(String recepient) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String OurEmail = tfSender.getText();
        String OurPswd = tfPassword.getText();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(OurEmail, OurPswd);
            }

        });
        Message message = PrepareMessage(session, OurEmail, recepient);

        Transport.send(message);
        System.out.println("sent!");
    }

    private Message PrepareMessage(Session session, String OurEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(OurEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(tfSubject.getText());
            message.setText(tfMessage.getText());
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ContactUsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void SendEmail(ActionEvent event) throws Exception {
        if ((tfSender.getText() == null) || (tfPassword.getText() == null) || (tfSubject.getText() == null) || (tfMessage.getText() == null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Contactez Nous");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        } else {
            
            SendingEmail("sportinushelp@gmail.com");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Contactez Nous");
            alert.setHeaderText(null);
            alert.setContentText("Votre message a été bien envoyé!");
            alert.show();
        }
    }

    @FXML
    private void CloseContactUsTab(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ResetValues(MouseEvent event) {
        tfSender.setText(null);
        tfPassword.setText(null);
        tfMessage.setText(null);
        tfSubject.setText(null);
    }

}
