/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Taha
 */
public interface IncService <T> {
    
    void ajouterInc(T t,int Uid) throws SQLException;
    void DeleteInc(int id) throws SQLException;
     void modifierInc(T t,int id)throws SQLException;
    List<T> afficherInc() throws SQLException;
}
