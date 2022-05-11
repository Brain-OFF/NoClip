/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Utilisateur
 */
public class Panier {
    
        int id;
        int idProduit;
        int idUser;

    public Panier( int idProduit, int idUser) {
        this.idProduit = idProduit;
        this.idUser = idUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Panier() {
    }
    
    
    
    
    
    
    
      

    
        
    

    
}
