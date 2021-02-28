/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projet.entities.workout;
import projet.services.workoutMETIER;

/**
 * FXML Controller class
 *
 * @author balti
 */
public class HomePageWorkoutController implements Initializable {

    ObservableList<workout> listW;
    @FXML
    private TableView<workout> table_arms;
    @FXML
    private TableView<workout> table_Legs;
    @FXML
    private TableView<workout> table_Glutes;
    @FXML
    private TableView<workout> table_Shoulders;
    @FXML
    private TableView<workout> table_Back;
    @FXML
    private TableView<workout> table_Abdominals;
    @FXML
    private TextField tfHnbr_series;
    @FXML
    private TextField tfHduree_serie;
    @FXML
    private TextArea tfHdesciption;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btwTansitionGRUDW;
    
    @FXML
    private TableColumn<workout, String> colNameArms;
    @FXML
    private TableColumn<workout, Integer> colNbr_series;
    @FXML
    private TableColumn<workout, Integer> colDuree_serie;
    @FXML
    private TableColumn<workout, String> colDescription;
    
    
    @FXML
    private TableColumn<workout, String> colNameLegs;
    @FXML
    private TableColumn<workout, String> colNameGlutes;
    @FXML
    private TableColumn<workout, String> colNameShoulders;
    @FXML
    private TableColumn<workout, String> colNameBack;
    @FXML
    private TableColumn<workout, String> colNameAbdominals;

    int index = -1;
    
    public void majTableArms(){
        String muscle = "arms";  
        colNameArms.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNbr_series.setCellValueFactory(new PropertyValueFactory<>("nbr_series"));
        colDuree_serie.setCellValueFactory(new PropertyValueFactory<>("duree_serie"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        workoutMETIER wm = new workoutMETIER();
        listW = wm.showWorkoutByMuscle(muscle);
        table_arms.setItems(listW);
    }
        
    public void majTableShoulders(){
        String muscle = "shoulders";  
        colNameShoulders.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        workoutMETIER wm = new workoutMETIER();
        listW = wm.showWorkoutByMuscle(muscle);
        table_Shoulders.setItems(listW);
    }
        
    public void majTableBack(){
        String muscle = "back";  
        colNameBack.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        workoutMETIER wm = new workoutMETIER();
        listW = wm.showWorkoutByMuscle(muscle);
        table_Back.setItems(listW);
    }
        
    public void majTableLegs(){
        String muscle = "legs";  
        colNameLegs.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        workoutMETIER wm = new workoutMETIER();
        listW = wm.showWorkoutByMuscle(muscle);
        table_Legs.setItems(listW);
    }
   
    public void majTableGlutes(){
        String muscle = "glutes";  
        colNameGlutes.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        workoutMETIER wm = new workoutMETIER();
        listW = wm.showWorkoutByMuscle(muscle);
        table_Glutes.setItems(listW);
    }
        
    public void majTableAbdominals(){
        String muscle = "abdominals";  
        colNameAbdominals.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        workoutMETIER wm = new workoutMETIER();
        listW = wm.showWorkoutByMuscle(muscle);
        table_Abdominals.setItems(listW);
    }
        
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        majTableArms();
        majTableShoulders();
        majTableBack();
        majTableGlutes();
        majTableAbdominals();
    }    

    @FXML
    private void showDetails(MouseEvent event) {
        index = table_arms.getSelectionModel().getSelectedIndex();
        if(index <=-1){
            return;
        }
        
        tfHnbr_series.setText(colNbr_series.getCellData(index).toString());
        tfHduree_serie.setText(colDuree_serie.getCellData(index).toString());
        tfHdesciption.setText(colDescription.getCellData(index));
        
    }

    
}
