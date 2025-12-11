package Domain;

import java.util.ArrayList;

public class Mano {
    private int fichas;
    private int multi;
    private double multiMultiplicativo;
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> cartasJugadas;
    private ArrayList<Joker> jokers;
    private ManoDePoker manoDePoker;


    public Mano(){
        this.cartas = new ArrayList<>();
        this.jokers = new ArrayList<>();
        this.fichas = 0;
        this.multi = 0;
        this.multiMultiplicativo = 1.0;
        this.manoDePoker = null;
    }

    public void seleccionarMano(ArrayList<Carta> cartasSeleccion) {
        cartasSeleccion.addAll(this.cartas);
    }

    public void setManoDePoker(ManoDePoker manoDePoker){
        this.manoDePoker = manoDePoker;
        this.fichas = manoDePoker.getFicha();
        this.multi = manoDePoker.getMulti();
    }

    public void setManoJugada(ArrayList<Carta> cartasJugada) {
        this.cartasJugadas = cartasJugada;
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

    public void sumarFichas(int cantidad) {
        this.fichas += cantidad;
    }

    public void sumarMulti(int cantidad) {
        this.multi += cantidad;
    }

    public void multiplicarMulti(double factor) {
        this.multiMultiplicativo *= factor;
    }

    public int getFichas() {
        return fichas;
    }

    public int getMultiAditivo() {
        return multi;
    }

    public double getMultiMultiplicativo() {
        return multiMultiplicativo;
    }

    public int calcularPuntaje() {
        int resultado = (int) (fichas * (multi * multiMultiplicativo));

        for (Carta carta : cartasJugadas) {
            if (carta.getMejora() instanceof SteelCard) {
                resultado *= 1.5;
            }
        }
        return resultado;
    }

}
