package Domain;

import java.util.ArrayList;

public class Mano {
    private int fichas;
    private int multi;
    private ArrayList<Carta> cartas;
    private ArrayList<Joker> jokers;
    private ManoDePoker manoDePoker;
    private Mazo mazo;

    public Mano(){
        this.cartas = new ArrayList<>();
        this.jokers = new ArrayList<>();
        this.fichas = 0;
        this.multi = 0;
        this.manoDePoker = null;
        this.mazo = new Mazo();
    }

    public void seleccionarMano(ArrayList<Carta> cartasSeleccion) {
        cartasSeleccion.addAll(this.cartas);
    }

    public void setManoDePoker(ManoDePoker manoDePoker){
        this.manoDePoker = manoDePoker;
        this.fichas = manoDePoker.getFicha();
        this.multi = manoDePoker.getMulti();
    }

    public void selecionaCarta(Carta carta){
        int valor = carta.getValorNumerico(carta.getValor());
        this.fichas += valor;
    }

    public void desSelecionaCarta(Carta carta){
        int valor = carta.getValorNumerico(carta.getValor());
        this.fichas -= valor;
    }

    public int getFichasTotales(){
        return fichas*multi;
    }



}
