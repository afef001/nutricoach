/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricoach.entity;

import java.sql.Date;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;



/**
 *
 * @author Utilisateur
 */
public class Programme {
    private int programId;
    private String programName; 
    private String programDescription; 
    private Date programStartDate;
    private Date programEndDate;
    private String qrcode_program;
    private SimpleObjectProperty<Image> image;

    public SimpleObjectProperty<Image> getImage() {
        return image;
    }

    public void setImage(SimpleObjectProperty<Image> image) {
        this.image = image;
    }

    private User U;
//many to one:user user

    public Programme() {
    }
    
    public Programme(int programId, String programName, String programDescription, Date programStartDate, Date programEndDate, User user) {
        this.programId = programId;
        this.programName = programName;
        this.programDescription = programDescription;
        this.programStartDate = programStartDate;
        this.programEndDate = programEndDate;
        this.U = user;
        
    }

    public Programme(String programName, String programDescription, Date programStartDate, Date programEndDate, User user) {
        this.programName = programName;
        this.programDescription = programDescription;
        this.programStartDate = programStartDate;
        this.programEndDate = programEndDate;
        this.U = user;
 
    }

    public String getQrcode_program() {
        return qrcode_program;
    }

    public void setQrcode_program(String qrcode_program) {
        this.qrcode_program = qrcode_program;
    }
public User getU() {
        return U;
    }

    public void setU(User U) {
        this.U = U;
    }
    
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public Date getProgramStartDate() {
        return programStartDate;
    }

    public void setProgramStartDate(Date programStartDate) {
        this.programStartDate = programStartDate;
    }

    public Date getProgramEndDate() {
        return programEndDate;
    }

    public void setProgramEndDate(Date programEndDate) {
        this.programEndDate = programEndDate;
    }

    @Override
    public String toString() {
        return "Programme{" + "programId=" + programId + ", programName=" + programName + ", programDescription=" + programDescription + ", programStartDate=" + programStartDate + ", programEndDate=" + programEndDate + ", U=" + U + '}';
    }

}
