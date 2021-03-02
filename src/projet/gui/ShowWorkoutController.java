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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projet.entities.workout;
import projet.services.workoutCRUD;
import projet.services.workoutMETIER;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ShowWorkoutController implements Initializable {

    
    @FXML
    private TableView<workout> table_workout;
    @FXML
    private TableColumn<workout ,Integer> colID;
    @FXML
    private TableColumn<workout ,Integer> colNbr_series;
    @FXML
    private TableColumn<workout ,Integer> colDuree_serie;
    @FXML
    private TableColumn<workout ,String> colBody_part;
    @FXML
    private TableColumn<workout ,String> colDescription;
    @FXML
    private TableColumn<workout ,String> colName;
    @FXML
    private TextField tfGwid;
    @FXML
    private TextField tfGwname;
    @FXML
    private TextField tfGwnbr_series;
    @FXML
    private TextField tfGwduree_serie;
    @FXML
    private ComboBox<String> tfGwbody_part;
    @FXML
    private TextArea tfGwdesciption;
    @FXML
    private Button btnSuppWorkout;
    @FXML
    private Button btnModifWorkout;
    @FXML
    private Button btnAjtWorkout;
    
    ObservableList<Integer> listW1;
    ObservableList<workout> listW;
    ObservableList<workout> dataList;
    
    int index = -1; 
    ObservableList body_part_list = FXCollections.observableArrayList();
    @FXML
    private TextField rechercheWorkout;
    @FXML
    private ListView<Integer> listStats;
    @FXML
    private Button btnRetour;
    
     
    @FXML
    private void getSelectedWorkout(MouseEvent event) {
        
        index = table_workout.getSelectionModel().getSelectedIndex();
        if(index <=-1){       
            return;
        }
        
        tfGwid.setText(colID.getCellData(index).toString());
        tfGwname.setText(colName.getCellData(index));
        tfGwnbr_series.setText(colNbr_series.getCellData(index).toString());
        tfGwduree_serie.setText(colDuree_serie.getCellData(index).toString());
        tfGwbody_part.setValue(colBody_part.getCellData(index));
        tfGwdesciption.setText(colDescription.getCellData(index));
        
    }

    
    private void afficherComboBox(){
        body_part_list.removeAll(body_part_list);
        String a="arms";
        String b="shoulders";
        String c="back";
        String d="legs";
        String e="glutes";
        String f="abdominals";
        body_part_list.addAll(a,b,c,d,e,f);
        tfGwbody_part.getItems().addAll(body_part_list);
    
    }
    
    
    public void majTable(){
        
        colID.setCellValueFactory(new PropertyValueFactory<workout ,Integer>("id"));
        colNbr_series.setCellValueFactory(new PropertyValueFactory<workout ,Integer>("nbr_series"));
        colDuree_serie.setCellValueFactory(new PropertyValueFactory<workout ,Integer>("duree_serie"));
        colBody_part.setCellValueFactory(new PropertyValueFactory<workout ,String>("body_part"));
        colDescription.setCellValueFactory(new PropertyValueFactory<workout ,String>("description"));
        colName.setCellValueFactory(new PropertyValueFactory<workout ,String>("name"));
        
        workoutCRUD wcd = new workoutCRUD();
        listW = wcd.showWorkout();
        table_workout.setItems(listW);
    }
     
    
    @FXML
    private void ajouterWorkout(ActionEvent event) {
            String rId1 = tfGwid.getText();
            String rNbr_series1 = tfGwnbr_series.getText();
            String rDuree_serie1 = tfGwduree_serie.getText(); 
            String rBody_part = tfGwbody_part.getValue();
            String rDescription = tfGwdesciption.getText();
            String rName = tfGwname.getText();
            
            int rId=Integer.parseInt(rId1);
            int rNbr_series=Integer.parseInt(rNbr_series1);
            int rDuree_serie=Integer.parseInt(rDuree_serie1);
                 
            workout w = new workout(rId,rNbr_series,rDuree_serie,rBody_part,rDescription,rName);
            workoutCRUD wcd = new workoutCRUD();
            wcd.addWorkout(w);
            majTable();
            recherche_workout();
    }
    
    
    @FXML
    private void modifierWorkout(ActionEvent event) {
            
            String mID1 = tfGwid.getText();
            String mNbr_series1 = tfGwnbr_series.getText();
            String mDuree_serie1 = tfGwduree_serie.getText();
            String mBody_part = tfGwbody_part.getValue();
            String mDescription = tfGwdesciption.getText();
            String mName = tfGwname.getText(); 
            
            int mID=Integer.parseInt(mID1);
            int mNbr_series=Integer.parseInt(mNbr_series1);
            int mDuree_serie=Integer.parseInt(mDuree_serie1);
                 
            workout w = new workout(mID,mNbr_series,mDuree_serie,mBody_part,mDescription,mName);
            workoutCRUD wcd = new workoutCRUD();
            wcd.editWorkout(w);
            majTable();
            recherche_workout();
    }
    
    
    @FXML
    private void supprimerWorkout(ActionEvent event) {
       
            String mID1 = tfGwid.getText();
            
            int mID=Integer.parseInt(mID1);
        
            workoutCRUD wcd = new workoutCRUD();
            wcd.deleteWorkout(mID);
            majTable();
            recherche_workout();
    }
    
    
     private void recherche_workout() {   
            colID.setCellValueFactory(new PropertyValueFactory<workout ,Integer>("id"));
            colNbr_series.setCellValueFactory(new PropertyValueFactory<workout ,Integer>("nbr_series"));
            colDuree_serie.setCellValueFactory(new PropertyValueFactory<workout ,Integer>("duree_serie"));
            colBody_part.setCellValueFactory(new PropertyValueFactory<workout ,String>("body_part"));
            colDescription.setCellValueFactory(new PropertyValueFactory<workout ,String>("description"));
            colName.setCellValueFactory(new PropertyValueFactory<workout ,String>("name"));
        
             workoutCRUD wc = new workoutCRUD();
             dataList = wc.showWorkout();
             table_workout.setItems(dataList);
             
             FilteredList<workout> filteredData = new FilteredList<>(dataList, b -> true);  
             rechercheWorkout.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredData.setPredicate(workout -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }    
    
                   String lowerCaseFilter = newValue.toLowerCase();
    
                    if (workout.getBody_part().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; 
                    } 
                    else if (workout.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) 
                        return true;
                                        
                    else  
                       return false; 
                 });
                 });  
             SortedList<workout> sortedData = new SortedList<>(filteredData);  
             sortedData.comparatorProperty().bind(table_workout.comparatorProperty());  
             table_workout.setItems(sortedData);      
    }
    
     void ShowStats(){
         workoutMETIER wm = new workoutMETIER();
         listW1 = wm.showWorkoutStats();
         listStats.setItems(listW1);     
     
     }
     
      @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficherComboBox();
         majTable();
         recherche_workout();
         ShowStats();
    }

    @FXML
    private void workoutmainTransition(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePageWorkout.fxml"));
            Parent root = loader.load();
            btnRetour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }
   
}
