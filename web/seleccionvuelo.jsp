<%-- 
    Document   : seleccionvuelo
    Created on : 25-ene-2016, 19:51:31
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Client"%>
<%@page import="FlyFunPackage.MODEL.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vuelos - Fly Fun Airlines</title>
        
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
        <link href="VIEW/css/style1.css" rel="stylesheet"/>
        <!-- Estilo general, lyout -->
        <link href="VIEW/fonts/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- Libreria de FontAwesome -->
        <link rel="stylesheet" href="VIEW/fonts/Fuente/stylesheet.css" type="text/css" charset="utf-8" />
        <!-- Fuente -->
        <link rel="stylesheet" href="VIEW/jquery-ui/jquery-ui.min.css">
        <!-- Estilos de la librería jQ -->
        
    </head>
    <body>
        
        <script>
            function setValueIda(id){
                document.getElementById('vueloIda').value = id;
            }
            
            function setValueVuelta(id){
                document.getElementById('vueloVuelta').setAttribute('value', id);
            }
        </script>
        <%
            ArrayList<Flight> vuelosIda= (ArrayList)session.getAttribute("owFly");
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
            <form action="servletSeleccionVuelo" class="formulario-viaje">
            <!-- One Way Trips -->
                        <span class="header-vuelo">Vuelos de ida</span>
            <table class="oferta-vuelo">
                <thead>
                </thead>
                <tbody>
                    <tr>
                        <%
                        if(vuelosIda.isEmpty()){
                            %>
                            <td>
                                <span>No existen vuelos en un rago de 5 días anteriores y posteriores</span><br>
                                <div class="btn-volver" onclick="window.location.href='inicioviaje.jsp'">Volver a seleccionar otra fecha</div>
                            </td>
                            <%
                        }else{
                        for (int i=0; i < vuelosIda.size(); i++){
                        %>
                        <td class="flight" onclick="setValueIda('<%=vuelosIda.get(i).getIdFlight()%>')">
                            <input class="ida" type="hidden" value="<%=vuelosIda.get(i).getIdFlight() %>" />
                            <span><%=vuelosIda.get(i).getDepartureDate() %></span><br>
                            <span><%=vuelosIda.get(i).getDepartureTime() %></span><br>
                            <span><%=vuelosIda.get(i).getFrecio() %>€</span>
                        </td>
                        <%
                        } 
                        }
                        %>
                        
                    </tr>
                    <tr><td><input type="hidden" id="vueloIda" name="vueloIda" value="" /></td></tr>
                </tbody>
            </table>
            
                        <h1 class="separador"></h1>
            
            <!-- Round Trips -->
            <% if (((String)session.getAttribute("kindTrip")).equals("vuelta")){
                ArrayList<Flight> vuelosVuelta= (ArrayList)session.getAttribute("rFly");
            %>
            <span class="header-vuelo">Vuelos de vuelta</span>
            <table class="oferta-vuelo">
                <tbody>
                    <tr>
                        <%
                        if(vuelosVuelta.isEmpty()){
                %>
                        <td>
                                <span>No existen vuelos en un rago de 5 días anteriores y posteriores</span><br>
                                <div class="btn-volver" onclick="window.location.href='inicioviaje.jsp'">Volver a seleccionar otra fecha</div>
                            </td>
                            <%
                        }else
                        {   
                        for (int i=0; i < vuelosVuelta.size(); i++){
                        %>
                        <td class="flight" onclick="setValueVuelta('<%=vuelosVuelta.get(i).getIdFlight() %>')" >
                            <input type="hidden" value="<%=vuelosVuelta.get(i).getIdFlight() %>" />
                            <span><%=vuelosVuelta.get(i).getDepartureDate() %></span><br>
                            <span><%=vuelosVuelta.get(i).getDepartureTime() %></span><br>
                            <span><%=vuelosVuelta.get(i).getFrecio() %>€</span>
                        </td>
                        <%
                        } 
                        }
                        %>
                        
                    </tr>
                    <tr><td><input type="hidden" id="vueloVuelta" name="vueloVuelta" value="" /></td></tr>
                </tbody>
            </table>
              <%
            }
            %>
            
                <button class="btn-siguiente"><i class="fa fa-paper-plane"></i></button>            
            </form>
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
        <%
            Client cliente = (Client)session.getAttribute("client");
            if(cliente != null){
                %>
                
            <script> userLogged(); </script>
                <%
            }
            %>
    </body>
</html>
