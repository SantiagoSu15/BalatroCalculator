package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Domain.TipoMano.*;

public class ManoFullHouse implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoFullHouse(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }




    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        Map<String, Long> conteos = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        boolean tieneTrio = false;
        boolean tienePar = false;

        for (Map.Entry<String, Long> entry : conteos.entrySet()) {
            long count = entry.getValue();
            if (count >= 3 && !tieneTrio) {
                tieneTrio = true;
            } else if (count >= 2) {
                tienePar = true;
            }
        }

        return tieneTrio && tienePar;
    }


    @Override
    public ArrayList<Carta> getPokerHand() {
        ArrayList<Carta> mano = new ArrayList<>();
        if (!isPokerHand(cartas)) {
            return mano;
        }

        // Agrupar cartas por valor
        Map<String, List<Carta>> groupedByValue = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        // Encontrar el valor del trío (3 o más cartas)
        String valorTrio = null;
        for (Map.Entry<String, List<Carta>> entry : groupedByValue.entrySet()) {
            if (entry.getValue().size() >= 3) {
                valorTrio = entry.getKey();
                break;
            }
        }

        // Encontrar todos los valores con 2 o más cartas (para incluir todos los pares posibles)
        List<String> valoresPar = new ArrayList<>();
        for (Map.Entry<String, List<Carta>> entry : groupedByValue.entrySet()) {
            if (entry.getValue().size() >= 2 && !entry.getKey().equals(valorTrio)) {
                valoresPar.add(entry.getKey());
            }
        }

        // Agregar todas las cartas que coincidan con valorTrio o cualquier valorPar, respetando el orden original
        for (Carta carta : cartas) {
            if (carta.getValor().equals(valorTrio) || valoresPar.contains(carta.getValor())) {
                mano.add(carta);
            }
        }

        return mano;
    }




    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<Carta> todosFull = getPokerHand();
        List<List<Carta>> valoresFulll = obtenerSubFulls(todosFull);

        int p=0;
        for(List<Carta> v : valoresFulll){
            p++;
            mano.add(new ArrayList<>(v));

        }


        return mano;
    }

    public List<List<Carta>> obtenerSubFulls(ArrayList<Carta> cartas) {
        List<List<Carta>> subFulls = new ArrayList<>();
        for (int i = 0; i <= cartas.size() - 5; i++) {
            for (int j = i + 1; j < cartas.size(); j++) {
                for (int k = j + 1; k < cartas.size(); k++) {
                    for (int l = k + 1; l < cartas.size(); l++) {
                        for (int m = l + 1; m < cartas.size(); m++) {
                            List<Carta> combinacion = List.of(
                                    cartas.get(i),
                                    cartas.get(j),
                                    cartas.get(k),
                                    cartas.get(l),
                                    cartas.get(m)
                            );
                            if (isPokerHand(new ArrayList<>(combinacion))) {
                                subFulls.add(combinacion);
                            }
                        }
                    }
                }
            }
        }
        return subFulls;
    }


}
