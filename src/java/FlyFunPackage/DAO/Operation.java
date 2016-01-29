/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.DAO;

import FlyFunPackage.MODEL.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coconut
 */
public class Operation {
 
    /**
     * @author 
     * @param _connection
     * @return ArrayList
     */
    public ArrayList<Airport> airportOrigin(Connection _connection){
        ArrayList<Airport> _origins = new ArrayList();
        
        try{
            Statement st = _connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.airport;");

            while(rs.next()){
                Airport _origin = new Airport(rs.getInt("ID_AIRPORT"), rs.getString("IATA"), rs.getString("NOMBRE"), rs.getString("TERMINAL"), rs.getString("CIUDAD"), rs.getString("PAIS"));
                _origins.add(_origin);
            }
            rs.close();
        }catch (SQLException ex) {
            System.out.println("cacurria origen");
        }
        
        return _origins;
    }
    
    public ArrayList<Airport> airportDestiny(Connection _connection, int _origin){
        ArrayList<Airport> _destinys = new ArrayList();
        
        try{
            Statement st = _connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.airport WHERE ID_AIRPORT IN (SELECT ac.T_DESTINO FROM flyfunairlines.airport a, flyfunairlines.air_connect ac WHERE ID_AIRPORT="+_origin+" AND a.ID_AIRPORT=ac.T_ORIGEN);");

            while(rs.next()){
                Airport _destiny = new Airport(rs.getInt("ID_AIRPORT"), rs.getString("IATA"), rs.getString("NOMBRE"), rs.getString("TERMINAL"), rs.getString("CIUDAD"), rs.getString("PAIS"));
                _destinys.add(_destiny);
            }
            rs.close();
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return _destinys;
    }
    
    public AirConnect getConnectionOneWay(Connection _connection, Airport A_origin, Airport A_destiny){
        AirConnect cnx = null;
        try {
            Statement st = _connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.air_connect WHERE T_ORIGEN = "+A_origin.getIdAirport()+" AND T_DESTINO = "+A_destiny.getIdAirport()+";");
                while(rs.next()){
                    cnx = new AirConnect(rs.getInt("ID_CONNECT"), A_origin, A_destiny);
                }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cnx;
        
    }
    
    public ArrayList<Flight> oneWayFlights(Connection _connection, String _date, int _vacances, AirConnect cnx){
        
            ArrayList<Flight> flyTrip = new ArrayList();
            //DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //DateTimeFormatter hhmm = DateTimeFormatter.ofPattern("HH:mm:ss");
            //string ->localdate;

        try {    
            Statement st = _connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.flight WHERE F_SALIDA between date_sub('"+_date+"', interval 5 day) and date_add('"+_date+"', interval 5 day) and PLAZAS > "+_vacances+" and CONNECTION="+cnx.getIdConnection()+";");
            
            while(rs.next()){
                LocalDate dateTrip = LocalDate.parse(rs.getString("F_SALIDA"));
                LocalTime hourTrip = LocalTime.parse(rs.getString("H_SALIDA"));

                flyTrip.add(new Flight(rs.getInt("ID_FLY"), cnx, rs.getString("N_VUELO") , dateTrip, hourTrip, Integer.parseInt(rs.getString("FRECIO")), rs.getInt("PLAZAS"), 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flyTrip;
    }
    
    
    
        
}
