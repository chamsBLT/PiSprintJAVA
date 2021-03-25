/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;
import projet.entities.diet;
import projet.services.dietCRUD;
import projet.services.ingredientCRUD;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ShowDietController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfBreakfast;
    @FXML
    private TextField tfLunch;
    @FXML
    private TextField tfDinner;
    @FXML
    private TextField tfSnacks;
    @FXML
    private ComboBox<String> cbCalories;
    @FXML
    private TableView<diet> table_diet;
    @FXML
    private TableColumn<diet, Integer> colId;
    @FXML
    private TableColumn<diet, String> colCalories;
    @FXML
    private TableColumn<diet, String> colBreakfast;
    @FXML
    private TableColumn<diet, String> colLunch;
    @FXML
    private TableColumn<diet, String> colDinner;
    @FXML
    private TableColumn<diet, String> colSnacks;
    @FXML
    private Button btnAjtDiet;
    @FXML
    private Button btnModDiet;
    @FXML
    private Button btnSuppDiet;

    @FXML
    private TextField rechercheDiet;

    int index = -1;
    ObservableList calories_list = FXCollections.observableArrayList();

    ObservableList<diet> listD;
    ObservableList<diet> dataList;

    @FXML
    private Button btnRetour;

    @FXML
    private void getSelectedDiet(MouseEvent event) {

        index = table_diet.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        tfId.setText(colId.getCellData(index).toString());
        tfBreakfast.setText(colBreakfast.getCellData(index));
        tfLunch.setText(colLunch.getCellData(index));
        tfDinner.setText(colDinner.getCellData(index));
        tfSnacks.setText(colSnacks.getCellData(index));
        cbCalories.setValue(colCalories.getCellData(index));

    }

    private void afficherComboBox() {
        calories_list.removeAll(calories_list);
        String a = "1600";
        String b = "1800";
        String c = "2000";
        String d = "2200";
        String e = "2400";
        String f = "2600";
        calories_list.addAll(a, b, c, d, e, f);
        cbCalories.getItems().addAll(calories_list);

    }

    public void majTable() {

        colId.setCellValueFactory(new PropertyValueFactory<diet, Integer>("id"));
        colBreakfast.setCellValueFactory(new PropertyValueFactory<diet, String>("breakfast"));
        colLunch.setCellValueFactory(new PropertyValueFactory<diet, String>("lunch"));
        colDinner.setCellValueFactory(new PropertyValueFactory<diet, String>("dinner"));
        colSnacks.setCellValueFactory(new PropertyValueFactory<diet, String>("snacks"));
        colCalories.setCellValueFactory(new PropertyValueFactory<diet, String>("calories"));

        dietCRUD dcd = new dietCRUD();
        listD = dcd.showDiet();
        table_diet.setItems(listD);
    }

    @FXML
    private void ajouterDiet(ActionEvent event) {
        if (cbCalories.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Diet");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
            String rBreakfast = tfBreakfast.getText();
            String rLunch = tfLunch.getText();
            String rDinner = tfDinner.getText();
            String rSnacks = tfSnacks.getText();
            String rCalories = cbCalories.getValue();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Diet");
            alert.setHeaderText(null);
            alert.setContentText("Diet ajoutée!");
            alert.show();

            diet d = new diet(rBreakfast, rLunch, rDinner, rSnacks, rCalories);
            dietCRUD dcd = new dietCRUD();
            dcd.addDiet(d);
            majTable();
            recherche_diet();
        }
    }

    @FXML
    private void modifierDiet(ActionEvent event) {

        if (cbCalories.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Diet");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un régime!");
            alert.showAndWait();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification Régime");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText("Le régime va être modifier");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {

                String rId1 = tfId.getText();
                String rBreakfast = tfBreakfast.getText();
                String rLunch = tfLunch.getText();
                String rDinner = tfDinner.getText();
                String rSnacks = tfSnacks.getText();
                String rCalories = cbCalories.getValue();

                int rId = Integer.parseInt(rId1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Diet");
                alert.setHeaderText(null);
                alert.setContentText("Diet modifiée!");
                alert.show();

                diet d = new diet(rId, rBreakfast, rLunch, rDinner, rSnacks, rCalories);
                dietCRUD dcd = new dietCRUD();
                dcd.editDiet(d);
                majTable();
                recherche_diet();
            } else {
                return;
            }
        }
    }

    @FXML
    private void supprimerDiet(ActionEvent event) {

        if (cbCalories.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Diet");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un régime!");
            alert.show();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression Régime");
            alertWE2.setHeaderText("Voulez vous confirmer la Suppression ?");
            alertWE2.setContentText("Le régime va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
                String mID1 = tfId.getText();

                int mID = Integer.parseInt(mID1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Diet");
                alert.setHeaderText(null);
                alert.setContentText("Diet supprimée!");
                alert.show();

                dietCRUD dcd = new dietCRUD();
                dcd.deleteDiet(mID);
                majTable();
                recherche_diet();
            } else {
                return;
            }
        }
    }

    void recherche_diet() {
        colId.setCellValueFactory(new PropertyValueFactory<diet, Integer>("id"));
        colBreakfast.setCellValueFactory(new PropertyValueFactory<diet, String>("breakfast"));
        colLunch.setCellValueFactory(new PropertyValueFactory<diet, String>("lunch"));
        colDinner.setCellValueFactory(new PropertyValueFactory<diet, String>("dinner"));
        colSnacks.setCellValueFactory(new PropertyValueFactory<diet, String>("snacks"));
        colCalories.setCellValueFactory(new PropertyValueFactory<diet, String>("calories"));

        dietCRUD dc = new dietCRUD();
        dataList = dc.showDiet();
        table_diet.setItems(dataList);

        FilteredList<diet> filteredData = new FilteredList<>(dataList, b -> true);
        rechercheDiet.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(diet -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (diet.getBreakfast().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (diet.getLunch().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (diet.getDinner().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (diet.getSnacks().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (diet.getCalories().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<diet> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_diet.comparatorProperty());
        table_diet.setItems(sortedData);
    }

    @FXML
    private void showAutoCompleteOptions(ActionEvent event) {
        ingredientCRUD icd = new ingredientCRUD();

        ObservableList<String> autoCompleteBreakfast = icd.showIngredientBreakfast(cbCalories.getValue());
        TextFields.bindAutoCompletion(tfBreakfast, autoCompleteBreakfast);
        ObservableList<String> autoCompleteDinner = icd.showIngredientDinner(cbCalories.getValue());
        TextFields.bindAutoCompletion(tfDinner, autoCompleteDinner);
        ObservableList<String> autoCompleteLunch = icd.showIngredientLunch(cbCalories.getValue());
        TextFields.bindAutoCompletion(tfLunch, autoCompleteLunch);
        ObservableList<String> autoCompleteSnack = icd.showIngredientSnack(cbCalories.getValue());
        TextFields.bindAutoCompletion(tfSnacks, autoCompleteSnack);

    }

    @FXML
    private void CRUDIngTransition(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowIngredients.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gestion des ingredients");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ResetValues(MouseEvent event) {
        tfId.setText(null);
        tfBreakfast.setText(null);
        tfDinner.setText(null);
        tfLunch.setText(null);
        tfSnacks.setText(null);
        cbCalories.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox();
        majTable();
        recherche_diet();

    }

}
