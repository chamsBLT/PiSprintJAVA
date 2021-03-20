/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.entities.workout;
import projet.tools.MyConnection;

/**
 *
 * @author balti
 */
public class workoutMETIER {

    public ObservableList<String> showWorkoutByMuscle(String muscle) {

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT name FROM workout WHERE body_part LIKE ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, muscle);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<workout> showWorkoutByMuscleDetails(String muscle) {

        ObservableList<workout> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT nbr_series, duree_serie, description FROM workout WHERE name LIKE ?";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, muscle);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new workout(rs.getInt("nbr_series"), rs.getInt("duree_serie"), rs.getString("description")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Integer> showWorkoutStats() {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT COUNT(*) AS n FROM workout GROUP BY body_part ORDER BY body_part";
            PreparedStatement pst
                    = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("n"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
