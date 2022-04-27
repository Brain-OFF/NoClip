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
public class History {
    int id,id_user;
    String Date,type;

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public String getDate() {
        return Date;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public History() {
    }

    public History(int id, int id_user, String Date, String type) {
        this.id = id;
        this.id_user = id_user;
        this.Date = Date;
        this.type = type;
    }

    @Override
    public String toString() {
        return "History{" + "id=" + id + ", id_user=" + id_user + ", Date=" + Date + ", type=" + type + '}';
    }
    
}
