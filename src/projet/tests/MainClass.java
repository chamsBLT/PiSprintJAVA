/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tests;

import javafx.collections.ObservableList;
import projet.entities.diet;
import projet.services.dietCRUD;
import projet.tools.MyConnection;

/**
 *
 * @author balti
 */
public class MainClass {
     public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        
        ObservableList<diet> listD;
        dietCRUD dcd = new dietCRUD();
        listD = dcd.showDiet();
        
        
    }
}
