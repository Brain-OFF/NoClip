/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author macbook
 */
public interface IaService<T> {
    
    void ajouter(T t) throws SQLException;
    
    List<T> afficher() throws SQLException;
     void Delete(int id) throws SQLException ;
     void modifier(T t, int id) throws SQLException ;
    
    
    
}
