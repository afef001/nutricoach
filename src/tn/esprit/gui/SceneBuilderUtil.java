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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import nutricoach.entity.User;

/**
 *
 * @author Utilisateur
 */
public class SceneBuilderUtil {
    /*public static void changeScene(ActionEvent event, String fxmlFile, String title, User user, int width, int hight){//fxmlFile:interface destination
        Parent root = null;
        try {  
            if (user != null){ 
    
            FXMLLoader loader = new  FXMLLoader(SceneBuilderUtil.class.getResource(fxmlFile));
            root = loader.load();
            DetailUserController detailUserController = loader.getController();
            detailUserController.initialiserUser(user);
            root = FXMLLoader.load(SceneBuilderUtil.class.getResource(fxmlFile));
            }
            else{
                root = FXMLLoader.load(SceneBuilderUtil.class.getResource(fxmlFile));
                
            }
           
        } catch (IOException ex) {
            Logger.getLogger(SceneBuilderUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root,width, hight));
            primaryStage.show(); 
    }
    */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, User user, int width, int height) {
    try {
        FXMLLoader loader = new FXMLLoader(SceneBuilderUtil.class.getResource(fxmlFile));
        Parent root = loader.load();
        
        if (user != null) {
            DetailUserController detailUserController = loader.getController();
            detailUserController.initialiserUser(user);
        }

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    } catch (IOException ex) {
        Logger.getLogger(SceneBuilderUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    } 
}
