<%-- 
    Document   : inicioviaje
    Created on : 23-dic-2015, 19:36:25
    Author     : Coconut
--%>
<%@page import="FlyFunPackage.MODEL.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio - Fly Fun Airlines</title>
        
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
        
        <script>
        $(document).ready(function(){
            $('#datos-vuelta').hide();
            if( !(navigator.userAgent.indexOf('Chrome') !== -1) ){
                 
                $(function() {
                    $( "#fecha-salida" ).datepicker({
                      dateFormat: "yy-mm-dd",
                      numberOfMonths: 3,
                      showButtonPanel: false
                    });
                    
                    $( "#fecha-vuelta" ).datepicker({
                      dateFormat: "yy-mm-dd",
                      numberOfMonths: 3,
                      showButtonPanel: false
                    });
                   
                   var minDateVuelta = $( "#fecha-salida" ).datepicker(  "option", "minDate"  );
                  // $( "" ).datepicker( "option", "minDate", minDateVuelta );
                   $( "#fecha-vuelta" ).datepicker({
                        minDate: minDateVuelta
                      });
                });
            }
            
            canvas();
            
        });
        </script>
    </head>
    
    <body onload="loadOrigin()">
        
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
            <div id="weather-origin">
                <a class="aw-widget-legal"></a>
                <div id="awcc1455910518006" class="aw-widget-current"  data-locationkey="308526" data-unit="c" data-language="es" data-useip="false" data-uid="awcc1455910518006"></div>
                <script type="text/javascript" src="http://oap.accuweather.com/launch.js"></script>
            </div>
            <div id="twitter"><a class="twitter-timeline" href="https://twitter.com/mss_Fanni" data-widget-id="700738019196669952">Tweets por @mss_Fanni.</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
</div>
        </aside>
        
        <!-- Formulario Viaje -->
        <section class="info centro">
            <div class="mapa">
                <canvas id="mapa" width="500" height="300"></canvas>
                <!--<img src="VIEW/img/map.jpg" alt="" />-->
            </div>
                    
            <form action="servletInicioViaje" class="formulario-viaje">
                <button class="btn-siguiente"><i class="fa fa-paper-plane"></i></button>
            
                <div class="estilo-origen">
                    <label class="lbl-origen" for="origen">ORIGEN</label>
                    <select class="slc-origen" name="origen" id="origen" required onchange="loadDestiny(this.value)">
                        <option>-Selecciona el origen-</option>
                    </select>
                </div>
                <div class="estilo-destino">
                    <label class="lbl-destino" for="destino">DESTINO</label>
                    <select class="slc-destino" name="destino" id="destino" required onchange="dinamicoOD(origen.value, this.value)">
                        <option>-Selecciona el destino-</option>
                    </select>
                </div>
                <div class="tipo-viaje">
                    <span  class="radio-tipo-viaje" ><input type="radio" name="tipo-viaje" id="ida" value="ida" required/><label for="ida">IDA</label></span>
                    <span  class="radio-tipo-viaje" ><input type="radio" name="tipo-viaje" id="vuelta" value="vuelta" required/><label for="vuelta">IDA y VUELTA</label></span>
                </div>
                <br>
                <div class="datos-ida">
                    <div class="pasajeros">
                        <label for="adulto">ADULTOS</label>
                        <input type="number" name="adulto" id="adulto" min="1" max="10" required class="inp-inicio"/>
                        
                        <label for="joven">JÓVENES</label>
                        <input type="number" name="joven" id="joven" min="0" value="0" class="inp-inicio"/>
                    
                        <label for="bebe">BEBES</label>
                        <input type="number" name="bebe" id="bebe" min="0" value="0" class="inp-inicio"/>
                    
                    </div>
                    <br>
                    <label for="fecha-salida">FECHA SALIDA</label>
                    <input type="date" id="fecha-salida" name="f_salida" required/>
                </div>
                <div class="datos-vuelta" id="datos-vuelta">
                    <label for="fecha-salida">FECHA VUELTA</label>
                    <input type="date" id="fecha-vuelta" name="f_vuelta" required/>
                </div>
                
            </form>
            
        </section>
        <!-- tiempo + info viaje -->
        <aside class="info derecha">
            <div id="weather-destiny">
                <a class="aw-widget-legal"></a>
                <div id="awcc1455910426981" class="aw-widget-current"  data-locationkey="623" data-unit="c" data-language="es" data-useip="false" data-uid="awcc1455910426981"></div>
                <script type="text/javascript" src="http://oap.accuweather.com/launch.js"></script>
            </div>
            <div id="dinamico" class="vuelo">
                <div id="airports"></div>
                <h1 class="separador"></h1>
                <div id="passengers">
                    
                </div>
                <div id="flight"></div>
                <div id="price"></div>                    
            </div>
        </aside>
        
        <section class="pantalla-usuario" id="pantalla-usuario">
        </section>    
           <aside class="usuario" id="area-usuario">
                <div id="head-aside" class="head-aside"></div>
                <div class="btn-inicio" onclick="inicioSesion()"><span>Inicia sesión</span></div>
                <div class="btn-inicio" onclick="registro()"><span>Nuevo usuario</span></div>
                <div class="btn-inicio" onclick="facturar()"><span>Check - in</span></div>
               
            </aside>
        
        <!-- Pie de página -->
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
