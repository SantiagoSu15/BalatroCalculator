package com.example.BalatroCalculator.Service.DTO;

import com.example.BalatroCalculator.Domain.TipoMano;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartaDTO {
    private String valor;
    private TipoMano tipo;
}