<%-- 
    Document   : pago
    Created on : 03-feb-2016, 18:10:51
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pago - Fly Fun Airlines</title>
        
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
            //HttpSession session = request.getSession(true);
            Client cliente = (Client)session.getAttribute("client");
            if(cliente == null){
              %>
              <script>
                 // popUpCliente();
                 alert("<%=cliente.getName() %>");
              </script>
              <%
            }
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
            <div class="billetes">
                <!-- Aquí se mostrarán los billetes por pasajero -->
            </div>
            
            <button>Confirmar compra</button>
            
            <div class="pago">
                <form action="servletPago">
                    <div class="datos-cliente">
                        <!-- Esto se rellena con los datos del cliente -->
                    </div>
                    <div class="datos-tarjeta">
                        <label></label><select>
                            <option>VISA</option>
                            <option>MasterCard</option>
                        </select>
                        <label for="tjNumber">Nº de tarjeta</label><input type="text" id="tjNumber" name="tjNumber" placeholder="número de tarjeta" maxlength="16" /><br>
                        <label for="cvvTj">CVV</label><input type="number" id="cvvTj" name="cvvTj" placeholder="CVV" min="000" max="999" /><br>
                        <label for="mesCad">Mes de caducidad</label><input type="number" id="mesCad" name="mesCad" min="1" max="12" /><br>
                        <label for="anoCad">Año de caducidad</label><input type="number" id="anoCad" name="anoCad" min="2016" /><br>
                    </div>
                    
                    <input type="submit" name="confirmPayment" value="Confirmar pago" />
                </form>
            </div>
            
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
                        <button onclick="validaUsuario(this.idUsuario.value, this.pas.value)">Entra<i class="fa fa-sign-in fa-2x"></i></button>
                    </div>
                </form>
                <button onclick="window.location.href='cliente.jsp'"><i class="fa fa-plus-circle fa-2x"></i>Regístrate</button>
            </aside>
        
    </body>
    
</html>
