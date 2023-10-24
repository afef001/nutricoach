/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.sun.javafx.scene.SceneHelper;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import nutricoach.entity.Role;
import nutricoach.entity.SessionManager;
import nutricoach.entity.User;
import nutricoach.services.ServiceUser;
import nutricoach.util.MailService;
import org.apache.commons.lang.RandomStringUtils;

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
    @FXML
    private Button btn_motdepass_oublier;
    @FXML
    private Button btn_reset_passwd;
    @FXML
    private TextField txt_username_forgot;
    @FXML
    private TextField txt_email_forgot;
    @FXML
    private Label label_username;
    @FXML
    private Label label_password;
    @FXML
    private Button btn_go_connect;
    
    public static SessionManager session;
    @FXML
    private Label label_navigation_programme;
    @FXML
    private Label label_navigation_compte;
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_reset_passwd.setVisible(false);
        txt_username_forgot.setVisible(false);
        txt_email_forgot.setVisible(false);
        btn_go_connect.setVisible(false);
       
    }    

    @FXML
    private void login(ActionEvent event) {
        
        try {
            User user = serviceuser.findUserByUsername(username.getText());
            System.out.println(user);
            System.out.println(serviceuser.authenticateUser(username.getText(), password.getText()));
            System.out.println(user.checkPassword(password.getText()));
            System.out.println(password.getText());
            if (serviceuser.authenticateUser(username.getText(), password.getText())){
                if(user.getRole().equals(Role.ADMIN)){//if (user.getrole=Admin
                    SceneBuilderUtil.changeScene(event, "ListUser.fxml", "Ajout compte", null, 600, 500);
                } else{ 
                    SceneBuilderUtil.changeScene(event, "DetailUser.fxml", "Ajout compte", user, 600, 500);
                }
            }
            else {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("I have account");
                alert.setHeaderText("I haven't an account");
                alert.showAndWait();
            }
            // save logged user into sessionManager
            session = new SessionManager();
            session.setCurrentUser(user);
            session.setLoginDate(new Date());
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    @FXML
    private void signup(ActionEvent event) {
        //SceneBuilderUtil sceneBuilderUtil = new  SceneBuilderUtil();
       SceneBuilderUtil.changeScene(event, "AjouterUser.fxml", "Ajout compte", null, 800, 600 );
    }

    @FXML
    private void resetPassword(ActionEvent event) {
        username.setVisible(false);
        password.setVisible (false);
        btn_login.setVisible(false);
        label_username.setVisible(false);
        label_password.setVisible (false);
        btn_reset_passwd.setVisible(true);
        txt_username_forgot.setVisible(true);
        txt_email_forgot.setVisible(true);
        btn_go_connect.setVisible(true);
       
    }

    @FXML
    private void renitialiserPassword(ActionEvent event) {
         String generatedpassword = RandomStringUtils.random(7, true, true);//logn caractète numoro et lettre 7, true, true
         String message = "Votre nouveau mot de passe est: " + generatedpassword;
         serviceuser.resetPassword(txt_username_forgot.getText(),generatedpassword );
         MailService.sendActivationEmail(txt_email_forgot.getText(), message);
         
    }

    @FXML
    private void goConnect(ActionEvent event) {
        username.setVisible(true);
        password.setVisible (true);
        btn_login.setVisible(true);
        label_username.setVisible(true);
        label_password.setVisible (true);
        btn_reset_passwd.setVisible(false);
        txt_username_forgot.setVisible(false);
        txt_email_forgot.setVisible(false);
        btn_go_connect.setVisible(false);
       
        
    }

   
    /* private void navigationToProgramme(MouseEvent event) {
        try {
            
       
        Parent page = FXMLLoader.load(getClass().getResource("DetailUser"));
                Scsene scene = new Scene (page);
                Stage stage = (Stage) (Node) event.getSource()).SceneHelper.getScene().getWindow();
                Stage.setScene(scene);
                Stage.setReszable(false);
                Stage.show();
    } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }

    @FXML
    private void navigateToCompte(MouseEvent event) {
    }
    */

    }
