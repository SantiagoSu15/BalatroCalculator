package Domain;

public enum CartasPlanetarias {
    Pluton(10, 1, ManoDePoker.CartaAlta),
    Mercurio(15, 1, ManoDePoker.Par),
    Urano(20, 1, ManoDePoker.DoblePar),
    Venus(20, 2, ManoDePoker.Trio),
    Saturno(30, 3, ManoDePoker.Escalera),
    Jupiter(15, 2, ManoDePoker.Color),
    Tierra(25, 2, ManoDePoker.FullHouse),
    Marte(30, 3, ManoDePoker.Poker),
    Neptuno(40, 4, ManoDePoker.EscaleraCorrida),
    PlanetaX(35, 3, ManoDePoker.Quintilla),
    Ceres(40, 4, ManoDePoker.FullHouseColor),
    Eris(50, 3, ManoDePoker.QuintillaColor);

    private final int ficha;
    private final int multi;
    private final ManoDePoker manoDePoker;

    CartasPlanetarias(int ficha, int multi, ManoDePoker manoDePoker) {
        this.ficha = ficha;
        this.multi = multi;
        this.manoDePoker = manoDePoker;
    }

    public int getFicha() {
        return ficha;
    }

    public int getMulti() {
        return multi;
    }

    public ManoDePoker getManoDePoker() {
        return manoDePoker;
    }


}

