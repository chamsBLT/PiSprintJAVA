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
public class workoutCRUD {
    
    public void addWorkout(workout w){
         try {
             String requete ="INSERT INTO workout (id,nbr_series,duree_serie,body_part,description,name)" + "VALUES (?,?,?,?,?,?)";
             PreparedStatement pst =
                     new MyConnection().cn.prepareStatement(requete);
                     pst.setInt(1, w.getId());
                     pst.setInt(2, w.getNbr_series());
                     pst.setInt(3, w.getDuree_serie());
                     pst.setString(4, w.getBody_part());
                     pst.setString(5, w.getDescription());
                     pst.setString(6, w.getName());
                     pst.executeUpdate();
                     
                     System.out.println("Workout ajoutée!");
         } catch (SQLException ex) {
             Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    
    
    public ObservableList<workout> showWorkout(){
        ObservableList<workout> list = FXCollections.observableArrayList();
        try {
            String requete ="SELECT * FROM workout";
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                list.add(new workout(rs.getInt("id"),rs.getInt("nbr_series"), rs.getInt("duree_serie"), rs.getString("body_part"), rs.getString("description"), rs.getString("name")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public void editWorkout(workout w){
        try {

            String requete ="UPDATE workout SET id= ? , nbr_series= ? ,duree_serie= ? ,body_part= ? ,description= ? ,name= ? where id = ? ";
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);   
                    pst.setInt(1, w.getId());
                    pst.setInt(2, w.getNbr_series());
                    pst.setInt(3, w.getDuree_serie());
                    pst.setString(4, w.getBody_part());
                    pst.setString(5, w.getDescription());
                    pst.setString(6, w.getName());
                    pst.setInt(7, w.getId());
                    pst.executeUpdate();
                    
                 System.out.println("Workout modifiée!");
                     
        } catch (SQLException ex) {
            Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public void deleteWorkout(int id){
        try {
            String requete ="DELETE FROM workout WHERE id = ?";
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();           
            
        } catch (SQLException ex) {
            Logger.getLogger(workoutCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
