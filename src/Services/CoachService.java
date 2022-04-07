/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Coach;
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
 * @author xDrais
 */
public class CoachService implements IService<Coach> {
    
    
     Connection con;
    Statement stm;

    public CoachService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouter(Coach t) throws SQLException {
        String req = "INSERT INTO `coach` (`name`, `lastname`,`rank`,`categorie`) VALUES ( '"
                + t.getName()+ "', '" + t.getLastname()+ "', '" + t.getRank()+ "', '" + t.getCategorie()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

 

    @Override
    public List<Coach> afficher() throws SQLException {
         String req = "Select * from `coach`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Coach> coach = new ArrayList<Coach>();
        while(rst.next()){
            
            Coach c = new Coach(rst.getInt(1),rst.getInt(4),rst.getString(3),rst.getString(3),rst.getString(5));
            coach.add(c);
            
    }
        return coach;
        
    }
    
    public void Delete(int id) throws SQLException {
  String req = "DELETE FROM coach WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    


    public void modifier(Coach t, int id) throws SQLException {
  String req = "UPDATE coach SET  name = ?, lastname = ?,  rank = ?, categorie = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
       pre.setString(1, t.getName());
        pre.setString(2, t.getLastname());
         pre.setString(3, t.getRank()+"");
          pre.setString(4, t.getCategorie());

        pre.executeUpdate();   
    }

  
    
    
}
