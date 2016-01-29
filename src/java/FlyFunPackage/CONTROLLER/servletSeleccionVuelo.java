/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.MODEL.Flight;
import FlyFunPackage.MODEL.Occupation;
import java.io.IOException;
import java.io.PrintWriter;
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
public class servletSeleccionVuelo extends HttpServlet {

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
                ArrayList<Flight> _vuelosIda= (ArrayList)session.getAttribute("owFly");
            //recogemos el vuelo necesario, el elegido
            int idOWTrip = Integer.parseInt(request.getParameter("vueloIda"));
            Occupation occupationOneWay = null;
            
            for(int i = 0; i < _vuelosIda.size() ; i++){
                if(_vuelosIda.get(i).getIdFlight() == idOWTrip){ occupationOneWay = new Occupation(_vuelosIda.get(i)); }
            }
            session.setAttribute("occupationOW", occupationOneWay);
            String trip = (String)session.getAttribute("kindTrip");
            
            if(trip.equalsIgnoreCase("vuelta")){
                ArrayList<Flight> _vuelosVuelta = (ArrayList)session.getAttribute("rFly");
                int idRTrip = Integer.parseInt(request.getParameter("vueloVuelta"));
                Occupation occupationReturn = null;
                
                for(int i = 0; i < _vuelosVuelta.size() ; i++){
                    if(_vuelosVuelta.get(i).getIdFlight() == idRTrip){occupationReturn = new Occupation(_vuelosVuelta.get(i));}
                }
                session.setAttribute("occupationR", occupationReturn);
            }
            
            response.sendRedirect("pasajero.jsp");
            
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
