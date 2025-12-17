package com.example.BalatroCalculator.Domain.Sellos;


import com.example.BalatroCalculator.Domain.Carta;
import com.example.BalatroCalculator.Domain.Mano;
import com.example.BalatroCalculator.Domain.manoJugada;

class SelloRojo implements Sello {
    private boolean aplicado = false;

    @Override
    public void setSeal(Carta carta) {
        carta.setSello(this);
    }

    @Override
    public void createAction(manoJugada estado, Carta carta) {
        if (aplicado) {
            return;
        }
        aplicado = true;
        carta.activarCarta(estado);
        aplicado = false;
    }

}