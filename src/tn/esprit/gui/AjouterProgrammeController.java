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
import javafx.scene.control.TableView;
import nutricoach.services.ServiceProgramme;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class AjouterProgrammeController implements Initializable {

    @FXML
    private TableView<?> tableprog;
    ServiceProgramme serviceProgramme = new ServiceProgramme();
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    };  

    
    @FXML
    private void addprog(ActionEvent event) {
      //  User user = getSelectedItem;
        
    }

    @FXML
    private void updateprog(ActionEvent event) {
    }

    @FXML
    private void deleteprog(ActionEvent event) {
    }
    
}
