/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Personne;
import Entities.User;
import Services.UserService;
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
        
        User u = new User(7, false, "tazarkour", "jaouaniaaw@gmail.com", "wzeazeazeaz", "hehe");
        UserService us = new UserService();
        try {
          //us.ajouter(u);
          //us.Delete(7);
          //us.modifier(u, 7);
            //ps.ajouterr(p2);
            //System.out.println("personne ajoutee");
            //System.out.println(us.afficher().toString());
            us.Login("bingus","walid123456");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
