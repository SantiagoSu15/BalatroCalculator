package Domain.ManosDePoker;

import Domain.Carta;

import java.util.*;

public abstract class Escalera {
    public Escalera() {
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


    public boolean esEscaleraConAs(List<Carta> cartas) {
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

    public List<Carta> obtenerCartasPorValores(Set<Integer> valoresEscalera, List<Carta> cartas) {
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

    public Set<Integer> obtenerTodosValoresEscalera(List<Integer> lista) {
        Set<Integer> valores = new HashSet<>();
        for (int i = 0; i <= lista.size() - 5; i++) {
            List<Integer> subLista = lista.subList(i, i + 5);
            if (esConsecutiva(subLista)) {
                valores.addAll(subLista);
            }
        }
        return valores;
    }

    public boolean esEscalera(List<Integer> lista) {
        for (int i = 0; i <= lista.size() - 5; i++) {
            List<Integer> subLista = lista.subList(i, i + 5);
            if (esConsecutiva(subLista)) {
                return true;
            }
        }
        return false;
    }

    public boolean esConsecutiva(List<Integer> lista) {
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

}
