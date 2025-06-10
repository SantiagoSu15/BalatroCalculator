package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.*;
import java.util.stream.Collectors;

public class ManoEscaleraReal implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoEscaleraReal(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas){

        if(cartas.size() <5){
            return false;
        }
        ArrayList<Integer> escalera1 = new ArrayList<>(Arrays.asList(10,11,12,13,14));
        List<Integer> lista = cartas.stream().map(c-> nuevoValor1(c))
                .sorted()
                .collect(Collectors.toList());
        ArrayList<Integer> escaleraAux = new ArrayList<>(lista);
        if(lista.size()<5){
            return false;
        }
        return esEscalera(escaleraAux,escalera1);
    }

    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){
            return mano;
        }
        ArrayList<Integer> escalera1 = new ArrayList<>(Arrays.asList(10,11,12,13,14));
        List<Integer> lista = cartas.stream().map(c-> nuevoValor1(c))
                .sorted()
                .collect(Collectors.toList());
        ArrayList<Integer> escaleraAux = new ArrayList<>(lista);
        mano.addAll(obtenerCartasPorValores(escaleraAux, cartas));

        return mano;
    }

    private List<Carta> obtenerCartasPorValores(List<Integer> valoresEscalera, List<Carta> cartas) {
        Set<Carta> cartasEscalera = new HashSet<>();

        for (Integer valor : valoresEscalera) {
            for (Carta carta : cartas) {
                if (nuevoValor1(carta) == valor ) {
                    cartasEscalera.add(carta);
                    break;
                }
            }
        }
        return new ArrayList<>(cartasEscalera);
    }


    public int nuevoValor1(Carta carta){
        switch (carta.getValor()){
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(carta.getValor());
        }

    }

    public static boolean esEscalera(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
        int j = 0;

        for (int i = 0; i <= lista2.size() - 5; i++) {
            boolean esConsecutivo = true;
            for (j = 0; j < 5; j++) {
                if (!lista2.get(i + j).equals(lista1.get(j))) {
                    esConsecutivo = false;
                    break;
                }
            }
            if (esConsecutivo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }
        return mano;
    }

}
