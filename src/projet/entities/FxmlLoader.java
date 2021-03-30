/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.entities;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author balti
 */
public class FxmlLoader {
    private Pane view;
    
    
    public Pane getPage(URL fileURL) throws IOException{
         view = new FXMLLoader().load(fileURL);
        return view;
    
    }
    
}
