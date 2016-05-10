<%-- 
    Document   : cliente
    Created on : 23-dic-2015, 19:34:19
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Occupation"%>
<%@page import="java.util.Iterator"%>
<%@page import="FlyFunPackage.MODEL.Passenger"%>
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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Libreria de iconos Material Google -->
        <link rel="stylesheet" href="VIEW/jquery-ui/jquery-ui.min.css">
        <!-- Estilos de la librería jQ -->
        
    </head>
    <body onload="loadAsientos()">
        <%
            Occupation oc = (Occupation)session.getAttribute("occupationCheckIn");
            ArrayList<Passenger> pasajeros = oc.getPassengers();
            Iterator itr = pasajeros.iterator();
            Passenger p;
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
            <form action="servletCheckInPasajero" class="formulario-viaje">
                <%
            while(itr.hasNext()){
                p = (Passenger)itr.next();
                %>
                <div class="pasajero">    
                    <span class="title-pasajero">Pasajero: </span><span><%=p.getSurname()%> <%=p.getName()%> - <%=p.getNif()%></span>
                    <div class="datos-pasajero">    
                        <span><%=p.geteMail()%></span><br>
                        <label for="fcad">Fecha de caducidad del doumento de identificación</label><br>
                        <input id="fcad" type="date" name="fcad<%=p.getIdPassenger()%>" value="<%=p.getFechaCaducidadNif() %>"/><br>
                        <label for="fnac">Fecha de nacimiento</label>
                        <input id="fnac" type="date" name="fnac<%=p.getIdPassenger()%>" value="<%=p.getFechaNacimiento() %>" /><br>
                        <label for="nacioladiad">Nacionalidad</label><br>
                        <input type="text" name="nacionalidad<%=p.getIdPassenger()%>" value="<%=p.getNacionalidad() %>"/><br>
                        <input type="hidden" class="asiento" name="asiento<%=p.getIdPassenger()%>" value="<%=p.getAsiento()%>" />
                        <!-- mirar funciones-control.js 80 -->
                        <span class="asiento-seleccionado">Asiento seleccionado: <%=p.getAsiento()%></span>
                    </div>
                </div>
            <%   }  %>
            
            <div class="airplane-schema">
                    <table class="airplane-style">
                        <tr>
                            <td colspan="3"></td>
                            <td colspan="2"><!--aseos--></td>
                        </tr>
                        <!--asientos normales-->
                        <tr>
                            <td class="seat">
                                <input type="hidden" value="1i" id="1i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="2i" id="2i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seat">
                                <input type="hidden" value="2d" id="2d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="1d" id="1d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="seat">
                                <input type="hidden" value="3i" id="3i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="4i" id="4i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seat">
                                <input type="hidden" value="4d" id="4d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="3d" id="3d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <tr><td colspan="5"></td></tr>
                        <!--asientos premiunm-->
                        <tr>
                            <td class="seat" colspan="2" rowspan="2">
                                <input type="hidden" value="pi" id="pi" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seat" colspan="2" rowspan="2">
                                <input type="hidden" value="pd" id="pd" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <!--Aseos-->
                        <tr>
                            <td colspan="5"></td>
                        </tr>
                    </table>
                </div>
            
            <br>
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