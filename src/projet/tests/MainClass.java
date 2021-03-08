/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tests;

import javafx.collections.ObservableList;
import projet.services.ingredientCRUD;
import projet.services.workoutCRUD;
import projet.tools.MyConnection;

/**
 *
 * @author balti
 */
public class MainClass {
     public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        ingredientCRUD icd = new ingredientCRUD();
        String test = "2000";
            ObservableList<String> autoCompleteBreakfast = icd.showIngredientDinner(test);
        
         System.out.println(autoCompleteBreakfast);
    }
}
