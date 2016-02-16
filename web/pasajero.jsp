<%-- 
    Document   : pasajero
    Created on : 23-dic-2015, 19:31:03
    Author     : Coconut
--%>

<%@page import="FlyFunPackage.MODEL.Service"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pasajeros - Fly Fun Airlines</title>
        
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
        <!-- Cabecera -->
        <%
        int adult = (int)session.getAttribute("adult");
        int child = (int)session.getAttribute("child");
        int infant = (int)session.getAttribute("infant");
        String kind = (String)session.getAttribute("kindTrip");
        ArrayList<Service> services = (ArrayList)session.getAttribute("services");
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
        
            <form action="servletPasajeroServicio" class="formulario-viaje">
                <div class="adulto">
                <% 
                for(int i=0 ; i<adult ; i++ ){
                %>
                    <div class="formulario-pasajero">
                        <label for="prefix">PREFIJO</label>
                        <select id="prefix" name="prefix<%=i%>">
                            <option>- Selecciona -</option>
                            <option value="Don">D.</option>
                            <option value="Doña">Dña.</option>
                        </select><br/>
                        <label for="name">NOMBRE</label><input type="text" name="name<%=i%>" class="i-adulto" /><br/>
                        <label for="surname">APELLIDOS</label><input type="text" name="surname<%=i%>" class="i-adulto" /><br/>
                        <label for="nif">NIF</label><input type="text" name="nif<%=i%>" class="i-adulto" /><br/>
                        <label for="email">e-MAIL</label><input type="email" name="email<%=i%>" /><br/>
                    </div>
                    
                    <div class="servicios">
                        <div class="serv-ida">
                            <label>Servicios viaje de ida</label><br/>
                            <input type="checkbox" name="asiento<%=i%>" value="Asiento" /><span>Selección de asiento.</span><br/>
                            <input type="checkbox" name="equipaje<%=i%>" value="Equipaje" /><span>Equipaje hasta 25kg.</span><br/>
                            <input type="checkbox" name="seguro<%=i%>" value="SeguroViaje" checked/><span>Seguro de viaje</span>
                        </div>
                            <% if( kind.equalsIgnoreCase("vuelta")){ %>
                        <div class="serv-vuelta">
                            <label>Servicios viaje de vuelta</label><br/>
                            <input type="checkbox" name="asientoV<%=i%>" value="Asiento" /><span>Selección de asiento.</span><br/>
                            <input type="checkbox" name="equipajeV<%=i%>" value="Equipaje" /><span>Equipaje hasta 25kg.</span><br/>
                            <input type="checkbox" name="seguroV<%=i%>" value="SeguroViaje" checked/><span>Seguro de viaje</span>
                        </div>
                        <%  }  %>
                    </div>
                <%
                }
                %>
                </div> 
                <div class="nino">
                <% 
                for(int i=0 ; i<child ; i++ ){
                %>
                    <div class="formulario-pasajero"><br/>
                        <label for="nameNino">NOMBRE</label><input type="text" name="nameNino<%=i%>" /><br/>
                        <label for="surnameNino">APELLIDOS</label><input type="text" name="surnameNino<%=i%>" /><br/>
                        <label for="nifNino">NIF</label><input type="text" name="nifNino<%=i%>" /><br/>
                        <label for="emailNino">e-MAIL</label><input type="text" name="emailNino<%=i%>"  /><br/>
                    </div>
                    <div class="servicios">
                        <div class="serv-ida">
                            <label>Servicios viaje de ida</label><br/>
                            <input type="checkbox" name="asientoNino<%=i%>" value="Asiento" /><span>Selección de asiento.</span><br/>
                            <input type="checkbox" name="equipajeNino<%=i%>" value="Equipaje" /><span>Equipaje hasta 25kg.</span><br/>
                            <input type="checkbox" name="seguroNino<%=i%>" value="SeguroViaje" checked/><span>Seguro de viaje</span>
                        </div>
                        <% if( kind.equalsIgnoreCase("vuelta")){ %>
                        <div class="serv-vuelta">
                            <label>Servicios viaje de vuelta</label><br/>
                            <input type="checkbox" name="asientoNinoV<%=i%>" value="Asiento" /><span>Selección de asiento.</span><br/>
                            <input type="checkbox" name="equipajeNinoV<%=i%>" value="Equipaje" /><span>Equipaje hasta 25kg.</span><br/>
                            <input type="checkbox" name="seguroNinoV<%=i%>" value="SeguroViaje" checked/><span>Seguro de viaje</span>
                        </div>
                        <%  }  %>
                    </div>
                <%
                }
                %>
                </div> 
                <div class="bebe">
                <% 
                for(int i=0 ; i<infant ; i++ ){
                %>
                <div class="formulario-pasajero">
                    <label for="nameBebe">NOMBRE</label><input type="text" name="nameBebe<%=i%>" id="nameBebe"/><br/>
                    <label for="surnameBebe">APELLIDOS</label><input type="text" name="surnameBebe<%=i%>" id="surnameBebe"/><br/>
                    <label for="nifBebe">NIF</label><input type="text" name="nifBebe<%=i%>" id="nifBebe"/><br/>
                    
                    <label for="adultoCargo">ADULTO A CARGO</label>
                    <select id="adultoCargo" name="adultoCargo<%=i%>" >
                        <option>-Selecciona adulto a cargo-</option>
                    </select>
                </div> 
                <%
                }
                %>
                </div>
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
                        <button onclick="validaUsuario(this.idUsuario.value, this.pas.value)">Entra<i class="fa fa-sign-in fa-2x"></i></button>
                    </div>
                </form>
                <button onclick="window.location.href='cliente.jsp'"><i class="fa fa-plus-circle fa-2x"></i>Regístrate</button>
            </aside>
    </body>
</html>
