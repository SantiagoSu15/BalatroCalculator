package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManoCartaAlta implements ManosPokerUso {
    private ArrayList<Carta> cartas;

    public ManoCartaAlta(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        return !cartas.isEmpty();
    }

    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(isPokerHand(cartas)){
            List<Carta> c = cartas.stream()
                    .sorted(Comparator.comparing(
                            carta -> carta.getValorNumerico(carta.getValor()),
                            Comparator.reverseOrder())).collect(Collectors.toList());
            mano.addAll(c);
        }else{
            return mano;
        }
        return mano;
    }


    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){
            return mano;
        }
        ArrayList<Carta> cartas = this.getPokerHand();
        for(Carta c : cartas){
            ArrayList<Carta> cartaUnica = new ArrayList<>();
            cartaUnica.add(c);
            mano.add(cartaUnica);
        }
        return mano;

    }
}
