/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.Date;
import java.util.Comparator;
/**
 *
 * @author xDrais
 */
public class Reservation implements Comparable<Reservation>{
    
    
private int id , coach,user_id ;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Reservation(int id, int coach, int user_id, String tempsstart, String tempsend, int dispo) {
        this.id = id;
        this.coach = coach;
        this.user_id = user_id;
        this.tempsstart = tempsstart;
        this.tempsend = tempsend;
        this.dispo = dispo;
    }
private String tempsstart , tempsend  ;
private int dispo;

  

    public Reservation( int coach, String tempsstart, String tempsend , int dispo) {
        this.coach = coach;
        this.tempsstart = tempsstart;
        this.tempsend = tempsend;
        this.dispo = dispo;

    }
 public Reservation( String tempsstart, String tempsend , int dispo) {
        this.tempsstart = tempsstart;
        this.tempsend = tempsend;
        this.dispo = dispo;

    }

    public Reservation(int id, int coach, String tempsstart, String tempsend, int dispo) {
        this.id = id;
        this.coach = coach;
        this.tempsstart = tempsstart;
        this.tempsend = tempsend;
        this.dispo = dispo;
    }
 
 

  @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", coach=" + coach + ", time start=" + tempsstart + ", time end=" + tempsend +  ", dispo =" + dispo + "\n";
    }

public String idtoString ()
    {
        return String.valueOf(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoach() {
        return coach;
    }

    public void setCoach(int coach) {
        this.coach = coach;
    }

    public String getTempsstart() {
        return tempsstart;
    }

    public void setTempsstart(String tempsstart) {
        this.tempsstart = tempsstart;
    }

    public String getTempsend() {
        return tempsend;
    }

    public void setTempsend(String tempsend) {
        this.tempsend = tempsend;
    }

   

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }


@Override
  public int compareTo(Reservation r) 
  {
      return tempsstart.compareTo(r.tempsstart);
  }

  

    public Reservation(String tempsstart) {
        this.tempsstart = tempsstart;
    }
  
    
}
