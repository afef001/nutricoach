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
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
import nutricoach.util.MailService;
import org.apache.commons.lang.RandomStringUtils;

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
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "\\d{8}";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addSaisiControl();
       btn_add_user.disableProperty().bind(validationBinding());
        
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
     
        String generatedString = RandomStringUtils.random(5, true, true);
        ServiceUser ps = new ServiceUser();
        User p = new User(firstName, lastName, birthDate, Email, phoneNumber, Username, Password, Diplome, Specialite, role);
        
        p.setVerificationCode(generatedString);
        p.setEnabled(false);
        ps.ajouter(p);
        MailService.sendActivationEmail(p.getEmail(), generatedString);
        SceneBuilderUtil.changeScene(event, "VerificationCompte.fxml", "validation", null, 600 ,400);
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
   
 
        
 
    private void addSaisiControl() {
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Pattern.matches(EMAIL_REGEX, newValue)) {
                email.setStyle("-fx-border-color: red;");
            } else {
                email.setStyle("");
            }
        });

        phone_number.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Pattern.matches(PHONE_REGEX, newValue)) {
                phone_number.setStyle("-fx-border-color: red;");
            }else{
               phone_number.setStyle("");
            }
        });
    }
    
   private BooleanBinding validationBinding() {
        return Bindings.createBooleanBinding(
                () -> !Pattern.matches(EMAIL_REGEX, email.getText()) || !Pattern.matches(PHONE_REGEX, phone_number.getText()),
                email.textProperty(),
                phone_number.textProperty()
        );
    }     
}
    
