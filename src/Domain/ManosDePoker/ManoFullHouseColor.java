package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import static Domain.TipoMano.*;
import static Domain.TipoMano.Corazon;
import static Domain.TipoMano.Diamante;
import static Domain.TipoMano.Pica;
import static Domain.TipoMano.Trebol;

public class ManoFullHouseColor implements ManosPokerUso {
    private ArrayList<Carta> cartas;
    private ManoFullHouse manoFullHouse;
    private ManoColor manoColor;


    public ManoFullHouseColor(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        this.manoFullHouse = new ManoFullHouse(cartas);
        this.manoColor = new ManoColor(cartas);
    }

    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        return manoFullHouse.isPokerHand(cartas) && manoColor.isPokerHand(cartas);
    }
    @Override
    public ArrayList<Carta> getPokerHand(){
        ArrayList<Carta> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){return mano;}

        manoFullHouse.isPokerHand(cartas);
        manoColor.isPokerHand(manoFullHouse.getPokerHand());
        mano.addAll(manoColor.getPokerHand());

        return mano;
    }

    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<ArrayList<Carta>> mano2 = manoFullHouse.getPokerHand2();
        for(ArrayList<Carta> cartas : mano2){
            if(manoColor.isPokerHand(cartas)){
                mano.add(cartas);}

        }

        return mano;
    }






}
