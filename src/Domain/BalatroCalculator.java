package Domain;

import Domain.ManosDePoker.ManoPar;
import Domain.ManosDePoker.ManoPoker;

import java.io.InvalidClassException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static Domain.ManoDePoker.Par;

public class BalatroCalculator {
    private Mazo mazo;
    private Mano mano;
    private ArrayList<Carta> cartasSeleccionadas;
    private HashMap<ManoDePoker, ArrayList<Integer>> planetarias;




    public BalatroCalculator() {
        this.mazo = new Mazo();
        this.mano = new Mano();
        this.cartasSeleccionadas = new ArrayList<>();
        this.planetarias = new HashMap<>();
    }

    public static void main(String[] args) {
        BalatroCalculator calculator = new BalatroCalculator();
        calculator.seleccionarCartas();
        calculator.MejorJugada();

    }





    public void seleccionarCartas(){

        Scanner scanner = new Scanner(System.in);
        int cont = 1;
        for(Carta c : mazo.getCartas()){
            System.out.println(cont + " " + c.getValor() + " " + c.getTipo());
            cont++;
        }

        for (int i = 0; i < 8; i++) {
            int seleccion = -1;

            while (seleccion < 1 || seleccion > mazo.getCartas().size()) {
                System.out.print("Carta " + (i + 1) + ": ");
                seleccion = scanner.nextInt();

                if (seleccion < 1 || seleccion > mazo.getCartas().size() ) {
                    System.out.println("Selección inválida. Por favor, elige un número entre 1 y " + mazo.getCartas().size() + ".");
                }
            }

            cartasSeleccionadas.add(mazo.getCarta(seleccion - 1));
        }

        System.out.println("\nLas cartas que seleccionaste son:");
        for (Carta c : cartasSeleccionadas) {
            System.out.println(c.getValor() + " " + c.getTipo());
        }
        mano.seleccionarMano(this.cartasSeleccionadas);
    }

    public void ponerPlanetarias(CartasPlanetarias carta, int nivel) {
        ArrayList<Integer> potencia = new ArrayList<>();
        ManoDePoker mano = carta.getManoDePoker();
        int fichas = carta.getFicha() * nivel;
        int multi = carta.getMulti() * nivel;
        potencia.add(fichas);
        potencia.add(multi);

        planetarias.put(mano, potencia);

    }



    public HashMap<Integer, ArrayList<Carta>> mejorBarajaDeMano(ManoDePoker mano, ArrayList<ArrayList<Carta>> cartas) {
        HashMap<Integer, ArrayList<Carta>> best = new HashMap<>();
        int mejor = 0;

        for (ArrayList<Carta> c : cartas) {
            int temp = puntosPosibles(mano, c);

            if (temp > mejor) {
                mejor = temp;
                best.clear();
                best.put(mejor, c);
            }

        }

        for (Map.Entry<Integer, ArrayList<Carta>> entry : best.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Carta> value = entry.getValue();
            ArrayList<String> valores = new ArrayList<>();
            for (Carta carta : value) {
                valores.add(carta.getValor());
            }
            System.out.println("Puntaje = " + key + " " + valores.toString());
        }

        return best;
    }

    public ArrayList<Carta> getCartas() {
        return this.cartasSeleccionadas;
    }

    public void MejorJugada() {
        HashMap<ManoDePoker, HashMap<Integer, ArrayList<Carta>>> mejor = new HashMap<>();
        HashMap<ManoDePoker, ArrayList<Carta>> mejorBaraja = new HashMap<>();
        int temp = 0;

        for (ManoDePoker p : ManoDePoker.values()) {
            try {
                String nombre = "Domain.ManosDePoker.Mano" + p.toString();
                Class<?> clase = Class.forName(nombre);
                Constructor<?> cons = clase.getConstructor(ArrayList.class);
                Object objeto = cons.newInstance(cartasSeleccionadas);
                ManosPokerUso manoDePoker = (ManosPokerUso) objeto;
                Boolean mano = manoDePoker.isPokerHand(cartasSeleccionadas);

                if (mano) {
                    System.out.println("--------------------------------------");
                    System.out.println("Se puede hacer un: " + p.toString());
                    ArrayList<Carta> cartasPosibles = manoDePoker.getPokerHand();
                    cartasPosibles.stream().forEach(c -> System.out.println("tipo " + c.getTipo() + " Valor " + c.getValor()));
                    HashMap<Integer, ArrayList<Carta>> mejorPorMano = mejorBarajaDeMano(p, manoDePoker.getPokerHand2());
                    mejor.put(p, mejorPorMano);

                    Integer maxKey = Collections.max(mejorPorMano.keySet());
                    ArrayList<Carta> cartasDeBaraja = mejorPorMano.get(maxKey);
                    if (maxKey > temp) {
                        temp = maxKey;
                        mejorBaraja.clear();
                        mejorBaraja.put(p, cartasDeBaraja);
                    } else if (maxKey == temp) {
                        mejorBaraja.put(p, cartasDeBaraja);
                    }
                    System.out.println("--------------------------------------");
                }
            } catch (RuntimeException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        if (!mejorBaraja.isEmpty()) {
            ManoDePoker manoBest = mejorBaraja.keySet().iterator().next();
            ArrayList<Carta> cartasBest = mejorBaraja.get(manoBest);
            int puntaje = puntosPosibles(manoBest, cartasBest);
            System.out.println("La mejor Mano es: " + manoBest.toString());
            ArrayList<String> valores = new ArrayList<>();

            for (Carta c : cartasBest) {
                valores.add(c.getValor());
            }

            System.out.println("Con las cartas: " + valores.toString());
            System.out.println("Y un puntaje de: " + puntaje);
        } else {
            System.out.println("No se encontro ninguna mano válida con las cartas seleccionadas.");
        }
    }

    public int puntosPosibles(ManoDePoker mano, ArrayList<Carta> cartas) {
        ArrayList<Integer> potencia = planetarias.get(mano);
        this.mano.setManoDePoker(mano);
        this.mano.setManoJugada(cartas);
        for (Carta c : cartas) {
            c.jugarCarta(this.mano);
        }
        return this.mano.calcularPuntaje();
    }
}
