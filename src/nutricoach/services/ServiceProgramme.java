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
import nutricoach.entity.Programme;
import nutricoach.entity.User;
import nutricoach.util.DataSource;

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

        String req = "INSERT INTO programmes( program_name, program_description, program_start_date, program_end_date, coach_id) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, t.getProgramName());
            pre.setString(2, t.getProgramDescription());
            pre.setDate(3, t.getProgramStartDate());
            pre.setDate(4, t.getProgramEndDate());
            pre.setInt(5, t.getU().getUserId());
           
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

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
            user.setUserId(rs.getInt("coach_id"));
            programme.setU(user);
           
                programmes.add(programme);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return programmes;
    }

}

