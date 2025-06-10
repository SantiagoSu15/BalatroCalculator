package Domain;

public interface Sello {
    void createAction(Carta carta);
    void setSeal(Carta carta);
}


class SelloRojo implements Sello {
    private boolean aplicado = false;
    @Override
    public void createAction(Carta carta) {
        if(!aplicado ){
            carta.setAction();
            aplicado = true;
        }else{
            aplicado =false;
        }

    }

    @Override
    public void setSeal(Carta carta) {
        carta.setSello(this);
    }
}
