package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Domain.TipoMano.Corazon;
import static Domain.TipoMano.Pica;

public class ManoEscaleraCorrida extends Escalera implements ManosPokerUso {
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
            if (color.isPokerHand(cartas2)) {
                for (Carta carta : cartas2) {
                    if (!mano.contains(carta)) {
                        mano.add(carta);
                    }
                }
            }
        }

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
