/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.DAO.ConnectionBBDD;
import FlyFunPackage.DAO.Operation;
import FlyFunPackage.MODEL.Booking;
import FlyFunPackage.MODEL.Card;
import FlyFunPackage.MODEL.Client;
import FlyFunPackage.MODEL.Occupation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Coconut
 */
public class servletPago extends HttpServlet {

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
            Occupation oOW = (Occupation)session.getAttribute("occupationOW");
            Occupation oR = null;
            Booking booking = (Booking)session.getAttribute("booking");
                        
            Client cliente = (Client)session.getAttribute("client");
               
            String nifCliente = cliente.getNif();
            
            String numTarjeta = request.getParameter("tjNumber");
            String cvv = request.getParameter("cvvTj");
            int mesCad = Integer.parseInt(request.getParameter("mesCad"));
            int anoCad = Integer.parseInt(request.getParameter("anoCad"));
            
            Card tjt = new Card(numTarjeta, mesCad, anoCad);
            
            cliente.setCard(tjt);
            
            booking.setClient(cliente);
                booking.priceCalc();
                
//            if(((String)session.getAttribute("kindTrip")).equalsIgnoreCase("vuelta")){
//                //oR = (Occupation)session.getAttribute("occupationR");
//                booking.setClient(cliente);
//                booking.priceCalc();
//            }else{
//                booking.setClient(cliente);
//                booking.priceCalc();
//            }
            
                    new Operation().insertBooking(connection, booking, (String)session.getAttribute("kindTrip"));
                    session.invalidate();
                    
                    session = request.getSession(true);
                    session.invalidate();
                    
                    session.setAttribute("client", cliente);
                    
                    response.sendRedirect("viajereservado.jsp");
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
