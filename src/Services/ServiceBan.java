/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Ban;
import Entities.History;
import Entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;
import java.sql.PreparedStatement;

/**
 *
 * @author WALID
 */
public class ServiceBan implements BanServiceI{

    Connection con;
    Statement stm;
    public ServiceBan() {
        con = MyDB.getInstance().getCon();   
    }
    @Override
    public List Search_ban(int id) throws SQLException {
        String req = "SELECT * FROM `ban` WHERE `id_user_id`="+id;
        System.out.println(req);
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        List<Ban> bans = new ArrayList<Ban>();
        while(rst.next()){
            
            Ban u = new Ban(rst.getString("date_fin"),rst.getInt("id"),rst.getInt("id_user_id"),rst.getString("reason"));
            bans.add(u);
        }
        return bans;
    }

    @Override
    public void ajouter(Ban b) throws SQLException {
        String req = "INSERT INTO `ban` (`id_user_id`, `date_fin`, `reason`) VALUES ('"+b.getUser_id()+"', '"+b.getDate()+"', '"+b.getReason()+"');";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List afficher() throws SQLException {
        String req = "SELECT * FROM `ban` inner Join user where user.id=ban.id_user_id";
        System.out.println(req);
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        List<Ban> bans = new ArrayList<Ban>();
        System.out.println(rst.toString());
        while(rst.next()){
            
            Ban u = new Ban(rst.getString("date_fin"),rst.getInt("id"),rst.getInt("id_user_id"),rst.getString("reason"));
            u.setUsername(rst.getString("username"));
            bans.add(u);
        }
        return bans;
    }

    @Override
    public void Delete(int id) throws SQLException {
        String req = "DELETE FROM ban WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void modifier(Ban b, int id) throws SQLException {
         String req = "UPDATE `ban` SET `date_fin`='"+b.getDate()+"',`reason`='"+b.getReason()+"' WHERE `id`="+id;
        PreparedStatement pre;
        pre = con.prepareStatement(req);
        pre.executeUpdate();       
    }
    public List gethistory() throws SQLException {
        String req = "SELECT * FROM `history`";
        System.out.println(req);
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        List<History> bans = new ArrayList<History>();
        System.out.println(rst.toString());
        while(rst.next()){
            
            History u = new History(rst.getInt("id"), rst.getInt("id_banned_user"), rst.getString("date_fin"), rst.getString("type"),rst.getString("date_done"));
            bans.add(u);
        }
        return bans;
    }
    public List gethistorytable() throws SQLException {
        String req = "SELECT * FROM `history`";
        System.out.println(req);
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        List<History> bans = new ArrayList<History>();
        while(rst.next()){
            
            History u = new History(rst.getInt("id"), rst.getInt("id_banned_user"), rst.getString("date_fin"), rst.getString("type"),rst.getString("date_done"));
            if ( rst.getString("type").equals("A"))
                u.setType("User Banned");
            if ( rst.getString("type").equals("D"))
                u.setType("User UnBanned");
            bans.add(u);
        }
        return bans;
    }
    
}
