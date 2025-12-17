package com.example.BalatroCalculator.Domain.ManosDePoker;


import com.example.BalatroCalculator.Domain.Carta;

import java.util.*;

public interface ManosPokerUso {
    boolean isPokerHand(ArrayList<Carta> cartas);
    ArrayList<Carta> getPokerHand();
    ArrayList<ArrayList<Carta>> getPokerHand2();
}


