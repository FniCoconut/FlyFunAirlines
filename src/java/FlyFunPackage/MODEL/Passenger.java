/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.MODEL;

import java.util.ArrayList;

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
    private String asiento;
    private Passenger attend;
    private ArrayList<Service> services;
    //Private services;

    // --> CONSTRUCTOR DE ADULTOS Y NIÑOS <--
    /**
     * @author Coconut
     * Recogida de pasajeros de la base de datos sin asiento.
     * @param idPassenger
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     */
    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = "";
        this.attend = null;
        this.services = new ArrayList();
    }

    /**
     * @author Coconut
     * Recogida de pasajeros para insertar, sin asiento asignado.
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type 
     */
    public Passenger(String nif, String prefix, String name, String surname, String eMail, String type) {
        this.idPassenger = 0;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = "";
        this.attend = null;
        this.services = new ArrayList();
    }
    
    /**
     * @author Coconut
     * Pasajeros con servicios que no sean asiento para insertar
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param services 
     */
    public Passenger(String nif, String prefix, String name, String surname, String eMail, String type, ArrayList<Service> services) {
        this.idPassenger = 0;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = "";
        this.services = services;
    }

    /**
     * @author Coconut
     * Pasajeros con asiento asignado para insertar
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param asiento
     * @param services 
     */
    public Passenger(String nif, String prefix, String name, String surname, String eMail, String type, String asiento,  ArrayList<Service> services) {
        this.idPassenger = 0;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = asiento;
        this.services = services;
    }

    /**
     * @author Coconut
     * Pasajeros con asiento asignado para recoger de la bbdd
     * @param idPassenger
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param asiento
     * @param services 
     */
    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type, String asiento, ArrayList<Service> services) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = asiento;
        this.services = services;
    }
    
    // --> CONSTRUCTOR DE BEBES <--
    
    /**
     * Pasajero bebe con un pasajero asignado a su cargo para recoger de la bbdd
     * @param idPassenger
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param attend 
     */
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
    
    /**
     * Pasajero bebe con un pasajero asignado a su cargo para insertar.
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param attend 
     */
    public Passenger( String nif, String prefix, String name, String surname, String eMail, String type, Passenger attend) {
        this.idPassenger = 0;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.attend = attend;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        for(int i=0 ; i<services.size() ; i++){
            this.services.add(services.get(i).clone());
        }
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

    @Override
    public String toString() {
        return "Passenger{" + "idPassenger=" + idPassenger + ", nif=" + nif + ", prefix=" + prefix + ", name=" + name + ", surname=" + surname + ", eMail=" + eMail + ", type=" + type + ", attend=" + attend + ", services=" + services + '}';
    }
    
}
