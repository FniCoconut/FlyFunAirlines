/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.CONTROLLER;

import FlyFunPackage.DAO.ConnectionBBDD;
import FlyFunPackage.DAO.Operation;
import FlyFunPackage.MODEL.AirConnect;
import FlyFunPackage.MODEL.Airport;
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
public class servletOrigenDestino extends HttpServlet {

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
                ArrayList<Airport> _origins = (ArrayList)session.getAttribute("airportsO");
                Airport origen = null;
            ArrayList<Airport> _destinys = (ArrayList)session.getAttribute("airportsD");
                Airport destino = null;
                
            ArrayList total= new ArrayList();
            
            int O = Integer.parseInt(request.getParameter("o"));
                for(int i=0; i < _origins.size(); i++){
                    if( _origins.get(i).getIdAirport() == O){
                        origen = new Airport(_origins.get(i).getIdAirport(), _origins.get(i).getIata(), _origins.get(i).getName(), _origins.get(i).getTerm(), _origins.get(i).getCity(), _origins.get(i).getCountry(), _origins.get(i).getKey());
                    }
                }
            // origen = obj Aeropuerto de origen    
            total.add(origen);
            
            int D = Integer.parseInt(request.getParameter("d"));
                for(int i=0; i < _destinys.size(); i++){
                    if( _destinys.get(i).getIdAirport() == D){
                        destino = new Airport(_destinys.get(i).getIdAirport(), _destinys.get(i).getIata(), _destinys.get(i).getName(), _destinys.get(i).getTerm(),  _destinys.get(i).getCity(), _destinys.get(i).getCountry(), _destinys.get(i).getKey());
                    }
                }
            
            total.add(destino);
            
            Gson gson = new Gson();
            String str=gson.toJson(total);
            
//            String str = "Viaje desde: "+origen.getCity()+", "+origen.getName()+"<br>"+"hasta: "+destino.getCity()+", "+destino.getName();
            out.print(str);
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
