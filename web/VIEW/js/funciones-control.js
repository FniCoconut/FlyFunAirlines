var adultos;
var jovenes;
var bebes;

$(document).ready(function(){
    // --> CONTROL DE FECHAS <--
    //Chrome
    
    $("#fecha-salida").change(function(){
        document.getElementById("fecha-vuelta").min = $("#fecha-salida").val();
    });

    // --> CONTROL IDA Y VUELTA <--
    $('#vuelta').click(function(){
        $('#datos-vuelta').slideDown();
        $('#fecha-vuelta').removeAttr('disabled');
    });

    $('#ida').click(function(){
        $('#datos-vuelta').slideUp();
        $('#fecha-vuelta').attr('disabled','disabled');
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
        $(this).css('transition','500ms');
        $(this).css('color','#4A148C');
        $(this).css('background-color','#68efad');
        
        $(this).siblings().css("background", "white");
        $(this).siblings().css('color', 'darkgrey');
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
    
   
    
    //--> CONTROL SELECCION DE ASIENTO <--
    $("div.pasajero").click(function(){
        $(this).children("div.datos-pasajero").slideToggle();
        $(this).children("div.datos-pasajero").addClass("active");
        $(this).children("div.datos-pasajero").click(function(event){
                            event.stopPropagation(); });
        
        $(this).siblings().children("div.datos-pasajero").slideUp();
        $(this).siblings().children("div.datos-pasajero").removeClass("active");
    });
    
    $("td.seat").click(function(){
        
        if( $(this).children("input[type='hidden']").hasClass('disabled') ){
        }else{
            
            var seat = $(this).children("input[type='hidden']").val();//valor del asiento
            
            if( $("div.datos-pasajero").children("input[type='hidden']").val() !== seat ){
                                                
                $("div.active").children("span.asiento-seleccionado").text("Asiento seleccionado: "+seat);
                $("div.active").children("input[type='hidden'].asiento").val(seat);//valor del asiento seleccionado
                
            }
        }
    });
        
    $("td.seatV").click(function(){
        
        if( !$(this).children("input[type='hidden']").hasClass('disabled') ){
            
            var seat = $(this).children("input[type='hidden']").val();//valor del asiento
            
            if( $("div.datos-pasajero").children("input[type='hidden']").val() !== seat ){
                
                $("div.active").children("span.asiento-seleccionadoV").text("Asiento seleccionado: "+seat);
                $("div.active").children("input[type='hidden'].asientoV").val(seat);//valor del asiento seleccionado
                
            }
        }
    
    });
    
    $('#adulto').change(function(){
        if( $('#passenger').children('#nAdultos') ){
            $('#nAdultos').remove();
        }
        
        var divadultos = document.createElement('div');
        divadultos.setAttribute('id', 'nAdultos');
        var txt = document.createElement('span');
            txt.className='label';
        $('#passengers').append(divadultos);
                
        divadultos.appendChild(txt);
        var adultos = $(this).val();
            txt.appendChild(document.createTextNode('Adultos: '+adultos));
        
    });
    
    $('#joven').change(function(){
        if( $('#passenger').children('#nJovenes') ){
            $('#nJovenes').remove();
        }
        
        var divadultos = document.createElement('div');
        divadultos.setAttribute('id', 'nJovenes');
        var txt = document.createElement('span');
            txt.className='label';
        $('#passengers').append(divadultos);
                
        divadultos.appendChild(txt);
        var jovenes = $(this).val();
            txt.appendChild(document.createTextNode('Jóvenes: '+jovenes));
        
    });
    
    $('#bebe').change(function(){
        if( $('#passenger').children('#nBebes') ){
            $('#nBebes').remove();
        }
        
        var divadultos = document.createElement('div');
        divadultos.setAttribute('id', 'nBebes');
        var txt = document.createElement('span');
            txt.className='label';
        $('#passengers').append(divadultos);
                
        divadultos.appendChild(txt);
        var bebes = $(this).val();
            txt.appendChild(document.createTextNode('Bebés: '+bebes));
        
        servicioBebe(bebes);
    });
    
    
});


 function servicioBebe(numBebes){
     if( $('#services').children('#servBb') ){
         $('#servBb').remove();
     }
     
     var divServBb = document.createElement('div');
         divServBb.setAttribute('id', 'servBb');
     var txt = document.createElement('span');
         txt.className ='';
     
     $('#services').append(divServBb);
     
     divServBb.appendChild(txt);
        txt.appendChild(document.createTextNode('Servicio Bebé x '+numBebes));
 }

 function asientosOcupados(str){
     var i;
     for( i=0 ; i<str[0].length ; i++){
         if( str[0][i] !== null){
            $('#'+str[0][i]).parent('td.seat').css({'color':'#FF6464'});
            $('#'+str[0][i]).addClass('disabled');
        }
     }
     
     if(document.getElementById('return-airplane')){
       for( i=0 ; i<str[1].length ; i++){
         if( str[1][i] !== null){
            $('#'+str[1][i]+'V').parent('td.seatV').css({'color':'#FF6464'});
            $('#'+str[1][i]+'V').addClass('disabled');
        }
     } 
         
     }
 }

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
        btn.setAttribute("onclick","inicioSesionPop()");
        btn.appendChild(document.createTextNode("Inicio de sesion"));
        
        container.appendChild(btn);
        
    var btn = document.createElement("button");
        btn.setAttribute("class", "btn-popup");
        btn.setAttribute("onclick","registro()");
        btn.appendChild(document.createTextNode("Nuevo usuario"));
        
        container.appendChild(btn);
        popup.appendChild(container);
        document.body.appendChild(popup);
    
 }
 
 //--> CONTROL SELECT ADULTO PARA BEBÉ <--
 function adultoCargo(id){
           
       nodelist = document.getElementsByClassName("i-adulto");
       var items = nodelist.length;
       var n_options = items/3;
       
       while(document.getElementById(id).hasChildNodes()){
           document.getElementById(id).removeChild(document.getElementById(id).firstChild);
       }
       
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
           document.getElementById(id).appendChild(option);
           i= i+2;
       }
   
 }
 
 // devuelve la letra correspondiente a un número DNI
  function letraDni(numDNI, id) {
      var dni = numDNI.substr(0,8);
      var letra = numDNI.charAt(8);
      var input = document.getElementById(id);
      var valida = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(dni % 23);
      
      if(letra !== valida){
          //cambiar fondo DNI
          input.style="background-color:red";
      }else{
          input.style="background:none";
      }
  }
     
 function pagar(){
    $('.pago').slideDown();
 }
    
    
 function printDiv(id) {
      var divToPrint = document.getElementById(id);
     
    newWin = window.open(' ','popimpr');
    newWin.document.close();
    
//    var css = document.createElement("link");  
//    css.setAttribute("href", "printStyle.css");
//    css.setAttribute("rel", "stylesheet");
//    css.setAttribute("type", "text/css");
//    css.setAttribute("media", "print");
//    
//    newWin.document.head.appendChild(css);
    newWin.document.write(divToPrint.outerHTML);
    newWin.print();
    newWin.close();
   }