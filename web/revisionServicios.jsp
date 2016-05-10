<%-- 
    Document   : revisionServicios
    Created on : 30-ene-2016, 14:55:14
    Author     : Coconut
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="FlyFunPackage.MODEL.Client"%>
<%@page import="FlyFunPackage.MODEL.Occupation"%>
<%@page import="FlyFunPackage.MODEL.Service"%>
<%@page import="FlyFunPackage.MODEL.Passenger"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Selección de asiento - Fly Fun Airlines</title>
        
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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Libreria de iconos Material Google -->
        <link rel="stylesheet" href="VIEW/jquery-ui/jquery-ui.min.css">
        <!-- Estilos de la librería jQ -->
       
    </head>
    
    <body onload="loadAsientos()">
       
        <!-- Cabecera -->
        <%
        //ArrayList<Service> aux = (ArrayList)session.getAttribute("services");
        ArrayList<Service> aux = new ArrayList();
        Occupation ocOW = (Occupation)session.getAttribute("occupationOW");
        ArrayList<Passenger> pasajeros = ocOW.getPassengers();
                int asiento = 0;
        String kind = (String)session.getAttribute("kindTrip");
        %>
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
        
        <!-- Formulario Viaje -->
        <section class="info centro">
            
            <form action="servletRevisionServicios" class="formulario-viaje">
                    <span class="header-asiento">Selección de asiento:</span>
                <div class="adulto pasajeros-servicio">
    <%
        for( int i=0; i<pasajeros.size() ; i++){
            aux = (pasajeros.get(i)).getServices();
            if( aux != null ){
            for (int j=0 ; j<aux.size(); j++){
            
                //el pasajero ha elegido asiento
        if( ((aux.get(j)).getDenomination()).equalsIgnoreCase("Asiento") ){
            asiento++;
                    %>
                    <div class="pasajero">
                        <span class="title-pasajero">Pasajero <%=i+1%>:</span>
                        <div class="datos-pasajero">
                            <input type="hidden" class="asiento" name="asiento<%=i%>" value="">
                            <span><%=pasajeros.get(i).getPrefix()+" "+pasajeros.get(i).getName()+" "+pasajeros.get(i).getSurname() %></span><br>
                            <span>NIF: <%=pasajeros.get(i).getNif() %></span><br>
                            <span class="asiento-seleccionado">Asiento seleccionado: </span>
                        </div>
                    </div>
            <%
                }
            }
        }
    }
    %>
                </div>
        <%   if( asiento > 0 ) { %>
                <div class="airplane-schema">
                    <table class="airplane-style">
                        <tr>
                            <td colspan="3"></td>
                            <td colspan="2"><!--aseos--></td>
                        </tr>
                        <!--asientos normales-->
                        <tr>
                            <td class="seat">
                                <input type="hidden" value="1i" class="1i" id="1i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="2i" class="2i" id="2i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seat">
                                <input type="hidden" value="2d" class="2d" id="2d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="1d" class="1d" id="1d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="seat">
                                <input type="hidden" value="3i" class="3i" id="3i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="4i" class="4i" id="4i" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seat">
                                <input type="hidden" value="4d" class="4d" id="4d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seat">
                                <input type="hidden" value="3d" class="3d" id="3d" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <tr><td colspan="5"></td></tr>
                        <!--asientos premiunm-->
                        <tr>
                            <td class="seat" colspan="2" rowspan="2">
                                <input type="hidden" value="pi" class="pi" id="pi" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seat" colspan="2" rowspan="2">
                                <input type="hidden" value="pd" class="pd" id="pd" name="asientoPassenger"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <!--Aseos-->
                        <tr>
                            <td colspan="5"></td>
                        </tr>
                    </table>
                </div>
                <% } %>
        <% 
        if(kind.equalsIgnoreCase("vuelta")){
            
        //ArrayList<Passenger> pasajerosVuelta = (ArrayList)session.getAttribute("passengerR");
            ArrayList<Passenger> pasajerosVuelta = ((Occupation)session.getAttribute("occupationR")).getPassengers();
            
            %>
            
        <div class="asientos-viaje-vuelta">
            <h1 class="separador"></h1>
                <span class="header-asiento">Selección de asiento de vuelta:</span>
            <div class="adulto pasajeros-servicio">
            <%
                for( int i=0; i<pasajerosVuelta.size() ; i++){
                    aux = pasajerosVuelta.get(i).getServices();
                    
                    if( aux != null ){
                    for (int j=0 ; j<aux.size(); j++){

                        //el pasajero ha elegido asiento
                if( ((aux.get(j)).getDenomination()).equalsIgnoreCase("Asiento") ){   
                    asiento++;
                            %>
                    <div class="pasajero">
                        <span class="title-pasajero">Pasajero <%=i+1%>:</span>
                        <div class="datos-pasajero">
                            <input type="hidden" class="asientoV" name="asientoV<%=i%>" value="">
                            <span><%=pasajerosVuelta.get(i).getPrefix()+" "+pasajerosVuelta.get(i).getName()+" "+pasajerosVuelta.get(i).getSurname() %></span><br>
                            <span>NIF: <%=pasajerosVuelta.get(i).getNif() %></span><br>
                            <span class="asiento-seleccionadoV">Asiento seleccionado: </span>
                        </div>
                    </div>
            <%
                }
            }
        }
        }
    %>
                </div>
                <%if(asiento > 0) {%>
                <div class="airplane-schema" id="return-airplane">
                    <table class="airplane-style">
                        <tr>
                            <td colspan="3"></td>
                            <td colspan="2"><!--aseos--></td>
                        </tr>
                        <!--asientos normales-->
                        <tr>
                            <td class="seatV">
                                <input type="hidden" value="1i" class="1i" id="1iV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seatV">
                                <input type="hidden" value="2i" class="2i" id="2iV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seatV">
                                <input type="hidden" value="2d" class="2d" id="2dV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seatV">
                                <input type="hidden" value="1d" class="1d" id="1dV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="seatV">
                                <input type="hidden" value="3i" class="3i" id="3iV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seatV">
                                <input type="hidden" value="4i" class="4i" id="4iV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seatV">
                                <input type="hidden" value="4d" class="4d" id="4dV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="seatV">
                                <input type="hidden" value="3d" class="3d" id="3dV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <tr><td colspan="5"></td></tr>
                        <!--asientos premiunm-->
                        <tr>
                            <td class="seatV" colspan="2" rowspan="2">
                                <input type="hidden" value="pi" class="pi" id="piV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                            <td class="pasillo"><!--pasillo--></td>
                            <td class="seatV" colspan="2" rowspan="2">
                                <input type="hidden" value="pd" class="pd" id="pdV" name="asientoPassengerR"/>
                                <span><i class="material-icons">event_seat</i></span>
                            </td>
                        </tr>
                        <!--Aseos-->
                        <tr>
                            <td colspan="5"></td>
                        </tr>
                    </table>
                </div>
                <%
                }    }
    %>
            </div>
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
                Gson g = new Gson();
                String json = g.toJson(cliente);
                %>
                
            <script> userLogged(<%=json%>); </script>
                <%
            }
            %>
    </body>
</html>
