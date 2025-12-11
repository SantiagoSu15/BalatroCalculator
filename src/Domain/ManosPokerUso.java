package Domain;

import java.util.*;
import java.util.stream.Collectors;

public interface ManosPokerUso {
    boolean isPokerHand(ArrayList<Carta> cartas);
    ArrayList<Carta> getPokerHand();
    ArrayList<ArrayList<Carta>> getPokerHand2();
}


