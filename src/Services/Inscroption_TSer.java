/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Inscription_t;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Taha
 */
public class Inscroption_TSer implements IncService<Inscription_t>{
    
      Connection con;
    Statement stm;

    public Inscroption_TSer() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouterInc(Inscription_t t,int Uid) throws SQLException {
          String req = "INSERT INTO `inscription_t`(`tournoi_id`, `user_name`, `email`, `etat`, `rank`,`user_id`) VALUES ( '"+ t.getTournoi_id()+ "', '" + t.getUser_name() + "', '" 
        + t.getEmail() + "', '" + t.isEtat() +"', '" + t.getRank()+ "','"+Uid+"') ";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public void DeleteInc(int id) throws SQLException {
         String req = "DELETE FROM inscription_t WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void modifierInc(Inscription_t t, int id) throws SQLException {
         String req = "UPDATE `inscription_t` SET `tournoi_id`= ? ,`user_name`= ? ,`email`= ? ,`etat`= ? ,`rank`= ?  WHERE  " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
        pre.setInt(1,t.getTournoi_id());
        pre.setString(2, t.getUser_name());
        pre.setString(3, t.getEmail());
        pre.setInt(4, t.isEtat());
        pre.setString(5, t.getRank());


        pre.executeUpdate();   
        
        
    }

    @Override
    public List<Inscription_t> afficherInc() throws SQLException {
          String req = "SELECT * FROM `inscription_t`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Inscription_t> personnes = new ArrayList<Inscription_t>();
        while(rst.next()){
            
            Inscription_t p = new Inscription_t(rst.getInt(1),rst.getString("user_name"),rst.getString("email"),rst.getInt(2),rst.getString("rank"),rst.getInt(3));
            personnes.add(p);
        }
        return personnes;
    }

        
}
