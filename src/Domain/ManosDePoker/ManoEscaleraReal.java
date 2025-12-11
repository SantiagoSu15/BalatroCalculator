package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.*;
import java.util.stream.Collectors;

import static Domain.TipoMano.Corazon;
import static Domain.TipoMano.Pica;

public class ManoEscaleraReal extends Escalera implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoEscaleraReal(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public static void main(String[] args) {
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(Pica, "A", null, null, null));
        cartas.add(new Carta(Pica, "J", null, null, null));
        cartas.add(new Carta(Pica, "10", null, null, null));
        cartas.add(new Carta(Pica, "Q", null, null, null));
        cartas.add(new Carta(Pica, "Q", null, null, null));
        cartas.add(new Carta(Pica, "Q", null, null, null));
        cartas.add(new Carta(Pica, "K", null, null, null));
        cartas.add(new Carta(Pica, "K", null, null, null));
        cartas.add(new Carta(Pica, "9", null, null, null));


        cartas.add(new Carta(Corazon, "A", null, null, null));
        cartas.add(new Carta(Corazon, "A", null, null, null));
        cartas.add(new Carta(Corazon, "A", null, null, null));
        cartas.add(new Carta(Corazon, "K", null, null, null));


        cartas.add(new Carta(Corazon, "K", null, null, null));
        cartas.add(new Carta(Corazon, "K", null, null, null));


        ManoEscaleraReal m = new ManoEscaleraReal(cartas);


        Boolean t = m.isPokerHand(cartas);
        System.out.println(t);
        m.getPokerHand().stream().forEach(z -> System.out.println("Tipo " + z.getTipo() + " Valor: " + z.getValor()));

        ArrayList<ArrayList<Carta>> fullHouses = m.getPokerHand2();
        System.out.println(fullHouses.size());
        System.out.println("Número de combinaciones: " + fullHouses.size());
        for (ArrayList<Carta> combinacion : fullHouses) {
            String valores = combinacion.stream()
                    .map(Carta::getValor)
                    .collect(Collectors.joining(""));
            System.out.println(valores);
        }
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
