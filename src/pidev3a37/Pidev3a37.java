/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Coach;
import Entities.Games;
import Entities.Personne;
import Entities.Reservation;
import Services.CoachService;
import Services.GamesService;
import Services.ReservationService;
import Services.ServiceComImpl;
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
        
      
        ServiceComImpl gs = new ServiceComImpl();
 Games p2 = new Games(40,"Foulen","BEN FOULEN","mmorpg");
 Games p3 = new Games(99,"Foulen","BEN FOULEN","rout");
        /*try {
            gs.modifier(p3,28);
           // gs.ajouter(p2);
     System.out.println("********************************************");
     
            System.out.println(gs.afficher().toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/


         try {
     System.out.println("********************************************");
            System.out.println(gs.afficher().toString());
            
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    }
    
}
