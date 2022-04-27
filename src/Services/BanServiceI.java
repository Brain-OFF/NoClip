/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.User;
import Entities.Ban;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author WALID
 */
public interface BanServiceI <B>{
    List<B> Search_ban(int id) throws SQLException;
    void ajouter(Ban b) throws SQLException;
    List<B> afficher() throws SQLException;
    void Delete(int id) throws SQLException;
    void modifier(Ban b,int id)throws SQLException;
    
}
