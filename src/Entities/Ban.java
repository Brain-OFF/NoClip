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
public class Ban {
    String date;
    int id,user_id;
    String reason;
    String Username;

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getUsername() {
        return Username;
    }

    public Ban(String date, int id, int user_id, String reason) {
        this.date = date;
        this.id = id;
        this.user_id = user_id;
        this.reason = reason;
    }

    public Ban(String date, int user_id, String reason) {
        this.date = date;
        this.user_id = user_id;
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getReason() {
        return reason;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Ban{" + "date=" + date + ", id=" + id + ", user_id=" + user_id + ", reason=" + reason + '}';
    }
    
    
}
