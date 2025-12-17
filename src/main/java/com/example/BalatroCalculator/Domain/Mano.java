package com.example.BalatroCalculator.Domain;


import com.example.BalatroCalculator.Domain.Jokers.Joker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Mano {

    private ArrayList<Carta> cartas = new ArrayList<>();
    private ArrayList<Joker> jokers = new ArrayList<>();

    public void seleccionarMano(ArrayList<Carta> cartasSeleccion) {
        this.cartas.clear();
        this.cartas.addAll(cartasSeleccion);
    }



}
