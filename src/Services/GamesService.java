/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Games;
import Entities.User;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author month
 */
public class GamesService implements IaService<Games>{

    
       
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
     
    public void ajouterjoint(String a)  {
      
         Connection con = MyDB.getInstance().getCon();
    try {
         int k=0;
         int c=0;
        
        ResultSet rs = con.createStatement().executeQuery("Select Max(id) id from Games ");
        ResultSet rs1 = con.createStatement().executeQuery("Select * from Gamescat WHERE nom = '"+ a +"'");
        while(rs.next()){
        System.out.println(rs.getInt("id"));
         k =rs.getInt("id");
       
        }
         while(rs1.next()){  c=  rs1.getInt(1);}
         
       String req = "INSERT INTO `games_gamescat` (`games_id`, `gamescat_id`) VALUES ( '"
                +k+  "', '"+ c+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }
public void ajouterfav(int g,User u)  {
    int a=0;
    int b=0;
         try {
             a=g;
             b=u.getId();
             Connection con = MyDB.getInstance().getCon();
             String req = "INSERT INTO `games_user` (`games_id`, `user_id`) VALUES ( "
                     +a+  ", "+ b+ ") ";
             stm = con.createStatement();
             stm.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
         }
}
public void removefav(int g,User u)  {
    int a=0;
    int b=0;
         try {
             a=g;
             b=u.getId();
             Connection con = MyDB.getInstance().getCon();
             String req = "Delete  FROM `games_user` WHERE `games_id`="+a+" and `user_id`="+b+"  ";
             stm = con.createStatement();
             stm.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
         }
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
    
    
            public List<Games> ShowProduit(){
        List<Games> Games = new ArrayList<>();
        String sql="select * from Games";
        Statement ste;
       
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Games p = new Games();
                 p.setId(rs.getInt("id"));
                 p.setName(rs.getString("name"));
                 p.setDescreption(rs.getString("descreption"));
                 p.setPrix(rs.getFloat("prix"));
                 p.setPromos_id(rs.getInt("promos_id"));
                
                 Games.add(p);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return Games;
    }
      public List<Integer> getId() {
        List<Integer> Games = new ArrayList<>();
        String query = "select * from Games";
        Statement ste;
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
        Games.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Games;
    }
    public void Rechercher( List<Games> jeu, String nomJeu){
       
        
         jeu.stream().filter(cc->cc.getName().equals(nomJeu)).forEach((t) -> {System.out.println(t);
        });
    }
     public List<Games> cherchejeu(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<Games> jeu = new ArrayList<>();
           
                ch=(String) o;
                query="SELECT * FROM `Games` WHERE `name` LIKE '%" + ch + "%' OR `descreption` LIKE '%" + ch + "%'";
          
            try {
                //System.out.println(query);
                GamesService sj=new GamesService();
                PreparedStatement ste = con.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                   
                  
                  Games c = new Games();
                 c.setId(rs.getInt("id"));
                 c.setName(rs.getString("name"));
                 c.setDescreption(rs.getString("descreption"));
                 c.setPrix(rs.getFloat("prix"));
                 jeu.add(c);
                 
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return jeu;   
        }

 public void TriTournoi(List<Games> jeu){
        
        jeu.stream().sorted((o1, o2)->o1.getName().
                                                                compareTo(o2.getName())).
                                                                collect(Collectors.toList()).forEach(t-> System.out.println(t));
        
    }
    
}
