package com.example.BalatroCalculator.Domain.Mejoras;

import com.example.BalatroCalculator.Domain.Carta;
import com.example.BalatroCalculator.Domain.manoJugada;

public class BonusCard implements Mejora {
    @Override
    public void aplicarMejora(manoJugada mano, Carta carta) {
        mano.sumarFichas(30);
    }
}
