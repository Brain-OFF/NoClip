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
public class games_user {
    int games_id,user_id;

    public games_user(int games_id, int user_id) {
        this.games_id = games_id;
        this.user_id = user_id;
    }

    public games_user() {
    }

    public int getGames_id() {
        return games_id;
    }

    public void setGames_id(int games_id) {
        this.games_id = games_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "games_user{" + "games_id=" + games_id + ", user_id=" + user_id + '}';
    }
    
}
