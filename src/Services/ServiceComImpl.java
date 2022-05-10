/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Entities.Commande;
import Entities.Games;
import Utils.MyDB;


/**
 *
 * @author poste 1
 */

   
public class ServiceComImpl implements IcomService<Commande> {

    Connection cnx = MyDB.getInstance().getCon();
int x=5;
    @Override
    public void ajouter(Commande t) {
        
        
        try {

            String requete = "INSERT INTO commande (nom, prenom,adresse,numtelephone,email) values ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdresse()+"','"+t.getNumtelephone()+"','"+t.getEmail()+"')";
              
           Statement st;
               st = cnx.createStatement();
               st.executeUpdate(requete);
            System.out.println("Commande ajoutée !");

           
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Commande(rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"),rs.getString("numtelephone"),rs.getString("email")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<Commande> cherchecom(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<Commande> jeu = new ArrayList<>();
           
                ch=(String) o;
                query="SELECT * FROM `commande` WHERE `nom` LIKE '%" + ch + "%' OR `prenom` LIKE '%" + ch + "%'";
          
            try {
                //System.out.println(query);
                GamesService sj=new GamesService();
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                   
                  
                  Commande c = new Commande();
                 c.setId(rs.getInt("id"));
                 c.setNom(rs.getString("nom"));
                 c.setPrenom(rs.getString("prenom"));
                 c.setEmail(rs.getString("email"));
                 jeu.add(c);
                 
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return jeu;   
        }
    public List<Commande> recherche(String crt) {
        List<Commande> list = new ArrayList<>();

        try {
            crt = "'%" + crt + "%'";
            String requete = "select * from commande WHERE name LIKE " + crt + " OR prenom LIKE " + crt + " OR adressecomplet LIKE " + crt;
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Commande(rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public String getProductName(int id) {
        String title = "";
        try {
            String requete = "SELECT name FROM commande_product where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                title = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return title;

    }

   /* public String getFileName(int id) {
        String name = "";
        try {
            int image_id = 0;
            String requete = "SELECT images_id FROM product_images where product_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                image_id = rs.getInt(1);
            }

            String requete1 = "SELECT image_name FROM images where id=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1, image_id);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                name = rs1.getString(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return name;
    }*/

 

    @Override
    public void modifier(Commande t,int idf) {
        try {
   Statement statement= cnx.createStatement();
  
            String requete = "UPDATE commande SET nom='"+t.getNom()+"' ,  prenom='"+t.getPrenom()+"'  , adressecomplet='"+t.getAdresse()+"' , telephone='"+t.getNumtelephone()+"' ,   email='"+t.getEmail()+"'  where id='"+idf+"' ";
            statement.executeUpdate(requete);
         //   String requete = "UPDATE commande SET `nom`=?, `prenom`=? , `adressecomplet`=?, `telephone`=?, `email`=? WHERE `id`=?";
          //  PreparedStatement pst = cnx.prepareStatement(requete);
              
          /*  pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getAdressecomplet());
            pst.setString(5, t.getTelephone());
            pst.setString(6, t.getEmail());
          
            pst.executeUpdate();*/
          
            
            System.out.print("Updated !!");
 
            /*    String requete2 = "INSERT INTO commande_product (commande_id,product_id) VALUES (?,?)";
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                pst2.setInt(1, t.getId());
                pst2.setInt(2, t.getIdpro());

                pst2.executeUpdate();
            */

            System.out.println("commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    

    @Override
    public void Delete(int id) throws SQLException {
                           try {
               cnx.createStatement().execute("Delete from commande where id="+id+";");
               
        } catch (SQLException ex) {
            System.err.println("Error d'suppression"+ex);
        }
              
              }
    }

   



