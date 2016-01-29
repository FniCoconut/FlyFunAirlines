/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.MODEL.Flight;
import FlyFunPackage.MODEL.Occupation;
import FlyFunPackage.MODEL.Passenger;
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
public class servletPasajeros extends HttpServlet {

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
            ArrayList<Passenger> pasajeros = new ArrayList();
            Occupation OOW = (Occupation)session.getAttribute("occupationOW");
            
            
            int adult = (int)session.getAttribute("adult");
            int child = (int)session.getAttribute("child");
            int infant = (int)session.getAttribute("infant");
            
            for(int i=0; i < adult ; i++){
                String prefix = request.getParameter("prefix"+i);
                String name = request.getParameter("name"+i);
                String surname = request.getParameter("surname"+i);
                String nif = request.getParameter("nif"+i);
                String email = request.getParameter("email"+i);
                
                Passenger adulto = new Passenger(nif, prefix, name, surname, email, "adulto");
                pasajeros.add(adulto);
                out.println(adulto.getNif());
            }
            
            for(int i=0; i < child ; i++){
                String name = request.getParameter("nameNino"+i);
                String surname = request.getParameter("surnameNino"+i);
                String nif = request.getParameter("nifNino"+i);
                String email = request.getParameter("emailNino"+i);
            
                Passenger nino = new Passenger(nif, null, name, surname, email, "niño");
                pasajeros.add(nino);
                out.println(nino.getNif());
            }
            
            for(int i=0; i < infant ; i++){
                String name = request.getParameter("nameBebe"+i);
                String surname = request.getParameter("surnameBebe"+i);
                String nif = request.getParameter("nifBebe"+i);
            
                Passenger bebe = new Passenger(nif, null, name, surname, null, "bebe");
                pasajeros.add(bebe);
                out.println(bebe.getNif());
            }
            
            //OOW.setPassengers(pasajeros);
            
            if (((String)session.getAttribute("kindTrip")).equals("vuelta")){
                Occupation OR = (Occupation)session.getAttribute("occupationR");
                //OR.setPassengers(pasajeros);
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletPasajeros</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletPasajeros at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
