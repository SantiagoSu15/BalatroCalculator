package Domain;

public interface Mejora {
    void aplicarMejora(Mano mano, Carta carta);
}

class MultiCard implements Mejora {
    @Override
    public void aplicarMejora(Mano mano, Carta carta) {
        mano.sumarMulti(4);
    }
}


class BonusCard implements Mejora {
    @Override
    public void aplicarMejora(Mano mano, Carta carta) {
        mano.sumarFichas(30);
    }
}

class GlassCard implements Mejora {
    @Override
    public void aplicarMejora(Mano mano, Carta carta) {
        mano.multiplicarMulti(1.5);
    }
}

class SteelCard implements Mejora {
    @Override
    public void aplicarMejora(Mano mano, Carta carta) {
        mano.multiplicarMulti(1.5);
    }
}

