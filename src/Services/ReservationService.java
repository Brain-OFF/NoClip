/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reservation;
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
public class ReservationService implements IService<Reservation> {

    Connection con;
    Statement stm;
    public ReservationService() {
        con = MyDB.getInstance().getCon();
    }
    
    @Override
    public void ajouter(Reservation t) throws SQLException {
          String req = "INSERT INTO `reservation` (`tempsstart`, `tempsend`,`dispo`,`coach_id`) VALUES ( '"
                + t.getTempsend()+ "', '" + t.getTempsend()+ "', '" + t.getDispo()+ "', '" + t.getCoach()+ "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Reservation> afficher() throws SQLException {
 String req = "Select * from `reservation`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Reservation> reservation = new ArrayList<Reservation>();
        while(rst.next()){
            
            Reservation c = new Reservation(rst.getInt(1),rst.getInt(5),rst.getString(2),rst.getString(3),rst.getInt(4));
            reservation.add(c);
            
            
    }
        return reservation;
    }

    @Override
    public void Delete(int id) throws SQLException {
        String req = "DELETE FROM reservation WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    

    @Override
    public void modifier(Reservation t, int id) throws SQLException {
String req = "UPDATE reservation SET  tempsstart = ?, tempsend = ?,  dispo = ?, coach_id = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
       pre.setString(1, t.getTempsend());
        pre.setString(2, t.getTempsend());
         pre.setString(3, t.getDispo()+"");
          pre.setString(4, t.getCoach()+"");

        pre.executeUpdate();   
    }
    
}
