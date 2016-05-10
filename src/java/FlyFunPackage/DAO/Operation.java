/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.DAO;

import FlyFunPackage.MODEL.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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
                Airport _origin = new Airport(rs.getInt("ID_AIRPORT"), rs.getString("IATA"), rs.getString("NOMBRE"), rs.getString("TERMINAL"), rs.getString("CIUDAD"), rs.getString("PAIS"), rs.getInt("KEY"));
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
                Airport _destiny = new Airport(rs.getInt("ID_AIRPORT"), rs.getString("IATA"), rs.getString("NOMBRE"), rs.getString("TERMINAL"), rs.getString("CIUDAD"), rs.getString("PAIS"), rs.getInt("KEY"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.flight WHERE F_SALIDA between date_sub('"+_date+"', interval 5 day) and date_add('"+_date+"', interval 5 day) and PLAZAS > "+_vacances+" and CONNECTION="+cnx.getIdConnection()+" ORDER BY F_SALIDA ASC;");
            
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
    
    public ArrayList<Flight> returnFlights(Connection _connection, String _date, int _vacances, AirConnect cnx, String _departureDate){
        
            ArrayList<Flight> flyTrip = new ArrayList();
            //DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //DateTimeFormatter hhmm = DateTimeFormatter.ofPattern("HH:mm:ss");
            //string ->localdate;

        try {    
            Statement st = _connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flyfunairlines.flight WHERE F_SALIDA between date_sub('"+_date+"', interval 5 day) and date_add('"+_date+"', interval 5 day) and PLAZAS > "+_vacances+" and F_SALIDA > '"+_departureDate+"' and CONNECTION="+cnx.getIdConnection()+" ORDER BY F_SALIDA ASC;");
            
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
     * @return Cliente, con esto se inicia sesión al registrarse.
     */
    public Client addCliente(Connection _connection, Client _client){
        
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
            _connection.setAutoCommit(false);
            
            PreparedStatement PStm = _connection.prepareStatement("INSERT INTO flyfunairlines.client VALUES( default, ?, aes_encrypt(?, '"+_eMail+"'), ?, ?, ?, ?, ?, ?, null)", Statement.RETURN_GENERATED_KEYS);
            PStm.setString(1, _nif);
            PStm.setString(2, _pass);
            PStm.setString(3, _prefix);
            PStm.setString(4, _surname);
            PStm.setString(5, _name);
            PStm.setString(6, _tlf);
            PStm.setString(7, _eMail);
            PStm.setString(8, _adress);
            
            PStm.executeUpdate();
            
            _connection.commit();
            
                ResultSet lastIdC = PStm.getGeneratedKeys();
                if(lastIdC.next()){
                    _client.setIdCliente(lastIdC.getInt(1));
                    return _client;
                }else{
                        throw new SQLException("caca id cliente");
                    }
                             
            
        }catch (SQLException ex){
            ex.getMessage();
            if(_connection != null){
                try{
                    _connection.rollback();
                    return null;
                }catch(SQLException SQLEx2){
                    SQLEx2.getMessage();
                }
            }
        }
        return _client;
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
     * @param f Object Flight
     * @return ArrayList de asientos ocupados
     */
    public ArrayList getOccupation(Connection _connection, Flight f){
        ArrayList seats = new ArrayList();
        try {
            PreparedStatement pSOccup = _connection.prepareStatement("select ASIENTO from flyfunairlines.occupation where VUELO ="+f.getIdFlight());
            ResultSet rs = pSOccup.executeQuery();
            while(rs.next()){
                seats.add(rs.getString("ASIENTO"));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seats;
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param bk Obj booking, contiene la ocupacion que se haga para el viaje de ida y de haber, también el de vuelta
     * @param viaje String que contiene el tipo de viaje
     */
    public void insertBooking(Connection _connection, Booking bk,String viaje){
        //recibe reserva que tiene obj ocupacion que tienen array de pasajeros que tienen array deservicios
        boolean flag = false;
        String tipo = viaje;
        Occupation ocOW = bk.getIda();
        Client cl = bk.getClient();
        Card cr = cl.getCard();
            String bookingCode = ocOW.getBookingCode();
            ArrayList<Passenger> pasajeros = ocOW.getPassengers();
            //añadir columnas fechaCaducidad, nacionalidad, fechaNacimiento
            String passenger = "INSERT INTO flyfunairlines.passenger VALUES( default, ?,null, ?, ?, ?,null, ?, ?, null, ?)";
            String occup = "INSERT INTO flyfunairlines.occupation VALUES ( default, '"+bookingCode+"', "+(ocOW.getFlight()).getIdFlight()+", ?, ?)";
            String serv = "INSERT INTO flyfunairlines.occupation_service VALUES ( default, ?, ?)";
            
        try{
            _connection.setAutoCommit(false);
            
            PreparedStatement pStmCard = _connection.prepareStatement("INSERT INTO flyfunairlines.card VALUES( default,? , aes_encrypt(?,'"+cl.getNif()+"'), ?, ?)",Statement.RETURN_GENERATED_KEYS);
                pStmCard.setInt(1, cl.getIdCliente());
                pStmCard.setString(2, cr.getNumTj());
                pStmCard.setInt(3, cr.getMesCad());
                pStmCard.setInt(4, cr.getAnoCad());
                
                pStmCard.executeUpdate();
                
                try(ResultSet lastId = pStmCard.getGeneratedKeys()){
                    if(lastId.next()){
                        cr.setIdTj(lastId.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id card");
                    }
                }
                
                
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
                    
                    PreparedStatement pStmVuelo = _connection.prepareStatement("UPDATE flyfunairlines.flight SET PLAZAS = PLAZAS - 1 WHERE ID_FLY ="+ocOW.getFlight().getIdFlight());
                    pStmVuelo.executeUpdate();
                }
                //aqui el inser en bookig_services
                if(!servicios.isEmpty()){
                for(int k=0 ; k<servicios.size(); k++){
                PreparedStatement pStmServicio = _connection.prepareStatement(serv);
                    pStmServicio.setInt(1, (servicios.get(k)).getIdService());
                    pStmServicio.setInt(2, ocOW.getIdOcupation());
                    
                    pStmServicio.executeUpdate();
                }
                }
            }else{
                Passenger bebe = pasajeros.get(i);
                if( pasajeros.contains(bebe.getAttend()) && !flag ){
                //for(int j=0; j<pasajeros.size(); j++){
                //    if( (bebe.getAttend().getNif()).equalsIgnoreCase((pasajeros.get(j)).getNif()) ){
                    String bebeQ = "INSERT INTO flyfunairlines.passenger VALUES( default, ?,null, ?, ?, ?,null, ?, ?, null, ?)";
                        PreparedStatement pStmPasajerosB = _connection.prepareStatement(bebeQ);
                            pStmPasajerosB.setString(1, (bebe).getNif());
                            pStmPasajerosB.setString(2, null);
                            pStmPasajerosB.setString(3, (bebe).getSurname());
                            pStmPasajerosB.setString(4, (bebe).getName());
                            pStmPasajerosB.setString(5, null);
                            pStmPasajerosB.setString(6, (bebe).getType());
                            pStmPasajerosB.setInt(7, (bebe).getAttend().getIdPassenger() );//aqui necesitamos el id de la persona que está a cargo
                        
                        pStmPasajerosB.executeUpdate();
                    flag = true;
                    }
                }
            
            }
            
        if(tipo.equalsIgnoreCase("vuelta")){
            
            Occupation ocR = bk.getVuelta();
            String occupR = "INSERT INTO flyfunairlines.occupation VALUES (default, '"+bookingCode+"', "+(ocR.getFlight()).getIdFlight()+", ?, ?)";
            String servR = "INSERT INTO flyfunairlines.occupation_service VALUES ( default, ?, ?)";
            ArrayList<Passenger> pasajerosR = ocR.getPassengers();
            
            for( int i=0 ; i<pasajerosR.size() && i<pasajerosR.size() ; i++){
                
            if(!((pasajerosR.get(i)).getType().equalsIgnoreCase("bebe"))){
                // --> SERVICIOS DE VUELTA <--
                ArrayList<Service> serviciosR = (pasajerosR.get(i)).getServices();
                
                //aqui insert en ocupación
                PreparedStatement pStmOcupacionR = _connection.prepareStatement(occupR,Statement.RETURN_GENERATED_KEYS);
                    pStmOcupacionR.setInt(1, (pasajeros.get(i)).getIdPassenger());
                    pStmOcupacionR.setString(2, (pasajerosR.get(i)).getAsiento());
                    
                pStmOcupacionR.executeUpdate();
                   // if(rowO == 0){throw new SQLException("caca insert ocupacion");}
                try(ResultSet lastIdO = pStmOcupacionR.getGeneratedKeys()){
                    if(lastIdO.next()){
                        ocR.setIdOcupation(lastIdO.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id ocupacion");
                    }
                }
                
                PreparedStatement pStmVueloVuelta = _connection.prepareStatement("UPDATE flyfunairlines.flight SET PLAZAS = PLAZAS - 1 WHERE ID_FLY ="+ocR.getFlight().getIdFlight());
                    pStmVueloVuelta.executeUpdate();
                
                //aqui el inser en bookig_services
                if( !serviciosR.isEmpty() ){
                for(int k=0 ; k<serviciosR.size(); k++){
                PreparedStatement pStmServicioR = _connection.prepareStatement(servR);
                    pStmServicioR.setInt(1, (serviciosR.get(k)).getIdService());
                    pStmServicioR.setInt(2, ocR.getIdOcupation());
                    
                    pStmServicioR.executeUpdate();
                    }
                }
            }
            }
            
        }
        String booking = "INSERT INTO flyfunairlines.booking VALUES( default, "+cl.getIdCliente()+", '"+bookingCode+"', "+bk.getrIda()+", "+bk.getrVuelta()+","+bk.getPrecio()+" )";    
            PreparedStatement pStmBooking = _connection.prepareStatement(booking,Statement.RETURN_GENERATED_KEYS);
                pStmBooking.executeUpdate();
                
                try(ResultSet lastIdO = pStmBooking.getGeneratedKeys()){
                    if(lastIdO.next()){
                        bk.setIdBooking(lastIdO.getInt(1));
                    }
                    else{
                        throw new SQLException("caca id ocupacion");
                    }
                }
                
            PreparedStatement pStmPayment = _connection.prepareStatement("INSERT INTO flyfunairlines.payment VALUES(default, ?, ?, ?)");
                pStmPayment.setInt(1, bk.getIdBooking());
                pStmPayment.setInt(2, cl.getIdCliente());
                pStmPayment.setInt(3, cr.getIdTj());
            
                pStmPayment.executeUpdate();
                
            _connection.commit();
            
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
   
    /**
     * @author Coconut
     * @param _connection
     * @param cnx int
     * @return AirConnect, Objeto necesario para la formación del obj. Occupation obtenido con el código de conexión
     */
    
    public AirConnect getAirportsConnected(Connection _connection, int cnx){
        Airport o;
        Airport d;
        AirConnect aC = new AirConnect(cnx);
        try {
            String airportO = "SELECT * FROM flyfunairlines.airport WHERE ID_AIRPORT IN (SELECT T_ORIGEN FROM flyfunairlines.air_connect WHERE ID_CONNECT = ?)";
            PreparedStatement pSaorg = _connection.prepareStatement(airportO);
                pSaorg.setInt(1, cnx);
            ResultSet rsO = pSaorg.executeQuery();
            while(rsO.next()){
                //con la conexion cnx recogemos el aeropuerto
                o = new Airport(rsO.getInt("ID_AIRPORT"), rsO.getString("IATA"), rsO.getString("NOMBRE"), rsO.getString("TERMINAL"), rsO.getString("CIUDAD"), rsO.getString("PAIS"));
                aC.setTermOrigin(o);
            }
            
            String airportD = "SELECT * FROM flyfunairlines.airport WHERE ID_AIRPORT IN (SELECT T_DESTINO FROM flyfunairlines.air_connect WHERE ID_CONNECT = ?)";
            PreparedStatement pStadest = _connection.prepareStatement(airportD);
                pStadest.setInt(1, cnx);
            ResultSet rsD = pStadest.executeQuery();
            while(rsD.next()){
                d = new Airport(rsD.getInt("ID_AIRPORT"), rsD.getString("IATA"), rsD.getString("NOMBRE"), rsD.getString("TERMINAL"), rsD.getString("CIUDAD"), rsD.getString("PAIS"));
                aC.setTermDestiny(d);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aC;
    }
    
    /*
    Necesario un getflight por cliente para recoger tdos los vuelos contratados
    */
    public ArrayList<String> getBookingCustomer(Connection _connection, Client c, String bkp){
            Gson g = new GsonBuilder().create();
            ArrayList<String> cadenas = new ArrayList();
            ArrayList<Booking> reservas = new ArrayList();
            //ArrayList<Flight> vuelos = new ArrayList();
            ArrayList<Passenger> passengers;
            Occupation ocp = null;
            Flight v = null;
            Passenger p = null;
            Passenger bebe = null;
            AirConnect cnxFly;
            Booking r = null;
            String bkcode="";
            String queryBooking="";
            String vuelo ="";
            String pasajeros = "";
            String servicios = "";
            String aux ="";
            
        try {
            if(bkp.isEmpty()){
                queryBooking = "SELECT * FROM flyfunairlines.booking WHERE CLIENTE = ?;";
                vuelo = "SELECT distinct f.* FROM flyfunairlines.flight f join flyfunairlines.occupation o WHERE f.ID_FLY=o.VUELO and f.ID_FLY IN(SELECT o.VUELO FROM flyfunairlines.occupation o WHERE o.BOOKING_CODE=?);";
                pasajeros="select p.*, o.ASIENTO from flyfunairlines.passenger p, flyfunairlines.occupation o where o.VUELO=? and o.BOOKING_CODE=? and p.ID_PASSENGER in (select PASAJERO from flyfunairlines.occupation where BOOKING_CODE=? and PASAJERO=p.ID_PASSENGER and PASAJERO=o.PASAJERO);";
                servicios = "select * from flyfunairlines.service where ID_SERVICE in (select SERVICIO from flyfunairlines.occupation_service where OCUPACION in (select ID_OCP from flyfunairlines.occupation where PASAJERO=? and VUELO=?));";
                                    
            }else{
                queryBooking = "SELECT * FROM flyfunairlines.booking_bkp WHERE CLIENTE = ?;";
                vuelo = "SELECT distinct f.* FROM flyfunairlines.flight_bkp f join flyfunairlines.occupation_bkp o WHERE f.ID_FLY IN(SELECT o.VUELO FROM flyfunairlines.occupation_bkp o WHERE o.BOOKING_CODE=?);";
                pasajeros="select p.*, o.ASIENTO from flyfunairlines.passenger_bkp p, flyfunairlines.occupation_bkp o where o.VUELO=? and o.BOOKING_CODE=? and p.ID_PASSENGER in (select PASAJERO from flyfunairlines.occupation_bkp where BOOKING_CODE=? and PASAJERO=p.ID_PASSENGER and PASAJERO=o.PASAJERO);";
                servicios = "select * from flyfunairlines.service_bkp where ID_SERVICE in (select SERVICIO from flyfunairlines.occupation_service_bkp where OCUPACION in (select ID_OCP from flyfunairlines.occupation_bkp where PASAJERO=? and VUELO=?));";
                    
            }
                PreparedStatement pSreservas = _connection.prepareStatement(queryBooking);
                    pSreservas.setInt(1, c.getIdCliente());
                ResultSet rsB = pSreservas.executeQuery();

                while(rsB.next()){
                    if(rsB.wasNull()){return null;}
                    else{
                        r = new Booking(rsB.getInt("ID_BK"),c, rsB.getFloat("PRECIO"), rsB.getString("IDA"), rsB.getString("VUELTA"));
                        
                //el array de reservas está listo para recabar las distintas ocupaciones
                        bkcode = rsB.getString("BK_CODE");
                        //con el bkcode extraer los vuelos
                        
                        PreparedStatement pSvuelo = _connection.prepareStatement(vuelo);
                            pSvuelo.setString(1, bkcode);

                        ResultSet rsV = pSvuelo.executeQuery();
                        while(rsV.next()){
                            //aqui hay que recoger el aeropuerto para componer la conexion
                            //recogemos rs.getInt("CONNECTION");
                            //cnxFly = new AirConnect(rs.getInt("CONNECTION"));
                            cnxFly = this.getAirportsConnected(_connection, rsV.getInt("CONNECTION"));
                            
                            v = new Flight(rsV.getInt(1), cnxFly , rsV.getString(3), rsV.getDate(4).toLocalDate(), rsV.getTime(5).toLocalTime(), rsV.getFloat(6), rsV.getInt("AVION"), rsV.getInt("PLAZAS"));
                            //El vuelo a occupation
                            
                            PreparedStatement pStpasaje = _connection.prepareStatement(pasajeros);
                                pStpasaje.setInt(1, v.getIdFlight());
                                pStpasaje.setString(2, bkcode);
                                pStpasaje.setString(3, bkcode);

                            ResultSet rsP = pStpasaje.executeQuery();
                            passengers = new ArrayList();
                            
                            while(rsP.next()){//PASAJEROS
                                LocalDate fcad, fnac;
                                //Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type);
                                if( (rsP.getString("FECHA_CAD"))==null || (rsP.getString("FECHA_NAC"))==null ){
                                    fcad = LocalDate.now();
                                    fnac = LocalDate.now();
                                }else{    
                                    fcad = LocalDate.parse(rsP.getString("FECHA_CAD"));
                                    fnac = LocalDate.parse(rsP.getString("FECHA_NAC"));
                                }
                                p = new Passenger(rsP.getInt("ID_PASSENGER"), rsP.getString("NIF"),rsP.getString("PREFIJO"),rsP.getString("NOMBRE"), rsP.getString("APELLIDOS"), rsP.getString("EMAIL"), rsP.getString("TIPO"), rsP.getString("ASIENTO"), null, fcad, fnac, rsP.getString("NACIONALIDAD"));
                                passengers.add(p);
                            }
            
                            Iterator itr = passengers.iterator();
                            //casca aqui, wtf happens?
                                while(itr.hasNext()){//SERVICIOS DE PASAJEROS
                                    p = (Passenger)itr.next();
                                    int id = p.getIdPassenger();
                                    PreparedStatement pSserv = _connection.prepareStatement(servicios);
                                        pSserv.setInt(1, id);
                                        pSserv.setInt(2, v.getIdFlight());
                                        //ahora falla aqui
                                    ResultSet rsS = pSserv.executeQuery();
                                        while(rsS.next()){
                                            p.getServices().add(new Service(rsS.getInt("ID_SERVICE"), rsS.getString("DENOMINACION"), rsS.getString("DESCRIPCION"), rsS.getFloat("PRECIO")));
                                            if(rsS.getString("DENOMINACION").equalsIgnoreCase("infante")){
                                                //FUNCION QUE DEVUELVA EL PASAJERO BEBE DEL PASAJERO QUE POSEE EL SERVICIO
                                                bebe = this.getInfant(_connection, p, bkp);
                                                bebe.setAttend(p);
                                                //passengers.add(bebe);
                                            }
                                        }
                                    }
                                        passengers.add(bebe);
                            ocp = new Occupation(bkcode, v, passengers);
                            
                            if(r.getIda() == null){
                                r.setIda(ocp);
                            }else if(r.getrVuelta() != null){
                                r.setVuelta(ocp);
                            }
                            
                            if(r.getIda() != null && (r.getrVuelta() == null || r.getVuelta() != null)){
                                reservas.add(r);
                                aux = g.toJson(reservas);
                                cadenas.add(aux);
                            }
                        }
                    }
                        
                }
                
            _connection.close();
            
            //SELECT * FROM flyfunairlines.flight f JOIN flyfunairlines.occupation o WHERE f.ID_FLY= o.VUELO AND o.BOOKING_CODE IN
            //(SELECT BK_CODE FROM flyfunairlines.booking WHERE CLIENTE = 23) GROUP BY o.BOOKING_CODE, f.ID_FLY;
            //EXTRAEMOS LOS VUELOS QUE SE REALIZAN CON SU OCUPACION
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            return cadenas;
    }
    
    
    /**
     * @author Coconut
     * @param _connection
     * @param bk String
     * @param nif String
     * @param mail String
     * @return ArrayList de vuelos en los que el usuario introducido puede facturar con su booking code
     */
    public ArrayList<Flight> getFlightCheckIn(Connection _connection, String bk, String nif, String mail){
        Flight v = null;
        ArrayList<Flight> vuelos = new ArrayList();
        AirConnect cnxFly;
        boolean flag = true;
        try {
            String cliente = "SELECT * FROM flyfunairlines.client where NIF=? and EMAIL=?;";
            PreparedStatement pScliente = _connection.prepareStatement(cliente);
                pScliente.setString(1, nif);
                pScliente.setString(2, mail);
            ResultSet rsC = pScliente.executeQuery();
                if(rsC.wasNull()){
                    flag = false;
                }
            if(flag){ 
                String vuelo = "SELECT * FROM flyfunairlines.flight f WHERE f.F_SALIDA < date_add(sysdate(), interval 50 day) AND f.ID_FLY IN(SELECT o.VUELO FROM flyfunairlines.occupation o WHERE o.BOOKING_CODE=?);";
                PreparedStatement pSvuelo = _connection.prepareStatement(vuelo);
                    pSvuelo.setString(1, bk);

                ResultSet rs = pSvuelo.executeQuery();
                while(rs.next()){
                    //aqui hay que recoger el aeropuerto para componer la conexion
                    //recogemos rs.getInt("CONNECTION");
                    //cnxFly = new AirConnect(rs.getInt("CONNECTION"));
                    cnxFly = this.getAirportsConnected(_connection, rs.getInt("CONNECTION"));
                    
                    v = new Flight(rs.getInt(1), cnxFly , rs.getString(3), rs.getDate(4).toLocalDate(), rs.getTime(5).toLocalTime(), rs.getFloat(6), rs.getInt("AVION"), rs.getInt("PLAZAS"));
                    vuelos.add(v);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vuelos;
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param p Passenger
     * @return Passenger bebe con su adulto correspondiente asignado
     */
    public Passenger getInfant(Connection _connection, Passenger p){
        Passenger aux = null;
        try {
            PreparedStatement pStbebe = _connection.prepareStatement("SELECT * FROM flyfunairlines.passenger WHERE CARGO=?");
                pStbebe.setInt(1, p.getIdPassenger());
            
                ResultSet rsBe = pStbebe.executeQuery();
                while(rsBe.next()){
                    LocalDate fcad, fnac;
                    if(rsBe.getString("FECHA_CAD")==null || rsBe.getString("FECHA_NAC")==null ){
                        fcad = LocalDate.now();
                        fnac = LocalDate.now();
                    }else{    
                        fcad = LocalDate.parse(rsBe.getString("FECHA_CAD"));
                        fnac = LocalDate.parse(rsBe.getString("FECHA_NAC"));
                    }
                    
                    aux = new Passenger(rsBe.getInt("ID_PASSENGER"), rsBe.getString("NIF"), "infante", rsBe.getString("NOMBRE"), rsBe.getString("APELLIDOS"), null, rsBe.getString("TIPO"), null, null, fcad, fnac, rsBe.getString("NACIONALIDAD"));
                }
                            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
           return aux; 
        
    }
    
    public Passenger getInfant(Connection _connection, Passenger p, String bkp){
        Passenger aux = null;
        try {
            PreparedStatement pStbebe = _connection.prepareStatement("SELECT * FROM flyfunairlines.passenger"+bkp+" WHERE CARGO=?");
                pStbebe.setInt(1, p.getIdPassenger());
            
                ResultSet rsBe = pStbebe.executeQuery();
                while(rsBe.next()){
                    LocalDate fcad, fnac;
                    if(rsBe.getString("FECHA_CAD")==null || rsBe.getString("FECHA_NAC")==null ){
                        fcad = LocalDate.now();
                        fnac = LocalDate.now();
                    }else{    
                        fcad = LocalDate.parse(rsBe.getString("FECHA_CAD"));
                        fnac = LocalDate.parse(rsBe.getString("FECHA_NAC"));
                    }
                    
                    aux = new Passenger(rsBe.getInt("ID_PASSENGER"), rsBe.getString("NIF"), "infante", rsBe.getString("NOMBRE"), rsBe.getString("APELLIDOS"), null, rsBe.getString("TIPO"), null, null, fcad, fnac, rsBe.getString("NACIONALIDAD"));
                }
                            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
           return aux; 
        
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param bk String
     * @param f Flight 
     * @return ArrayList de Pasajeros con ocupacion a facturar
     */
    public ArrayList<Passenger> getPassengersCheckIn(Connection _connection, String bk, Flight f){
        ArrayList<Passenger> passengers = new ArrayList();
        ArrayList<Service> services = new ArrayList();
        Passenger p;
        Passenger aux = null;
        try {
            String pasajeros="select p.*, o.ASIENTO from flyfunairlines.passenger p, flyfunairlines.occupation o where o.VUELO=? and o.BOOKING_CODE=? and p.ID_PASSENGER in (select PASAJERO from flyfunairlines.occupation where BOOKING_CODE=? and PASAJERO=p.ID_PASSENGER and PASAJERO=o.PASAJERO);";
            PreparedStatement pStpasaje = _connection.prepareStatement(pasajeros);
                pStpasaje.setInt(1, f.getIdFlight());
                pStpasaje.setString(2, bk);
                pStpasaje.setString(3, bk);
                
            ResultSet rsP = pStpasaje.executeQuery();
            
            while(rsP.next()){
                LocalDate fcad, fnac;
                //Passenger(int idPassenger, String nif, String prefix, String name, String surname, String eMail, String type);
                if( (rsP.getString("FECHA_CAD"))==null || (rsP.getString("FECHA_NAC"))==null ){
                    fcad = LocalDate.now();
                    fnac = LocalDate.now();
                }else{    
                    fcad = LocalDate.parse(rsP.getString("FECHA_CAD"));
                    fnac = LocalDate.parse(rsP.getString("FECHA_NAC"));
                }
                aux = new Passenger(rsP.getInt("ID_PASSENGER"), rsP.getString("NIF"),rsP.getString("PREFIJO"),rsP.getString("NOMBRE"), rsP.getString("APELLIDOS"), rsP.getString("EMAIL"), rsP.getString("TIPO"), rsP.getString("ASIENTO"), null, fcad, fnac, rsP.getString("NACIONALIDAD"));
                passengers.add(aux);
            }
            
            Iterator itr = passengers.iterator();
            //casca aqui, wtf happens?
            while(itr.hasNext()){
                p = (Passenger)itr.next();
                int id = p.getIdPassenger();
                String servicios = "select * from flyfunairlines.service where ID_SERVICE in (select SERVICIO from flyfunairlines.occupation_service where OCUPACION in (select ID_OCP from flyfunairlines.occupation where PASAJERO=? and VUELO=?));";
                PreparedStatement pSserv = _connection.prepareStatement(servicios);
                    pSserv.setInt(1, id);
                    pSserv.setInt(2, f.getIdFlight());
                    //ahora falla aqui
                ResultSet rsS = pSserv.executeQuery();
                    while(rsS.next()){
                        p.getServices().add(new Service(rsS.getInt("ID_SERVICE"), rsS.getString("DENOMINACION"), rsS.getString("DESCRIPCION"), rsS.getFloat("PRECIO")));
                        if(rsS.getString("DENOMINACION").equalsIgnoreCase("infante")){
                            //FUNCION QUE DEVUELVA EL PASAJERO BEBE DEL PASAJERO QUE POSEE EL SERVICIO
                            aux = this.getInfant(_connection, p);
                            aux.setAttend(p);
                            passengers.add(aux);
                        }
                    }
            }
                //
                
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passengers;
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param oc Occupation
     * 
     */
    public void checkIn(Connection _connection, Occupation oc){
        
        try {
            _connection.setAutoCommit(false);
            
            this.updatePassenger(_connection, oc);
            this.updateBooking(_connection, oc);
            
            _connection.commit();
            
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
    
    /**
     * @author Coconut
     * @param _connection
     * @param oc Occupation
     * @throws SQLException 
     */
    public void updatePassenger(Connection _connection, Occupation oc) throws SQLException{
        ArrayList<Passenger> ap = oc.getPassengers();
        Iterator itr = ap.iterator();
        Passenger p = null;
        int er;
            //_connection.setAutoCommit(false);
            
            while(itr.hasNext()){
                p = (Passenger)itr.next();
            PreparedStatement pSupdate = _connection.prepareStatement("UPDATE flyfunairlines.passenger SET FECHA_CAD=?, NACIONALIDAD=?, FECHA_NAC=? where ID_PASSENGER=?");
                LocalDate fcad = p.getFechaCaducidadNif();
                pSupdate.setString(1, fcad.toString());//fecha caducidad nif
                pSupdate.setString(2, p.getNacionalidad());//nacionalidad
                LocalDate fnac = p.getFechaNacimiento();
                pSupdate.setString(3, fnac.toString());//fecha nacimiento
                pSupdate.setInt(4, p.getIdPassenger());//id pasajero
            
            er = pSupdate.executeUpdate();
            if(er==0)throw new SQLException();
            if( !((p.getType()).equalsIgnoreCase("bebe"))){
            PreparedStatement slctOc = _connection.prepareStatement("SELECT * FROM flyfunairlines.occupation WHERE BOOKING_CODE=? AND VUELO=? AND PASAJERO=?");
                slctOc.setString(1, oc.getBookingCode());
                slctOc.setInt(2, (oc.getFlight()).getIdFlight());
                slctOc.setInt(3, p.getIdPassenger());
                
                ResultSet rsO = slctOc.executeQuery();
                
                while(rsO.next()){
                    oc.setIdOcupation(rsO.getInt("ID_OCP"));
                }
            
            PreparedStatement pSupdtOcc = _connection.prepareStatement("UPDATE flyfunairlines.occupation SET ASIENTO = ? WHERE ID_OCP= ?");
                pSupdtOcc.setString(1, p.getAsiento());
                pSupdtOcc.setInt(2, oc.getIdOcupation());
                
                er = pSupdtOcc.executeUpdate();
                if(er==0)throw new SQLException();
            }
            }
           
    }
    
    /**
     * @author Coconut
     * @param _connection
     * @param oc
     * @return true existe viaje de vuelta, false no existe viaje de vuelta o se trata de ese mismo, luego la ida se ha facturado ya
     */
    public boolean checkFlight(Connection _connection, Occupation oc){
        try {
            //SELECT DISTINCT f.* FROM flyfunairlines.occupation o JOIN flyfunairlines.flight f WHERE o.BOOKING_CODE='abm657en' AND o.VUELO=f.ID_FLY AND f.F_SALIDA > (SELECT F_SALIDA FROM flyfunairlines.flight WHERE ID_FLY = 7);
            PreparedStatement pSf = _connection.prepareStatement("SELECT DISTINCT f.* FROM flyfunairlines.occupation o JOIN flyfunairlines.flight f WHERE o.BOOKING_CODE=? AND o.VUELO=f.ID_FLY AND f.F_SALIDA > (SELECT F_SALIDA FROM flyfunairlines.flight WHERE ID_FLY = ?)");
                pSf.setString(1, oc.getBookingCode());
                pSf.setInt(2, (oc.getFlight()).getIdFlight());
            ResultSet rsF = pSf.executeQuery();
            
            if(rsF.wasNull()){
                return false;
            }else{
                return true;
            }
            
            /*
            Con esta consulta nos devuelve una fila (true) si existe el vuelo de vuelta y
            no devuelve nada (false) cuando este no existe
            
            true <=> existe vuelta
            false <=> no existe vuelta o se trata de la vuelta
            
            si false, con booking_code update booking:
            ida <=> vuelta == null
            vuelta <==> existe vuelta
            
            si true, con booking_code update booking ida <=> existe vuelta
            */
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
    /**
     * @author Coconut
     * @param _connection
     * @param oc Occupation
     * 
     */
    public void updateBooking(Connection _connection, Occupation oc){
        Booking bk = null;
        boolean flag = false;
        try {
            PreparedStatement pSbking = _connection.prepareStatement("SELECT * FROM flyfunairlines.booking WHERE BK_CODE=?");
                pSbking.setString(1, oc.getBookingCode());
                 
                ResultSet rsBK = pSbking.executeQuery();
                while(rsBK.next()){
                    bk = new Booking(rsBK.getInt("ID_BK"), rsBK.getFloat("PRECIO"), rsBK.getString("IDA"), rsBK.getString("VUELTA"));
                }
                
            if( this.checkFlight(_connection, oc) ){
                //verdad que existe vuelta, luego se actualiza solo ida porque se refiere a ese vuelo
                
                PreparedStatement pSUbk =  _connection.prepareStatement("UPDATE flyfunairlines.booking SET IDA='1' WHERE ID_BK=?");
                    pSUbk.setInt(1, bk.getIdBooking());
                pSUbk.executeUpdate();
                
            }else{
                //no existe fila de vuelo de vuelta luego, o se trata del vuelo de vuelta o se trata del unico vuelo de ida
                if(bk.getrVuelta().isEmpty()){
                    //variable de vuelta vacía, se trata de un check in de un vuelo unico de ida
                    PreparedStatement pSUbk =  _connection.prepareStatement("UPDATE flyfunairlines.booking SET IDA='1' WHERE ID_BK=?");
                        pSUbk.setInt(1, bk.getIdBooking());
                    pSUbk.executeUpdate();
                }else{
                    //variable de vuelta > 0 , se trata de un check in del vuelo de vuelta
                    PreparedStatement pSUbk =  _connection.prepareStatement("UPDATE flyfunairlines.booking SET VUELTA='1' WHERE ID_BK=?");
                        pSUbk.setInt(1, bk.getIdBooking());
                    pSUbk.executeUpdate();
                }
            }
                
            
            /*
            En esta funcion se actualiza la tupla de booking que cumpla con los requisitos:
            todos los pasajeros actualizados correctamente
            identificada la actualizacion de ida o vuelta
        
            if true : update flyfunairlines.booking set ida=1 where vuelta == 0
            
            if false: update flyfunairlines.booking set ida=1 where vuelta == null
            update flyfunairlines.booking set vuelta = 1 where ida = 0 or ida = 1;
            
            puesto que necesitamos el id para hacer el update 1º select * booking where bk_code = 'asdf' y
            segun los datos de ida y vuelta recibidos de esa consulta procedemos con un update u otro en caso de
            checkFlight::false.
            */
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteFlight(Connection _connection, String nVuelo, String fecha){
        int idV = 0;
        try {
//        Date date = java.sql.Date.valueOf(fecha);
            _connection.setAutoCommit(false);
            
            try {
                PreparedStatement pSslct = _connection.prepareStatement("SELECT ID_FLY FROM flyfunairlines.flight WHERE N_VUELO = ? AND F_SALIDA = ?");
                pSslct.setString(1, nVuelo);
                pSslct.setString(2, fecha);
            
                ResultSet rs = pSslct.executeQuery();
                
                while(rs.next()){
                    idV = rs.getInt("ID_FLY");
                }
                
                PreparedStatement pSdlt =  _connection.prepareStatement("DELETE FROM flyfunairlines.flight WHERE ID_FLY = ?");
                pSdlt.setInt(1, idV);
                
                pSdlt.executeUpdate();
                _connection.commit();
                
            } catch (SQLException ex) {
                ex.getMessage();
                if(_connection != null){
                    try{
                        _connection.rollback();
                    }catch(SQLException SQLEx2){
                        SQLEx2.getMessage();
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
