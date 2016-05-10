/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.MODEL;

import java.time.LocalDate;
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
    private LocalDate fechaCaducidadNif;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private ArrayList<Service> services = new ArrayList();
    //Private services;

    /**
     * @author Coconut
     * Recogida de pasajeros de la base de datos sin asiento y sin adulto asignado.
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
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.asiento = null;
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
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.asiento = null;
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
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.asiento = null;
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
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
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
    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type, String asiento) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.asiento = asiento;
        this.services = services;
    }
    
    public Passenger(int idPassenger) {
        this.idPassenger = idPassenger;
        this.nif = null;
        this.prefix = null;
        this.name = null;
        this.surname = null;
        this.eMail = null;
        this.type = null;
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.asiento = null;
        this.services = new ArrayList();
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
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.attend = attend;
        this.services = new ArrayList();
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
        this.fechaCaducidadNif = null;
        this.fechaNacimiento = null;
        this.nacionalidad= null;
        this.attend = attend;
        this.services = new ArrayList();
    }
    /**
     * @author Coconut
     * @param idPassenger
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param asiento
     * @param attend
     * @param fechaCaducidadNif
     * @param fechaNacimiento
     * @param nacionalidad
     * @param services 
     * Pasajero completo
     */
    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type, String asiento, Passenger attend, LocalDate fechaCaducidadNif, LocalDate fechaNacimiento, String nacionalidad, ArrayList<Service> services) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = asiento;
        this.attend = attend;
        this.fechaCaducidadNif = fechaCaducidadNif;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.services = services;
    }
    /**
     * @author Coconut
     * @param nif
     * @param prefix
     * @param name
     * @param surname
     * @param eMail
     * @param type
     * @param asiento
     * @param attend
     * @param fechaCaducidadNif
     * @param fechaNacimiento
     * @param nacionalidad
     * @param services
     * Pasajero compleo sin id
     */
    public Passenger(String nif, String prefix, String name, String surname, String eMail, String type, String asiento, Passenger attend, LocalDate fechaCaducidadNif, LocalDate fechaNacimiento, String nacionalidad, ArrayList<Service> services) {
        this.idPassenger = 0;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = asiento;
        this.attend = attend;
        this.fechaCaducidadNif = fechaCaducidadNif;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.services = services;
    }

    public Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type, String asiento, Passenger attend, LocalDate fechaCaducidadNif, LocalDate fechaNacimiento, String nacionalidad) {
        this.idPassenger = idPassenger;
        this.nif = nif;
        this.prefix = prefix;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.type = type;
        this.asiento = asiento;
        this.attend = attend;
        this.fechaCaducidadNif = fechaCaducidadNif;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.services = new ArrayList();
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
            /*
            Añadir servicios desde el check-in falla aqui.
            */
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

    public LocalDate getFechaCaducidadNif() {
        return fechaCaducidadNif;
    }

    public void setFechaCaducidadNif(LocalDate fechaCaducidadNif) {
        this.fechaCaducidadNif = fechaCaducidadNif;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    
    
    @Override
    public String toString() {
        return "Passenger{" + "idPassenger=" + idPassenger + ", nif=" + nif + ", prefix=" + prefix + ", name=" + name + ", surname=" + surname + ", eMail=" + eMail + ", type=" + type + ", attend=" + attend + ", services=" + services + '}';
    }
    
}
