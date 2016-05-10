/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.DAO.ConnectionBBDD;
import FlyFunPackage.DAO.Operation;
import FlyFunPackage.MODEL.Occupation;
import FlyFunPackage.MODEL.Passenger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Coconut
 */
public class servletCheckInPasajero extends HttpServlet {

    private Connection connection;
    private ConnectionBBDD connectionBBDD;
    
    
    @Override
    public void init() throws ServletException{
        
        try{
            connectionBBDD = ConnectionBBDD.GetConexion();
            connection = connectionBBDD.GetCon();
        }catch(ClassNotFoundException cnfe){  
                }
        catch(SQLException sqle){
        }
    }
    
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
           HttpSession session = request.getSession(true);
           Occupation oc = (Occupation)session.getAttribute("occupationCheckIn");
           ArrayList<Passenger> pasajeros = oc.getPassengers();
                   
           Iterator itr = pasajeros.iterator();
           Passenger p = null;
           
            while(itr.hasNext()){
                p = (Passenger)itr.next();
                LocalDate cad = LocalDate.parse(request.getParameter("fcad"+p.getIdPassenger()));
                LocalDate nac = LocalDate.parse(request.getParameter("fnac"+p.getIdPassenger()));
                String nacion = request.getParameter("nacionalidad"+p.getIdPassenger());
                String asiento = request.getParameter("asiento"+p.getIdPassenger());
                
                p.setAsiento(asiento);
                p.setFechaCaducidadNif(cad);
                p.setFechaNacimiento(nac);
                p.setNacionalidad(nacion); 
                
            }
            
            new Operation().checkIn(connection, oc);
            //ahora hay que actualizar los datos
            //¿Cómo saber si es ida o vuelta?
            //Creando la ocupacion y comparando la fecha de vuelo o 
            //añadiendo un campo a la ocupacion que se repetirá en todas las filas
            //pero puede que haga mas facil la eliminación 
            
            //aqui generar pdf con los datos de los pasajeros y la ocupacion
            response.sendRedirect("embarque.jsp");
            
            //hay que imprimir la tarjeta de embarque
            
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
