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
public class Client {
    
    private int idCliente;
    private String nif;
    private String prefix;
    private String surname;
    private String name;
    private String tlf;
    private String eMail;
    private String adress;
    private String password;
    private Card card;
    private ArrayList<Booking> bookingList;

    
    public Client(){
        this.nif = "";
        this.password = "";
        this.prefix = "";
        this.surname = "";
        this.name = "";
        this.tlf = "";
        this.eMail = "";
        this.adress = "";
    }
    //-->CONSTRUCTOR ALTA CLIENTE<--
    public Client(String nif, String prefix, String surname, String name, String tlf, String eMail, String adress, String password) {
        this.nif = nif;
        this.prefix = prefix;
        this.surname = surname;
        this.name = name;
        this.tlf = tlf;
        this.eMail = eMail;
        this.adress = adress;
        this.password = password;
    }
    //-->CONSTRUCTOR GET CLIENTE FROM BBDD<--
    public Client(int idCliente, String nif, String password, String prefix, String surname, String name, String tlf, String eMail, String adress) {
        this.idCliente = idCliente;
        this.nif = nif;
        this.password = password;
        this.prefix = prefix;
        this.surname = surname;
        this.name = name;
        this.tlf = tlf;
        this.eMail = eMail;
        this.adress = adress;
    }
    
    public Client(int idCliente, String nif, String password, String prefix, String surname, String name, String tlf, String eMail, String adress, Card card) {
        this.idCliente = idCliente;
        this.nif = nif;
        this.password = password;
        this.prefix = prefix;
        this.surname = surname;
        this.name = name;
        this.tlf = tlf;
        this.eMail = eMail;
        this.adress = adress;
        this.card = card;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
    
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" + "idCliente=" + idCliente + ", nif=" + nif + ", surname=" + surname + ", name=" + name + ", eMail=" + eMail + ", adress=" + adress + ", password=" + password + '}';
    }
    
    
    
}
