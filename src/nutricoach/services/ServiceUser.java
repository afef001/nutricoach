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
import nutricoach.entity.Role;
import nutricoach.entity.User;
import nutricoach.util.DataSource;

/**
 *
 * @author Utilisateur
 */
public class ServiceUser implements IServices<User> {

    Connection cnx;
    Statement ste;//execution des req sql statiq

    public ServiceUser() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(User t) {

        String req = "INSERT INTO users( first_name, last_name, date_of_birth, email, phone_number, username, password, diplome, specialite, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pre = cnx.prepareStatement(req);//execution des req sql Parametre
            pre.setString(1, t.getFirstName());
            pre.setString(2, t.getLastName());
            pre.setDate(3, t.getDateOfBirth());
            pre.setString(4, t.getEmail());
            pre.setInt(5, t.getPhoneNumber());
            pre.setString(6, t.getUsername());
            pre.setString(7, t.getPassword());
            pre.setString(8, t.getDiplome());
            pre.setString(9, t.getSpecialite());
            pre.setString(10, t.getRole().toString());
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void modifier(User t) {
        String sql = "UPDATE users SET first_name=?, last_name=?, date_of_birth=?, email=?, phone_number=?, username=?, password=?, diplome=?, specialite=?, role=? WHERE user_id=?";
        try {
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setString(1, t.getFirstName());
            pre.setString(2, t.getLastName());
            pre.setDate(3, t.getDateOfBirth());
            pre.setString(4, t.getEmail());
            pre.setInt(5, t.getPhoneNumber());
            pre.setString(6, t.getUsername());
            pre.setString(7, t.getPassword());
            pre.setString(8, t.getDiplome());
            pre.setString(9, t.getSpecialite());
            pre.setString(10, t.getRole().toString());
            pre.setInt(11, t.getUserId());

            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void supprimer(int userId) {
        String req = "DELETE from users WHERE user_id=?";
        try {
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setInt(1, userId);

            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public User getOne(int userId) {
        String req = "SELECT * FROM users WHERE user_id=" +userId;

        User user = new User();
        try {
            PreparedStatement pre = cnx.prepareStatement(req);

            //pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery(req);
            if(rs.next()){

            user.setUserId(rs.getInt("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDateOfBirth(rs.getDate("date_of_birth"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getInt("phone_number"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setDiplome(rs.getString("diplome"));
            user.setRole(Role.valueOf(rs.getString("role")));//convertir string a un objet de type enum Role
            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return user;
    }

    @Override
    public List<User> afficher() {
        String req = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getInt("phone_number"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDiplome(rs.getString("diplome"));
                user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));//convertir string a un objet de type enum Role

                users.add(user);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return users;
    }
    public List<User> filterByName(String name) {
        String req = "SELECT * FROM users WHERE first_name=" + "'"+name+"'";

        User user = new User();
        ArrayList<User> users = new ArrayList();
        try {
            PreparedStatement pre = cnx.prepareStatement(req);

            //pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery(req);
            while(rs.next()){

            user.setUserId(rs.getInt("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDateOfBirth(rs.getDate("date_of_birth"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getInt("phone_number"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setDiplome(rs.getString("diplome"));
            user.setRole(Role.valueOf(rs.getString("role")));//convertir string a un objet de type enum Role
            users.add(user);

            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
        return users;
        
}
    public boolean getLogin(String login, String password){
             String req = "SELECT * FROM users WHERE username='" +login +"' and password ='"+password+"'";

  
        try {
            PreparedStatement pre = cnx.prepareStatement(req);

            //pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery(req);
            if(rs.next()){
                return true;

            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            

        }
        return false;
}
}
