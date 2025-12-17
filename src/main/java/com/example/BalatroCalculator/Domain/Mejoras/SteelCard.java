package com.example.BalatroCalculator.Domain.Mejoras;

import com.example.BalatroCalculator.Domain.Carta;
import com.example.BalatroCalculator.Domain.manoJugada;

public class SteelCard implements Mejora {
    @Override
    public void aplicarMejora(manoJugada mano, Carta carta) {
        mano.multiplicarMulti(1.5);
    }
}
