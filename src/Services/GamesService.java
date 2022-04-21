/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Games;
import java.sql.SQLException;
import java.util.List;
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
public class GamesService implements IService<Games>{

    
       
     Connection con;
    Statement stm;
    PreparedStatement ste;
    public GamesService() {
        con = MyDB.getInstance().getCon();
    }
    @Override
    public void ajouter(Games t) throws SQLException {
       String req = "INSERT INTO `Games` (`name`, `descreption`,`prix`,`img`) VALUES ( '"
                + t.getName()+ "', '" + t.getDescreption()+ "', '" + t.getPrix()+ "', '" + t.getImg()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Games> afficher() throws SQLException {
     String req = "Select * from `Games`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Games> Games = new ArrayList<Games>();
        while(rst.next()){
            
            Games c = new Games(rst.getInt(1),rst.getFloat(4),rst.getString(3),rst.getString(2),rst.getString(5));
            Games.add(c);
            
    }
        return Games;
        
    }
        public List<Games> displayProduitService() {
        //nteger id, Integer id_categorie, String titre, String description, String type, float prix_unitaire
        List<Games> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM games ";
            ste = con.prepareStatement(requete);
            
              ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
               Games c = new Games(rst.getInt(1),rst.getFloat(4),rst.getString(3),rst.getString(2),rst.getString(5));
                myList.add(c);
            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void Delete(int id) throws SQLException {
    String req = "DELETE FROM Games WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void modifier(Games t, int id) throws SQLException {
String req = "UPDATE Games SET  name = ?, descreption = ?,  prix = ?, img = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
       pre.setString(1, t.getName());
        pre.setString(2, t.getDescreption());
         pre.setString(3, t.getPrix()+"");
          pre.setString(4, t.getImg());

        pre.executeUpdate();   
    }
    
}
