package Domain;

import java.io.InvalidClassException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BalatroCalculator {
    private Mazo mazo;
    private Mano mano;
    private ArrayList<Carta> cartasSeleccionadas;
    public BalatroCalculator() {
        this.mazo = new Mazo();
        this.mano = new Mano();
        this.cartasSeleccionadas = new ArrayList<>();
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


    public int puntosPosibles(ManoDePoker mano, ArrayList<Carta> cartas){
        int fichas = mano.getFicha();
        int multi = mano.getMulti();
        for (Carta c : cartas) {
            fichas += c.jugarCarta();
        }
        return fichas * multi;
    }

    public int mejorBarajaDeMano(ManoDePoker mano, ArrayList<ArrayList<Carta>> cartas){
        int fichas = mano.getFicha();
        int multi = mano.getMulti();

        int mejor = 0;

        for (ArrayList<Carta> c : cartas) {
            int temp = fichas;
            for(Carta carta : c){
                temp+= carta.jugarCarta();
            }
            temp = temp * multi;
            if(temp > mejor){
                mejor = temp;
            }

        }
        return mejor;
    }




    public void MejorJugada()  {
        HashMap<ManoDePoker,ArrayList<Carta>> mejor = new HashMap<>();
        for(ManoDePoker p : ManoDePoker.values()){
            try{
                String nombre =  "Domain.ManosDePoker.Mano"+p.toString();
                Class<?> clase = Class.forName(nombre);
                Object objeto = clase.getDeclaredConstructor().newInstance();
                ManosPokerUso manoDePoker = (ManosPokerUso) objeto;
                Boolean mano = manoDePoker.isPokerHand(cartasSeleccionadas);

                if(mano) {
                    System.out.println("Se puede hacer un: " + p.toString());
                    ArrayList<Carta> cartasPosibles = manoDePoker.getPokerHand();
                    cartasPosibles.stream().forEach(c -> System.out.println("tipo " + c.getTipo() + " Valor " + c.getValor()));
                    mejor.put(p, cartasPosibles);
                    System.out.println(puntosPosibles(p,cartasPosibles));

                }
            }catch (RuntimeException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }







    public static void main(String[] args) {
        BalatroCalculator calculator = new BalatroCalculator();
        calculator.seleccionarCartas();
        calculator.MejorJugada();
    }

}
