<%-- 
    Document   : newjspembarque
    Created on : 04-may-2016, 16:01:08
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Service"%>
<%@page import="FlyFunPackage.MODEL.Flight"%>
<%@page import="FlyFunPackage.MODEL.Passenger"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FlyFunPackage.MODEL.Occupation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tarjetas de embarque - FlyFun Airlines</title>
        
        <script src="VIEW/jquery/jquery-1.12.0.min.js"></script> 
        <!-- Libreria de jQuery compatible con IE -->
        <script src="VIEW/js/canvas.js"></script>
        <!-- Canvas de mapa imagen -->
        <script src="VIEW/js/funciones-control.js"></script>
        <script src="VIEW/js/funciones-usuario.js"></script>
        <script src="VIEW/js/qrcode.js"></script>
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
        <%
            Occupation oc = (Occupation)session.getAttribute("occupationCheckIn");
            ArrayList<Passenger> pasajeros = oc.getPassengers();
            ArrayList<Service> s = new ArrayList();
            Iterator itr = pasajeros.iterator();
            Passenger p = null;
            Iterator itS;
            int i=0;
            String qr="";
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
            <span class="header-asiento">Tarjeta de embarque individual:</span>
            <%
            while( itr.hasNext() ){
                p = (Passenger)itr.next();
                
                s = p.getServices();
                itS = s.iterator();
                qr="no se han contratado servicios";
                 
                    while( itS.hasNext() ){
                        qr="";
                        Service aux = (Service)itS.next();
                        qr += aux.getDenomination()+" "+qr;
                    }
                
                %>
                <div class="tjt" id="<%=p.getNif()%>" >
                    <div class="iata-vuelos">
                        <span>Código de vuelo: <%=oc.getFlight().getNumFlight()%></span>
                        <span>Origen: <%=oc.getFlight().getConnection().getTermOrigin().getIata()%></span>
                        <span>Destino: <%=oc.getFlight().getConnection().getTermDestiny().getIata()%></span>
                        <span>Fecha: <%=oc.getFlight().getDepartureDate()%> Hora de embarque: <%=oc.getFlight().getDepartureTime()%></span>
                    </div>
                    <div class="datos-pasajero">
                        <span>Nombre y apellidos: <%=p.getSurname()%> <%=p.getName()%></span>
                        <span>Asiento: <%=p.getAsiento()%></span>
                    </div>
                    <div class="qr-pasajero" >
                        <div id="qr<%=i%>">
                            <script type="text/javascript">
                                var qrcode = new QRCode(document.getElementById('qr<%=i%>'), {
                                    width:112,
                                    height:112
                                });
                                function makeCode () {       
                                    qrcode.makeCode("<%=qr%>");
                                }
                                makeCode();
                            </script>
                        </div>
                    </div>
                    
                </div>
            <%
            i++;}
            %>
        
            
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
