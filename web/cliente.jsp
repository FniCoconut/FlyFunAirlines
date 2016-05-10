<%-- 
    Document   : cliente
    Created on : 23-dic-2015, 19:34:19
    Author     : Coconut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Cliente - Fly Fun Airlines</title>
        
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
        <link href="VIEW/fonts/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet" />
        <!-- Libreria de FontAwesome -->
        <link rel="stylesheet" href="VIEW/fonts/Fuente/stylesheet.css" type="text/css" charset="utf-8" />
        <!-- Fuente -->
        <link rel="stylesheet" href="VIEW/jquery-ui/jquery-ui.min.css">
        <!-- Estilos de la librería jQ -->
        
    </head>
    <body>
        <%

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
            
            <form action="servletCliente" class="formulario-registro">
                <label for="prefijoCliente">Prefijo</label>
                <select id="prefijoCliente" name="prefijoCliente">
                    <option>-Selecciona-</option>
                    <option value="Don">D.</option>
                    <option value="Doña">Dña.</option>
                </select>
                <label for="nombreCliente">Nombre</label><input type="text" id="nombreCliente" name="nombreCliente" maxlength="45" required /><br/> 
                <label for="apellidoCliente">Apellidos</label><input type="text" id="apellidoCliente" name="apellidoCliente" maxlength="100" required /><br/> 
                <label for="nifCliente">N.I.F.</label><input type="text" id="nifCliente" name="nifCliente" maxlength="10" required onfocusout="letraDni(this.value)" /><br/> 
                <label for="tlfCliente">Teléfono</label><input type="text" id="tlfCliente" name="tlfCliente" maxlength="9">
                <label for="emailCliente">e-Mail</label><input type="email" id="emailCliente" name="emailCliente" maxlength="60" required /><br/> 
                <label for="direccionCliente">Dirección Postal</label><input type="text" id="direccionCliente" name="direccionCliente" maxlength="140" required /><br/> 
                <label for="passwordCliente">Contraseña</label><input type="password" id="passwordCliente" name="passwordCliente" minlength="6" pattern="[A-N-O-Za-n-o-z0-9!?-]{8,12}" required /><br/> 
                <button class="btn-popup"><span><i class="fa fa-plus-circle"></i>Registrar</span></button>
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
        <button onclick="window.history.forward();">Volver</button>
        
    </body>
</html>