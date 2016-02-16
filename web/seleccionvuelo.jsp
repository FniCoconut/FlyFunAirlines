<%-- 
    Document   : seleccionvuelo
    Created on : 25-ene-2016, 19:51:31
    Author     : Coconut
--%>

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
        <!-- JS de funciones varias -->
        <script src="VIEW/js/ajax.js"></script>
        <!-- Ajax -->
        <script src="VIEW/jquery-ui/jquery-ui.min.js"></script>
        <!-- Lireria de jQuery User Interface -->
        <link href="VIEW/css/general-style.css" rel="stylesheet"/>
        <!-- Estilo general, lyout -->
        <link href="VIEW/fonts/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- Libreria de FontAwesome -->
        <link rel="stylesheet" href="VIEW/jquery-ui/jquery-ui.min.css">
        <!-- Estilos de la librería jQ -->
        
    </head>
    <body>
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
            <table>
                <thead>
                    <tr>
                        <th>Vuelos de ida</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                        for (int i=0; i < vuelosIda.size(); i++){
                        %>
                        <td class="flight">
                            <input type="hidden" id="vueloIda" name="vueloIda" value="<%=vuelosIda.get(i).getIdFlight() %>" />
                            <span><%=vuelosIda.get(i).getDepartureDate() %></span><br>
                            <span><%=vuelosIda.get(i).getDepartureTime() %></span><br>
                            <span><%=vuelosIda.get(i).getFrecio() %></span>
                        </td>
                        <%
                        } 
                        %>
                        
                    </tr>
                </tbody>
            </table>
            
            
            
            <!-- Round Trips -->
            <% if (((String)session.getAttribute("kindTrip")).equals("vuelta")){
                ArrayList<Flight> vuelosVuelta= (ArrayList)session.getAttribute("rFly");
            %>
            
            <table>
                <thead>
                    <tr>
                        <th>Vuelos de vuelta</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                        for (int i=0; i < vuelosVuelta.size(); i++){
                        %>
                        <td class="flight">
                            <input type="hidden" id="vueloVuelta" name="vueloVuelta" value="<%=vuelosVuelta.get(i).getIdFlight() %>" />
                            <span><%=vuelosVuelta.get(i).getDepartureDate() %></span><br>
                            <span><%=vuelosVuelta.get(i).getDepartureTime() %></span><br>
                            <span><%=vuelosVuelta.get(i).getFrecio() %>€</span>
                        </td>
                        <%
                        } 
                        %>
                        
                    </tr>
                </tbody>
            </table>
              <%
            }
            %>
            <input type="submit" class="btn-siguiente" value=">" />
            
            
            </form>
        </section>
        <!-- tiempo + info viaje -->
        <aside class="info derecha">
            seccion de info vuelo y tiempo
        </aside>
        
        <section class="pantalla-usuario" id="pantalla-usuario">
        </section>    
            <aside class="usuario" id="area-usuario">
                <form action="" class="formulario-cliente">
                    <div class="datos-usuario inicio-sesion">
                        <label for="id-usuario">Nombre de usuario</label>
                        <br>
                        <input type="text" id="id-usuario"/>
                        <br><br>
                        <label for="pass-usuario">Contraseña</label>
                        <br>
                        <input type="password" />
                        <br>
                        <button onclick="validaUsuario(idUsuario.value, pass.value)">Entra<i class="fa fa-sign-in fa-2x"></i></button>
                    </div>
                </form>
                <button onclick="window.location.href='cliente.jsp'"><i class="fa fa-plus-circle fa-2x"></i>Regístrate</button>
            </aside>
        
    </body>
</html>
