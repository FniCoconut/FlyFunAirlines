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
public class Service implements Cloneable{
    
    private int idService;
    private String denomination;
    private String description;
    private float frecio;

    public Service(String denomination, String description, float frecio) {
        this.denomination = denomination;
        this.description = description;
        this.frecio = frecio;
    }

    public Service(int idService, String denomination, String description, float frecio) {
        this.idService = idService;
        this.denomination = denomination;
        this.description = description;
        this.frecio = frecio;
    }

    public Service(String denomination) {
        this.idService = 0;
        this.denomination = denomination;
        this.description = null;
        this.frecio = 0;
    }
    
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getFrecio() {
        return frecio;
    }

    public void setFrecio(float frecio) {
        this.frecio = frecio;
    }
    
    public Service clone(){
        try {
            return (Service)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }
    
}
