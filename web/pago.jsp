<%-- 
    Document   : pago
    Created on : 03-feb-2016, 18:10:51
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Card"%>
<%@page import="FlyFunPackage.MODEL.Service"%>
<%@page import="FlyFunPackage.MODEL.Passenger"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FlyFunPackage.MODEL.Occupation"%>
<%@page import="FlyFunPackage.MODEL.Booking"%>
<%@page import="FlyFunPackage.MODEL.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pago - Fly Fun Airlines</title>
        
        <script src="VIEW/jquery/jquery-1.12.0.min.js"></script> 
        <!-- Libreria de jQuery compatible con IE -->
        <script src="VIEW/js/canvas.js"></script>
        <!-- Canvas de mapa imagen -->
        <script src="VIEW/js/funciones-control.js"></script> 
        <script src="VIEW/js/funciones-usuario.js"></script> 
        <!-- JS de funciones varias -->
        <script src="VIEW/js/ajax.js"></script>
        <!-- Ajax -->
        <script src="VIEW/jquery-ui/jquery-ui.min.js"></script>
        <!-- Lireria de jQuery User Interface -->
        <link href="VIEW/css/general-style.css" rel="stylesheet"/>
        <!-- Estilo general, lyout -->
        <link rel="stylesheet" href="VIEW/fonts/Fuente/stylesheet.css" type="text/css" charset="utf-8" />
        <!-- Fuente -->
        <link href="VIEW/fonts/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- Libreria de FontAwesome -->
        <link rel="stylesheet" href="VIEW/jquery-ui/jquery-ui.min.css">
        <!-- Estilos de la librería jQ -->
        
    </head>
    <body>
        <%
            //HttpSession session = request.getSession(true);
            Booking reserva = (Booking)session.getAttribute("booking");
            Client cliente = (Client)session.getAttribute("client");
            
            Occupation ida = reserva.getIda();
            ArrayList<Passenger> pasajeros = ida.getPassengers();
                
            if(cliente == null){
              %>
              <script>
                  popUpCliente();
                 //alert("");
              </script>
              <%
            }else{ Card crd = cliente.getCard(); 
            %>
            <script> userLogged() </script>
            <%
            }
        %> 
              
        <!-- Cabecera -->  
        <header class="header-bar">
            <div class="logo">
                <img src="./VIEW/img/logo.jpg" width="80" height="80" />
                <div class="title"><span>Fly Fun Airlines</span></div>
                <div id="acceso-usuario" class="acceso-usuario"><i class="fa fa-bars fa-2x"></i></div>
                <nav class="path-bar"></nav>
            </div>
        </header>
        
        <!-- tiempo + redes -->
        <aside class="info izquierda">
            seccion de redes y tiempo
        </aside>
        
        <!-- Vuelos disponibles -->
                           
        
        <section class="info centro">
            <div class="resumen-reserva">
                <div class="resumen-viaje-ida">
                <div class="resumen-vuelo">
                    <span>Código de vuelo: <%=ida.getFlight().getNumFlight() %></span>
                </div>
                <% 
                for ( int i=0 ; i<pasajeros.size() ; i++){
                %>
                
                <div class="resumen-pasajero">
                    <span>Pasajero: <%=pasajeros.get(i).getName()%>, <%=pasajeros.get(i).getSurname()%></span>
                    <%if(pasajeros.get(i).getServices() != null){
                        %>
                        <div class="resumen-pasajero-servicios">
                        <span>Servicios:</span><br>
                        <%
                        ArrayList<Service> servicios = pasajeros.get(i).getServices();
                        for(int j=0 ; j<servicios.size() ; j++){ %>
                    
                        <span><%=servicios.get(j).getDenomination()%> : <%=servicios.get(j).getDescription()%> , <%=servicios.get(j).getFrecio()%>€</span>
                    </div>
                        <%}
                    }%>
                </div>
                <%
                }
                %>
                </div>
                <% 
            Occupation vuelta = reserva.getVuelta();
            if ( vuelta != null){
            ArrayList<Passenger> pasajerosVuelta = vuelta.getPassengers();
                             %>
                <div class="resumen-viaje-vuelta">
                   <% 
                for ( int i=0 ; i<pasajerosVuelta.size() ; i++){
                %>
                <div class="resumen-vuelo">
                    <span>Código de vuelo: <%=vuelta.getFlight().getNumFlight() %></span>
                </div>
                <div class="resumen-pasajero">
                    <span>Pasajero: <%=pasajerosVuelta.get(i).getName()%>, <%=pasajerosVuelta.get(i).getSurname()%></span>
                    <%if(pasajerosVuelta.get(i).getServices() != null){
                        %>
                        <div class="resumen-pasajero-servicios">
                        <span>Servicios:</span><br>
                        <%
                        ArrayList<Service> servicios = pasajerosVuelta.get(i).getServices();
                        for(int j=0 ; j<servicios.size() ; j++){ %>
                    
                        <span><%=servicios.get(j).getDenomination()%> : <%=servicios.get(j).getDescription()%> , <%=servicios.get(j).getFrecio()%>€</span>
                    </div>
                        <%}
                    }%>
                </div>
                <%
                }
                %> 
                </div>
                <% } %>
            </div>
            
            <button>Confirmar compra</button>
            
            <div class="pago">
                <form action="servletPago">
                    <div class="datos-cliente">
                        <!-- Esto se rellena con los datos del cliente -->
                    </div>
                    <div class="datos-tarjeta">
                        <label></label><select>
                            <option>VISA</option>
                            <option>MasterCard</option>
                        </select>
                        <label for="tjNumber">Nº de tarjeta</label><input type="text" id="tjNumber" name="tjNumber" placeholder="número de tarjeta" maxlength="16" /><br>
                        <label for="cvvTj">CVV</label><input type="number" id="cvvTj" name="cvvTj" placeholder="CVV" min="000" max="999" /><br>
                        <label for="mesCad">Mes de caducidad</label><input type="number" id="mesCad" name="mesCad" min="1" max="12" /><br>
                        <label for="anoCad">Año de caducidad</label><input type="number" id="anoCad" name="anoCad" min="2016" /><br>
                    </div>
                    
                    <input type="submit" name="confirmPayment" value="Confirmar pago" />
                </form>
            </div>
            
        </section>
        <!-- tiempo + info viaje -->
        <aside class="info derecha">
            seccion de info vuelo y tiempo
        </aside>
        
        <section class="pantalla-usuario" id="pantalla-usuario">
        </section>    
            <aside class="usuario" id="area-usuario">
                <div id="head-aside" class="head-aside"></div>
                <div class="btn-inicio" onclick="inicioSesion()"><span>Inicia sesión</span></div>
                <div class="btn-inicio" onclick="registro()"><span>Nuevo usuario</span></div>
                <div class="btn-inicio" onclick="facturar()"><span>Check - in</span></div>
               
            </aside>
        
    </body>
    
</html>
