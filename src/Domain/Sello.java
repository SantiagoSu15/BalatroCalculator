package Domain;

import java.util.ArrayList;

import static Domain.TipoMano.Pica;

public interface Sello {
    void createAction(Mano estado, Carta carta);
    void setSeal(Carta carta);
}


class SelloRojo implements Sello {
    private boolean aplicado = false;

    public static void main(String[] args) {
        BalatroCalculator calculator = new BalatroCalculator();
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(TipoMano.Pica, "2", null, null, null)); // Carta 1: Valor 2
        cartas.add(new Carta(TipoMano.Pica, "2", new MultiCard(), new SelloRojo(), null)); // Carta 2: Valor 2, +4 multi, Sello Rojo
        int puntos = calculator.puntosPosibles(ManoDePoker.Par, cartas);
        System.out.println("Puntaje: " + puntos);
    }

    @Override
    public void setSeal(Carta carta) {
        carta.setSello(this);
    }

    @Override
    public void createAction(Mano estado, Carta carta) {
        if (aplicado) {
            return;
        }
        aplicado = true;
        carta.activarCarta(estado);
        aplicado = false;
    }

}



