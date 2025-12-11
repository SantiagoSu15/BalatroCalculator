package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.*;
import java.util.stream.Collectors;

import static Domain.TipoMano.*;

public class ManoEscalera extends Escalera implements ManosPokerUso {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> escalera;

    public ManoEscalera(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }



    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        if (cartas.size() < 5) {
            return false;
        }

        List<Integer> lista1 = cartas.stream()
                .map(this::nuevoValor1)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> lista2 = cartas.stream()
                .map(this::nuevoValor2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return esEscalera(lista1) || esEscalera(lista2);
    }

    @Override
    public ArrayList<Carta> getPokerHand() {
        ArrayList<Carta> mano = new ArrayList<>();
        if (!isPokerHand(cartas)) {
            return mano;
        }

        List<Integer> lista1 = cartas.stream()
                .map(this::nuevoValor1)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> lista2 = cartas.stream()
                .map(this::nuevoValor2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        Set<Integer> valoresEscaleras = new HashSet<>();
        valoresEscaleras.addAll(obtenerTodosValoresEscalera(lista1));
        valoresEscaleras.addAll(obtenerTodosValoresEscalera(lista2));

        if (!valoresEscaleras.isEmpty()) {
            mano.addAll(obtenerCartasPorValores(valoresEscaleras, cartas));
        }

        return mano;
    }


    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<Carta> manoCartas = getPokerHand();
        List<List<Carta>> cartasLista = obtenerSubescaleras(manoCartas);

        for(List<Carta> cartas : cartasLista){
            ArrayList<Carta> manoCartas2 = new ArrayList<>(cartas);
            mano.add(manoCartas2);
        }
        return mano;
    }



















}