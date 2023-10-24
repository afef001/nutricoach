/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Utilisateur
 */
public class NewFXMain extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
      
        try {
             Parent root = FXMLLoader.load(getClass().
                getResource("Login.fxml"));
       
  
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
            
        } catch (Exception e) {
            System.out.println("tn.esprit.gui.NewFXMain.start()");
        }
     
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
