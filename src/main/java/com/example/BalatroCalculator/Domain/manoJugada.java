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
public class manoJugada {
    private int fichas;
    private int multi;
    private double multiMultiplicativo;
    private int fichasFinales = fichas*multi;
    private ArrayList<Carta> cartasJugadas;
    private ManoDePoker manoDePoker;

    public void multiplicarMulti(double factor) {
        this.multiMultiplicativo *= factor;
    }

    public void sumarMulti(int factor) {
        this.multi += factor;
    }

    public void sumarFichas(int factor) {
        this.fichas += factor;
    }
}
