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
public class Games {
    int id,promos_id;
    float prix;
    String name,descreption,img;

    public Games() {
    }

    public Games(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Games(int id, float prix, String name, String descreption, String img) {
        this.id = id;
        this.prix = prix;
        this.name = name;
        this.descreption = descreption;
        this.img = img;
    }

    public Games(float prix, String name, String descreption, String img) {
        this.prix = prix;
        this.name = name;
        this.descreption = descreption;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPromos_id() {
        return promos_id;
    }

    public void setPromos_id(int promos_id) {
        this.promos_id = promos_id;
    }
    

    @Override
    public String toString() {
        return "Games{" + "id=" + id + ", prix=" + prix + ", name=" + name + ", descreption=" + descreption + ", img=" + img + '}';
    }
    
    
    
}
