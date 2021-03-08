/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox1();
        afficherComboBox2();
        majTable();
    }    
    
    private void afficherComboBox1(){
        category_list.removeAll(category_list);
        String a="breakfast";
        String b="lunch";
        String c="dinner";
        String d="snacks";
        category_list.addAll(a,b,c,d);
        cbIngCategory.getItems().addAll(category_list);
    }
    
    private void afficherComboBox2(){
        caloriesCategory_list.removeAll(caloriesCategory_list);
        String a="1600";
        String b="1800";
        String c="2000";
        String d="2200";
        String e="2400";
        String f="2600";
        caloriesCategory_list.addAll(a,b,c,d,e,f);
        cbIngCalorieCategory.getItems().addAll(caloriesCategory_list);
    
    }

    @FXML
    private void SupprimerIngredient(ActionEvent event) {
        String mID1 = tfIngId.getText();
            
        int mID=Integer.parseInt(mID1);
        
        ingredientCRUD icd = new ingredientCRUD();
        icd.deleteWorkout(mID);
        majTable();
    }

    @FXML
    private void ModifierIngredient(ActionEvent event) {
        
            String mID1 = tfIngId.getText();
            String mName = tfIngName.getText(); 
            String mCategory = cbIngCategory.getValue();
            String mCalories_Category = cbIngCalorieCategory.getValue(); 
            
            int mID=Integer.parseInt(mID1);

            ingredient i = new ingredient(mID,mName,mCategory,mCalories_Category);
            ingredientCRUD icd = new ingredientCRUD();
            icd.editIngredient(i);
            majTable();
    }

    @FXML
    private void AjouterIngredient(ActionEvent event) {
            String rName = tfIngName.getText();
            String rCategory = cbIngCategory.getValue();
            String rCalories_Category = cbIngCalorieCategory.getValue(); 
            
            ingredient i = new ingredient(rName,rCategory,rCalories_Category);
            
            ingredientCRUD icd = new ingredientCRUD();
            icd.addIngredient(i);
            majTable();
    }
    
     public void majTable(){
        colID.setCellValueFactory(new PropertyValueFactory<ingredient ,Integer>("id"));
        colIngName.setCellValueFactory(new PropertyValueFactory<ingredient ,String>("name"));
        colIngCategory.setCellValueFactory(new PropertyValueFactory<ingredient ,String>("category"));
        colIngCaloriesCategory.setCellValueFactory(new PropertyValueFactory<ingredient ,String>("calories_category"));
        
        ingredientCRUD icd = new ingredientCRUD();
        listI = icd.showIngredient();
        tabIngredient.setItems(listI);
     }
     

    @FXML
    private void getSelectedIngredient(MouseEvent event) {
        index = tabIngredient.getSelectionModel().getSelectedIndex();
        if(index <=-1){       
            return;
        }   
        tfIngId.setText(colID.getCellData(index).toString());
        tfIngName.setText(colIngName.getCellData(index));
        cbIngCategory.setValue(colIngCategory.getCellData(index));
        cbIngCalorieCategory.setValue(colIngCaloriesCategory.getCellData(index));
     
    }
    
             @FXML
    private void FermerFenetre(ActionEvent event) {
    }
    }
    

