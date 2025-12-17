package com.example.BalatroCalculator.Domain.Sellos;


import com.example.BalatroCalculator.Domain.Carta;
import com.example.BalatroCalculator.Domain.Mano;
import com.example.BalatroCalculator.Domain.manoJugada;

public interface Sello {
    void createAction(manoJugada estado, Carta carta);
    void setSeal(Carta carta);
}

