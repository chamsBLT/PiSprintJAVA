/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tests;

import projet.services.workoutCRUD;
import projet.tools.MyConnection;

/**
 *
 * @author balti
 */
public class MainClass {
     public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        workoutCRUD wcd = new workoutCRUD();
    }
}
