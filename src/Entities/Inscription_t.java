/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Taha
 */
public class Inscription_t {
    private int id;
    private String user_name;	
      private String email;
     private int  etat;
  private String rank;
    private int tournoi_id;

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public int isEtat() {
        return etat;
    }

    public String getRank() {
        return rank;
    }

    public int getTournoi_id() {
        return tournoi_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setTournoi_id(int tournoi_id) {
        this.tournoi_id = tournoi_id;
    }

    @Override
    public String toString() {
        return "Inscription_t{" + "id=" + id + ", user_name=" + user_name + ", email=" + email + ", etat=" + etat + ", rank=" + rank + ", tournoi_id=" + tournoi_id + '}';
    }

    public Inscription_t(int id, String user_name, String email, int etat, String rank, int tournoi_id) {
        this.id = id;
        this.user_name = user_name;
        this.email = email;
        this.etat = etat;
        this.rank = rank;
        this.tournoi_id = tournoi_id;
    }

    public Inscription_t( String user_name, String email, int etat, String rank) {
        this.user_name = user_name;
        this.email = email;
        this.etat = etat;
        this.rank = rank;
    }
    

    public Inscription_t(String user_name, String email, int etat, String rank, int tournoi_id) {
        this.user_name = user_name;
        this.email = email;
        if(etat==0){
        this.etat = etat;
        }else if(etat==1){
        this.etat = etat;
        }
        else
            this.etat=0;
        this.rank = rank;
        this.tournoi_id = tournoi_id;
    }
    
    
    
    
}
