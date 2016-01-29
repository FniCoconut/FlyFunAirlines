/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.MODEL;

/**
 *
 * @author Coconut
 */
public class Passenger {
    
    private int idPassenger;
    private String nif;
    private String prefix;
    private String name;
    private String surname;
    private String eMail;
    private String type;
    private Passenger attend;
    //Private services;

    // --> CONSTRUCTOR DE ADULTOS Y NIÑOS <--
    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
    }

    public Passenger(String nif, String prefix, String name, String surname, String eMail, String type) {
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
    }
    
    
    
    // --> CONSTRUCTOR DE BEBES <--
    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type, Passenger attend) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.attend = attend;
    }

    public Passenger( String nif, String prefix, String name, String surname, String eMail, String type, Passenger attend) {
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.attend = attend;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Passenger getAttend() {
        return attend;
    }

    public void setAttend(Passenger attend) {
        this.attend = attend;
    }
    
    
    
    
    
}
