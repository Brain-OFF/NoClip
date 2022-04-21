/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;




/**
 *
 * @author admin
 */
public class News {
    private int id,categorie_id ;
      private Date date; 
    private String  titre,text,jeu ; 

  
    

  

    
    

   
    

    

    

   
 

    

    

    public void setJeu(String jeu) {
        this.jeu = jeu;
    }

    public String getJeu() {
        return jeu;
    }

    public News(int id) {
        this.id = id;
    }

    public News() {
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setText(String text) {
        this.text = text;
    }

    

    public int getId() {
        return id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    
    public String getTitre() {
        return titre;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public News(int categorie_id, Date date, String titre, String text, String jeu) {
        this.categorie_id = categorie_id;
        this.date = date;
        this.titre = titre;
        this.text = text;
        this.jeu = jeu;
    }

    public News(int id, int categorie_id, Date date, String titre, String text, String jeu) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.date = date;
        this.titre = titre;
        this.text = text;
        this.jeu = jeu;
    }

    public News( String titre, Date date,String text, String jeu) {
        this.titre = titre;
        this.date = date;
        
        this.text = text;
        this.jeu = jeu;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", categorie_id=" + categorie_id + ", date=" + date + ", titre=" + titre + ", text=" + text + ", jeu=" + jeu + '}';
    }

    public News(int id, int categorie_id,  String titre, Date date,String text, String jeu) {
        this.id = id;
        this.categorie_id = categorie_id;
         this.titre = titre;
        this.date = date;
       
        this.text = text;
        this.jeu = jeu;
    }

    public News(int id, int categorie_id) {
        this.id = id;
        this.categorie_id = categorie_id;
    }

   
    

   

    


   

   

    
    
    
    
}
