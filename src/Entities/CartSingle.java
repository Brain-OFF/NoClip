/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author WALID
 */
public final class CartSingle {
    private Commande commande;
    private double prix;
    private final static CartSingle INSTANCE=new CartSingle();
    public static CartSingle get_instace()
    {
        return INSTANCE;
    }
    public void setPrix(double p)
    {
        this.prix=p;
    }
    public double getPrix()
    {
        return this.prix;
    }
    public void setCom (Commande C)
    {
        this.commande=C;
    }
    public Commande setCom() 
    {
    return this.commande;
    }
    
  
}
