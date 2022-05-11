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
public class News_user {
    int News_id,user_id;

    public News_user(int News_id, int user_id) {
        this.News_id = News_id;
        this.user_id = user_id;
    }

    public News_user() {
    }

    public int getNews_id() {
        return News_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setNews_id(int News_id) {
        this.News_id = News_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

  

    @Override
    public String toString() {
        return "games_user{" + "games_id=" + News_id + ", user_id=" + user_id + '}';
    }
    
}
