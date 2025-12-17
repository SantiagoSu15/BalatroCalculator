package com.example.BalatroCalculator.Domain.Mejoras;

import com.example.BalatroCalculator.Domain.Carta;
import com.example.BalatroCalculator.Domain.manoJugada;

public interface Mejora {
    void aplicarMejora(manoJugada mano, Carta carta);
}


