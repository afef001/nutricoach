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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nutricoach.entity.User;
import nutricoach.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class VerificationCompteController implements Initializable {

    @FXML
    private TextField txt_code_verification;
    @FXML
    private Button btn_valider_compte;
    ServiceUser serviceUser = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerCompte(ActionEvent event) {
        User user = new User();
        user.setEnabled(true);
        if (serviceUser.isCompteVerified(txt_code_verification.getText()) ){
           serviceUser.modifierStatusCompte(user, txt_code_verification.getText());
           SceneBuilderUtil.changeScene(event, "Login.fxml", "validation", null, 600, 400 );
    }
        else{
            SceneBuilderUtil.showAlert("code" , "Le code est incorrect");
        }
    }    
}
    
