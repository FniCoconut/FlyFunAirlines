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
public class Card {
    //client's card data
    private int idTj;
    private String numTj;
    private String mesCad;
    private String anoCad;

    public Card(int idTj, String numTj, String mesCad, String anoCad) {
        this.idTj = idTj;
        this.numTj = numTj;
        this.mesCad = mesCad;
        this.anoCad = anoCad;
    }

    public Card(String numTj, String mesCad, String anoCad) {
        this.numTj = numTj;
        this.mesCad = mesCad;
        this.anoCad = anoCad;
    }

    public int getIdTj() {
        return idTj;
    }

    public void setIdTj(int idTj) {
        this.idTj = idTj;
    }

    public String getNumTj() {
        return numTj;
    }

    public void setNumTj(String numTj) {
        this.numTj = numTj;
    }

    public String getMesCad() {
        return mesCad;
    }

    public void setMesCad(String mesCad) {
        this.mesCad = mesCad;
    }

    public String getAnoCad() {
        return anoCad;
    }

    public void setAnoCad(String anoCad) {
        this.anoCad = anoCad;
    }
            
    
    
}
