/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.entities.ingredient;
import projet.services.ingredientCRUD;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ShowIngredientsController implements Initializable {

    @FXML
    private TextField tfIngId;
    @FXML
    private TextField tfIngName;
    @FXML
    private ComboBox<String> cbIngCategory;
    @FXML
    private Button btnDeleteIng;
    @FXML
    private Button btnEditIng;
    @FXML
    private Button btnAddIng;
    @FXML
    private Button btnFermer;
    @FXML
    private TableView<ingredient> tabIngredient;
    @FXML
    private TableColumn<ingredient, String> colIngName;
    @FXML
    private TableColumn<ingredient, String> colIngCategory;
    @FXML
    private TableColumn<ingredient, Integer> colID;

    ObservableList category_list = FXCollections.observableArrayList();
    ObservableList caloriesCategory_list = FXCollections.observableArrayList();
    ObservableList<ingredient> listI;

    int index = -1;
    @FXML
    private TableColumn<ingredient, String> colIngCaloriesCategory;
    @FXML
    private ComboBox<String> cbIngCalorieCategory;
    @FXML
    private Pane backgroundPane;
    @FXML
    private Pane fieldsPane;

    private void afficherComboBox1() {
        category_list.removeAll(category_list);
        String a = "breakfast";
        String b = "lunch";
        String c = "dinner";
        String d = "snacks";
        category_list.addAll(a, b, c, d);
        cbIngCategory.getItems().addAll(category_list);
    }

    private void afficherComboBox2() {
        caloriesCategory_list.removeAll(caloriesCategory_list);
        String a = "1600";
        String b = "1800";
        String c = "2000";
        String d = "2200";
        String e = "2400";
        String f = "2600";
        caloriesCategory_list.addAll(a, b, c, d, e, f);
        cbIngCalorieCategory.getItems().addAll(caloriesCategory_list);

    }

    @FXML
    private void SupprimerIngredient(ActionEvent event) {
        if (cbIngCalorieCategory.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ingredient");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un ingredient!");
            alert.show();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression Ingredients");
            alertWE2.setHeaderText("Voulez vous confirmer la suppression ?");
            alertWE2.setContentText(tfIngName.getText() + " va être supprimé");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
                String mID1 = tfIngId.getText();

                int mID = Integer.parseInt(mID1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Ingredient");
                alert.setHeaderText(null);
                alert.setContentText("Ingredient supprimé!");
                alert.show();

                ingredientCRUD icd = new ingredientCRUD();
                icd.deleteWorkout(mID);
                majTable();
            } else {
                return;
            }
        }
    }

    @FXML
    private void ModifierIngredient(ActionEvent event) {
        if (cbIngCalorieCategory.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ingredient");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un ingredient!");
            alert.show();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification Ingredients");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText(tfIngName.getText() + " va être modifié");
            Optional<ButtonType> result = alertWE2.showAndWait();

            if (result.get() == ButtonType.OK) {
                String mID1 = tfIngId.getText();
                String mName = tfIngName.getText();
                String mCategory = cbIngCategory.getValue();
                String mCalories_Category = cbIngCalorieCategory.getValue();

                int mID = Integer.parseInt(mID1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Ingredient");
                alert.setHeaderText(null);
                alert.setContentText("Ingredient modifié!");
                alert.show();

                ingredient i = new ingredient(mID, mName, mCategory, mCalories_Category);
                ingredientCRUD icd = new ingredientCRUD();
                icd.editIngredient(i);
                majTable();
            } else {
                return;
            }
        }
    }

    @FXML
    private void AjouterIngredient(ActionEvent event) {
        if (cbIngCalorieCategory.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ingredient");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        } else {
            String rName = tfIngName.getText();
            String rCategory = cbIngCategory.getValue();
            String rCalories_Category = cbIngCalorieCategory.getValue();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ingredient");
            alert.setHeaderText(null);
            alert.setContentText("Ingredient ajouté!");
            alert.show();

            ingredient i = new ingredient(rName, rCategory, rCalories_Category);
            ingredientCRUD icd = new ingredientCRUD();
            icd.addIngredient(i);
            majTable();
        }
    }

    public void majTable() {
        colID.setCellValueFactory(new PropertyValueFactory<ingredient, Integer>("id"));
        colIngName.setCellValueFactory(new PropertyValueFactory<ingredient, String>("name"));
        colIngCategory.setCellValueFactory(new PropertyValueFactory<ingredient, String>("category"));
        colIngCaloriesCategory.setCellValueFactory(new PropertyValueFactory<ingredient, String>("calories_category"));

        ingredientCRUD icd = new ingredientCRUD();
        listI = icd.showIngredient();
        tabIngredient.setItems(listI);
    }

    @FXML
    private void getSelectedIngredient(MouseEvent event) {
        index = tabIngredient.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfIngId.setText(colID.getCellData(index).toString());
        tfIngName.setText(colIngName.getCellData(index));
        cbIngCategory.setValue(colIngCategory.getCellData(index));
        cbIngCalorieCategory.setValue(colIngCaloriesCategory.getCellData(index));

    }

    @FXML
    private void FermerFenetre(ActionEvent event) {
        Stage stage = (Stage) btnFermer.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ResetValues(MouseEvent event) {
        tfIngId.setText(null);
        tfIngName.setText(null);
        cbIngCalorieCategory.setValue(null);
        cbIngCategory.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox1();
        afficherComboBox2();
        majTable();
    }
}
