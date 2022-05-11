/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author month
 */
public class Gamescat {
    int id;
    String nom,description;

    public Gamescat() {
    }

    public Gamescat(String nom) {
        this.nom = nom;
    }

    public Gamescat(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Gamescat(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Gamescat(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Gamescat{" + "id=" + id + ", nom=" + nom + ", description=" + description + '}';
    }
    
}
