var OAjax;

function Ajax(){
	if(window.XMLHttpRequest){
		OAjax = new XMLHttpRequest();
	}
	else{
		OAjax = new ActiveXObject();
	}
	return OAjax;
}

function loadOrigin(){
    Ajax();
    
    OAjax.open('POST', 'servletOrigen', true);
    OAjax.onreadystatechange = makeSelectOrigin;
    OAjax.send();
}

function makeSelectOrigin(){
    
    if(OAjax.readyState === 4 && OAjax.status === 200){
		var selectOrigen = OAjax.responseText;

            if( selectOrigen === false ){
                document.getElementById('origen').innerHTML = "-- no hay estanterías disponibles --";
            }
            else{
                document.getElementById('origen').innerHTML = selectOrigen;
            }
    }
    
}

function loadDestiny(origin){
    Ajax();
    
    OAjax.open('POST', 'servletDestino?origin='+origin, true);
    OAjax.onreadystatechange = makeSelectDestiny;
    OAjax.send();
}

function makeSelectDestiny(){
    
    if(OAjax.readyState === 4 && OAjax.status === 200){
		var selectDestino = OAjax.responseText;

            if( selectDestino === false ){
                document.getElementById('destino').innerHTML = "-- no hay estanterías disponibles --";
            }
            else{
                document.getElementById('destino').innerHTML = selectDestino;
            }
    }
    
}

function validaUsuario(user, pass){
    Ajax();
    OAjax.open('POST', 'servletLogin?user='+user+'&pass='+pass, true);
    OAjax.send();
    OAjax.onreadystatechange = makeLogin;
    
}

function makeLogin(){
    
    if(OAjax.readyState === 4 && OAjax.status === 200){
		var str = OAjax.responseText;
            if( str === false ){
                document.getElementById('area-usuario').innerHTML = "-- no hay estanterías disponibles --";
            }
            else{
                document.getElementById('area-usuario').innerHTML = str;
            }
    }
    return false;
}

function dinamicoOD(origen, destino){
    if(window.XMLHttpRequest){OAX = new XMLHttpRequest();}
	else{OAX = new ActiveXObject();}
    OAX.open('POST', 'servletOrigenDestino?o='+origen+'&d='+destino, true);
    OAX.send();
    OAX.onreadystatechange = function(){
        if(OAX.readyState === 4 && OAX.status === 200){
		var str = OAX.responseText;
            if( str === false ){
                document.getElementById('dinamico').innerHTML = "-- no hay estanterías disponibles --";
            }
            else{
                document.getElementById('dinamico').innerHTML = str;
            }
    }
    };
}

function dinamicoPasajeros(pasajero){
    var i = 0;
    
    i = i+pasajero;
    
    document.getElementById('dinamico').appendChild(document.createTextNode("Nº de pasajeros: "+i));
}