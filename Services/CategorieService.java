/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.News;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategorieService implements IService<Categorie> {
     Connection con;
    Statement stm;

    public CategorieService() {
        con = MyDB.getInstance().getCon();
    }


    @Override
    public void ajouter(Categorie t) throws SQLException {
        String req = "INSERT INTO `categorie` ( `nom`) VALUES ( '"
                + t.getNom()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Categorie> afficher() throws SQLException {
        String req = "Select * from `categorie`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Categorie> categorie = new ArrayList<Categorie>();
        while(rst.next()){
            
            Categorie c = new Categorie(rst.getInt(1),rst.getString(2));
            categorie.add(c);
            
    }
        return categorie;
    }

    @Override
    public void Delete(int id) throws SQLException {
        String req = "DELETE FROM categorie WHERE `id` = "+ id;
         stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void modifier(Categorie t, int id) throws SQLException {
      try {
            Statement statement= con.createStatement();
            String requete="update categorie set id='"+t.getId()+"' ,  nom='"+t.getNom()+"' ";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            
  e.printStackTrace();
        } 

    
    
    
    
    
    
    
    }
    }

    

