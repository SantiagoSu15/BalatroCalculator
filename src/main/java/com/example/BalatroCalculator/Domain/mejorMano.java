package com.example.BalatroCalculator.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class mejorMano {
    private int fichas;
    private int multi;
    private int fichasFinales;
    private ArrayList<Carta> cartasJugadas;
    private ManoDePoker manoDePoker;

    public int getFichasFinales() {
        return (int) (fichas * (multi ));
    }


}
