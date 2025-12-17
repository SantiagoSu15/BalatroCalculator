package com.example.BalatroCalculator.Service;



import com.example.BalatroCalculator.Domain.*;
import com.example.BalatroCalculator.Domain.Jokers.Joker;
import com.example.BalatroCalculator.Domain.ManosDePoker.*;

import com.example.BalatroCalculator.Service.DTO.CartaDTO;
import com.example.BalatroCalculator.Service.DTO.JokerDTO;
import com.example.BalatroCalculator.Service.Request.EvaluarManoRequest;
import com.example.BalatroCalculator.Service.Response.MejorJugadaResponse;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.*;

@Service
public class BalatroService {

    public MejorJugadaResponse evaluarMejorJugada(EvaluarManoRequest request) {
        ArrayList<Carta> cartasSeleccionadas = convertirCartas(request.getCartas());
        HashMap<ManoDePoker, ArrayList<Integer>> planetarias = procesarPlanetarias(request.getPlanetarias());

        mejorMano mejorMano = null;
        int mejorPuntaje = 0;

        // Evaluar todas las manos posibles
        for (ManoDePoker tipoMano : ManoDePoker.values()) {
            try {
                String nombreClase = "com.example.BalatroCalculator.Domain.ManosDePoker.Mano" + tipoMano.toString();
                Class<?> clase = Class.forName(nombreClase);
                Constructor<?> constructor = clase.getConstructor(ArrayList.class);
                Object objeto = constructor.newInstance(cartasSeleccionadas);
                ManosPokerUso manoPoker = (ManosPokerUso) objeto;

                if (manoPoker.isPokerHand(cartasSeleccionadas)) {
                    ArrayList<ArrayList<Carta>> combinaciones = manoPoker.getPokerHand2();

                    for (ArrayList<Carta> combinacion : combinaciones) {
                        manoJugada mJ = calcularPuntajeMano(
                                tipoMano, combinacion, request.getJokers(), planetarias
                        );

                        if (mJ.getFichasFinales() > mejorPuntaje) {
                            mejorPuntaje = mJ.getFichasFinales();
                            mejorMano = mejorMano.builder()
                                    .manoDePoker(tipoMano)
                                    .fichas(mJ.getFichas())
                                    .multi(mJ.getMulti())
                                    .fichasFinales(mJ.getFichasFinales())
                                    .cartasJugadas(combinacion)
                                    .build();
                        }
                    }
                }
            } catch (Exception e) {
                // Mano no encontrada, continuar
            }
        }

        if (mejorMano == null) {
            throw new RuntimeException("No se encontró ninguna mano válida");
        }

        return MejorJugadaResponse.builder()
                .manoDePoker(mejorMano.getManoDePoker().name())
                .fichas(mejorMano.getFichas())
                .multi(mejorMano.getMulti())
                .puntajeFinal(mejorMano.getFichasFinales())
                .cartasJugadas(convertirCartasADTO(mejorMano.getCartasJugadas()))
                .build();
    }

    private manoJugada calcularPuntajeMano(
            ManoDePoker tipoMano,
            ArrayList<Carta> cartas,
            ArrayList<JokerDTO> jokersDTO,
            HashMap<ManoDePoker, ArrayList<Integer>> planetarias) {

        // Inicializar con valores base del tipo de mano
        int fichas = tipoMano.getFicha();
        int multi = tipoMano.getMulti();

        // Aplicar planetarias (siempre, si no está en el HashMap = nivel 0)
        if (planetarias.containsKey(tipoMano)) {
            ArrayList<Integer> bonusPlanetaria = planetarias.get(tipoMano);
            fichas += bonusPlanetaria.get(0);
            multi += bonusPlanetaria.get(1);
        }

        // Crear manoJugada para aplicar efectos
        manoJugada manoJ = manoJugada.builder()
                .fichas(fichas)
                .multi(multi)
                .multiMultiplicativo(1.0)
                .cartasJugadas(cartas)
                .manoDePoker(tipoMano)
                .build();

        // Aplicar habilidades de cada carta jugada
        for (Carta carta : cartas) {
            carta.jugarCarta(manoJ);
        }

//        // Aplicar jokers si existen
//        if (jokersDTO != null && !jokersDTO.isEmpty()) {
//            ArrayList<Joker> jokersConvertidos = convertirJokers(jokersDTO);
//
//            for (Joker joker : jokersConvertidos) {
//                aplicarJoker(joker, manoJ);
//            }
//        }

        // Calcular fichas finales con el multiplicador
        int fichasFinales = (int) (manoJ.getFichas() * manoJ.getMulti() * manoJ.getMultiMultiplicativo());
        manoJ.setFichasFinales(fichasFinales);

        return manoJ;
    }

//    private void aplicarJoker(JokerDTO jokerDTO, Mano mano) {
//        // Implementar lógica de jokers según tu sistema
//        // Ejemplo básico:
//        switch (jokerDTO.getNombre().toLowerCase()) {
//            case "joker_multi":
//                mano.sumarMulti(4);
//                break;
//            case "joker_fichas":
//                mano.sumarFichas(30);
//                break;
//            // Agregar más casos según tus jokers
//        }
//    }

    private HashMap<ManoDePoker, ArrayList<Integer>> procesarPlanetarias(HashMap<ManoDePoker, Integer> planetariasRequest) {
        HashMap<ManoDePoker, ArrayList<Integer>> planetarias = new HashMap<>();

        if (planetariasRequest != null && !planetariasRequest.isEmpty()) {
            for (Map.Entry<ManoDePoker, Integer> entry : planetariasRequest.entrySet()) {
                ManoDePoker nombreMano = entry.getKey();
                int nivel = entry.getValue();

                // Si nivel es 0, no agregar al HashMap (se considera nivel base)
                if (nivel > 0) {
                    try {
                        CartasPlanetarias carta = obtenerCartaPlanetaria(nombreMano);

                        if (carta != null) {
                            ArrayList<Integer> bonus = new ArrayList<>();
                            bonus.add(carta.getFicha() * nivel);
                            bonus.add(carta.getMulti() * nivel);
                            planetarias.put(nombreMano, bonus);
                        }
                    } catch (IllegalArgumentException e) {
                        // Mano no válida, ignorar
                    }
                }
            }
        }

        return planetarias;
    }

    private CartasPlanetarias obtenerCartaPlanetaria(ManoDePoker mano) {
        for (CartasPlanetarias carta : CartasPlanetarias.values()) {
            if (carta.getManoDePoker() == mano) {
                return carta;
            }
        }
        return null;
    }

    private ArrayList<Carta> convertirCartas(ArrayList<CartaDTO> cartasDTO) {
        ArrayList<Carta> cartas = new ArrayList<>();
        for (CartaDTO dto : cartasDTO) {
            Carta carta = new Carta();
            carta.setValor(dto.getValor());
            carta.setTipo(dto.getTipo());
            cartas.add(carta);
        }
        return cartas;
    }

    private ArrayList<CartaDTO> convertirCartasADTO(ArrayList<Carta> cartas) {
        ArrayList<CartaDTO> cartasDTO = new ArrayList<>();
        for (Carta carta : cartas) {
            cartasDTO.add(CartaDTO.builder()
                    .valor(carta.getValor())
                    .tipo(carta.getTipo())
                    .build());
        }
        return cartasDTO;
    }


}