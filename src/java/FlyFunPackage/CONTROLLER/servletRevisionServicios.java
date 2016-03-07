/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.MODEL.Booking;
import FlyFunPackage.MODEL.Client;
import FlyFunPackage.MODEL.Occupation;
import FlyFunPackage.MODEL.Passenger;
import FlyFunPackage.MODEL.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Coconut
 */
public class servletRevisionServicios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            char[] elementos={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

            char[] conjunto = new char[8];
            String bkcode = "";

            for(int i=0;i<8;i++){
                int el = (int)(Math.random()*37);
                conjunto[i] = (char)elementos[el];
            }
            bkcode = new String(conjunto);
            
            
            HttpSession session = request.getSession(true);
            
            Occupation ida = (Occupation)session.getAttribute("occupationOW");
            ida.setBookingCode(bkcode);
            
            Client cliente = (Client)session.getAttribute("client");
            
            String kind = (String)session.getAttribute("kindTrip");
            
            //servicios de la BBDD
            ArrayList<Service> services = (ArrayList)session.getAttribute("services");
           // Occupation ocOW = (Occupation)session.getAttribute("occupationOW");
            ArrayList<Passenger> passengers = ((Occupation)session.getAttribute("occupationOW")).getPassengers();
            
            ArrayList<Passenger> passengersReturn = new ArrayList();
            
            for(int i=0; i<passengers.size() ; i++){
                if(passengers.get(i).getServices() != null){
                for(int j=0; j<(passengers.get(i)).getServices().size() ; j++){
                    
                    if((passengers.get(i).getServices().get(j)).getDenomination().equalsIgnoreCase("Asiento")){
                        if( request.getParameter("asiento"+i) != null){
                            
                            String kindOfSeat = request.getParameter("asiento"+i);
                            passengers.get(i).setAsiento(kindOfSeat);
                            kindOfSeat = kindOfSeat.substring(0, 1);
                            
                            if(kindOfSeat.equalsIgnoreCase("p")){
                               (passengers.get(i).getServices().get(j)).setDenomination("AsientoPremium");
                            }
                        }else{response.sendRedirect("revisionServicios.jsp");}
                    }
                    //ASIGNAR ASIENTO AL OBJ PASAJERO
                    for(int k=0 ; k<services.size(); k++){
                                                
                        if(passengers.get(i).getServices().get(j).getDenomination().equalsIgnoreCase(services.get(k).getDenomination())){
                            //necesitamos una bandera de victoria porque
                            passengers.get(i).getServices().get(j).setIdService(services.get(k).getIdService());
                            passengers.get(i).getServices().get(j).setDescription(services.get(k).getDescription());
                            passengers.get(i).getServices().get(j).setFrecio(services.get(k).getFrecio());
                            //hemos encontrado en lo mas profundo un servicio
                        }
                    }
                }
                }
            }
            String codI = "1";
            Occupation vuelta = null;
            String codV = "null";
            //servicios de vuelta
            if( kind.equalsIgnoreCase("vuelta")){
                codV = "1";
                vuelta = (Occupation)session.getAttribute("occupationR");
                vuelta.setBookingCode(bkcode);
                passengersReturn = ((Occupation)session.getAttribute("occupationR")).getPassengers();
           // passengersReturn = (ArrayList)session.getAttribute("passengerR");
            
            for(int i=0; i<passengersReturn.size() ; i++){
                if(passengersReturn.get(i).getServices() != null){
                for(int j=0; j<passengersReturn.get(i).getServices().size() ; j++){
                    
                    if((passengersReturn.get(i).getServices().get(j)).getDenomination().equalsIgnoreCase("Asiento")){
                        if( request.getParameter("asientoV"+i) != null){
                            
                            String kindOfSeat = request.getParameter("asientoV"+i);
                            passengersReturn.get(i).setAsiento(kindOfSeat);
                            kindOfSeat = kindOfSeat.substring(0, 1);
                            
                            if(kindOfSeat.equalsIgnoreCase("p")){
                               (passengersReturn.get(i).getServices().get(j)).setDenomination("AsientoPremium");
                            }
                        }else{response.sendRedirect("revisionServicios.jsp");}
                    }
                    
                    for(int k=0 ; k<services.size(); k++){
                                                
                        if(passengersReturn.get(i).getServices().get(j).getDenomination().equalsIgnoreCase(services.get(k).getDenomination()) ){
                            //necesitamos una bandera de victoria porque
                            passengersReturn.get(i).getServices().get(j).setIdService(services.get(k).getIdService());
                            passengersReturn.get(i).getServices().get(j).setDescription(services.get(k).getDescription());
                            passengersReturn.get(i).getServices().get(j).setFrecio(services.get(k).getFrecio());
                            //hemos encontrado en lo mas profundo un servicio
                        }
                    }
                }
            }
            }  
            }
            
            Booking reserva = new Booking(null, ida, vuelta, codI, codV);
            session.setAttribute("booking", reserva);
            response.sendRedirect("pago.jsp");
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
