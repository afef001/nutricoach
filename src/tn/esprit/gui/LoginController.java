/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nutricoach.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_sign_up;
    ServiceUser serviceuser = new ServiceUser();
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        
        if (serviceuser.getLogin(username.getText(), password.getText()))
       
        {
            SceneBuilderUtil.changeScene(event, "ListUser.fxml", "Ajout compte");
            
        }
        else {
          
             Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("I have account");
           alert.setHeaderText("I haven't an account");
           alert.showAndWait();
        }
          
    }

    @FXML
    private void signup(ActionEvent event) {
        //SceneBuilderUtil sceneBuilderUtil = new  SceneBuilderUtil();
       SceneBuilderUtil.changeScene(event, "AjouterUser.fxml", "Ajout compte");
    }
    
}
