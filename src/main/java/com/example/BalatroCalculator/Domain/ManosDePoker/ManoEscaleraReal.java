package com.example.BalatroCalculator.Domain.ManosDePoker;
import com.example.BalatroCalculator.Domain.Carta;

import java.util.*;
import java.util.stream.Collectors;



public class ManoEscaleraReal extends Escalera implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoEscaleraReal(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }


    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas){
        if(cartas.size() <5){
            return false;
        }

        List<Integer> lista = cartas.stream().map(c-> nuevoValor1(c))
                .sorted()
                .collect(Collectors.toList());

        List<Integer> lista1 = cartas.stream()
                .map(this::nuevoValor1)
                .filter(n -> n >= 10)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return esEscalera(lista1);
    }

    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){
            return mano;
        }
        List<Integer> lista =
                cartas.stream()
                        .map(c -> nuevoValor1(c))
                        .filter(n -> n >= 10)
                        .distinct()
                .sorted()
                .collect(Collectors.toList());


        mano.addAll(obtenerCartasPorValores(new HashSet<>(lista), cartas));

        return mano;
    }

    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<Carta> cartasEscalera = getPokerHand();
        List<List<Carta>> subEscaleras = obtenerSubescaleras(cartasEscalera);
        for (List<Carta> cartas : subEscaleras) {
            ArrayList<Carta> manoCartas2 = new ArrayList<>(cartas);
            mano.add(manoCartas2);
        }

        return mano;
    }

}
