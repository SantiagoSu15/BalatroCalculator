package Domain.ManosDePoker;

import Domain.Carta;
import Domain.ManosPokerUso;

import java.util.*;
import java.util.stream.Collectors;

import static Domain.TipoMano.*;

public class ManoEscalera implements ManosPokerUso {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> escalera;

    public ManoEscalera(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }



    @Override
    public boolean isPokerHand(ArrayList<Carta> cartas) {
        if (cartas.size() < 5) {
            return false;
        }

        List<Integer> lista1 = cartas.stream()
                .map(this::nuevoValor1)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> lista2 = cartas.stream()
                .map(this::nuevoValor2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return esEscalera(lista1) || esEscalera(lista2);
    }

    @Override
    public ArrayList<Carta> getPokerHand() {
        ArrayList<Carta> mano = new ArrayList<>();
        if (!isPokerHand(cartas)) {
            return mano;
        }

        List<Integer> lista1 = cartas.stream()
                .map(this::nuevoValor1)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> lista2 = cartas.stream()
                .map(this::nuevoValor2)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        Set<Integer> valoresEscaleras = new HashSet<>();
        valoresEscaleras.addAll(obtenerTodosValoresEscalera(lista1));
        valoresEscaleras.addAll(obtenerTodosValoresEscalera(lista2));

        if (!valoresEscaleras.isEmpty()) {
            mano.addAll(obtenerCartasPorValores(valoresEscaleras, cartas));
        }

        return mano;
    }


    @Override
    public ArrayList<ArrayList<Carta>> getPokerHand2(){
        ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
        if(!isPokerHand(cartas)){ return mano; }

        ArrayList<Carta> manoCartas = getPokerHand();
        List<List<Carta>> cartasLista = obtenerSubescaleras(manoCartas);

        for(List<Carta> cartas : cartasLista){
            ArrayList<Carta> manoCartas2 = new ArrayList<>(cartas);
            mano.add(manoCartas2);
        }
        return mano;
    }








    private List<Carta> obtenerCartasPorValores(Set<Integer> valoresEscalera, List<Carta> cartas) {
        List<Carta> cartasEscalera = new ArrayList<>();
        for (Carta carta : cartas) {
            int valor1 = nuevoValor1(carta);
            int valor2 = nuevoValor2(carta);
            if (valoresEscalera.contains(valor1) || valoresEscalera.contains(valor2)) {
                cartasEscalera.add(carta);
            }
        }
        return cartasEscalera;
    }

    private Set<Integer> obtenerTodosValoresEscalera(List<Integer> lista) {
        Set<Integer> valores = new HashSet<>();
        for (int i = 0; i <= lista.size() - 5; i++) {
            List<Integer> subLista = lista.subList(i, i + 5);
            if (esConsecutiva(subLista)) {
                valores.addAll(subLista);
            }
        }
        return valores;
    }

    private boolean esEscalera(List<Integer> lista) {
        for (int i = 0; i <= lista.size() - 5; i++) {
            List<Integer> subLista = lista.subList(i, i + 5);
            if (esConsecutiva(subLista)) {
                return true;
            }
        }
        return false;
    }

    private boolean esConsecutiva(List<Integer> lista) {
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i) != lista.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    public int nuevoValor1(Carta carta) {
        switch (carta.getValor()) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(carta.getValor());
        }
    }

    public int nuevoValor2(Carta carta) {
        switch (carta.getValor()) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 1;
            default:
                return Integer.parseInt(carta.getValor());
        }
    }

    public List<List<Carta>> obtenerSubescaleras(ArrayList<Carta> cartas) {
        List<List<Carta>> subescaleras = new ArrayList<>();

        for (int i = 0; i <= cartas.size() - 5; i++) {
            for (int j = i + 1; j < cartas.size(); j++) {
                for (int k = j + 1; k < cartas.size(); k++) {
                    for (int l = k + 1; l < cartas.size(); l++) {
                        for (int m = l + 1; m < cartas.size(); m++) {
                            List<Carta> combinacion = List.of(
                                    cartas.get(i),
                                    cartas.get(j),
                                    cartas.get(k),
                                    cartas.get(l),
                                    cartas.get(m)
                            );
                            if (esEscaleraConAs(combinacion)) {
                                subescaleras.add(combinacion);
                            }
                        }
                    }
                }
            }
        }
        return subescaleras;
    }


    private boolean esEscaleraConAs(List<Carta> cartas) {
        List<List<Integer>> posiblesListas = new ArrayList<>();
        List<Integer> lista1 = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();

        for (Carta c : cartas) {
            lista1.add(nuevoValor1(c));
            lista2.add(nuevoValor2(c));
        }

        Collections.sort(lista1);
        Collections.sort(lista2);

        posiblesListas.add(lista1);
        posiblesListas.add(lista2);

        for (List<Integer> lista : posiblesListas) {
            if (esConsecutiva(lista)) {
                return true;
            }
        }
        return false;
    }





}