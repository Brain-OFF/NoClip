/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Personne;
import Entities.User;
import Utils.MyDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.applet.Main;

/**
 *
 * @author macbook
 */
public class UserService implements IService<User> {

    Connection con;
    Statement stm;
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(Main.class.getName());
    private static HttpURLConnection conn;
    public String encrypt_password(String Username,String Email,String pwd)
        {
            
            BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();
		try{
			URL url = new URL("http://127.0.0.1:8000/encrypt_pass?username=tazarkour&email=jaouaniw@gmail.com&password=walid123456");
			conn = (HttpURLConnection) url.openConnection();
			
			// Request setup
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
			conn.setReadTimeout(5000);
			
			// Test if the response from the server is successful
			int status = conn.getResponseCode();
			
			if (status >= 300) {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			log.info("response code: " + status);
			System.out.println(responseContent.toString());
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.disconnect();
                        return responseContent.toString();
		}
        }
    public UserService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void ajouter(User u) throws SQLException {
        String pwd=encrypt_password(u.getUsername(),u.getEmail(),u.getPassword());
        pwd.replace("\"", "");
        System.out.println(pwd);
        String req = "INSERT INTO `user`( `username`, `email`, `password`, `bio`, `points`,`is_verified`,`roles`)VALUES ( '"+ u.getUsername()+ "', '" + u.getEmail() + "', " 
        + pwd + ", '" + u.getBio() + "', '" + u.getPoints()+ "', '" + 0  + "', '" + null + "') ";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

     @Override
    public List<User> afficher() throws SQLException {
        String req = "SELECT * FROM `User`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<User> personnes = new ArrayList<User>();
        while(rst.next()){
            
            User u = new User(rst.getInt(1),rst.getString("username"),rst.getString("email"),rst.getString("password"),rst.getInt(7),rst.getString("bio"),rst.getBoolean(9));
            personnes.add(u);
        }
        return personnes;
        
    }

    @Override
    public void Delete(int id) throws SQLException {
  String req = "DELETE FROM user WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }
    
    Connection connexion;


      @Override
    public void modifier(User u, int id) throws SQLException {
       String pwd=encrypt_password(u.getUsername(),u.getEmail(),u.getPassword());

  String req = "UPDATE user SET  username = ?, email = ?,  password = "+pwd+", bio = ? , points = ? where id= " + id;
        PreparedStatement pre;

            pre = con.prepareStatement(req);
       
      
        pre.setString(1, u.getUsername());
        pre.setString(2, u.getEmail());
        pre.setString(3, u.getBio());
        pre.setString(4, u.getPoints()+"");
        pre.executeUpdate();   
   }

 

}
