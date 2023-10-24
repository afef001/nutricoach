/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nutricoach.entity.Role;
import nutricoach.entity.User;
import nutricoach.services.ServiceUser;
import nutricoach.util.DataSource;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class ListUserController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, Date> datedenaissance;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, Integer> numerotel;

    ObservableList<User> list = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<User, String> role;
    
    @FXML
    private Button btn_modifier;
    
    @FXML
    private Button btn_supprimer;
    @FXML
    private ImageView image;
    ServiceUser ps = new ServiceUser();
    @FXML
    private TextField search;
    @FXML
    private TextField edit_nom;
    @FXML
    private TextField edit_prenom;
    @FXML
    private TextField edit_email;
    @FXML
    private TextField edit_role;
   
   private ObservableList<User> userListData = FXCollections.observableArrayList();
    @FXML
    private Button searchuser;
   
   
     
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    private void updateuser(ActionEvent event) {
         User selectedUser = table.getSelectionModel().getSelectedItem();
       User user = new User(
              selectedUser.getUserId() , edit_nom.getText(), edit_prenom.getText(), selectedUser.getDateOfBirth()
               , edit_email.getText(), selectedUser.getPhoneNumber(), selectedUser.getUsername(), 
               selectedUser.getPassword(), selectedUser.getDiplome(), selectedUser.getSpecialite(), 
              Role.valueOf(edit_role.getText()));    
       ps.modifier(user);
       loadData();
       System.out.println("tn.esprit.gui.ListUserController.modifier()");
   }
        
    

    @FXML
    private void deleteuser(ActionEvent event) {
        User selectedUser = table.getSelectionModel().getSelectedItem();
        ps.supprimer(selectedUser.getUserId());
        loadData();
        
        
    }

    @FXML
    private void actualiser(ActionEvent event) {
         loadData();
    }
   private void loadData(){
        
        List<User> listUser=ps.afficher();
        //idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        prenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        numerotel.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPhoneNumber()).asObject());
        datedenaissance.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateOfBirth()));
        role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole().toString()));
      
        userListData.clear();//pour ne pas concatiner le tableau a chaque chargement
        userListData.addAll(listUser);
        table.setItems(userListData);
   } 
   
      

    @FXML
    private void search(ActionEvent event) {
        String filterName = search.getText();
        if (filterName != null && !filterName.isEmpty()) {
            List<User> listUser=ps.filterByName(filterName);
             userListData.clear();
             userListData.addAll(listUser);
             table.setItems(userListData);
        }
    }

    @FXML
    public User getSelectedItem(MouseEvent event) {//pour selection d'une ligne dans list
        
        User selectedUser = table.getSelectionModel().getSelectedItem();
        edit_nom.setText(selectedUser.getFirstName());
        edit_prenom.setText(selectedUser.getLastName());
        edit_email.setText(selectedUser.getEmail());
        edit_role.setText(selectedUser.getRole().toString());
        return selectedUser;
    }



}

