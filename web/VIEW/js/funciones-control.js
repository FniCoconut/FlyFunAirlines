$(document).ready(function(){
    // --> CONTROL DE FECHAS <--
    
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
                
    });

    //--> CONTROL SELECCION DE VUELO <--
    $("td").click(function(){
        $(this).css("background","aliceblue");
        $(this).siblings().css("background", "white");
    });
    
});