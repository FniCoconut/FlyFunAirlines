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