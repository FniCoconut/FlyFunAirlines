/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.DAO.ConnectionBBDD;
import FlyFunPackage.DAO.Operation;
import FlyFunPackage.MODEL.Booking;
import FlyFunPackage.MODEL.Client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
public class servletReservasCliente extends HttpServlet {
    
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
            Client cliente = (Client)session.getAttribute("client");
            int caso = Integer.valueOf(request.getParameter("caso"));
            ArrayList<String> reservas = new ArrayList();
            
            //Type listaReservas = new TypeToken<ArrayList<Booking>>(){}.getType();
            Gson total = new GsonBuilder().create();
            
            switch(caso){
                case 1:
                    reservas = new Operation().getBookingCustomer(connection, cliente, ""); //esto devuelev un array de reservas en curso
                    cliente.addBooking(reservas);
                    session.setAttribute("reservasEnCurso", reservas);
                    
                    String string1 = total.toJson(reservas);
                    out.print(string1);
                    break;
                case 2:
                    reservas = new Operation().getBookingCustomer(connection, cliente, "_bkp"); //esto deuelve un array de viajes realizados
                    cliente.addBooking(reservas);
                    session.setAttribute("reservasRealizadas", reservas);
                    
                    String string2 = total.toJson(reservas); 
                    out.print(string2);
                    break;
            }
            
            
            
            
            
            //ArrayList<Booking> puaj = cliente.getBooking();
            //response.sendRedirect("reservas.jsp");
            
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
