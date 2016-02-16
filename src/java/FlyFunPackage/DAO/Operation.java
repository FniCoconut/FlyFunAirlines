/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.DAO;

import FlyFunPackage.MODEL.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
     * @author Coconut
     * @param _connection
     * @return ArrayList de Aeropuertos
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
    /**
     * @author Coconut
     * @param _connection
     * @param _origin PK de aeropuerto para devolver los destinos conectados
     * @return ArrayList de aeropuertos de destino
     */
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
    /**
     * @author Coconut
     * @param _connection
     * @param A_origin
     * @param A_destiny
     * @return AirConnect conexión de Aeropuertos de origen y destino
     */
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
    /**
     * @author Coconut
     * @param _connection
     * @param _date
     * @param _vacances
     * @param cnx
     * @return ArrayList con todos los vuelos correspondientes a esa _connection
     */
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
    /**
     * @author Coconut
     * @param _connection
     * @return ArrayList de servicios con todos los servicios disponibles
     */
    public ArrayList<Service> allServices(Connection _connection){
        ArrayList<Service> services = new ArrayList();
        try{
            Statement st = _connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.service;");

            while(rs.next()){
                services.add(new Service(rs.getInt("ID_SERVICE"), rs.getString("DENOMINACION"), rs.getString("DESCRIPCION"), rs.getFloat("PRECIO")));
            }
            
            } catch (SQLException ex) {
            System.out.println(ex);
        }
        return services;
        
    }
    /**
     * @author Coconut
     * @param _connection
     * @param _client
     * @return true si el cliente ha sido añadido, false cuando ocurre algún problema
     */
    public boolean addCliente(Connection _connection, Client _client){
        
        String _nif = _client.getNif();
        String _prefix = _client.getPrefix();
        String _surname = _client.getSurname();
        String _name = _client.getName();
        String _adress = _client.getAdress();
        String _tlf = _client.getTlf();
        String _eMail = _client.geteMail();
        String _pass = _client.getPassword();
        boolean flg = true;
        
        try{
            PreparedStatement PStm = _connection.prepareStatement("INSERT INTO flyfunairlines.client VALUES( default, ?, aes_encrypt(?, '"+_eMail+"'), ?, ?, ?, ?, ?, ?, null)");
            PStm.setString(1, _nif);
            PStm.setString(2, _pass);
            PStm.setString(3, _prefix);
            PStm.setString(4, _surname);
            PStm.setString(5, _name);
            PStm.setString(6, _tlf);
            PStm.setString(7, _eMail);
            PStm.setString(8, _adress);
            
            PStm.executeUpdate();
            flg = true;
        }catch (SQLException ex){
            ex.getMessage();
            if(_connection != null){
                try{
                    flg = false;
                    _connection.rollback();
                }catch(SQLException SQLEx2){
                    SQLEx2.getMessage();
                }
            }
        }
        return flg;
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param _user
     * @param _pass
     * @return Client cuando el login es exitoso null si el usuario no coincide
     */
    public Client loginClient(Connection _connection, String _user, String _pass){
        boolean flg = true;
        //Client cliente = new Client();
        try{
            boolean flag = false;
            PreparedStatement PStm = _connection.prepareStatement("SELECT aes_decrypt(PASSWORD, EMAIL) as clave FROM flyfunairlines.client where EMAIL=?;");
            PStm.setString(1, _user);
            
            ResultSet Rs = PStm.executeQuery();
            
            while(Rs.next()){
                String claveBD = Rs.getString("clave");//recogemos el dato binario de la bbdd
                flg = claveBD.equals(_pass); // si coinciden true
            }
            
            if(flg){
                PreparedStatement PSs = _connection.prepareStatement("SELECT * FROM flyfunairlines.client WHERE EMAIL=?");
                PSs.setString(1, _user);
                
                ResultSet rs = PSs.executeQuery();
                
                while(rs.next()){
                    return new Client(rs.getInt("ID_CLIENT"), rs.getString("NIF"), rs.getString("PASSWORD"), rs.getString("TRATAMIENTO"), rs.getString("NOMBRE"),  rs.getString("APELLIDOS"), rs.getString("TELEFONO"), rs.getString("EMAIL"), rs.getString("DIRECCION"));
                    //session.setAttribute("client", cliente);
                }
            }
            
        }catch (SQLException ex){
            ex.getMessage();
            if(_connection != null){
                try{
                    flg = false;
                    _connection.rollback();
                }catch(SQLException SQLEx2){
                    SQLEx2.getMessage();
                }
            }
        }
        return null;
        
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param bk Obj booking, contiene la ocupacion que se haga para el viaje de ida y de haber, también el de vuelta
     */
    public void insertBooking(Connection _connection, Booking bk){
        //recibe reserva que tiene obj ocupacion que tienen array de pasajeros que tienen array deservicios
                    boolean flag = false;
        String tipo = bk.getTipo();
        Occupation ocOW = bk.getIda();
        Client cl = bk.getClient();
            String bookingCode = ocOW.getBookingCode();
            ArrayList<Passenger> pasajeros = ocOW.getPassengers();
            
            String passenger = "INSERT INTO flyfunairlines.passenger VALUES( default, ?, ?, ?, ?, ?, ?, ?)";
            String occup = "INSERT INTO flyfunairlines.occupation VALUES ( default, '"+bookingCode+"', "+(ocOW.getFlight()).getIdFlight()+", ?, ?)";
            String serv = "INSERT INTO flyfunairlines.occupation_service VALUES ( default, ?, ?)";
            String booking = "INSERT INTO flyfunairlines.booking VALUES( default, "+cl.getIdCliente()+", '"+bookingCode+"', "+tipo+")";
        try{
            _connection.setAutoCommit(false);
            
            for( int i=0 ; i<pasajeros.size() ; i++){
                
            if(!((pasajeros.get(i)).getType().equalsIgnoreCase("bebe"))){
                //recorremos e insertamos personas adulto y nino
                ArrayList<Service> servicios = (pasajeros.get(i)).getServices();
                
                PreparedStatement pStmPasajeros = _connection.prepareStatement(passenger,Statement.RETURN_GENERATED_KEYS);
                    pStmPasajeros.setString(1, (pasajeros.get(i)).getNif());
                    pStmPasajeros.setString(2, (pasajeros.get(i)).getPrefix());
                    pStmPasajeros.setString(3, (pasajeros.get(i)).getSurname());
                    pStmPasajeros.setString(4, (pasajeros.get(i)).getName());
                    pStmPasajeros.setString(5, (pasajeros.get(i)).geteMail());
                    pStmPasajeros.setString(6, (pasajeros.get(i)).getType());
                    pStmPasajeros.setInt(7, 0);//aqui necesitamos el id de la persona que está a cargo
                
                pStmPasajeros.executeUpdate();
                
                //if(rowP == 0){throw new SQLException("caca insert pasajero");}
                
                try(ResultSet lastId = pStmPasajeros.getGeneratedKeys()){
                    if(lastId.next()){
                        pasajeros.get(i).setIdPassenger(lastId.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id pasajero");
                    }
                }
                
                //aqui insert en ocupación
                PreparedStatement pStmOcupacion = _connection.prepareStatement(occup,Statement.RETURN_GENERATED_KEYS);
                    pStmOcupacion.setInt(1, (pasajeros.get(i)).getIdPassenger());
                    pStmOcupacion.setString(2, (pasajeros.get(i)).getAsiento());
                    
                //int rowO = 
                        pStmOcupacion.executeUpdate();
                   // if(rowO == 0){throw new SQLException("caca insert ocupacion");}
                try(ResultSet lastIdO = pStmOcupacion.getGeneratedKeys()){
                    if(lastIdO.next()){
                        ocOW.setIdOcupation(lastIdO.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id ocupacion");
                    }
                }
                //aqui el inser en bookig_services
                for(int k=0 ; k<servicios.size(); k++){
                PreparedStatement pStmServicio = _connection.prepareStatement(serv);
                    pStmServicio.setInt(1, (servicios.get(k)).getIdService());
                    pStmServicio.setInt(2, ocOW.getIdOcupation());
                    
                    pStmServicio.executeUpdate();
                }
                
            }else{
                Passenger bebe = pasajeros.get(i);
                if( pasajeros.contains(bebe.getAttend()) && !flag ){
                //for(int j=0; j<pasajeros.size(); j++){
                //    if( (bebe.getAttend().getNif()).equalsIgnoreCase((pasajeros.get(j)).getNif()) ){
                        PreparedStatement pStmPasajeros = _connection.prepareStatement(passenger);
                            pStmPasajeros.setString(1, (bebe).getNif());
                            pStmPasajeros.setString(2, (bebe).getPrefix());
                            pStmPasajeros.setString(3, (bebe).getSurname());
                            pStmPasajeros.setString(4, (bebe).getName());
                            pStmPasajeros.setString(5, (bebe).geteMail());
                            pStmPasajeros.setString(6, (bebe).getType());
                            pStmPasajeros.setInt(7, (bebe).getAttend().getIdPassenger() );//aqui necesitamos el id de la persona que está a cargo
                        
                        pStmPasajeros.executeUpdate();
                    flag = true;
                    }
                }
            
            }
            
        if(tipo.equalsIgnoreCase("vuelta")){
            
            Occupation ocR = bk.getVuelta();
            String occupR = "INSERT INTO flyfunairlines.occupation VALUES (default, '"+bookingCode+"', "+(ocR.getFlight()).getIdFlight()+", ?, '?')";
            
            ArrayList<Passenger> pasajerosR = ocOW.getPassengers();
            
            for( int i=0 ; i<pasajerosR.size() ; i++){
                
            if(!((pasajerosR.get(i)).getType().equalsIgnoreCase("bebe"))){
                //recorremos e insertamos personas adulto y nino
                ArrayList<Service> serviciosR = (pasajerosR.get(i)).getServices();
                
                PreparedStatement pStmPasajerosR = _connection.prepareStatement(passenger,Statement.RETURN_GENERATED_KEYS);
                    pStmPasajerosR.setString(1, (pasajerosR.get(i)).getNif());
                    pStmPasajerosR.setString(2, (pasajerosR.get(i)).getPrefix());
                    pStmPasajerosR.setString(3, (pasajerosR.get(i)).getSurname());
                    pStmPasajerosR.setString(4, (pasajerosR.get(i)).getName());
                    pStmPasajerosR.setString(5, (pasajerosR.get(i)).geteMail());
                    pStmPasajerosR.setString(6, (pasajerosR.get(i)).getType());
                    pStmPasajerosR.setInt(7, 0);//aqui necesitamos el id de la persona que está a cargo
                
                int rowP = pStmPasajerosR.executeUpdate();
                
                if(rowP == 0){throw new SQLException("caca insert pasajero");}
                
                try(ResultSet lastId = pStmPasajerosR.getGeneratedKeys()){
                    if(lastId.next()){
                        pasajerosR.get(i).setIdPassenger(lastId.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id pasajero");
                    }
                }
                
                //aqui insert en ocupación
                PreparedStatement pStmOcupacionR = _connection.prepareStatement(occupR,Statement.RETURN_GENERATED_KEYS);
                    pStmOcupacionR.setInt(1, (pasajeros.get(i)).getIdPassenger());
                    pStmOcupacionR.setString(2, (pasajeros.get(i)).getAsiento());
                    
                int rowO = pStmOcupacionR.executeUpdate();
                    if(rowO == 0){throw new SQLException("caca insert ocupacion");}
                try(ResultSet lastIdO = pStmOcupacionR.getGeneratedKeys()){
                    if(lastIdO.next()){
                        ocOW.setIdOcupation(lastIdO.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id ocupacion");
                    }
                }
                //aqui el inser en bookig_services
                for(int k=0 ; k<serviciosR.size(); k++){
                PreparedStatement pStmServicioR = _connection.prepareStatement(serv);
                    pStmServicioR.setInt(1, (serviciosR.get(k)).getIdService());
                    pStmServicioR.setInt(2, ocR.getIdOcupation());
                    
                    pStmServicioR.executeUpdate();
                }
                
            }else{
                Passenger bebe = pasajerosR.get(i);
                for(int j=0; j<pasajerosR.size(); j++){
                    if( (bebe.getAttend().getNif()).equalsIgnoreCase((pasajeros.get(j)).getNif()) ){
                        PreparedStatement pStmPasajerosR = _connection.prepareStatement(passenger,Statement.RETURN_GENERATED_KEYS);
                            pStmPasajerosR.setString(1, (bebe).getNif());
                            pStmPasajerosR.setString(2, (bebe).getPrefix());
                            pStmPasajerosR.setString(3, (bebe).getSurname());
                            pStmPasajerosR.setString(4, (bebe).getName());
                            pStmPasajerosR.setString(5, (bebe).geteMail());
                            pStmPasajerosR.setString(6, (bebe).getType());
                            pStmPasajerosR.setInt(7, (bebe).getAttend().getIdPassenger());//aqui necesitamos el id de la persona que está a cargo
                        
                        pStmPasajerosR.executeUpdate();
                    }
                }
            }
            }
            
        }
            
            PreparedStatement pStmBooking = _connection.prepareStatement(booking);
                pStmBooking.executeUpdate();
            
            
        }catch (SQLException ex){
            ex.getMessage();
            if(_connection != null){
                try{
                    _connection.rollback();
                }catch(SQLException SQLEx2){
                    SQLEx2.getMessage();
                }
            }
        }
        
    }
    
}
