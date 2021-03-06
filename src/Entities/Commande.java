/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author poste 1
 */
public class Commande {
    
     public static Commande instance;
    private int id;
    private int idpro;
    private int id_user_id;
     private String nom;
     private String prenom;
     private String adresse;
     private double total;
  //   private ArrayList<Product> quantites ;
     private String numtelephone;
    private String prixpro;
    private int etat;
    private String email;
    private String detailspro;
  //  private Product pc;
       private  ArrayList<Commande> c;
   //      private  ArrayList<Product> p;

    public Commande() {
         c = new ArrayList<Commande>();
    }

    public Commande(ArrayList<Commande> c) {
        this.c = c;
    }

    
  
    public Commande(int id, String nom, String prenom, String adressecomplet, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adressecomplet;
        this.numtelephone = telephone;
        this.email = email;
    }
    public Commande(int id, String nom, String prenom, String adressecomplet, String telephone, String email, double total) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adressecomplet;
        this.numtelephone = telephone;
        this.email = email;
        this.total=total;
    }

     public Commande( String nom, String prenom, String adressecomplet, String telephone, String email) {

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adressecomplet;
        this.numtelephone = telephone;
        this.email = email;
    }

    public Commande(int id, String nom, String prenom, String adressecomplet, int id_user_id, String telephone, String email, double total) {
        this.id = id;
         this.nom = nom;
        this.prenom = prenom;
        this.adresse = adressecomplet;
        this.id_user_id = id_user_id;
           this.numtelephone = telephone;
        this.email = email;
        this.total = total;
    
    }

    public Commande( String nom, String prenom, String adressecomplet, String telephone, double total, int etat, String email) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adressecomplet;
        this.total = total;
        this.numtelephone = telephone;
        this.etat = etat;
        this.email = email;
    }

    
    
    
   

   
   

    public ArrayList<Commande> getC() {
        return c;
    }

    



    public int getId() {
        return id;
    }

    public int getIdpro() {
        return idpro;
    }

    public int getId_user_id() {
        return id_user_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

   

    public double getTotal() {
        return total;
    }

   

    public String getPrixpro() {
        return prixpro;
    }

    public int getEtat() {
        return etat;
    }

    public String getEmail() {
        return email;
    }

    public String getDetailspro() {
        return detailspro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public void setId_user_id(int id_user_id) {
        this.id_user_id = id_user_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    

    public void setTotal(double total) {
        this.total = total;
    }

   
   
   

    public void setPrixpro(String prixpro) {
        this.prixpro = prixpro;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDetailspro(String detailspro) {
        this.detailspro = detailspro;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumtelephone() {
        return numtelephone;
    }

    public Commande(String adresse, String numtelephone) {
        this.adresse = adresse;
        this.numtelephone = numtelephone;
    }

    

   
   

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idpro=" + idpro + ", id_user_id=" + id_user_id + ", nom=" + nom + ", prenom=" + prenom + ", adressecomplet=" + adresse + ", total=" + total + ", telephone=" + numtelephone + ", prixpro=" + prixpro + ", etat=" + etat + ", email=" + email + ", detailspro=" + detailspro + ", c=" + c + '}';
    }

   


    
    

 
  
    
}
