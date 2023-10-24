/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nutricoach.entity.Role;
import nutricoach.entity.User;
import nutricoach.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class DetailUserController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_num_tel;
    @FXML
    private TextField txt_role;
    @FXML
    private TextField txt_diplome;
    @FXML
    private TextField txt_specialite;
    @FXML
    private DatePicker txt_date_birth;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_modifier_profil;
    @FXML
    private Button btn_ajouter_programme;
    @FXML
    private Button btn_deconnecter;
    @FXML
    private Label label_diplome;
    @FXML
    private Label label_specialite;
    @FXML
    private Label YASSINE;
    @FXML
    private Label txt_id;
    @FXML
    private Label txt_username;
    @FXML
    private Button navigateToProgramme;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

    @FXML
    private void modifierProfil(ActionEvent event) {
        String firstName = txt_nom.getText();
        String lastName = txt_prenom.getText();
        String Email = txt_email.getText();
        int phoneNumber = Integer.parseInt(txt_num_tel.getText());
        int UserId = Integer.parseInt(txt_id.getText());
        String Username = txt_username.getText();
        String Password = txt_password.getText();
        String Specialite = txt_specialite.getText();
        String Diplome = txt_diplome.getText();
        
        LocalDate localDate = txt_date_birth.getValue();
        Date birthDate = Date.valueOf(localDate);
        
        //String selectedRole = txt_role.getSelectionModel().getSelectedItem().toString();
        Role role = Role.valueOf(txt_role.getText().toUpperCase());
        ServiceUser serviceUser = new ServiceUser();
        User user = new User(
        UserId, firstName, lastName, birthDate, Email, phoneNumber, Username,Password,Diplome, Specialite, role);  
        System.out.println(user);
        serviceUser.modifier(user);
        System.out.println("tn.esprit.gui.ListUserController.modifier()");
   }
        
    

    @FXML
    private void ajouterProgramme(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneBuilderUtil.class.getResource("AjouterProgramme.fxml"));
            Parent root = loader.load();
            AjouterProgrammeController ajouterProgrammeController = loader.getController();
            ajouterProgrammeController.getCoachId(txt_id.getText());
            
            
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("programme");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DetailUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
          SceneBuilderUtil.changeScene(event, "Login.fxml", "validation", null, 600, 400 );
    }
   
    
    public void initialiserUser(User user) {
        System.out.println(user);
        txt_nom.setText(user.getFirstName());
        txt_prenom.setText(user.getLastName());
        txt_email.setText(user.getEmail());
        txt_num_tel.setText(String.valueOf(user.getPhoneNumber()));
        txt_role.setText(user.getRole().toString());
        LocalDate localDate = user.getDateOfBirth().toLocalDate();
        txt_date_birth.setValue(localDate);
        txt_diplome.setText(user.getDiplome());
        txt_specialite.setText(user.getSpecialite());
        txt_password.setText(user.getPassword());
        txt_username.setText(user.getUsername());
        txt_id.setText(String.valueOf(user.getUserId()));
        
        manupulatedSelection();
        
           
         
    }
    private void manupulatedSelection() {
          txt_id.setVisible(false);
          txt_username.setVisible(false);
        
        if (txt_role.getText()!= null) {
            txt_role.setDisable(true);
           if (!txt_role.getText().equals("ADHERON")) {
                txt_specialite.setVisible(true);
                txt_diplome.setVisible(true);
                   label_specialite.setVisible(true);
                   label_diplome.setVisible(true);
                   btn_ajouter_programme.setVisible(true);
       
        
            } else {
                txt_specialite.setVisible(false);
                txt_diplome.setVisible(false);
                label_specialite.setVisible(false);
                label_diplome.setVisible(false);
                 btn_ajouter_programme.setVisible(false);
                
        
            }
        }
        
    }

    @FXML
    private void navigateToProgramme(ActionEvent event) {
        ajouterProgramme(event);
    }
    
    
  
}

