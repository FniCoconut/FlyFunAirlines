/* FUNCIONALIDAD DE LAS IMÁGENES */
var lienzo;
var ctx;

function canvas() {
    lienzo = document.getElementById("mapa");
    ctx = lienzo.getContext('2d');
    mapa();
}

function mapa() {
    var img = new Image();
    img.src = './VIEW/img/map.jpg';
    img.onload = function(){
    ctx.drawImage(img, 0, 0, 500, 300);};
}

