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
public class Tournoi {
      private int id;
        private String name;
        String dateT;
        private String cathegorie;
        private String Discription;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateT(String dateT) {
        this.dateT = dateT;
    }

    public void setCathegorie(String cathegorie) {
        this.cathegorie = cathegorie;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateT() {
        return dateT;
    }

    public String getCathegorie() {
        return cathegorie;
    }

    public String getDiscription() {
        return Discription;
    }

    public Tournoi() {
    }

    public Tournoi(String name, String dateT, String cathegorie, String Discription) {
        this.name = name;
        this.dateT = dateT;
        this.cathegorie = cathegorie;
        this.Discription = Discription;
    }

    public Tournoi(int id, String name, String dateT, String cathegorie, String Discription) {
        this.id = id;
        this.name = name;
        this.dateT = dateT;
        this.cathegorie = cathegorie;
        this.Discription = Discription;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", name=" + name + ", dateT=" + dateT + ", cathegorie=" + cathegorie + ", Discription=" + Discription + '}';
    }

    public Tournoi(String name, String cathegorie) {
        this.name = name;
        this.cathegorie = cathegorie;
    }
    }       

