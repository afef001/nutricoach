/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nutricoach.entity.Role;
import nutricoach.entity.User;
import nutricoach.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class AjouterUserController implements Initializable {
    

    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField specialite;
    @FXML
    private TextField diplome;
    @FXML
    private ComboBox role;
    @FXML
    private DatePicker date_of_birth;
    @FXML
    private Button btn_add_user;
    @FXML
    private Button btn_exit;
    @FXML
    private Label label_diplome;
    @FXML
    private Label label_specialite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("ADHERON", "COACH", "NUTRITIONNISTE");
         role.setItems(list);
        // role.setItems(comboBoxValues);
        specialite.setVisible(false);
        diplome.setVisible(false);
        label_specialite.setVisible(false);
        label_diplome.setVisible(false);
        
    }    

    @FXML
    private void adduser(ActionEvent event) {
        String firstName = first_name.getText();
        String lastName = last_name.getText();
        String Email = email.getText();
        int phoneNumber = Integer.parseInt(phone_number.getText());
        String Username = username.getText();
        String Password = password.getText();
        String Specialite = specialite.getText();
        String Diplome = diplome.getText();
        
        LocalDate localDate = date_of_birth.getValue();
        Date birthDate = Date.valueOf(localDate);
        
        String selectedRole = role.getSelectionModel().getSelectedItem().toString();
        Role role = Role.valueOf(selectedRole.toUpperCase());
     
       
        ServiceUser ps = new ServiceUser();
        User p = new User(firstName, lastName, birthDate, Email, phoneNumber, Username, Password, Diplome, Specialite, role);
        ps.ajouter(p);
        SceneBuilderUtil.changeScene(event, "ListUser.fxml", "liste des utilisateurs");
    }

    @FXML
    private void exit(ActionEvent event) {
        
      Platform.exit();
    }

    @FXML
    private void manupulatedSelection(ActionEvent event) {
        String selectedValue = role.getSelectionModel().getSelectedItem().toString();
        if (selectedValue != null) {
           if (!selectedValue.equals("ADHERON")) {
                specialite.setVisible(true);
                diplome.setVisible(true);
                   label_specialite.setVisible(true);
                   label_diplome.setVisible(true);
        
            } else {
                specialite.setVisible(false);
                diplome.setVisible(false);
                label_specialite.setVisible(false);
                label_diplome.setVisible(false);
        
            }
        }
        
    }
    
}
