package Domain;

public enum ManoDePoker {
    CartaAlta(5,1),
    Par(10,2),
    DoblePar(20,2),
    Trio(30,3),
    Escalera(30,4),
    Color(35,4),
    FullHouse(40,4),
    Poker(60,7),
    EscaleraCorrida(100,8),
    EscaleraReal(100,8),
    Quintilla(120,12),
    FullHouseColor(140,12),
    QuintillaColor(160,16);





    private final int ficha;
    private final int multi;

    ManoDePoker(int ficha,int multi){
        this.ficha = ficha;
        this.multi = multi;
    }

    public int getFicha() {
        return ficha;
    }
    public int getMulti() {
        return multi;
    }
}
