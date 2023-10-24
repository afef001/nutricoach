/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.sun.javafx.scene.SceneHelper;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nutricoach.entity.Programme;
import nutricoach.entity.User;
import nutricoach.services.ServiceProgramme;

/**
 * FXML Controller class
 *
 * @author Utilisateur
 */
public class AjouterProgrammeController implements Initializable {

    @FXML
    private TableView<Programme> tableprog;
    ServiceProgramme serviceProgramme = new ServiceProgramme();
    @FXML
    private Label txt_id_coach;
    @FXML
    private TextField txt_nom_prg;

    @FXML
    private DatePicker txt_datedebut_prg;
    @FXML
    private DatePicker txt_datefin_prg;
    @FXML
    private TextField txt_description_prg;
    @FXML
    private Button btn_ajouter_prg;
    @FXML
    private Button btn_supprimer_prg;
    @FXML
    private TableColumn<Programme, String> table_nom_prg;
    @FXML
    private TableColumn<Programme, String> table_description_prg;
    @FXML
    private TableColumn<Programme, Date> table_datedebut_prg;
    @FXML
    private TableColumn<Programme, Date> table_datefin_prg;
    private ObservableList<Programme> programmeListData = FXCollections.observableArrayList();
    @FXML
    //private TableColumn<Programme, Image> qr_code_program ;
    TableColumn<Programme, Image> qr_code_program = new TableColumn<>("QR Code");
    @FXML
    private Button navigateToCompte;

    /**
     * * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }

    ;  

    
    @FXML
    private void addprog(ActionEvent event) {
        String nomprg = txt_nom_prg.getText();
        String descriptionprg = txt_description_prg.getText();
        LocalDate localDate = txt_datedebut_prg.getValue();
        LocalDate localDate1 = txt_datefin_prg.getValue();
        Date dateDebutPrg = Date.valueOf(localDate);
        Date dateFinPrg = Date.valueOf(localDate1);
        // récupération de l'utilisateur connecté à partir de sessionManager (à utiliser par toute l'équipe) 
        User user = LoginController.session.getCurrentUser();
        System.out.println("le user connecté est : " + user.toString());
        Programme programme = new Programme(nomprg, descriptionprg, dateDebutPrg, dateFinPrg, user);
        serviceProgramme.ajouter(programme);
        loadData();

        //  User user = getSelectedIte
    }

    @FXML
    private void deleteprog(ActionEvent event) {
        Programme selectedProgramme = tableprog.getSelectionModel().getSelectedItem();
        serviceProgramme.supprimer(selectedProgramme.getProgramId());
        loadData();
    }

    public void getCoachId(String coachId) {
        txt_id_coach.setText(coachId);
        txt_id_coach.setVisible(false);

    }

    private void loadData() {

        List<Programme> listProgramme = serviceProgramme.afficher();
        //idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        table_nom_prg.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProgramName()));
        table_description_prg.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProgramDescription()));
        table_datedebut_prg.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProgramStartDate()));
        table_datefin_prg.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProgramEndDate()));
        //qr_code_program.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQrcode_program()));

        qr_code_program.setCellValueFactory(cellData -> {
            Image qrCodeImage = cellData.getValue().getImage().get();
            return new SimpleObjectProperty<>(qrCodeImage);
        });

        qr_code_program.setCellFactory(column -> {
            return new TableCell<Programme, Image>() {
                private ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(item);
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);
                        setGraphic(imageView);
                    }
                }
            };
        });

        programmeListData.clear();//pour ne pas concatiner le tableau a chaque chargement
        programmeListData.addAll(listProgramme);

        tableprog.setItems(programmeListData);

    }

    @FXML
    private void navigateToCompte(ActionEvent event) {
        User user = LoginController.session.getCurrentUser();
         SceneBuilderUtil.changeScene(event, "DetailUser.fxml", "Ajout compte", user, 600, 500);
        
    
    }

}
