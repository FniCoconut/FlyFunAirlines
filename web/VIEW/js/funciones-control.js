var arrayAsientos = new Array();
    arrayAsientos['1i'] = 0;
    arrayAsientos['1d'] = 0;
    arrayAsientos['2i'] = 0;
    arrayAsientos['2d'] = 0;
    arrayAsientos['3i'] = 0;
    arrayAsientos['3d'] = 0;
    arrayAsientos['4i'] = 0;
    arrayAsientos['4d'] = 0;
    arrayAsientos['pi'] = 0;
    arrayAsientos['pd'] = 0;
    
var arrayAsientosVuelta = new Array();
    arrayAsientosVuelta['1i'] = 0;
    arrayAsientosVuelta['1d'] = 0;
    arrayAsientosVuelta['2i'] = 0;
    arrayAsientosVuelta['2d'] = 0;
    arrayAsientosVuelta['3i'] = 0;
    arrayAsientosVuelta['3d'] = 0;
    arrayAsientosVuelta['4i'] = 0;
    arrayAsientosVuelta['4d'] = 0;
    arrayAsientosVuelta['pi'] = 0;
    arrayAsientosVuelta['pd'] = 0;

$(document).ready(function(){
    // --> CONTROL DE FECHAS <--
    //Chrome
    $("#fecha-salida").change(function(){
        document.getElementById("fecha-vuelta").min = $("#fecha-salida").val();
    });

    // --> CONTROL IDA Y VUELTA <--
    $('#vuelta').click(function(){
        $('#datos-vuelta').slideDown();
    });

    $('#ida').click(function(){
        $('#datos-vuelta').slideUp();
    });

    //--> CONTROL DIV USUARIO <--
    $("#acceso-usuario").click(function(){
                $(".pantalla-usuario").fadeIn();
                $(".usuario").fadeIn();
            });
            
            $(".pantalla-usuario").click(function(){
                $(".pantalla-usuario").fadeOut();
                $(".usuario").fadeOut();
            });

    //--> CONTROL SELECCION DE VUELO <--
    $("td.flight").click(function(){
        $(this).css("background","aliceblue");
        $(this).siblings().css("background", "white");
    });
    
    //--> CONTROL PASAJEROS: MENORES EN FUNCION DE ADULTOS <--
    $("input#joven").change(function(){
        var a = $("input#adulto").val();
        var b = $("input#bebe").val();
        document.getElementById("joven").max = a-b;
    });
    $("input#bebe").change(function(){
        var a = $("input#adulto").val();
        var b = $("input#joven").val();
        document.getElementById("bebe").max = a-b;
    });
    
   //--> CONTROL SELECT ADULTO PARA BEBÉ <--
   $("#adultoCargo").click(function(){
       
       
       nodelist = document.getElementsByClassName("i-adulto");
       var items = nodelist.length;
       var n_options = items/3;
       
       if(!($("#adultoCargo").children("option").hasClass("opcion")) || ($("#adultoCargo").children("option").text() === "")){
       
       var str = "<option>-Selecciona adulto a cargo-</option>";
       for (var i=0; i<items ; i++){
           //recogida del contenido de los input de adultos
           var nombre = nodelist[i].value;
           var apel = nodelist[i+1].value;
           var nif = nodelist[i+2].value;
           //creación de los nodos option
           var option = document.createElement("option");
           option.setAttribute("value", nif);
           option.setAttribute("class", "opcion");
           option.appendChild(document.createTextNode(nombre+" "+apel));
           document.getElementById("adultoCargo").appendChild(option);
           i= i+2;
       }
   }
//       document.getElementById("adultoCargo").innerHTML = str;
   });
    
    //--> CONTROL SELECCION DE ASIENTO <--
    $("div.pasajero").click(function(){
        $(this).children("div.datos-pasajero").slideToggle();
        $(this).children("div.datos-pasajero").addClass("active");
        
        $(this).siblings().children("div.datos-pasajero").slideUp();
        $(this).siblings().children("div.datos-pasajero").removeClass("active");
    });
    
    $("td.seat").click(function(){
        
            var seat = $(this).children("input[type='hidden']").val();//valor del asiento
            
            if( $("div.datos-pasajero").children("input[type='hidden']").val() !== seat ){
                
                for ( var j=0 ; j<arrayAsientos.length ; j++){
                    
                }
                                
                $("div.active").children("span.asiento-seleccionado").text("Asiento seleccionado: "+seat);
                $("div.active").children("input[type='hidden']").val(seat);//valor del asiento seleccionado
                
            }
            
    });
    
    $("td.seatV").click(function(){
            var seat = $(this).children("input[type='hidden']").val();//valor del asiento
            
            if( $("div.datos-pasajero").children("input[type='hidden']").val() !== seat ){
                
                for ( var j=0 ; j<arrayAsientos.length ; j++){
                    
                }
                                
                $("div.active").children("span.asiento-seleccionado").text("Asiento seleccionado: "+seat);
                $("div.active").children("input[type='hidden']").val(seat);//valor del asiento seleccionado
                
            }
        
    });
    
});

 function popUpCliente(){
    var popup = document.createElement("div"); 
        popup.setAttribute("class","popUp");
        
    var container = document.createElement("div");
        container.setAttribute("class","container-popup");
        
    var text = document.createElement("span");
        text.setAttribute("class", "text-popup");
        text.appendChild(document.createTextNode("No has iniciado sesión:"));
        
        container.appendChild(text);
        container.appendChild(document.createElement("br"));
        container.appendChild(document.createElement("br"));
        
    var btn = document.createElement("button");
        btn.setAttribute("class", "btn-popup");
        btn.setAttribute("onclick","window.location.href='inicioSesionCliente.jsp'");
        btn.appendChild(document.createTextNode("Inicio de sesion"));
        
        container.appendChild(btn);
        
    var btn = document.createElement("button");
        btn.setAttribute("class", "btn-popup");
        btn.setAttribute("onclick","window.location.href='cliente.jsp'");
        btn.appendChild(document.createTextNode("Nuevo usuario"));
        
        container.appendChild(btn);
        popup.appendChild(container);
        document.body.appendChild(popup);
    
 }
    