function calcularPuntaje(nuevoPuntaje) {
    let puntaje = document.querySelector(".puntaje");
    puntaje.innerHTML = nuevoPuntaje;
}

function obtenerFichasyMulti(Fichas, Multi) {
    let fichas = document.querySelector("#ficha");
    let multi = document.querySelector("#multi");
    fichas.innerHTML = Fichas;
    multi.innerHTML = Multi;
}

function obtenerMano(Mano) {
    let mano = document.querySelector("#mano");
    mano.innerHTML = Mano;
}

