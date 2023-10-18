/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Utilisateur
 */
public class SceneBuilderUtil {
    public static void changeScene(ActionEvent event, String fxmlFile, String title){//fxmlFile:interface destination
        Parent root = null;
        try {  
             //root = FXMLLoader.load(getClass().getResource(fxmlFile));
          
            //FXMLLoader loader = new  FXMLLoader(SceneBuilderUtil.class.getResource(fxmlFile));
            //root = loader.load();
            root = FXMLLoader.load(SceneBuilderUtil.class.getResource(fxmlFile));
           
        } catch (IOException ex) {
            Logger.getLogger(SceneBuilderUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show(); 
    }
    
}
