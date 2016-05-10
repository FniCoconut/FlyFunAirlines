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
    
    $(document).remove("#link_get_widget");
    
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

function validaUsuario(user, pass, popup){
    //user llevará al usuario para mostrar sus datos
    if(window.XMLHttpRequest){OBAJX = new XMLHttpRequest();}
	else{OBAJX = new ActiveXObject();}
    OBAJX.open('POST', 'servletLogin?user='+user+'&pass='+pass, true);
    OBAJX.send();
    OBAJX.onreadystatechange = function(){
        if(OBAJX.readyState === 4 && OBAJX.status === 200){
            var str = JSON.parse(OBAJX.responseText);
            if( str === "null" ){
                popup= false;
                menuUsuario();}
            else{userLogged(str);}
        }else{
            popup= false;
            menuUsuario();
        }
    };
    return popup;
    }; 
   
//function catchUser(){
//            if(OBAJX.readyState === 4 && OBAJX.status === 200){
//                var str = JSON.parse(OBAJX.responseText);
//            userLogged(str);
//        }
//};
//   
function dinamicoOD(origen, destino){
    if(window.XMLHttpRequest){OAX = new XMLHttpRequest();}
	else{OAX = new ActiveXObject();}
    OAX.open('POST', 'servletOrigenDestino?o='+origen+'&d='+destino, true);
    OAX.send();
    OAX.onreadystatechange = function(){
        if(OAX.readyState === 4 && OAX.status === 200){
		var str = JSON.parse(OAX.responseText);
            if( str === false ){
                document.getElementById('airports').innerHTML = "-- no hay estanterías disponibles --";
                
            }
            else{
                
                var contenedor = document.getElementById('airports');
                
                while(contenedor.hasChildNodes()){
                    contenedor.removeChild(contenedor.firstChild);
                }
                
                    var spanO = document.createElement('span');
                        spanO.appendChild(document.createTextNode('Salida: '));
                        spanO.className='lbl-destino';
                    var spanON = document.createElement('span');
                        spanON.appendChild(document.createTextNode(str[0].name));
                    var spanD = document.createElement('span');
                        spanD.appendChild(document.createTextNode('Llegada: '));
                        spanD.className='lbl-destino';
                    var spanOD = document.createElement('span');
                        spanOD.appendChild(document.createTextNode(str[1].name));
                        contenedor.appendChild(spanO);
                        contenedor.appendChild(spanON);
                        contenedor.appendChild(document.createElement('br'));
                        contenedor.appendChild(spanD);
                        contenedor.appendChild(spanOD);
                        
                var tiempoOrigen = document.getElementById('awcc1455910518006');
                    tiempoOrigen.setAttribute('data-locationkey', str[0].key);
                    tiempoOrigen.setAttribute('data-uid', 'awcc1455910518006')
                var tiempoDestino = document.getElementById('awcc1455910426981');
                    tiempoDestino.setAttribute('data-locationkey', str[1].key);
                    tiempoDestino.setAttribute('data-uid', 'awcc1455910426981');
            }
    }
    };
}

function loadAsientos(){
    if(window.XMLHttpRequest){OAX = new XMLHttpRequest();}
	else{OAX = new ActiveXObject();}
    OAX.open('POST', 'servletAsientos', true);
    OAX.send();
    OAX.onreadystatechange=function(){
        if(OAX.readyState === 4 && OAX.status === 200){
            var str = JSON.parse(OAX.responseText);
            asientosOcupados(str);
        }
    };
}

function closeClientSession(){
    if(window.XMLHttpRequest){OAX = new XMLHttpRequest();}
	else{OAX = new ActiveXObject();}
    OAX.open('POST', 'servletCloseClientSession', true);
    OAX.send();
    OAX.onreadystatechange=menuUsuario;
}

function loadReservasCliente(caso){
    var i = parseInt(caso);
    if(window.XMLHttpRequest){OAX = new XMLHttpRequest();}
	else{OAX = new ActiveXObject();}
        alert('OPEN'+caso);
    OAX.open('POST', 'servletReservasCliente?caso='+caso, true);
    OAX.send();
    OAX.onreadystatechange = function(){
        if(OAX.readyState === 4 && OAX.status === 200){
            alert('vamos a pillar reservas');
            var str = JSON.parse(OAX.responseText);
            alert('ONREADYSTATECHANGE'+str);
            reservasCliente(str);
            
        }
    };
    
}

