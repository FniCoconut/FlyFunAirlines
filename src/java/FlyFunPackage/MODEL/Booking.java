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
    private float precio;
    private String rIda;
    private String rVuelta;

    public Booking(int idBooking, Client idClient, Occupation oOW, Occupation oR) {
        this.idBooking = idBooking;
        this.client = idClient;
        this.ida = oOW;
        this.vuelta = oR;
        this.rIda = null;
        this.rVuelta = null;
    }

    public Booking(Client idClient, Occupation oOW, Occupation oR, String i, String v) {
        this.client = idClient;
        this.ida = oOW;
        this.vuelta = oR;
        this.rIda = i;
        this.rVuelta = v;
    }

    public Booking(int idBooking, float precio, String rIda, String rVuelta) {
        this.idBooking = idBooking;
        this.client = null;
        this.ida = null;
        this.vuelta = null;
        this.precio = precio;
        this.rIda = rIda;
        this.rVuelta = rVuelta;
    }

    public Booking(int idBooking, Client client, float precio, String rIda, String rVuelta) {
        this.idBooking = idBooking;
        this.client = client;
        this.precio = precio;
        this.ida = null;
        this.vuelta = null;
        this.rIda = rIda;
        this.rVuelta = rVuelta;
        
    }

    
    
    public String getrIda() {
        return rIda;
    }

    public void setrIda(String rIda) {
        this.rIda = rIda;
    }

    public String getrVuelta() {
        return rVuelta;
    }

    public void setrVuelta(String rVuelta) {
        this.rVuelta = rVuelta;
    }


    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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

    public void priceCalc(){
        float pt = 0;
        
        for(int i=0 ; i< this.ida.getPassengers().size(); i++){
            pt += this.ida.getFlight().getFrecio();
            
            if((this.ida.getPassengers().get(i)).getServices() != null){
                
            for(int j=0; j<(this.ida.getPassengers().get(i)).getServices().size(); j++ ){
                pt += ((this.ida.getPassengers().get(i)).getServices().get(j)).getFrecio();
            }
            
            }          
        }
         
        if( this.vuelta != null ){
            
        for(int i=0 ; i< this.vuelta.getPassengers().size(); i++){
            pt += this.vuelta.getFlight().getFrecio();
            
            if((this.ida.getPassengers().get(i)).getServices() != null){
                
            for(int j=0; j< (this.vuelta.getPassengers().get(i)).getServices().size(); j++ ){
                pt += ((this.vuelta.getPassengers().get(i)).getServices().get(j)).getFrecio();
            }  
            
            } 
        }
        }
        this.precio = pt;
    }
    
    
}
