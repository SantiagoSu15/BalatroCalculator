package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import static Domain.TipoMano.Corazon;
import static Domain.TipoMano.Pica;

public class ManoEscaleraCorrida implements ManosPokerUso {
    private ArrayList<Carta> cartas;
    private ManoEscalera corrida;
    private ManoColor color;


    public ManoEscaleraCorrida(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }


    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        corrida = new ManoEscalera(cartas);
        color = new ManoColor(corrida.getPokerHand());
        ArrayList<ArrayList<Carta>> todasEsca = corrida.getPokerHand2();
        int p = 0;
        for(ArrayList<Carta> cartas2 : todasEsca) {
            if(color.isPokerHand(cartas2)) {
                p++;
            }
        }
        return p>0;
    }


    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){
            return mano;
        }

        ArrayList<ArrayList<Carta>> todasEsca = corrida.getPokerHand2();
        for(ArrayList<Carta> cartas2 : todasEsca) {
            if(color.isPokerHand(cartas2)) {
                mano.addAll(cartas2);
            }
        }

        return mano;

    }
    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }
        return mano;

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


        cartas.add(new Carta(Corazon, "A", null, null, null));
        cartas.add(new Carta(Corazon, "A", null, null, null));
        cartas.add(new Carta(Corazon, "A", null, null, null));
        cartas.add(new Carta(Corazon, "K", null, null, null));
        cartas.add(new Carta(Corazon, "K", null, null, null));



        ManoEscaleraCorrida m = new ManoEscaleraCorrida(cartas);


        Boolean t = m.isPokerHand(cartas);
        System.out.println(t);
        m.getPokerHand().stream().forEach(z-> System.out.println("Tipo " + z.getTipo() + " Valor: " +z.getValor()));

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



}
