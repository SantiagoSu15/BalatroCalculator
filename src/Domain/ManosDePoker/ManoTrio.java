package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.*;
import java.util.stream.Collectors;

public class ManoTrio implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoTrio(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {

        Map<String, Long> veces = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        boolean seRepite = veces.values().stream()
                .anyMatch(count -> count >= 3);


        return seRepite;
    }


    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){return mano;}
        Map<String, List<Carta>> cartasPorValor = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        Map<String, List<Carta>> pares = cartasPorValor.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (List<Carta> par : pares.values()) {
            mano.addAll(par);
        }
        return mano;
    }


    public ArrayList<ArrayList<Carta>> getPokerHand2() {
        ArrayList<ArrayList<Carta>> trio = new ArrayList<>();
        if(!isPokerHand(cartas)){return trio;}
        Map<String, List<Carta>> cartasPorValor = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        for (List<Carta> grupo : cartasPorValor.values()) {
            if (grupo.size() >= 3) {
                List<List<Carta>> combinaciones = obtenerCombinacionesDeTres(grupo);

                for (List<Carta> combinacion : combinaciones) {
                    trio.add(new ArrayList<>(combinacion));
                }
            }
        }

        return trio;
    }


    //combinaciones de 3 para los trios
    private List<List<Carta>> obtenerCombinacionesDeTres(List<Carta> lista) {
        List<List<Carta>> combinaciones = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                for (int k = j + 1; k < lista.size(); k++) {
                    combinaciones.add(Arrays.asList(lista.get(i), lista.get(j), lista.get(k)));
                }
            }
        }
        return combinaciones;
    }




}
