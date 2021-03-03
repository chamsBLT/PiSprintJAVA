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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projet.entities.diet;
import projet.services.dietCRUD;
import projet.services.workoutCRUD;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ShowDietController implements Initializable {
 
    @FXML
    private TextField tfId;
    @FXML
    private TextArea tfBreakfast;
    @FXML
    private TextArea tfLunch;
    @FXML
    private TextArea tfDinner;
    @FXML
    private TextArea tfSnacks;
    @FXML
    private ComboBox<String> cbCalories;
    @FXML
    private TableView<diet> table_diet;
    @FXML
    private TableColumn<diet ,Integer> colId;
    @FXML
    private TableColumn<diet ,String> colCalories;
    @FXML
    private TableColumn<diet ,String> colBreakfast;
    @FXML
    private TableColumn<diet ,String> colLunch;
    @FXML
    private TableColumn<diet ,String> colDinner;
    @FXML
    private TableColumn<diet ,String> colSnacks;
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
        if(index <=-1){
        
            return;
        }
        
        tfId.setText(colId.getCellData(index).toString());
        tfBreakfast.setText(colBreakfast.getCellData(index));
        tfLunch.setText(colLunch.getCellData(index));
        tfDinner.setText(colDinner.getCellData(index));
        tfSnacks.setText(colSnacks.getCellData(index));
        cbCalories.setValue(colCalories.getCellData(index));
        
    }

    
    private void afficherComboBox(){
        calories_list.removeAll(calories_list);
        String a="1600";
        String b="1800";
        String c="2000";
        String d="2200";
        String e="2400";
        String f="2600";
        calories_list.addAll(a,b,c,d,e,f);
        cbCalories.getItems().addAll(calories_list);
    
    }
    
    
    public void majTable(){
        
        colId.setCellValueFactory(new PropertyValueFactory<diet ,Integer>("id"));
        colBreakfast.setCellValueFactory(new PropertyValueFactory<diet , String>("breakfast"));
        colLunch.setCellValueFactory(new PropertyValueFactory<diet , String>("lunch"));
        colDinner.setCellValueFactory(new PropertyValueFactory<diet , String>("dinner"));
        colSnacks.setCellValueFactory(new PropertyValueFactory<diet , String>("snacks"));
        colCalories.setCellValueFactory(new PropertyValueFactory<diet , String>("calories"));
        
        dietCRUD dcd = new dietCRUD();
        listD = dcd.showDiet();
        table_diet.setItems(listD);
    }   

    
    @FXML
    private void ajouterDiet(ActionEvent event) {
            
            String rBreakfast = tfBreakfast.getText();
            String rLunch = tfLunch.getText(); 
            String rDinner = tfDinner.getText();
            String rSnacks = tfSnacks.getText();
            String rCalories = cbCalories.getValue();
            
                 
            diet d = new diet(rBreakfast,rLunch,rDinner,rSnacks,rCalories);
            dietCRUD dcd = new dietCRUD();
            dcd.addDiet(d);
            majTable();
            recherche_diet();
    }

    
    @FXML
    private void modifierDiet(ActionEvent event) {
        
            String rId1 = tfId.getText();
            String rBreakfast = tfBreakfast.getText();
            String rLunch = tfLunch.getText(); 
            String rDinner = tfDinner.getText();
            String rSnacks = tfSnacks.getText();
            String rCalories = cbCalories.getValue();
            
            int rId=Integer.parseInt(rId1);
                 
            diet d = new diet(rId,rBreakfast,rLunch,rDinner,rSnacks,rCalories);
            dietCRUD dcd = new dietCRUD();
            dcd.editDiet(d);
            majTable();
            recherche_diet();
    }

    
    @FXML
    private void supprimerDiet(ActionEvent event) {
        
            String mID1 = tfId.getText();
            
            int mID=Integer.parseInt(mID1);
        
            dietCRUD dcd = new dietCRUD();
            dcd.deleteDiet(mID);
            majTable();
            recherche_diet();
    }
    
      void recherche_diet() {   
        colId.setCellValueFactory(new PropertyValueFactory<diet ,Integer>("id"));
        colBreakfast.setCellValueFactory(new PropertyValueFactory<diet , String>("breakfast"));
        colLunch.setCellValueFactory(new PropertyValueFactory<diet , String>("lunch"));
        colDinner.setCellValueFactory(new PropertyValueFactory<diet , String>("dinner"));
        colSnacks.setCellValueFactory(new PropertyValueFactory<diet , String>("snacks"));
        colCalories.setCellValueFactory(new PropertyValueFactory<diet , String>("calories"));
        
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
                    } 
                    else if (diet.getLunch().toLowerCase().indexOf(lowerCaseFilter) != -1){ 
                        return true;
                    }
                    else if (diet.getDinner().toLowerCase().indexOf(lowerCaseFilter) != -1){ 
                        return true;
                    }
                    else if (diet.getSnacks().toLowerCase().indexOf(lowerCaseFilter) != -1){ 
                        return true;
                    }
                    else if (diet.getCalories().toLowerCase().indexOf(lowerCaseFilter) != -1) 
                        return true;
                    
                    else  
                       return false; 
                 });
                 });  
             SortedList<diet> sortedData = new SortedList<>(filteredData);  
             sortedData.comparatorProperty().bind(table_diet.comparatorProperty());  
             table_diet.setItems(sortedData);      
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox();
        majTable();    
        recherche_diet(); 
    } 

    @FXML
    private void mainTransition(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
