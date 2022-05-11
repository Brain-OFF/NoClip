/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.News;
import Entities.User;
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
public class NewsService implements IServiceNews<News> {
      Connection con;
    Statement stm;

    public NewsService() {
        con = MyDB.getInstance().getCon();
    }

    
    public void ajouter(News t) throws SQLException {
        try{
          String req = "INSERT INTO `news` ( `categorie_id`,`titre`,`date`,`text`,`jeu`) VALUES ( '"  + t.getCategorie_id()+ "',  '"  + t.getTitre()+ "', '" + t.getDate()+ "', '" + t.getText()+ "', '" + t.getJeu()+ "') ";
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
   public List<News> recherche(String crt) {
        List<News> list = new ArrayList<>();

        try {
            crt = "'%" + crt + "%'";
            String requete = "select * from news WHERE  titre " + crt + " OR jeu LIKE " + crt ;
            PreparedStatement pst = con.prepareStatement(requete);
            con.prepareStatement(requete).executeQuery();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new News(rs.getInt(1), rs.getString(2) , rs.getDate(3), rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

//    public void removefav(int parseInt, User user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    public void ajouterfav(int parseInt, User user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//      public List<News> cherchejeu(Object o) {
//            String query="";
//            String ch="";
//            int i=0;
//            List<News> jeu = new ArrayList<>();
//           
//                ch=(String) o;
//                query="SELECT * FROM `Games` WHERE `name` LIKE '%" + ch + "%' OR `descreption` LIKE '%" + ch + "%'";
//          
//            try {
//                //System.out.println(query);
//                NewsService sj=new NewsService();
//                PreparedStatement ste = con.prepareStatement(query);
//                ResultSet rs= ste.executeQuery();
//                while(rs.next()){
//                   
//                  
//                  News c = new News();
//                 c.setId(rs.getInt("id"));
//                 c.setTitre(rs.getString("name"));
//                 c.setText(rs.getString("descreption"));
////                 c.setPrix(rs.getFloat("prix"));
//                 jeu.add(c);
//                 
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//
//            return jeu;   
//        }
////       public List<News> ShowProduit() throws SQLException{
////        List<News> News = new ArrayList<>();
////        String sql="select * from news";
////        Statement ste;
////       
////        
////            ste = con.createStatement();
////            ResultSet rs = ste.executeQuery(sql);
////             while(rs.next()){
////                 News p = new News();
////                p.setId(rs.getInt("id"));
////                 p.setTitre(rs.getString("name"));
////                 p.setText(rs.getString("descreption"));
////                
////                 News.add(p);
////                 
//             }
//       }
    }


   
    

