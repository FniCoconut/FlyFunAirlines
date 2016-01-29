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
public class Occupation {
 
    private int idOcupation;
    private String bookingCode;
    private Flight flight;
    private ArrayList <Passenger> passengers;
    /*
    En este caso los asientos forman parte
    de los pasajeros aunque en el modelo
    formen parte de la ocupacion
    */

    public Occupation(Flight flight) {
        this.idOcupation = 0;
        this.flight = flight;
        this.bookingCode = null;
        this.passengers = new ArrayList();
    }

    public Occupation(String bookingCode, Flight flight) {
        this.idOcupation = 0;
        this.bookingCode = bookingCode;
        this.flight = flight;
        this.passengers = new ArrayList();
    }

    public Occupation(int idOcupation, String bookingCode, Flight flight) {
        this.idOcupation = idOcupation;
        this.bookingCode = bookingCode;
        this.flight = flight;
        this.passengers = new ArrayList();
    }

    public Occupation(int idOcupation, String bookingCode, Flight flight, ArrayList<Passenger> passengers) {
        this.idOcupation = idOcupation;
        this.bookingCode = bookingCode;
        this.flight = flight;
        this.passengers = passengers;
    }

    public int getIdOcupation() {
        return idOcupation;
    }

    public void setIdOcupation(int idOcupation) {
        this.idOcupation = idOcupation;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
    
    
    
    
    
}
