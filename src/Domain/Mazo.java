package Domain;

import java.util.ArrayList;

public class Mazo {
    private ArrayList<Carta> cartas;
    public Mazo() {
        this.cartas = new ArrayList<>();
        setDefaultDeck();
    }

    public void setDefaultDeck() {
        for (TipoMano palo : TipoMano.values()) {
            for (String valor : new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}) {
                cartas.add(new Carta(palo, valor, null, null, null));
            }
        }
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public void setCartas(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta getCarta(int pos) {
        return cartas.get(pos);
    }
}
