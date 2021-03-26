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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TableColumn<workout, Integer> colID;
    @FXML
    private TableColumn<workout, Integer> colNbr_series;
    @FXML
    private TableColumn<workout, Integer> colDuree_serie;
    @FXML
    private TableColumn<workout, String> colBody_part;
    @FXML
    private TableColumn<workout, String> colDescription;
    @FXML
    private TableColumn<workout, String> colName;
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
    @FXML
    private TextField rechercheWorkout;
    @FXML
    private ListView<Integer> listStats;
    @FXML
    private Button btnRetour;
    @FXML
    private ImageView BodyFrontView;
    @FXML
    private ImageView BodyBackView;
    @FXML
    private ImageView iChest;
    @FXML
    private ImageView iAbs;
    @FXML
    private ImageView iBack;
    @FXML
    private ImageView iLegs2;
    @FXML
    private ImageView iGlutes;
    @FXML
    private ImageView iLegs11;
    @FXML
    private ImageView iLegs12;
    @FXML
    private ImageView iArms11;
    @FXML
    private ImageView iShoulders11;
    @FXML
    private ImageView iShoulders21;
    @FXML
    private ImageView iArms21;
    @FXML
    private ImageView iShoulders12;
    @FXML
    private ImageView iArms12;
    @FXML
    private ImageView iArms22;
    @FXML
    private ImageView iShoulders22;
    @FXML
    private ListView<String> listStats2;

    ObservableList<String> listW2;
    ObservableList<Integer> listW1;
    ObservableList<workout> listW;
    ObservableList<workout> dataList;
    ObservableList body_part_list = FXCollections.observableArrayList();

    int index = -1;

    Image confirmation = new Image("/Graphics/checked.png");

    Image imArms11 = new Image("/Graphics/Arms11.png");
    Image imArms12 = new Image("/Graphics/Arms12.png");
    Image imArms21 = new Image("/Graphics/Arms21.png");
    Image imArms22 = new Image("/Graphics/Arms22.png");

    Image imShoulders11 = new Image("/Graphics/Shoulders11.png");
    Image imShoulders12 = new Image("/Graphics/Shoulders12.png");
    Image imShoulders21 = new Image("/Graphics/Shoulders21.png");
    Image imShoulders22 = new Image("/Graphics/Shoulders22.png");

    Image imAbs = new Image("/Graphics/Abs.png");

    Image imLegs11 = new Image("/Graphics/Legs11.png");
    Image imLegs12 = new Image("/Graphics/Legs12.png");
    Image imLegs2 = new Image("/Graphics/Legs2.png");

    Image imGlutes = new Image("/Graphics/Glutes.png");

    Image imBack = new Image("/Graphics/Back.png");

    Image imChest = new Image("/Graphics/Chest.png");
    @FXML
    private Pane backgroundPane;
    @FXML
    private Pane CRUDpane;
    @FXML
    private Pane statsPane;

    @FXML
    private void getSelectedWorkout(MouseEvent event) {

        index = table_workout.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        tfGwid.setText(colID.getCellData(index).toString());
        tfGwname.setText(colName.getCellData(index));
        tfGwnbr_series.setText(colNbr_series.getCellData(index).toString());
        tfGwduree_serie.setText(colDuree_serie.getCellData(index).toString());
        tfGwbody_part.setValue(colBody_part.getCellData(index));
        tfGwdesciption.setText(colDescription.getCellData(index));

    }

    private void afficherComboBox() {
        body_part_list.removeAll(body_part_list);
        String a = "arms";
        String b = "shoulders";
        String c = "back";
        String d = "legs";
        String e = "glutes";
        String f = "abdominals";
        String g = "chest";
        body_part_list.addAll(a, b, c, d, e, f, g);
        tfGwbody_part.getItems().addAll(body_part_list);

    }

    public void majTable() {

        colID.setCellValueFactory(new PropertyValueFactory<workout, Integer>("id"));
        colNbr_series.setCellValueFactory(new PropertyValueFactory<workout, Integer>("nbr_series"));
        colDuree_serie.setCellValueFactory(new PropertyValueFactory<workout, Integer>("duree_serie"));
        colBody_part.setCellValueFactory(new PropertyValueFactory<workout, String>("body_part"));
        colDescription.setCellValueFactory(new PropertyValueFactory<workout, String>("description"));
        colName.setCellValueFactory(new PropertyValueFactory<workout, String>("name"));

        workoutCRUD wcd = new workoutCRUD();
        listW = wcd.showWorkout();
        table_workout.setItems(listW);

    }

    @FXML
    private void ajouterWorkout(ActionEvent event) {

        if (tfGwname.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ajout Workout");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        } else {
            String rNbr_series1 = tfGwnbr_series.getText();
            String rDuree_serie1 = tfGwduree_serie.getText();
            String rBody_part = tfGwbody_part.getValue();
            String rDescription = tfGwdesciption.getText();
            String rName = tfGwname.getText();

            int rNbr_series = Integer.parseInt(rNbr_series1);
            int rDuree_serie = Integer.parseInt(rDuree_serie1);

            workout w = new workout(rNbr_series, rDuree_serie, rBody_part, rDescription, rName);
            workoutCRUD wcd = new workoutCRUD();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ajout Workout");
            alert.setHeaderText(null);
            alert.setContentText("Workout ajouté!");
            alert.show();

            wcd.addWorkout(w);
            majTable();
            recherche_workout();
            ShowStats();
        }
    }

    @FXML
    private void modifierWorkout(ActionEvent event) {
        if (tfGwid.getText() == null) {

            Alert alertWE1 = new Alert(Alert.AlertType.WARNING);
            alertWE1.initStyle(StageStyle.UTILITY);
            alertWE1.setTitle("Modification Workout");
            alertWE1.setHeaderText(null);
            alertWE1.setContentText("Veuillez selectionner un workout!");
            alertWE1.showAndWait();

        } else {
            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Modification Workout");
            alertWE2.setHeaderText("Voulez vous confirmer la modification ?");
            alertWE2.setContentText(tfGwname.getText() + " va être modifier");
            Optional<ButtonType> result = alertWE2.showAndWait();
            if (result.get() == ButtonType.OK) {

                String mID1 = tfGwid.getText();
                String mNbr_series1 = tfGwnbr_series.getText();
                String mDuree_serie1 = tfGwduree_serie.getText();
                String mBody_part = tfGwbody_part.getValue();
                String mDescription = tfGwdesciption.getText();
                String mName = tfGwname.getText();

                int mID = Integer.parseInt(mID1);
                int mNbr_series = Integer.parseInt(mNbr_series1);
                int mDuree_serie = Integer.parseInt(mDuree_serie1);

                Alert alertWE3 = new Alert(Alert.AlertType.INFORMATION);
                alertWE3.initStyle(StageStyle.UTILITY);
                alertWE3.setTitle("Modification Workout");
                alertWE3.setHeaderText(null);
                alertWE3.setContentText("Workout modifié!");
                alertWE3.show();

                workout w = new workout(mID, mNbr_series, mDuree_serie, mBody_part, mDescription, mName);
                workoutCRUD wcd = new workoutCRUD();
                wcd.editWorkout(w);
                majTable();
                recherche_workout();
                ShowStats();
            } else {
                return;
            }
        }
    }

    @FXML
    private void supprimerWorkout(ActionEvent event) {

        if (tfGwid.getText() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Suppression Workout");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner un workout!");
            alert.showAndWait();

        } else {

            Alert alertWE2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertWE2.setTitle("Suppression Workout");
            alertWE2.setHeaderText("Voulez vous confirmer la suppression ?");
            alertWE2.setContentText(tfGwname.getText() + " va être supprimer");
            Optional<ButtonType> result = alertWE2.showAndWait();
            if (result.get() == ButtonType.OK) {
                String mID1 = tfGwid.getText();

                int mID = Integer.parseInt(mID1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Suppression Workout");
                alert.setHeaderText(null);
                alert.setContentText("Workout supprimé!");
                alert.show();

                workoutCRUD wcd = new workoutCRUD();
                wcd.deleteWorkout(mID);

            } else {
                return;
            }
            majTable();
            recherche_workout();
            ShowStats();
        }
    }

    private void recherche_workout() {
        colID.setCellValueFactory(new PropertyValueFactory<workout, Integer>("id"));
        colNbr_series.setCellValueFactory(new PropertyValueFactory<workout, Integer>("nbr_series"));
        colDuree_serie.setCellValueFactory(new PropertyValueFactory<workout, Integer>("duree_serie"));
        colBody_part.setCellValueFactory(new PropertyValueFactory<workout, String>("body_part"));
        colDescription.setCellValueFactory(new PropertyValueFactory<workout, String>("description"));
        colName.setCellValueFactory(new PropertyValueFactory<workout, String>("name"));

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
                } else if (workout.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<workout> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_workout.comparatorProperty());
        table_workout.setItems(sortedData);
    }

    private void ShowStats() {
        workoutMETIER wm = new workoutMETIER();
        listW1 = wm.showWorkoutStatsNumbers();
        listW2 = wm.showWorkoutStatsNames();
        listStats.setItems(listW1);
        listStats2.setItems(listW2);
    }

    @FXML
    private void setBodyPartImages(ActionEvent event) {
        String selectedBp = tfGwbody_part.getValue();

        iArms11.setImage(null);
        iArms12.setImage(null);
        iArms21.setImage(null);
        iArms22.setImage(null);
        iShoulders11.setImage(null);
        iShoulders12.setImage(null);
        iShoulders21.setImage(null);
        iShoulders22.setImage(null);
        iAbs.setImage(null);
        iLegs11.setImage(null);
        iLegs12.setImage(null);
        iLegs2.setImage(null);
        iBack.setImage(null);
        iGlutes.setImage(null);
        iChest.setImage(null);

        switch (selectedBp) {
            case "arms":
                iArms11.setImage(imArms11);
                iArms12.setImage(imArms12);
                iArms21.setImage(imArms21);
                iArms22.setImage(imArms22);
                break;
            case "shoulders":
                iShoulders11.setImage(imShoulders11);
                iShoulders12.setImage(imShoulders12);
                iShoulders21.setImage(imShoulders21);
                iShoulders22.setImage(imShoulders22);
                break;
            case "abdominals":
                iAbs.setImage(imAbs);
                break;
            case "legs":
                iLegs11.setImage(imLegs11);
                iLegs12.setImage(imLegs12);
                iLegs2.setImage(imLegs2);
                break;
            case "back":
                iBack.setImage(imBack);
                break;
            case "glutes":
                iGlutes.setImage(imGlutes);
                break;
            case "chest":
                iChest.setImage(imChest);
                break;
            default:
                break;
        }
    }

    @FXML
    private void workoutmainTransition(ActionEvent event) throws IOException {
        Parent WcrudParent = FXMLLoader.load(getClass().getResource("HomePageWorkout.fxml"));
        Scene WcrudScene = new Scene(WcrudParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(WcrudScene);
        window.show();

    }

    @FXML
    private void resetValues(MouseEvent event) {
        tfGwid.setText(null);
        tfGwname.setText(null);
        tfGwnbr_series.setText(null);
        tfGwduree_serie.setText(null);
        tfGwbody_part.setValue(null);
        tfGwdesciption.setText(null);
        iArms11.setImage(null);
        iArms12.setImage(null);
        iArms21.setImage(null);
        iArms22.setImage(null);
        iShoulders11.setImage(null);
        iShoulders12.setImage(null);
        iShoulders21.setImage(null);
        iShoulders22.setImage(null);
        iAbs.setImage(null);
        iLegs11.setImage(null);
        iLegs12.setImage(null);
        iLegs2.setImage(null);
        iBack.setImage(null);
        iGlutes.setImage(null);
        iChest.setImage(null);
    }

    @FXML
    private void SetComboBoxLegs(MouseEvent event) {
        iLegs11.setImage(imLegs11);
        iLegs12.setImage(imLegs12);
        iLegs2.setImage(imLegs2);
        tfGwbody_part.setValue("legs");
    }

    @FXML
    private void SetComboBoxChest(MouseEvent event) {
        iChest.setImage(imChest);
        tfGwbody_part.setValue("chest");
    }

    @FXML
    private void SetComboBoxAbs(MouseEvent event) {
        iAbs.setImage(imAbs);
        tfGwbody_part.setValue("abdominals");
    }

    @FXML
    private void SetComboBoxArms(MouseEvent event) {
        iArms11.setImage(imArms11);
        iArms12.setImage(imArms12);
        iArms21.setImage(imArms21);
        iArms22.setImage(imArms22);
        tfGwbody_part.setValue("arms");
    }

    @FXML
    private void SetComboBoxShoulders(MouseEvent event) {
        iShoulders11.setImage(imShoulders11);
        iShoulders12.setImage(imShoulders12);
        iShoulders21.setImage(imShoulders21);
        iShoulders22.setImage(imShoulders22);
        tfGwbody_part.setValue("shoulders");
    }

    @FXML
    private void SetComboBoxBack(MouseEvent event) {
        iBack.setImage(imBack);
        tfGwbody_part.setValue("back");
    }

    @FXML
    private void SetComboBoxGlutes(MouseEvent event) {
        iGlutes.setImage(imGlutes);
        tfGwbody_part.setValue("glutes");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherComboBox();
        majTable();
        recherche_workout();
        ShowStats();

        tfGwdesciption.setWrapText(true);
    }

}
