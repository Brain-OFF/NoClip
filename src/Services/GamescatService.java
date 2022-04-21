/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Gamescat;
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
 * @author month
 */
public class GamescatService implements IService<Gamescat>{
    
        Connection con;
    Statement stm;

    public GamescatService() {
        con = MyDB.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Gamescat t) throws SQLException {
  String req = "INSERT INTO `Gamescat` (`nom`, `description`) VALUES ( '"
                + t.getNom()+ "', '" + t.getDescription()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Gamescat> afficher() throws SQLException {
 String req = "Select * from `Gamescat`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Gamescat> Gamescat = new ArrayList<Gamescat>();
        while(rst.next()){
            
            Gamescat c = new Gamescat(rst.getInt(1),rst.getString(3),rst.getString(2));
            Gamescat.add(c);
            
    }
        return Gamescat;
        
    }

    @Override
    public void Delete(int id) throws SQLException {
 String req = "DELETE FROM Gamescat WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void modifier(Gamescat t, int id) throws SQLException {
String req = "UPDATE Gamescat SET  nom = ?, description = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
       pre.setString(1, t.getNom());
        pre.setString(2, t.getDescription());
        

        pre.executeUpdate();  
    }
    
}
