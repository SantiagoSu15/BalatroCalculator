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

public class ManoQuintillaColor implements ManosPokerUso {
    private ArrayList<Carta> cartas;
    private ManoQuintilla mq;
    private ManoColor mc;

    public ManoQuintillaColor(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        int p=0;
         mq = new ManoQuintilla(cartas);
         ArrayList<Carta>  quintilla = mq.getPokerHand();
         ArrayList<ArrayList<Carta>>  todasQuintillas = mq.getPokerHand2();

        mc = new ManoColor(quintilla);
        for(ArrayList<Carta> quintilla2 : todasQuintillas) {
            if(mc.isPokerHand(quintilla2)) {
                p++;
            }
        }



        return p>0;
    }

    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if (!isPokerHand(cartas)) {
            return mano;
        }

        Map<String, List<Carta>> cartasPorValorYPalo = cartas.stream()
                .collect(Collectors.groupingBy(c -> c.getValor() + "_" + c.getTipo()));

        Map<String, List<Carta>> quintillas = cartasPorValorYPalo.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (List<Carta> quintilla : quintillas.values()) {
            if (mc.isPokerHand(new ArrayList<>(quintilla))) {
                mano.addAll(quintilla);
            }
        }

        return mano;
    }


    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<Carta>  quintilla = this.getPokerHand();
        List<List<Carta>> finales = obtenerCombinacionesDeTres(quintilla);

        for(List<Carta> cartas : finales){
            ArrayList<Carta> c =  new ArrayList<>(cartas);
            mano.add(c);
        }


        return mano;
    }


    public static void main(String[] args) {
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(Pica, "A", null, null, null));
        cartas.add(new Carta(Pica, "A", null, null, null));
        cartas.add(new Carta(Pica, "A", null, null, null));
        cartas.add(new Carta(Pica, "A", null, null, null));
        cartas.add(new Carta(Pica, "A", null, null, null));
        cartas.add(new Carta(Pica, "A", null, null, null));


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




        ManoQuintillaColor m = new ManoQuintillaColor(cartas);


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

    private List<List<Carta>> obtenerCombinacionesDeTres(List<Carta> lista) {
        List<List<Carta>> combinaciones = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                for (int k = j + 1; k < lista.size(); k++) {
                    for(int l = k + 1; l < lista.size(); l++) {
                        for(int m = l + 1; m < lista.size(); m++) {
                            List<Carta> combinacion = Arrays.asList(lista.get(i), lista.get(j), lista.get(k),lista.get(l), lista.get(m));
                            if(isPokerHand(new ArrayList<>(combinacion))) {
                                combinaciones.add(combinacion);
                            }

                        }
                    }
                }
            }
        }
        return combinaciones;
    }
}
