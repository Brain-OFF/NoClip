/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Personne;
import Entities.Tournoi;
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
        PersonneService ps = new PersonneService();
        try {
          ps.ajouter(p1);
          ps.Delete(13);
          ps.modifier(p1, 20);
            //ps.ajouterr(p2);
            //System.out.println("personne ajoutee");
            System.out.println(ps.afficher().toString());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
