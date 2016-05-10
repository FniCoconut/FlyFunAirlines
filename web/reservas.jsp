<%-- 
    Document   : reservas
    Created on : 18-abr-2016, 11:50:04
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Booking"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="FlyFunPackage.MODEL.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Reservas - Fly Fun Airlines</title>
        
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
        <link rel="stylesheet" href="VIEW/css/styleTags.css" type="text/css" />
        <!-- Estilo de pestañas -->
        
        <%
            Client cliente = (Client)session.getAttribute("client");
            
                Gson g = new Gson();
                String json = g.toJson(cliente);
                %>
            
    </head>
    <body>
        
        <!-- Cabecera -->  
        <header class="header-bar">
            <div class="logo">
                <img src="./VIEW/img/logo.png" width="80" height="80" />
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
            
            <span class="header-vuelo">Vuelos de <%=cliente.getSurname()%> <%=cliente.getName()%> .</span>
            
            <span class="hidden-span" id="facturacion">Todas las reservas</span>
            <div class="tab">
                <a href="#facturacion"><span id="shelves-title" class="tab-link"><i class="fa fa-th icono"></i>Todas las Reservas</span></a>
                <div class="panel" id="datosVueloTodas">
                    <form action="servletReservasCliente" class="">
                        
                    </form>
                </div>
            </div>
            
            <span class="hidden-span" id="facturado">Viajes Pendientes</span>
            <div class="tab">
                <a href="#facturado" onclick="loadReservasCliente(1)" ><span id="shelves-title" class="tab-link"><i class="fa fa-th icono"></i>Viajes Pendientes</span></a>
                <div class="panel" id="datosVueloPendientes">
                    <form action="servletReservasCliente" class="">
                        
                    </form>
                </div>
            </div>
            
            <span class="hidden-span" id="volado">Viajes Realizados</span>
            <div class="tab">
                <a href="#volado" onclick="loadReservasCliente(2)" ><span id="shelves-title" class="tab-link"><i class="fa fa-th icono"></i>Viajes Realizados</span></a>
                <div class="panel" id="datosVueloRealizadas">
                    <form action="servletReservasCliente" class="">
                        
                    </form>
                </div>
            </div>
            
        </section>
        
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
         
            <script> 
                userLogged(<%=json%>); 
            </script>
    </body>
</html>
