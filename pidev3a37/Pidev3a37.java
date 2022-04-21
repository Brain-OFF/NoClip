/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import Entities.Categorie;
import Entities.News;
import Entities.Personne;
import Services.CategorieService;
import Services.NewsService;
import Services.PersonneService;
import java.sql.Date;
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
        
//          News c1 = new News("lololo",Date.valueOf("2022-04-19"),"FPShhhvvvvvv","hhhfifavvvvv");
        Categorie p2 = new Categorie(1,"imed ");
//        NewsService ps = new NewsService();
        CategorieService cs =new CategorieService();
        try {
            cs.ajouter(p2);
            //ps.ajouter(p1);
            //ps.ajouterr(p2);
            //System.out.println("personne ajoutee");
            System.out.println(cs.afficher().toString());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
