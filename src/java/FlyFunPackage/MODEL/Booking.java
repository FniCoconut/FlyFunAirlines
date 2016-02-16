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
public class Booking {
    
    private int idBooking;
    private Client client;
    private Occupation ida;
    private Occupation vuelta;
    private String tipo;

    public Booking(int idBooking, Client idClient, Occupation oOW, Occupation oR, String tipo) {
        this.idBooking = idBooking;
        this.client = idClient;
        this.ida = oOW;
        this.vuelta = oR;
        this.tipo = tipo;
    }

    public Booking(Client idClient, Occupation oOW, Occupation oR, String tipo) {
        this.client = idClient;
        this.ida = oOW;
        this.vuelta = oR;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Occupation getIda() {
        return ida;
    }

    public void setIda(Occupation ida) {
        this.ida = ida;
    }

    public Occupation getVuelta() {
        return vuelta;
    }

    public void setVuelta(Occupation vuelta) {
        this.vuelta = vuelta;
    }

    
    
    
}
