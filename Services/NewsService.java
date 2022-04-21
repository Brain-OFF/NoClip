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
import java.sql.Date;
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
public class NewsService implements IService<News> {
      Connection con;
    Statement stm;

    public NewsService() {
        con = MyDB.getInstance().getCon();
    }

    
    public void ajouter(News t) throws SQLException {
        try{
          String req = "INSERT INTO `news` ( `categorie_id`,`titre`,`date`,`text`,`jeu`) VALUES ( '"  + t.getCategorie_id()+ "', '"  + t.getTitre()+ "', '" + t.getDate()+ "', '" + t.getText()+ "', '" + t.getJeu()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
        }catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @Override
    public List<News> afficher() throws SQLException {
//       String req = "Select * from `news`";
//        stm = con.createStatement();
//        ResultSet rst = stm.executeQuery(req);
//        System.out.println(rst.toString());
//        List<News> news = new ArrayList<News>();
//        while(rst.next()){
//            
//            News c = new News(rst.getInt("id"),rst.getInt(2),rst.getString("titre"),rst.getDate("date"),rst.getString("text"),rst.getString("jeu"));
//            news.add(c);
//            
//    }
//        return news;


        List<News> ListR = new ArrayList();
        
        try {
            String req = "Select * from news";
            stm = con.createStatement();
            ResultSet rst = stm.executeQuery(req);
             
            while(rst.next()){
                 
                 News p = new News(rst.getInt("id"),rst.getInt("categorie_id"),rst.getString("titre"),rst.getDate("date"),rst.getString("text"),rst.getString("jeu"));
                 ListR.add(p);
            }
            
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
          return ListR ;
    }
    

    @Override
    public void Delete(int id) throws SQLException {

 String req = "DELETE FROM news WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);   }

    @Override
    public void modifier(News t, int id) throws SQLException {
//        String req = "UPDATE news SET    titre = ?, date = ?, text = ?, jeu = ? where id= " + id;
//        PreparedStatement pre;
//
//            pre = con.prepareStatement(req);
//       
//      
//      
//        
//         pre.setString(1, t.getTitre()+"");
//          pre.setString(2, t.getText());
//            pre.setDate(3,  t.getDate());
//            pre.setString(4, t.getJeu());
//
//        pre.executeUpdate();  
        try {
            Statement statement= con.createStatement();
            String requete="update News set titre='"+t.getTitre()+"' ,  date='"+t.getDate()+"' ,  text='"+t.getText()+"',jeu='"+t.getJeu()+"'where id= '"+id+"'";
            statement.executeUpdate(requete);
            System.out.print("Updated !!");
        } catch (SQLException e) {
            
  e.printStackTrace();
        } 

        
    
    }

    public void ajouter(Categorie p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    }

   
    

