    package Domain.ManosDePoker;

    import Domain.Carta;
    import Domain.ManosPokerUso;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;

    public class ManoPar implements ManosPokerUso {
        private ArrayList<Carta> cartas;

        public ManoPar(ArrayList<Carta> cartas) {
            this.cartas = cartas;
        }


        @Override
        public boolean isPokerHand(ArrayList<Carta> cartas) {
            Map<String, Long> veces = cartas.stream()
                    .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
            boolean seRepite = veces.values().stream()
                    .anyMatch(count -> count >= 2);
            return seRepite;
        }

        @Override
        public ArrayList<Carta> getPokerHand(){
            ArrayList<Carta> mano = new ArrayList<>();
            if(!isPokerHand(cartas)){return mano;}
            Map<String, List<Carta>> cartasPorValor = cartas.stream()
                    .collect(Collectors.groupingBy(Carta::getValor));

            Map<String, List<Carta>> pares = cartasPorValor.entrySet().stream()
                    .filter(entry -> entry.getValue().size() >= 2)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            for (List<Carta> par : pares.values()) {
                mano.addAll(par);
            }

            return mano;

        }

        private List<List<Carta>> obtenerCombinacionesDeDos(List<Carta> lista) {
            List<List<Carta>> combinaciones = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                for (int j = i + 1; j < lista.size(); j++) {
                    combinaciones.add(Arrays.asList(lista.get(i), lista.get(j)));
                }
            }
            return combinaciones;
        }


        @Override
        public ArrayList<ArrayList<Carta>> getPokerHand2(){
            ArrayList<ArrayList<Carta>> mano = new ArrayList<>();
            if(!isPokerHand(cartas)){ return mano; }

            ArrayList<Carta> cartasDisponibles = this.getPokerHand();

            Map<String, List<Carta>> agrupadasPorPalo = cartasDisponibles.stream()
                    .collect(Collectors.groupingBy(Carta::getValor));

            for(List<Carta> valor : agrupadasPorPalo.values()){
                List<List<Carta>> combinaciones = obtenerCombinacionesDeDos(valor);
                for(List<Carta> combinacion : combinaciones){
                    ArrayList<Carta> c =  new ArrayList<>(combinacion);
                    mano.add(c);
                }

            }

            return mano;
        }





    }
