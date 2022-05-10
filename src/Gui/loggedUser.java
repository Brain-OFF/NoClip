/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.*;

/**
 *
 * @author WALID
 */
public final class loggedUser {
    private User user;
    private final static loggedUser INSTANCE=new loggedUser();
    public static loggedUser get_instace()
    {
        return INSTANCE;
    }
    public void setUser (User u)
    {
        this.user=u;
    }
    public User getUser() 
    {
    return this.user;
    }

  
}
