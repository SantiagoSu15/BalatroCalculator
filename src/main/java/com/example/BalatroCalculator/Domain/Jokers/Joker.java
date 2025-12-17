package com.example.BalatroCalculator.Domain.Jokers;

import com.example.BalatroCalculator.Domain.Mejoras.Mejora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Joker {
    private String nombre;
    private Mejora mejora;


}
