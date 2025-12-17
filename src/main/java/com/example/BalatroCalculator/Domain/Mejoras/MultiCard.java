package com.example.BalatroCalculator.Domain.Mejoras;

import com.example.BalatroCalculator.Domain.Carta;
import com.example.BalatroCalculator.Domain.manoJugada;

public class MultiCard implements Mejora {
    @Override
    public void aplicarMejora(manoJugada mano, Carta carta) {
        mano.sumarMulti(4);
    }
}
