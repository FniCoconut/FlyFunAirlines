<%-- 
    Document   : cliente
    Created on : 23-dic-2015, 19:34:19
    Author     : Coconut
--%>

<%@page import="java.util.Iterator"%>
<%@page import="FlyFunPackage.MODEL.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check In - Fly Fun Airlines</title>
        
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
            function setCheckInVuelo(id){
                document.getElementById('checkInVuelo').value=id; 
            }
        </script>
        <%
            ArrayList<Flight> vuelosCheckIn = (ArrayList)session.getAttribute("checkFlights");
            Iterator itr = vuelosCheckIn.iterator();
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
        
        <section class="info centro">
            <span class="header-asiento">Check - In</span>
            <form action="servletCheckInVuelo" class="formulario-viaje">
                <% while(itr.hasNext()){ 
                    int i = 0;
                Flight f = (Flight)itr.next();
                %>
                <div class="vuelo" onclick="setCheckInVuelo('<%=f.getIdFlight() %>')">
                    
                    <span>Origen:<%=f.getConnection().getTermOrigin().getCity()%></span>
                    <span>Destino:<%=f.getConnection().getTermDestiny().getCity()%></span>
                    <span><%=f.getDepartureDate() %></span><br>
                    <span><%=f.getDepartureTime() %></span><br>
                    <span><%=f.getFrecio() %></span>
                </div>
                <% i++; } %>
                <input type="hidden" value="" id="checkInVuelo" name="vuelo" /><br>
                <input type="submit" value="Facturar" />
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
        
    </body>
</html>