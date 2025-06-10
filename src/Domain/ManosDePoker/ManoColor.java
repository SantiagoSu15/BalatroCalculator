package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.*;
import java.util.stream.Collectors;

public class ManoColor implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoColor(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        Map<String, Long> veces = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getTipo, Collectors.counting()));
        boolean seRepite = veces.values().stream()
                .anyMatch(count -> count >= 5);


        return seRepite;
    }


    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){
            return mano;
        }
        Map<String, List<Carta>> agrupadasPorPalo = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getTipo));

        List<List<Carta>> palosConSuficientesCartas = agrupadasPorPalo.values().stream()
                .filter(cartasPorPalo -> cartasPorPalo.size() >= 5)
                .collect(Collectors.toList());

        for (List<Carta> cartasPorPalo : palosConSuficientesCartas) {
            mano.addAll(cartasPorPalo);
        }

        return mano;

    }

    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }
        ArrayList<Carta> cartasDisponibles = this.getPokerHand();

        Map<String, List<Carta>> agrupadasPorPalo = cartasDisponibles.stream()
                .collect(Collectors.groupingBy(Carta::getTipo));

        for(List<Carta> grupo  : agrupadasPorPalo.values()){
            if(grupo.size() >= 5){
                List<List<Carta>> combinacion = obtenerCombinacionesDeTres(grupo);

                for (List<Carta> c : combinacion) {
                    mano.add(new ArrayList<>(c));
                }
            }

        }


        return mano;
    }


    private List<List<Carta>> obtenerCombinacionesDeTres(List<Carta> lista) {
        List<List<Carta>> combinaciones = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                for (int k = j + 1; k < lista.size(); k++) {
                    for(int l = k + 1; l < lista.size(); l++) {
                        for(int m = l + 1; m < lista.size(); m++) {
                            combinaciones.add(Arrays.asList(lista.get(i), lista.get(j), lista.get(k),lista.get(l), lista.get(m)));

                        }
                    }
                }
            }
        }
        return combinaciones;
    }

}
