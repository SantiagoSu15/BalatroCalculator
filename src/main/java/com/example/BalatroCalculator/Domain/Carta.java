package com.example.BalatroCalculator.Domain;

import com.example.BalatroCalculator.Domain.Mejoras.Mejora;
import com.example.BalatroCalculator.Domain.Sellos.Sello;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public  class Carta {
    private String valor;
    private TipoMano tipo;
    private Mejora mejora;
    private Sello sello;
    private Edicion edicion;


    public int getValorNumerico(String valor) {
        HashMap<String, Integer> Valores = new HashMap();

        for(String Valor : new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10"}){
            Valores.put(Valor, Integer.parseInt(Valor));
        }
        for(String Valor : new String[]{"J", "Q", "K"}){
            Valores.put(Valor, 10);
        }
        Valores.put("A",11);

        return  Valores.getOrDefault(valor, 0);
    }

    public void activarCarta(manoJugada estado) {
        estado.sumarFichas(getValorNumerico(this.getValor()));
        if (mejora != null) {
            mejora.aplicarMejora(estado, this);
        }
    }


    public void jugarCarta(manoJugada estado) {
        activarCarta(estado);
        if (sello != null) {
            sello.createAction(estado, this);
        }
    }


}



