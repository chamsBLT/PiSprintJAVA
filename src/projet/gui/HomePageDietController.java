/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    @FXML
    private Button btnClose;
    @FXML
    private Pane backgroundPane;
    @FXML
    private Pane choicePane;
    @FXML
    private Pane lunchPane;
    @FXML
    private Pane dinnerPane;
    @FXML
    private Text MyTextZone;

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
    private void resetValues(MouseEvent event) {
        CbCaloriesUser.setValue(null);
        tfBreakfastUser.setText(null);
        tfLunchUser.setText(null);
        tfDinnerUser.setText(null);
        tfSnacksUser.setText(null);
    }

    @FXML
    private void downloadpdf(ActionEvent event) throws DocumentException, BadElementException, IOException {
        if (CbCaloriesUser.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Régimes");
            alert.setHeaderText(null);
            alert.setContentText("Aucun régime n'est sélectionné!");
            alert.showAndWait();
        } else {
            try {
                String file_name = ("Regime.pdf");
                Document document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream(file_name));

                document.open();

                Image LogoSportunus = Image.getInstance("src\\Graphics\\Logo_Diet_pdf.png");
                LogoSportunus.setAlignment(Element.ALIGN_CENTER);
                document.add(LogoSportunus);

                Paragraph paraHeader1 = new Paragraph("\n\nVous avez choisi un régime de " + CbCaloriesUser.getValue() + " calories , au date du " + java.time.LocalDate.now() + ".\n");
                document.add(paraHeader1);

                Paragraph paraHeader2 = new Paragraph("Le plan de votre régime est comme suit :\n\n\n ");
                document.add(paraHeader2);

                Image imageBreakfast = Image.getInstance("src\\Graphics\\breakfast_icon.png");
                Paragraph paraBreakfast = new Paragraph("Breakfast : \n\n" + tfBreakfastUser.getText());

                Image imageLunch = Image.getInstance("src\\Graphics\\lunch_icon.png");
                Paragraph paraLunch = new Paragraph("Lunch : \n\n" + tfLunchUser.getText());

                Image imageDinner = Image.getInstance("src\\Graphics\\dinner_icon.png");
                Paragraph paraDinner = new Paragraph("Dinner : \n\n" + tfDinnerUser.getText());

                Image imageSnacks = Image.getInstance("src\\Graphics\\snacks_icon.png");
                Paragraph paraSnacks = new Paragraph("Snacks : \n\n " + tfSnacksUser.getText());

                float[] pointColumnWidths = {2, 10};
                PdfPTable table = new PdfPTable(pointColumnWidths);

                table.addCell(imageBreakfast);
                table.addCell(paraBreakfast);
                table.addCell(imageLunch);
                table.addCell(paraLunch);
                table.addCell(imageDinner);
                table.addCell(paraDinner);
                table.addCell(imageSnacks);
                table.addCell(paraSnacks);
                document.add(table);

                document.close();
                try {
                    Desktop.getDesktop().open(new File(file_name));
                } catch (IOException ex) {
                    Logger.getLogger(HomePageDietController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(HomePageDietController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void transitionCRUDdiet(ActionEvent event) throws IOException {
        Parent DcrudParent = FXMLLoader.load(getClass().getResource("ShowDiet.fxml"));
        Scene DcrudScene = new Scene(DcrudParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(DcrudScene);
        DcrudScene.getStylesheets().add(getClass().getResource("/Graphics/AppCss/ShowDiet.css").toExternalForm());
        window.show();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox();

    }

}
