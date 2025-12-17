package com.example.BalatroCalculator.Service.Request;

import com.example.BalatroCalculator.Domain.ManoDePoker;
import com.example.BalatroCalculator.Service.DTO.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EvaluarManoRequest {
    private ArrayList<CartaDTO> cartas;
    private ArrayList<JokerDTO> jokers;
    private HashMap<ManoDePoker, Integer> planetarias;
}