<%-- 
    Document   : inicioviaje
    Created on : 23-dic-2015, 19:36:25
    Author     : Coconut
--%>
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
            seccion de redes y tiempo
        </aside>
        
        <!-- Formulario Viaje -->
        <section class="info centro">
            <div class="mapa">
                <canvas id="mapa" width="500" height="300"></canvas>
                <!--<img src="VIEW/img/map.jpg" alt="" />-->
            </div>
                    
            <form action="servletInicioViaje" class="formulario-viaje">
                <input type="submit" class="btn-siguiente" value=">" />
            
                <div class="estilo-origen">
                    <label for="origen">ORIGEN</label>
                    <select name="origen" id="origen" required onchange="loadDestiny(this.value)">
                        <option>-Selecciona origen-</option>
                    </select>
                </div>
                <div class="estilo-destino">
                    <label for="destino">DESTINO</label>
                    <select name="destino" id="destino" required onchange="dinamicoOD(origen.value, this.value)">
                        <option>-Selecciona destino-</option>
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
                        <input type="number" name="adulto" id="adulto" min="1" max="10" required onchange="dinamicoPasajeros(this.value)" />
                        
                        <label for="joven">JÓVENES</label>
                        <input type="number" name="joven" id="joven" min="0" value="0" onchange="dinamicoPasajeros(this.value)"/>
                    
                        <label for="bebe">BEBES</label>
                        <input type="number" name="bebe" id="bebe" min="0" value="0" onchange="dinamicoPasajeros(this.value)"/>
                    
                    </div>
                    <br>
                    <label for="fecha-salida">FECHA SALIDA</label>
                    <input type="date" id="fecha-salida" name="f_salida" required/>
                </div>
                <div class="datos-vuelta" id="datos-vuelta">
                    <label for="fecha-salida">FECHA VUELTA</label>
                    <input type="date" id="fecha-vuelta" name="f_vuelta"  required/>
                </div>
                
            </form>
            
        </section>
        <!-- tiempo + info viaje -->
        <aside class="info derecha">
            seccion de info vuelo y tiempo
            <div id="dinamico" class="info vuelo">
                
            </div>
        </aside>
        
        <section class="pantalla-usuario" id="pantalla-usuario">
        </section>    
           <aside class="usuario" id="area-usuario">
                <form action="" class="formulario-cliente">
                    <div class="datos-usuario inicio-sesion">
                        <label for="idUsuario">Nombre de usuario</label>
                        <br>
                        <input type="email" id="idUsuario"/>
                        <br><br>
                        <label for="pass">Contraseña</label>
                        <br>
                        <input type="password" id="pass"/>
                        <br>
                        <button onclick="validaUsuario(idUsuario.value, pass.value)">Entra<i class="fa fa-sign-in fa-2x"></i></button>
                    </div>
                </form>
                <button onclick="window.location.href='cliente.jsp'"><i class="fa fa-plus-circle fa-2x"></i>Regístrate</button>
            </aside>
        
        <!-- Pie de página -->
        
    </body>
</html>
