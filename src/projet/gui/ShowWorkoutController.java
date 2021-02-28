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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projet.entities.workout;
import projet.services.workoutCRUD;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class ShowWorkoutController implements Initializable {

    
    ObservableList<workout> listW;
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
    
    int index = -1; 
    ObservableList body_part_list = FXCollections.observableArrayList();
    
     
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
    }
      
    @FXML
    private void supprimerWorkout(ActionEvent event) {
       
            String mID1 = tfGwid.getText();
            
            int mID=Integer.parseInt(mID1);
        
            workoutCRUD wcd = new workoutCRUD();
            wcd.deleteWorkout(mID);
            majTable();
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficherComboBox();
         majTable();
    }
   
}