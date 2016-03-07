/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.MODEL;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coconut
 */
public class Airport implements Cloneable{
    
    private int idAirport;
    private String iata;
    private String name;
    private String term;
    private String city;
    private String country;
    private int key;

    // --> CONSRUCTOR CON TODOOS LOS DATOS.
    public Airport(int idAirport, String iata, String name, String term, String city, String country, int key) {
        this.idAirport = idAirport;
        this.iata = iata;
        this.name = name;
        this.term = term;
        this.city = city;
        this.country = country;
        this.key = key;
    }

    public Airport(int idAirport, String iata, String name, String term, String city, String country) {
        this.idAirport = idAirport;
        this.iata = iata;
        this.name = name;
        this.term = term;
        this.city = city;
        this.country = country;
    }
    
    public Airport(){
        this.idAirport = 0;
        this.iata = "";
        this.name = "";
        this.term = "";
        this.city = "";
        this.country = "";
    }
    
   

    // --> SETTERS & GETTERS
    public void setKey(int key) {
        this.key = key;
    }
    
    public int getKey() {
        return key;
    }
 
    public int getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(int idAirport) {
        this.idAirport = idAirport;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    //--> TO STRING & CLONE

    @Override
    public String toString() {
        return "Airport{" + "idAirport=" + idAirport + ", iata=" + iata + ", name=" + name + ", term=" + term + ", city=" + city + ", country=" + country + '}';
    }
    
    public Airport clonAirport(){
        try {
            return (Airport)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
    
    
}
