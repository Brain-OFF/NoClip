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
    int id;
    float prix;
    String name,descreption,img;

    public Games() {
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

    public Games(String string, float aFloat) {
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

    @Override
    public String toString() {
        return "Games{" + "id=" + id + ", prix=" + prix + ", name=" + name + ", descreption=" + descreption + ", img=" + img + '}';
    }
    
    
    
}
