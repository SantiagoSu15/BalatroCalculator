package com.example.BalatroCalculator.Service.Response;

import com.example.BalatroCalculator.Service.DTO.CartaDTO;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MejorJugadaResponse {
    private String manoDePoker;
    private int fichas;
    private int multi;
    private int puntajeFinal;
    private ArrayList<CartaDTO> cartasJugadas;
}