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
        
      
        GamesService gs = new GamesService();

  String nchoix="oo";
         try{
           
            int tchoix = Integer.parseInt(nchoix);
             System.out.println(gs.cherchejeu(tchoix));
            
        } catch (NumberFormatException e) {
           System.out.println(gs.cherchejeu(nchoix));
        }
        
    }
    
}
