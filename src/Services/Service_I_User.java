 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author macbook
 */
public interface Service_I_User<T> {
    
    void ajouter(T t) throws SQLException;
        //void Modifier(T t) throws SQLException;
    void Delete(int id) throws SQLException;
     User modifier(T t,int id)throws SQLException;
    List<T> afficher() throws SQLException;
    User Login(String U,String pwd) throws SQLException;
    public ObservableList<User> afficherobs() throws SQLException;
    
    
}
