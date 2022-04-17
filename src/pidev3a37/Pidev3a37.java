/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Inscription_t;
import Entities.Personne;
import Entities.Tournoi;
import Services.Inscroption_TSer;
import Services.PersonneService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbook
 */
public class Pidev3a37 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Tournoi p1 = new Tournoi( "Desktop", "2022-03-07 15:45:00","Rpg", "Blue");
        Inscription_t incT = new  Inscription_t("di","xx@gmail;com",1,"bronze",40);

        PersonneService ps = new PersonneService();
        Inscroption_TSer inc1 = new Inscroption_TSer();
        try {
//          ps.ajouter(p1);
        //  ps.Delete(14);
//          ps.modifier(p1, 20);
            //ps.ajouterr(p2);
            //System.out.println("personne ajoutee");
            System.out.println(ps.afficher().toString());
            //inc1.ajouterInc(incT);
            //inc1.DeleteInc(60);
            // inc1.modifierInc(incT,55);
            //System.out.println(inc1.afficherInc().toString());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
