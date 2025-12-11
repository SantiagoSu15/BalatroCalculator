// primeros 2 numeros del data posicion son:
//Primer fondo PrimerX, PrimerY, los sellos si no tiene queda como null
//Segundo fondo, Columna y fila de la carta (numero y figura)
//tercer fondo, SegundoX, SegundoY fondo de la carta

function generarCartas() {
    const contenedor = document.querySelector('.contenedorCartasParaJugar');
    const coords = generarCoords();

    coords.forEach((coordenada, index) => {
        let x = coordenada[0];
        let y = coordenada[1];
        const carta = document.createElement('div');
        carta.classList.add('carta');
        carta.setAttribute('data-numero', index + 1);
        carta.style.backgroundPosition = `${x}% ${y}% , 16.7% 0%`;
        console.log("Equis: " + x, " Ye: " + y);
        carta.onclick = () => mostrarCartaSeleccion(index + 1);
        contenedor.appendChild(carta);
    });
}

function mostrarCartaSeleccion(numero) {
    let clase1 = document.getElementById("cartaJugada");
    let clase = document.getElementById("cartaSeleccionada");
    let botones = document.getElementById('contenedorBotones');
    let botones2 = document.getElementById('contenedorBotones2');
    if (clase1.style.display === "none") {
        clase1.style.display = "block";
        botones.style.display = "flex";
        botones2.style.display = "none";
        botones2.innerHTML = ``;
        setTimeout(() => {
            const col = getFilaYColumna(numero)[0];
            const row = getFilaYColumna(numero)[1];
            console.log(col, row);
            clase.innerHTML = '';
            const carta = document.createElement('div');
            carta.classList.add('test');
            carta.style.backgroundPosition = `-100% 0 ,${col}% ${row}%, 16.7% 0`
            carta.setAttribute('data-posicion', `null,null,${col},${row},16.7 ,0`);
            clase.appendChild(carta);
        }, 50);

    } else {
        clase1.style.display = "none";
        clase.innerHTML = '';
    }

}

function getFilaYColumna(i) {
    let col = 0;
    let fila = 0;
    let columnas = 13;
    if (i <= 13) {
        col = i;
        fila = 1;
    } else if (i > 13 && i <= 26) {
        col = i - 13;
        fila = 2;
    } else if (i > 26 && i <= 39) {
        col = i - 26;
        fila = 3;
    } else if (i > 39 && i <= 52) {
        col = i - 39;
        fila = 4;
    }
    console.log("Columna" + col, " Fila" + fila);
    return getCartaPosition(col, fila, columnas, 4);
}

function getCartaPosition(col, fila, columnas, filas) {
    const x = (col - 1) * 8.35;
    const y = (fila - 1) * 33.5;
    return [x, y];
}

function generarCoords() {
    let coords = [];
    const alto = 33.5;
    const ancho = 8.35;
    let xInicial = 0;
    let yInicial = 0;
    let yFinal = alto;
    let xFinal = ancho;

    for (let i = 0; i < 4; i++) {

        for (let j = 0; j < 13; j++) {
            let cartaCoords = [xInicial, yInicial];
            xInicial += ancho;
            xFinal += ancho;
            coords.push(cartaCoords);
        }
        xInicial = 0;
        xFinal = ancho;
        yInicial += alto;
        yFinal += alto;
    }
    return coords;
}



function openCards() {
    let clase = document.getElementById("cartasJugar");
    let clase1 = document.getElementById("cartaJugada");

    if (clase.style.display === "none") {
        clase.style.display = "block";
        clase.style.display = "grid";
        setTimeout(() => {
            clase.innerHTML = "";
            generarCartas();
        }, 50);
    } else {
        if (clase1.style.display === "block") {
            clase1.style.display = "none";
        }
        clase.style.display = "none";
    }
}


function sacarCartaJugada() {
    let clase1 = document.getElementById("cartaJugada");
    let clase = document.getElementById("cartaSeleccionada");
    if (clase1.style.display === "none") {
        return;
    } else {
        clase1.style.display = "none";
        clase.innerHTML = '';
    }
}

function seleccionarCartaSeleccion() {
    const cartaSel = document.querySelectorAll('.contenedorCarta2 .test');
    const contenedor = document.getElementById('cartasSeleccionadas');

    cartaSel.forEach(carta => {
        let [fondoX, fondoY, col, row, SegundoFondoX, SegundoFondoY] = carta.getAttribute('data-posicion').split(',').map(Number);
        console.log(fondoX, fondoY, col, row, SegundoFondoX, SegundoFondoY);
        const mini = document.createElement('div');
        mini.classList.add('miniContenedor');
        contenedor.appendChild(mini);
        const cartaSi = document.createElement('div');
        cartaSi.classList.add('test');

        cartaSi.style.backgroundPosition = `${fondoX}% ${fondoY}%,${col}% ${row}%, ${SegundoFondoX}% ${SegundoFondoY}%`;
        cartaSi.setAttribute('data-posicion', `${fondoX},${fondoY},${col},${row},${SegundoFondoX},${SegundoFondoY}`);


        mini.appendChild(cartaSi);
    })


    let clase1 = document.getElementById("cartaJugada");
    let clase2 = document.getElementById("cartaSeleccionada");
    if (clase1.style.display === "block") {
        clase1.style.display = "none";
        clase2.innerHTML = '';
    }
}


function eliminarCartasSeleccionadas() {
    let clase1 = document.getElementById("cartasSeleccionadas");
    let clase2 = document.getElementById("jokerSeleccionados");
    clase1.innerHTML = '';
    clase2.innerHTML = '';
}

function ponerElementos(elemento) {
    let cartaSel = document.querySelector('#cartaSeleccionada .test');
    let dataPosicion = cartaSel.getAttribute('data-posicion');

    console.log("Data posición: ", dataPosicion);

    let [fondoX, fondoY, col, row, SegundoFondoX, SegundoFondoY] = dataPosicion.split(',').map(value => {
        let numValue = Number(value);
        return isNaN(numValue) ? null : numValue;
    });


    if (elemento === document.getElementById('selloCarta')) {
        creacionParaTiposCarta(33.4, 0, SegundoFondoX, SegundoFondoY);
        creacionParaTiposCarta(66.8, 100, SegundoFondoX, SegundoFondoY);
        creacionParaTiposCarta(83.5, 100, SegundoFondoX, SegundoFondoY);
        creacionParaTiposCarta(100.2, 100, SegundoFondoX, SegundoFondoY);
    } else if (elemento === document.getElementById('encantamientoCarta')) {
        creacionParaTiposCarta(fondoX, fondoY, 83.5, 0);
        creacionParaTiposCarta(fondoX, fondoY, 100.1, 0);
        let valor = 16.7;
        let x = 0;
        for (let i = 0; i < 6; i++) {
            x += valor;
            creacionParaTiposCarta(fondoX, fondoY, x, 25);
        }
    }
}


function ponerCartaEnElSeleccionador(carta) {
    let [fondoX, fondoY, col, row, SegundoFondoX, SegundoFondoY] = carta.getAttribute('data-posicion').split(',').map(Number);
    let cartaSel = document.querySelector('#cartaSeleccionada .test');
    cartaSel.style.backgroundImage = "url('src/presentacion/recursos/fondosYBarajas.png'), url('src/presentacion/recursos/cartastest.png'), url('src/presentacion/recursos/fondosYBarajas.png')";
    cartaSel.style.backgroundSize = '700% 500%,1300% 400%,700% 500%';
    cartaSel.style.backgroundPosition = `${fondoX}% ${fondoY}%,${col}% ${row}%, ${SegundoFondoX}% ${SegundoFondoY}%`;
    cartaSel.setAttribute('data-posicion', `${fondoX},${fondoY},${col}, ${row},${SegundoFondoX},${SegundoFondoY}`);
    console.log("Despues de oprimir el sello");
    console.log(fondoX, fondoY, col, row, SegundoFondoX, SegundoFondoY);


}

function creacionParaTiposCarta(PrimerX, PrimerY, SegundoX, SegundoY) {
    let botones = document.getElementById('contenedorBotones2');
    let cartaSel = document.querySelector('#cartaSeleccionada .test');
    let contenedorBotones = document.getElementById('contenedorBotones');

    contenedorBotones.style.display = "none";
    const mini = document.createElement('div');
    mini.classList.add('miniContenedor2');
    botones.appendChild(mini);
    let [fondoX, fondoY, col, row, SegundoFondoX, SegundoFondoY] = cartaSel.getAttribute('data-posicion').split(',').map(Number);
    const cartaSi = document.createElement('div');
    cartaSi.classList.add('test');

    if (SegundoX === null || SegundoY === null) {
        SegundoX = 16.7;
        SegundoY = 0;
    }
    if (PrimerX === null || PrimerY === null) {
        PrimerX = -100;
        PrimerY = 0;
    }

    cartaSi.style.backgroundPosition = `${PrimerX}% ${PrimerY}%,${col}% ${row}%, ${SegundoX}% ${SegundoY}%`;
    cartaSi.setAttribute('data-posicion', `${PrimerX},${PrimerY},${col},${row},${SegundoX},${SegundoY}`);


    cartaSi.onclick = () => ponerCartaEnElSeleccionador(cartaSi);
    mini.appendChild(cartaSi);
    ajustarGrid();
    botones.style.display = "grid";
}


function ajustarGrid() {
    const contenedor = document.getElementById("contenedorBotones2");
    const elementos = contenedor.getElementsByClassName("miniContenedor2");
    const totalElementos = elementos.length;

    let columnas = Math.ceil(Math.sqrt(totalElementos));
    let filas = Math.ceil(totalElementos / columnas);

    contenedor.style.gridTemplateColumns = `repeat(${columnas}, 1fr)`;
    contenedor.style.gridTemplateRows = `repeat(${filas}, 1fr)`;
}


function botonAtras() {
    const contenedor = document.getElementById("contenedorBotones");
    const elementos = document.getElementById("contenedorBotones2");
    const elementosSub = document.getElementById("contenedorBotones2");
    const superContenedor = document.getElementById("cartaJugada");
    if (contenedor.style.display === "none") {
        contenedor.style.display = "flex";
        elementos.style.display = "none";
        elementosSub.innerHTML = "";
    } else if (contenedor.style.display === "flex") {
        superContenedor.style.display = "none";
        let carta = document.getElementById("cartaSeleccionada");
        carta.innerHTML = "";
    }

}



