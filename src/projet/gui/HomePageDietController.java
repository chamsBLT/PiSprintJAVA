/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.entities.diet;
import projet.services.dietCRUD;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class HomePageDietController implements Initializable {

    @FXML
    private TextArea tfLunchUser;
    @FXML
    private TextArea tfDinnerUser;
    @FXML
    private TextArea tfBreakfastUser;
    @FXML
    private TextArea tfSnacksUser;
    @FXML
    private Button pdfPrintDiet;
    @FXML
    private Button reroll;
    @FXML
    private Button showDiet;
    @FXML
    private ComboBox<String> CbCaloriesUser;
    @FXML
    private Button transitionCRUD;
    @FXML
    private Button downloadBtn;
    ObservableList calories_list = FXCollections.observableArrayList();
    ObservableList<diet> listDu;

    private void afficherComboBox() {
        calories_list.removeAll(calories_list);
        String a = "1600";
        String b = "1800";
        String c = "2000";
        String d = "2200";
        String e = "2400";
        String f = "2600";
        calories_list.addAll(a, b, c, d, e, f);
        CbCaloriesUser.getItems().addAll(calories_list);

    }

    @FXML
    private void rerollDiet(ActionEvent event) {
        if (CbCaloriesUser.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Diet");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir le nombre de calories!");
            alert.showAndWait();
        } else {
            dietCRUD dcd = new dietCRUD();
            listDu = dcd.showDietUser(CbCaloriesUser.getValue());
            tfBreakfastUser.setText(listDu.stream().map(diet::getBreakfast).collect(Collectors.toList()).toString().replace("[", "").replace("]", ""));
            tfLunchUser.setText(listDu.stream().map(diet::getLunch).collect(Collectors.toList()).toString().replace("[", "").replace("]", ""));
            tfDinnerUser.setText(listDu.stream().map(diet::getDinner).collect(Collectors.toList()).toString().replace("[", "").replace("]", ""));
            tfSnacksUser.setText(listDu.stream().map(diet::getSnacks).collect(Collectors.toList()).toString().replace("[", "").replace("]", ""));

            tfBreakfastUser.setWrapText(true);
            tfLunchUser.setWrapText(true);
            tfDinnerUser.setWrapText(true);
            tfSnacksUser.setWrapText(true);
        }
    }

    @FXML
    private void transitionCRUDdiet(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowDiet.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestion Diet");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resetValues(MouseEvent event) {
        CbCaloriesUser.setValue(null);
        tfBreakfastUser.setText(null);
        tfLunchUser.setText(null);
        tfDinnerUser.setText(null);
        tfSnacksUser.setText(null);
    }

    @FXML
    private void downloadpdf(ActionEvent event) throws DocumentException {
        if(CbCaloriesUser.getValue()==null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Régimes");
            alert.setHeaderText(null);
            alert.setContentText("Aucun régime n'est sélectionné!");
            alert.showAndWait();
        }
        else{
        try {
            String file_name =("Regime.pdf");
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            
            Paragraph para = new Paragraph("Test Paragraph");
            document.add(para);
            
            document.close();
            try {
                Desktop.getDesktop().open(new File(file_name));
            } catch (IOException ex) {
                Logger.getLogger(HomePageDietController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Done");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomePageDietController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox();

    }

}
