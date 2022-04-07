/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Personne;
import Entities.Tournoi;
import Utils.MyDB;
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
 * @author macbook
 */
public class PersonneService implements IService<Tournoi> {

    Connection con;
    Statement stm;

    public PersonneService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouter(Tournoi t) throws SQLException {
        String req = "INSERT INTO `tournoi`( `nom`, `date`, `cathegorie`, `discription`)VALUES ( '"+ t.getName()+ "', '" + t.getDateT() + "', '" 
        + t.getCathegorie() + "', '" + t.getDiscription() + "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<Tournoi> afficher() throws SQLException {
        String req = "SELECT * FROM `tournoi`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Tournoi> personnes = new ArrayList<Tournoi>();
        while(rst.next()){
            
            Tournoi p = new Tournoi(rst.getInt(1),rst.getString("nom"),rst.getString("date"),rst.getString("cathegorie"),rst.getString("discription"));
            personnes.add(p);
            
        
        }
        return personnes;
        
    }

    @Override
    public void Delete(int id) throws SQLException {
  String req = "DELETE FROM tournoi WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    
    Connection connexion;


    @Override
    public void modifier(Tournoi t, int id) throws SQLException {
  String req = "UPDATE tournoi SET  nom = ?, date = ?,  cathegorie = ?, discription = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
        pre.setString(1, t.getName());
        pre.setString(2, t.getDateT());
        pre.setString(3, t.getCathegorie());
        pre.setString(4, t.getDiscription());

        pre.executeUpdate();   
    }

 

}
