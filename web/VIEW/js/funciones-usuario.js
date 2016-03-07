/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//menu usuario
function menuUsuario(){
    while(document.getElementById('area-usuario').hasChildNodes()){
       $('#area-usuario').empty(); 
    }
    var divHeader = document.createElement('div');
        divHeader.className='head-aside';
        divHeader.setAttribute('id', 'head-aside');
    $('#area-usuario').append(divHeader);
    
    
}

//En el div donde es llamada crea los elementos de inicio de sesion
function inicioSesion(){
    while(document.getElementById('area-usuario').hasChildNodes()){
       $('#area-usuario').empty(); 
    }
    var divHeader = document.createElement('div');
        divHeader.className='head-aside';
    $('#area-usuario').append(divHeader);
    var form = document.createElement('form');
        form.className='formulario-cliente';
    $('#area-usuario').append(form);
    var divDatos = document.createElement('div');
        divDatos.className='datos-usuario inicio-sesion';
        form.appendChild(divDatos);
    var labelId = document.createElement('label');
        labelId.setAttribute('for', 'idUsuario');
        labelId.appendChild(document.createTextNode('e - Mail de usuario'));
        form.appendChild(labelId);
        form.appendChild(document.createElement('br'));
    var inputId = document.createElement('input');
        inputId.setAttribute('id', 'idUsuario');
        inputId.setAttribute('type', 'email');
        form.appendChild(inputId);
        form.appendChild(document.createElement('br'));
        form.appendChild(document.createElement('br'));
    var labelPass = document.createElement('label');
        labelPass.setAttribute('for', 'pass');
        labelPass.appendChild(document.createTextNode('Contraseña'));
        form.appendChild(labelPass);
        form.appendChild(document.createElement('br'));
    var inputPass = document.createElement('input');
        inputPass.setAttribute('id', 'pass');
        inputPass.setAttribute('type', 'password');
        form.appendChild(inputPass);
    var btn = document.createElement('button');
        btn.setAttribute('onclick', 'validaUsuario(idUsuario.value, pass.value)');
        btn.className='btn-popup';
        btn.appendChild(document.createTextNode('Entra '));
    var ico = document.createElement('i');
        ico.className='fa fa-sign-in';
        btn.appendChild(ico);   
        form.appendChild(btn);
}

function inicioSesionPop(){
    var popup = document.createElement("div"); 
        popup.setAttribute("class","popUp");
        
    var container = document.createElement("div");
        container.setAttribute("class","container-popup");
        
    var form = document.createElement('form');
        form.className='formulario-cliente';
    container.appendChild(form);
    var divDatos = document.createElement('div');
        divDatos.className='datos-usuario inicio-sesion';
        form.appendChild(divDatos);
    var labelId = document.createElement('label');
        labelId.setAttribute('for', 'idUsuario');
        labelId.appendChild(document.createTextNode('e - Mail de usuario'));
        form.appendChild(labelId);
        form.appendChild(document.createElement('br'));
    var inputId = document.createElement('input');
        inputId.setAttribute('id', 'idUsuario');
        inputId.setAttribute('type', 'email');
        form.appendChild(inputId);
        form.appendChild(document.createElement('br'));
        form.appendChild(document.createElement('br'));
    var labelPass = document.createElement('label');
        labelPass.setAttribute('for', 'pass');
        labelPass.appendChild(document.createTextNode('Contraseña'));
        form.appendChild(labelPass);
        form.appendChild(document.createElement('br'));
    var inputPass = document.createElement('input');
        inputPass.setAttribute('id', 'pass');
        inputPass.setAttribute('type', 'password');
        form.appendChild(inputPass);
    var btn = document.createElement('button');
        btn.setAttribute('onclick', 'validaUsuario(idUsuario.value, pass.value)');
        btn.className='btn-popup';
        btn.appendChild(document.createTextNode('Entra '));
    var ico = document.createElement('i');
        ico.className='fa fa-sign-in';
        btn.appendChild(ico);   
        form.appendChild(btn);
        
        container.appendChild(form);
        popup.appendChild(container);
        document.body.appendChild(popup);
}

//Redirección al jsp de registro de nuevo cliente
function registro(){
    window.location.href='cliente.jsp';   
}

//Carga formulario de datos de factouración
function facturar(){
    window.location.href='checkin.jsp'; 
}

//Carga formulario de datos de factouración
function reservas(){
    window.location.href='reservas.jsp'; 
}

//Función que carga los elementos del aside de usuario cuando este esta logeado
function userLogged(){
    while(document.getElementById('area-usuario').hasChildNodes()){
       $('#area-usuario').empty(); 
    }
    var divHeader = document.createElement('div');
        divHeader.className='head-aside';
    $('#area-usuario').append(divHeader);
   
    var divUser = document.createElement('div');
        divUser.setAttribute('id', 'nombreCliente');
        divUser.className='';
    $('#area-usuario').append(divUser);
    
    var divReservas = document.createElement('div');
        divReservas.className='btn-inicio';
        divReservas.setAttribute('onclick', 'reservas()');
    var spanR = document.createElement('span');
        spanR.appendChild(document.createTextNode('Mis Reservas'));
        divReservas.appendChild(spanR);
        $('#area-usuario').append(divReservas);
        
    var divCheck = document.createElement('div');
        divCheck.className='btn-inicio';
        divCheck.setAttribute('onclick', 'facturar()');
    var spanC = document.createElement('span');
        spanC.appendChild(document.createTextNode('Check - in'));
        divCheck.appendChild(spanC);
        $('#area-usuario').append(divCheck);
        
}