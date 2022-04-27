/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Ban;
import Entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;

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
    
}
