/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author month
 */
public class Promos {
    int id;
    String name;
    Date datefin;
    float porc;

    public Promos() {
    }

    public Promos(int id, String name, Date datefin, float porc) {
        this.id = id;
        this.name = name;
        this.datefin = datefin;
        this.porc = porc;
    }

    public Promos(String name, Date datefin, float porc) {
        this.name = name;
        this.datefin = datefin;
        this.porc = porc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public float getPorc() {
        return porc;
    }

    public void setPorc(float porc) {
        this.porc = porc;
    }

    @Override
    public String toString() {
        return "Promos{" + "id=" + id + ", name=" + name + ", datefin=" + datefin + ", porc=" + porc + '}';
    }
    
}
