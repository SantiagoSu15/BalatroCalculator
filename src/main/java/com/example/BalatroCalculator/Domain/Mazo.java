package com.example.BalatroCalculator.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class Mazo {
    private ArrayList<Carta> cartas;
    public Mazo() {
        this.cartas = new ArrayList<>();
        setDefaultDeck();
    }

    public Mazo(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        setDefaultDeck();
    }

    public void setDefaultDeck() {
        for (TipoMano palo : TipoMano.values()) {
            for (String valor : new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}) {
                cartas.add(new Carta( valor,palo, null, null, null));
            }
        }
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public void setCartas(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta getCarta(int pos) {
        return cartas.get(pos);
    }
}
