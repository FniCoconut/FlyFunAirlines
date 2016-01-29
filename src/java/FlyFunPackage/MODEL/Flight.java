/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.MODEL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coconut
 */
public class Flight {
    
    private int idFlight;
    private AirConnect airportsConnection;
    private String numFlight;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private float frecio;
    private int places;
    private int airplane;

    //--> CONSTRUCTOR COMPLETO PARA LA RECOGIDA DE DATOS DESDE LA BBDD <--
    public Flight(int idFlight, AirConnect airportsConnection, String numFlight, LocalDate departureDate, LocalTime departureTime,  float frecio, int places, int airplane) {
        this.idFlight = idFlight;
        this.airportsConnection = airportsConnection;
        this.numFlight = numFlight;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.frecio = frecio;
        this.places = places;
        this.airplane = airplane;
    }
    //--> 
    public Flight(AirConnect connection, String numFlight, LocalDate departureDate, LocalTime departureTime,  float frecio, int places, int airplane) {
        this.airportsConnection = connection;
        this.numFlight = numFlight;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.frecio = frecio;
        this.places = places;
        this.airplane = airplane;
    }

    //--> SETTER & GETTER <--

    
    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public AirConnect getConnection() {
        return airportsConnection;
    }

    public void setConnection(AirConnect connection) {
        this.airportsConnection = connection;
    }

    public String getNumFlight() {
        return numFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public float getFrecio() {
        return frecio;
    }

    public void setFrecio( float frecio) {
        this.frecio = frecio;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getAirplane() {
        return airplane;
    }

    public void setAirplane(int airplane) {
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Flight{" + "idFlight=" + idFlight + ", connection=" + airportsConnection + ", numFlight=" + numFlight + ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", frecio=" + frecio + ", places=" + places + ", airplane=" + airplane + '}';
    }
    
    public Flight clonFlight(){
        try {
            return (Flight)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
