/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.DAO.ConnectionBBDD;
import FlyFunPackage.DAO.Operation;
import FlyFunPackage.MODEL.Occupation;
import com.google.gson.Gson;
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
public class servletAsientos extends HttpServlet {

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
            ArrayList asientos = new ArrayList();
            ArrayList asientosV = new ArrayList();
            ArrayList total = new ArrayList();
            
            Gson g = new Gson();
//            Gson ida = new Gson();
//            Gson vuelta = new Gson();
            
            Occupation OOW = (Occupation)session.getAttribute("occupationOW");
                asientos = new Operation().getOccupation(connection, OOW.getFlight());
                total.add(asientos);
//                ida.toJson(asientos);
//                total.add(ida);
            if (((String)session.getAttribute("kindTrip")).equals("vuelta")){
                
            Occupation OR = (Occupation)session.getAttribute("occupationR");
                asientosV = new Operation().getOccupation(connection, OR.getFlight());
                total.add(asientosV);
//                vuelta.toJson(asientosV);
//                total.add(vuelta);
            }
            
            out.print(g.toJson(total));
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
