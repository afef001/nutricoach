/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricoach.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nutricoach.entity.Programme;
import nutricoach.entity.User;
import nutricoach.util.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author Utilisateur
 */
public class ServiceProgramme implements IServices<Programme> {

    Connection cnx;
    Statement ste;

    public ServiceProgramme() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Programme t) {

        String req = "INSERT INTO programmes( program_name, program_description, program_start_date, program_end_date, coach_id, qrcode_program ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        // generate QR_code for the current program
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType,
                ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);
        String path = t.getProgramName()+"_qrCode.png";
        String data = "Nom :"+t.getProgramName()+"_Description :"+t.getProgramDescription();
       
        
        try {
            String qrCode = createQR(data, path, charset, hashMap, 200, 200);
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, t.getProgramName());
            pre.setString(2, t.getProgramDescription());
            pre.setDate(3, t.getProgramStartDate());
            pre.setDate(4, t.getProgramEndDate());
            pre.setInt(5, t.getU().getUserId());
            pre.setString(6, qrCode);
           
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        catch  (Exception ex) {
            System.out.println(ex);
        }

    }

    
    
    private  String createQR(String data, String path,
                                String charset, Map hashMap,
                                int height, int width)
            throws WriterException, IOException
    {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
        //encode qrCode from image to base64 : l'idée ici est de sauvegarder le qr dans la base de donnée sous format String
        // donc on va convertir le qrCode(image) en une chaine de caractère(String)
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);
        return encodedString;
        //decode qrCode from base64 to image
        //String outputFileName = "outputFileName.png";
        //byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        //FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
    }
    
    @Override
    public void modifier(Programme t) {
        String sql = "UPDATE programmes SET program_name=?, program_description=?, program_start_date=?, program_end_date=?, coach_id=? WHERE program_id=?";
        try {
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setString(1, t.getProgramName());
            pre.setString(2, t.getProgramDescription());
            pre.setDate(3, t.getProgramStartDate());
            pre.setDate(4, t.getProgramEndDate());
            pre.setInt(5, t.getU().getUserId());
            pre.setInt(6, t.getProgramId());
           

            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void supprimer(int programId) {
        String req = "DELETE from programmes WHERE program_id=?";
        try {
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setInt(1, programId);

            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public Programme getOne(int programId) {
        String req = "SELECT * FROM programmes WHERE program_id=" +programId;
       
       Programme programme = new Programme();
      
        try {
            PreparedStatement pre = cnx.prepareStatement(req);
             User user = new User();

            //pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery(req);
            if(rs.next()){
            
            programme.setProgramId(rs.getInt("program_id"));
            programme.setProgramName(rs.getString("program_name"));
            programme.setProgramDescription(rs.getString("program_description"));
            programme.setProgramStartDate(rs.getDate("program_start_date"));
            programme.setProgramEndDate(rs.getDate("program_end_date"));
            user.setUserId(rs.getInt("coach_id"));
            programme.setU(user);
            
           
            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
   
        }
        return programme;
    }

    @Override
    public List<Programme> afficher() {
        String req = "SELECT * FROM programmes";
        ArrayList<Programme> programmes = new ArrayList();
        try {
            ste = cnx.createStatement();
            User user = new User();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
            Programme programme = new Programme();
            programme.setProgramId(rs.getInt("program_id"));
            programme.setProgramName(rs.getString("program_name"));
            programme.setProgramDescription(rs.getString("program_description"));
            programme.setProgramStartDate(rs.getDate("program_start_date"));
            programme.setProgramEndDate(rs.getDate("program_end_date"));
            programme.setQrcode_program(rs.getString("qrcode_program"));
            user.setUserId(rs.getInt("coach_id"));
            if(programme.getQrcode_program() !=null ){
                //decode qrCode from base64 to image
                String outputFileName = "outputFileName.png";
                byte[] decodedBytes = Base64.getDecoder().decode(programme.getQrcode_program());
                    try {
                        FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
                    } catch (IOException ex) {
                        Logger.getLogger(ServiceProgramme.class.getName()).log(Level.SEVERE, null, ex);
                    }
                Image image1 = new Image("file:"+outputFileName);
                programme.setImage(new SimpleObjectProperty<>(image1));
            }
            
            programme.setU(user);
           
            programmes.add(programme);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return programmes;
    }

}

