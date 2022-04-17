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
public class User {
    int id,points;
    boolean is_verified;
    String username,email,password,bio,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(int points, boolean is_verified, String username, String email, String password, String bio) {
        this.points = points;
        this.is_verified = is_verified;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
    }
    

    public User(int id,String username, String email, String password,int points, String bio,  boolean is_verified ) {
        this.id = id;
        this.points = points;
        this.is_verified = is_verified;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
    }

    public User() {
        
    }



    @Override
    public String toString() {
        return "User{" + "id=" + id + ", points=" + points + ", is_verified=" + is_verified + ", username=" + username + ", email=" + email + ", password=" + password + ", bio=" + bio + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
