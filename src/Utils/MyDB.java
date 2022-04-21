/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Cart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author macbook
 */
public class MyDB {
 
    final String url ="jdbc:mysql://localhost:3306/pidev";
    final String user="root";
    final String password="";
    
    static MyDB instance ;
    private Connection con;
    
    private MyDB(){
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connection established");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static MyDB getInstance(){
    if (instance == null)
        instance = new MyDB();
    return instance;
    }

    public Connection getCon() {
        return con;
    }
    
    
    
    
    
    public static ObservableList<Cart> getDataCart(){
        Connection conn = MyDB.instance.getCon();
        ObservableList<Cart> list1 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from cart");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list1.add(new Cart(rs.getInt("id") ,rs.getInt("quantity"),rs.getInt("produit_id")));               
            }
        } catch (Exception e) {
        }
        return list1;
    }
    
}
