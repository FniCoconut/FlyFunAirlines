package FlyFunPackage.CONTROLLER;

import FlyFunPackage.MODEL.Client;
import FlyFunPackage.MODEL.Flight;
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
public class servletPasajeroServicio extends HttpServlet {

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
            
            ArrayList<Passenger> pasajeros = new ArrayList();
            ArrayList<Service> servicios;
            
            Occupation OOW = (Occupation)session.getAttribute("occupationOW");
            
            
            int adult = (int)session.getAttribute("adult");
            int child = (int)session.getAttribute("child");
            int infant = (int)session.getAttribute("infant");
            
            int asiento = 0;
            int asientoV = 0;
            
            for(int i=0; i < adult ; i++){
                //datos de adulto
                String prefix = request.getParameter("prefix"+i);
                String name = request.getParameter("name"+i);
                String surname = request.getParameter("surname"+i);
                String nif = request.getParameter("nif"+i);
                String email = request.getParameter("email"+i);
                
                //servicios de adulto
                servicios = new ArrayList();
                String servicioAsiento = request.getParameter("asiento"+i);
                if( !(servicioAsiento == null)){ servicios.add(new Service(servicioAsiento)); asiento++;}
                String equipaje = request.getParameter("equipaje"+i);
                if( !(equipaje == null)){ servicios.add(new Service(equipaje)); }
                String seguro = request.getParameter("seguro"+i);
                if( !(seguro == null)){ servicios.add(new Service(seguro)); }
                
                Passenger adulto = new Passenger(nif, prefix, name, surname, email, "adulto", servicios);
                pasajeros.add(adulto);
            }
            
            for(int i=0; i < child ; i++){
                //datos de niño
                String name = request.getParameter("nameNino"+i);
                String surname = request.getParameter("surnameNino"+i);
                String nif = request.getParameter("nifNino"+i);
                String email = request.getParameter("emailNino"+i);
            
                //servicios de niño
                servicios = new ArrayList();
                String servicioAsiento = request.getParameter("asientoNino"+i);
                if( !(servicioAsiento == null)){ servicios.add(new Service(servicioAsiento)); asiento++; }
                String equipaje = request.getParameter("equipajeNino"+i);
                if( !(equipaje == null)){ servicios.add(new Service(equipaje)); }
                String seguro = request.getParameter("seguroNino"+i);
                if( !(seguro == null)){ servicios.add(new Service(seguro)); }
                
                Passenger nino = new Passenger(nif, null, name, surname, email, "niño", servicios);
                pasajeros.add(nino);
            }
            
            for(int i=0; i < infant ; i++){
                //datos de bebé
                Passenger bebe = null;
                String name = request.getParameter("nameBebe"+i);
                String surname = request.getParameter("surnameBebe"+i);
                String nif = request.getParameter("nifBebe"+i);
                String adultoCargo = request.getParameter("adultoCargo"+i);
            
                for(int j=0 ;j<pasajeros.size();j++){
                    if(pasajeros.get(j).getNif().equalsIgnoreCase(adultoCargo)){
                    bebe = new Passenger(nif, null, name, surname, null, "bebe", pasajeros.get(j));
                    
                    }
                }
                pasajeros.add(bebe);
            }
            
            //session.setAttribute("passengerOW", pasajeros);
            OOW.setPassengers(pasajeros);
            
            // --> DATOS DE VUELTA <--
            if (((String)session.getAttribute("kindTrip")).equals("vuelta")){
                
            ArrayList<Passenger> pasajerosVuelta = new ArrayList();
            ArrayList<Service> serviciosVuelta;
                
            for(int i=0; i < adult ; i++){
                //datos de adulto
                String prefix = request.getParameter("prefix"+i);
                String name = request.getParameter("name"+i);
                String surname = request.getParameter("surname"+i);
                String nif = request.getParameter("nif"+i);
                String email = request.getParameter("email"+i);
                
                //servicios de adulto
                serviciosVuelta = new ArrayList();
                String servicioAsiento = request.getParameter("asientoV"+i);
                if( !(servicioAsiento == null)){ serviciosVuelta.add(new Service(servicioAsiento)); asientoV++; }
                String equipaje = request.getParameter("equipajeV"+i);
                if( !(equipaje == null)){ serviciosVuelta.add(new Service(equipaje)); }
                String seguro = request.getParameter("seguroV"+i);
                if( !(seguro == null)){ serviciosVuelta.add(new Service(seguro)); }
                
                Passenger adulto = new Passenger(nif, prefix, name, surname, email, "adulto", serviciosVuelta);
                pasajerosVuelta.add(adulto);
            }
            
            for(int i=0; i < child ; i++){
                //datos de niño
                String name = request.getParameter("nameNino"+i);
                String surname = request.getParameter("surnameNino"+i);
                String nif = request.getParameter("nifNino"+i);
                String email = request.getParameter("emailNino"+i);
            
                //servicios de niño
                serviciosVuelta = new ArrayList();
                String servicioAsiento = request.getParameter("asientoNinoV"+i);
                if( !(servicioAsiento == null)){ serviciosVuelta.add(new Service(servicioAsiento)); asientoV++; }
                String equipaje = request.getParameter("equipajeNinoV"+i);
                if( !(equipaje == null)){ serviciosVuelta.add(new Service(equipaje)); }
                String seguro = request.getParameter("seguroNinoV"+i);
                if( !(seguro == null)){ serviciosVuelta.add(new Service(seguro)); }
                
                Passenger nino = new Passenger(nif, null, name, surname, email, "niño", serviciosVuelta);
                pasajerosVuelta.add(nino);
            }
            
            for(int i=0; i < infant ; i++){
                //datos de bebé
                Passenger bebe = null;
                String name = request.getParameter("nameBebe"+i);
                String surname = request.getParameter("surnameBebe"+i);
                String nif = request.getParameter("nifBebe"+i);
                String adultoCargo = request.getParameter("adultoCargo"+i);
                
                for(int j=0 ;j<pasajeros.size();j++){
                    if(pasajeros.get(j).getNif().equalsIgnoreCase(adultoCargo)){
                    bebe = new Passenger(nif, null, name, surname, null, "bebe", pasajeros.get(j));
                    }
                }
                pasajerosVuelta.add(bebe);
                //Passenger bebe = new Passenger(nif, null, name, surname, null, "bebe");
                //pasajerosVuelta.add(bebe);
                //out.println(bebe.getNif());
            }
                
                //session.setAttribute("passengerR", pasajerosVuelta);
                
                Occupation OR = (Occupation)session.getAttribute("occupationR");
                OR.setPassengers(pasajerosVuelta );
            }
            
            if(((String)session.getAttribute("kindTrip")).equals("vuelta")){//el viaje es de ida y vuelta
                if ( asiento != 0 || asientoV != 0){response.sendRedirect("revisionServicios.jsp");}//se ha elegido al menos un servicio asiento
                else{response.sendRedirect("pago.jsp");}//no se ha seleccionado servicio asiento
            }
            else{
                if ( asiento != 0){response.sendRedirect("revisionServicios.jsp");}//se ha seleccionado al menos un servicio asiento
                else{response.sendRedirect("pago.jsp");}//no se ha seleccionado servicio asiento
            }
            
            
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
