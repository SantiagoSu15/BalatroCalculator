package Domain;

import java.util.HashMap;

public  class Carta {
    private String valor;
    private TipoMano tipo;
    private Mejora mejora;
    private Sello sello;
    private Edicion edicion;

    public Carta(TipoMano tipo, String valor, Mejora mejora, Sello sello, Edicion edicion){
        this.tipo = tipo;
        this.valor = valor;
        this.mejora = mejora;
        this.sello = sello;
        this.edicion = edicion;
    }

    public void setTipo(TipoMano tipo) {
        this.tipo = tipo;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setMejora(Mejora mejora) {
        this.mejora = mejora;
    }
    public void setSello(Sello sello) {
        this.sello = sello;
    }
    public void setEdicion(Edicion edicion) {
        this.edicion = edicion;
    }

    public void setAction(){
        if(sello != null){
            sello.createAction(this);
        }
    }

    public void setActionWithoutSeal(){

    }

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

    public String getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo.toString();
    }

    public int jugarCarta(){
        return getValorNumerico(this.getValor());
    }
}
