/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Coach;
import Entities.Personne;
import Entities.Reservation;
import Services.CoachService;
import Services.ReservationService;
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
        
//        Coach c1 = new Coach(15,"Nouha","BEN SLIMEN","FPS");
//        Coach p2 = new Coach(40,"Foulen","BEN FOULEN","mmorpg");
        
        Reservation r1 = new Reservation(55,"2022-04-13 12:25:01.0","2022-06-13 12:25:01.0",0);
        
//        CoachService cs = new CoachService();
      ReservationService rs = new ReservationService();
//        try {
//          //  cs.ajouter(c1);
//        //cs.ajouter(p2);
//       // cs.Delete(54);
//       // cs.modifier(p2,39);
//            //ps.ajouter(p1);
//            //ps.ajouterr(p2);
//            //System.out.println("personne ajoutee");
//            System.out.println(cs.afficher().toString());
//            
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//
         try {
            rs.ajouter(r1);
        //cs.ajouter(p2);
       //rs.Delete(62);
       // rs.modifier(p2,39);
            //ps.ajouter(p1);
            //ps.ajouterr(p2);
            //System.out.println("personne ajoutee");
            System.out.println(rs.afficher().toString());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        






        
        
        
    }
    
}
