package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;
import com.sun.tools.javac.Main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Domain.TipoMano.Corazon;
import static Domain.TipoMano.Pica;

public class ManoDoblePar implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoDoblePar(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        Map<String, List<Carta>> cartasPorValor = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));



        int contador = 0;

        for (List<Carta> valor : cartasPorValor.values()) {
            int pares = valor.size() / 2;
            contador += pares;
        }

        if(contador >= 2){ return true;}
        return false;
    }





    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){return mano;}

        Map<String, List<Carta>> cartasPorValor = cartas.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        Map<String, List<Carta>> pares = cartasPorValor.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (Map.Entry<String, List<Carta>> entry : pares.entrySet()) {
            List<Carta> valor = entry.getValue();
            mano.addAll(new ArrayList<>(valor));
        }


        return mano;

    }






    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<Carta> cartasDisponibles = this.getPokerHand();

        Map<String, List<Carta>> agrupadasPorPalo = cartasDisponibles.stream()
                .collect(Collectors.groupingBy(Carta::getValor));

        List<List<Carta>> cartasFinales = new ArrayList<>();

        for(List<Carta> valor : agrupadasPorPalo.values()){
            List<List<Carta>> combinaciones = obtenerCombinacionesDeDos(valor);
            for(List<Carta> combinacion : combinaciones){
                ArrayList<Carta> c =  new ArrayList<>(combinacion);
                cartasFinales.add(c);
            }
        }

        List<List<Carta>> sisFinal = obtenerCombinacionesDoblePar(cartasFinales);

        for(List<Carta> combinacion : sisFinal){
            ArrayList<Carta> c =  new ArrayList<>(combinacion);
            mano.add(c);
        }

        return mano;
    }


    private List<List<Carta>> obtenerCombinacionesDeDos(List<Carta> lista) {
        List<List<Carta>> combinaciones = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                combinaciones.add(Arrays.asList(lista.get(i), lista.get(j)));
            }
        }
        return combinaciones;
    }
    private List<List<Carta>> obtenerCombinacionesDoblePar(List<List<Carta>> lista) {
        List<List<Carta>> combinaciones = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                List<Carta> listaUnida = new ArrayList<>();
                listaUnida.addAll(lista.get(i));
                listaUnida.addAll(lista.get(j));
                combinaciones.add(listaUnida);
            }
        }

        return combinaciones;
    }




}
