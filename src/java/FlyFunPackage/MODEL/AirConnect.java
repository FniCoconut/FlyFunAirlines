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
public class AirConnect {
    
    private int idConnection;
    private Airport termOrigin;
    private Airport termDestiny;

    // --> CONSRUCTOR CON TODOOS LOS DATOS.
    public AirConnect(int idConnection, Airport termOrigin, Airport termDestiny) {
        this.idConnection = idConnection;
        this.termOrigin = termOrigin;
        this.termDestiny = termDestiny;
    }
    
    public AirConnect(int idConnection) {
        this.idConnection = idConnection;
        this.termDestiny = null;
        this.termOrigin = null;
    }
    
    public AirConnect(){
        this.idConnection = 0;
        this.termDestiny = null;
        this.termOrigin = null;        
    }
    // --> SETTERS & GETTERS

    public int getIdConnection() {
        return idConnection;
    }

    public void setIdConnection(int idConnection) {
        this.idConnection = idConnection;
    }

    public Airport getTermOrigin() {
        return termOrigin;
    }

    public void setTermOrigin(Airport termOrigin) {
        this.termOrigin = termOrigin;
    }

    public Airport getTermDestiny() {
        return termDestiny;
    }

    public void setTermDestiny(Airport termDestiny) {
        this.termDestiny = termDestiny;
    }
    
    public void setTerminal(Airport a){
        if(this.termOrigin == null){
            this.termOrigin = a;
        }else{
            this.termDestiny = a;
        }
    }
    
    // --> TO STRING & CLONE

    @Override
    public String toString() {
        return "Connection{" + "idConnection=" + idConnection + ", termOrigin=" + termOrigin + ", termDestiny=" + termDestiny + '}';
    }
    
    public AirConnect clonConnection(){
        try {
            return (AirConnect)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AirConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
