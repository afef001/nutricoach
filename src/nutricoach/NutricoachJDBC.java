/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricoach;

import java.sql.Date;
import nutricoach.entity.Programme;
import nutricoach.entity.Role;
import nutricoach.entity.User;
import nutricoach.services.ServiceProgramme;
import nutricoach.services.ServiceUser;


/**
 *
 * @author Utilisateur
 */
public class NutricoachJDBC {

    public static void main(String[] args) {
        String str = "2023-10-10";
        String str1 = "2023-10-30";
        String str2 = "1990-09-16";
        Date startDate = Date.valueOf(str);//converting string into sql date
        Date endDate = Date.valueOf(str1);//converting string into sql date
        Date birthDate = Date.valueOf(str2);//converting string into sql date
//pour ajouter user
        User t1 = new User("yassine", "khadraoui", birthDate, "afefkhadraoui@esprit.tn", 53900900, "afef", "1234A", "liscence", "sport", Role.NUTRITIONNISTE);
        User t2 = new User("balkis", "khadraoui", birthDate, "afefkhadraoui@esprit.tn", 53900900, "afef", "1234A", "", "", Role.ADHERON);
        User t3 = new User("Isaak", "khadraoui", birthDate, "afefkhadraoui@esprit.tn", 53900900, "afef", "1234A", "liscence", "sport", Role.COACH);
        User t5 = new User("Siwar", "djebbi", birthDate, "afefkhadraoui@esprit.tn", 53900900, "afef", "1234A", "liscence", "sport", Role.ADHERON);               
//pour modifier user 
        User t6 = new User(12,"Samira", "djebbi", birthDate, "afefkhadraoui@esprit.tn", 53900900, "afef", "1234A", "", "", Role.ADHERON);   

 //pour ajouter programme       
        Programme p1 = new Programme("yoga", "ggggggggg", startDate, endDate, t6);
        Programme p2 = new Programme("gymnas", "ggggggggg", startDate, endDate, t6);
        Programme p3 = new Programme("aerobic", "hhhhh", startDate, endDate, t6);
        Programme p6 = new Programme("aerobic", "hhhhh", startDate, endDate, t6);
//pour modifier programme
        Programme p4 = new Programme(4,"aerobic", "hhhhh", startDate, endDate, t6);
        Programme p7 = new Programme(5,"fitness", "tttttt", startDate, endDate, t6);
       
        
        ServiceProgramme ss = new ServiceProgramme();
        ServiceUser sp = new ServiceUser();
        //sp.ajouter(t5);
        //sp.ajouter(t2);
       // sp.ajouter(t3);
        sp.modifier(t6);
       // sp.supprimer(1);
       // sp.getOne(10);
       // ss.ajouter(p1);
        // ss.ajouter(p2);
         // ss.ajouter(p6);
         //ss.supprimer(4);
         // ss.modifier(p10);
//afficher une seule valeur selon id       
        
        System.out.println(sp.getOne(15));
// afficher tout   
        
        System.out.println(ss.afficher());
        
        
        
    }                                                       
    
}
 