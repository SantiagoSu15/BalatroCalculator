package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Domain.TipoMano.Corazon;
import static Domain.TipoMano.Pica;

public class ManoQuintilla implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoQuintilla(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        Map<String, Long> veces = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        boolean seRepite = veces.values().stream()
                .anyMatch(count -> count >= 5);


        return seRepite;
    }


    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){return mano;}
        Map<String, List<Carta>> cartasPorValor = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        Map<String, List<Carta>> pares = cartasPorValor.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));



        for (List<Carta> par : pares.values()) {
            mano.addAll(par);
        }

        return mano;
    }

    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }
        ArrayList<Carta> cartasJugables = getPokerHand();
        Map<String, List<Carta>> agrupadasPorPalo = cartasJugables.stream()
                .collect(Collectors.groupingBy(Carta::getValor));


        for (List<Carta> pok : agrupadasPorPalo.values()) {
            List<List<Carta>> manosPokers= obtenerCombinacionesDeTres(pok);
            for (List<Carta> manos : manosPokers) {
                ArrayList<Carta> c =  new ArrayList<>(manos);
                mano.add(c);
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
