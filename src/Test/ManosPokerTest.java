package Test;

import Domain.Carta;
import Domain.ManosDePoker.*;
import org.junit.*;

import java.util.ArrayList;

import static Domain.TipoMano.*;

public class ManosPokerTest {
    private Carta AsTreboles, KCorazon, QCorazon, DiezPicas, JDiamante, DosDiamane, TresDiamane,
     Cuatro, Cinco;

    @Before
    public void prepareTest() {
         AsTreboles = new Carta(Trebol,"A",null,null,null);
         KCorazon = new Carta(Corazon,"K",null,null,null);
         QCorazon = new Carta(Corazon,"Q",null,null,null);
         DiezPicas = new Carta(Pica,"10",null,null,null);
         JDiamante = new Carta(Diamante,"J",null,null,null);
         DosDiamane = new Carta(Diamante,"2",null,null,null);
         TresDiamane = new Carta(Diamante,"3",null,null,null);
         Cuatro = new Carta(Trebol,"4",null,null,null);
         Cinco = new Carta(Trebol,"5",null,null,null);
    }

    @Test
    public void shouldBeHighCard(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(AsTreboles);
        cartas.add(KCorazon);
        cartas.add(QCorazon);
        cartas.add(DiezPicas);
        cartas.add(JDiamante);
        cartas.add(DosDiamane);
        cartas.add(TresDiamane);
        cartas.add(Cuatro);

        ManoCartaAlta mc = new ManoCartaAlta(cartas);
        Assert.assertTrue(mc.isPokerHand(cartas));
        Assert.assertTrue(mc.getPokerHand().size() == 8);
        Assert.assertTrue(mc.getPokerHand2().size() == 8);
    }

    @Test
    public void shouldBeStair(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(AsTreboles);
        cartas.add(KCorazon);
        cartas.add(QCorazon);
        cartas.add(DiezPicas);
        cartas.add(DiezPicas);
        cartas.add(JDiamante);
        cartas.add(JDiamante);

        ManoEscalera manoEscalera = new ManoEscalera(cartas);

        Assert.assertTrue(manoEscalera.isPokerHand(cartas));
        Assert.assertTrue(manoEscalera.getPokerHand().size() == 7);
        manoEscalera.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));

        System.out.println("----------------------------");
        ArrayList<Carta> cartas2 = new ArrayList<Carta>();

        Carta AsPica = new Carta(Pica,"A",null,null,null);

        cartas2.add(TresDiamane);
        cartas2.add(KCorazon);
        cartas2.add(DiezPicas);
        cartas2.add(DosDiamane);
        cartas2.add(JDiamante);
        cartas2.add(Cuatro);
        cartas2.add(AsTreboles);
        cartas2.add(AsPica);
        cartas2.add(Cinco);
        cartas2.add(Cinco);


        Assert.assertTrue(manoEscalera.isPokerHand(cartas2));
        Assert.assertTrue(manoEscalera.getPokerHand().size() == 7);
        manoEscalera.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));


    }

    @Test
    public void shouldBeStair2(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);

        cartas.add(AsTreboles);
        cartas.add(KCorazon);
        cartas.add(QCorazon);
        cartas.add(DiezPicas);
        cartas.add(DiezPicas);
        cartas.add(JDiamante);
        cartas.add(JDiamante);
        cartas.add(AsTreboles);
        cartas.add(TresDiamane);
        cartas.add(DosDiamane);
        cartas.add(Cuatro);
        cartas.add(Cinco);


        ManoEscalera manoEscalera = new ManoEscalera(cartas);

        Assert.assertTrue(manoEscalera.isPokerHand(cartas));
        Assert.assertTrue(manoEscalera.getPokerHand().size() == 12);
        manoEscalera.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));


    }

    @Test
    public void shouldBePair(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        cartas.add(AsPica);
        cartas.add(AsTreboles);
        cartas.add(AsTreboles);
        ManoPar manoPar = new ManoPar(cartas);
        Assert.assertTrue(manoPar.isPokerHand(cartas));
        Assert.assertTrue(manoPar.getPokerHand().size() == 3);
        manoPar.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));
    }

    @Test
    public void shouldBeThird(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        cartas.add(AsPica);
        cartas.add(AsTreboles);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);
        cartas.add(Cinco);
        ManoTrio manoPar = new ManoTrio(cartas);
        Assert.assertTrue(manoPar.isPokerHand(cartas));
        //Assert.assertTrue(manoPar.getPokerHand().size() == 4);
        manoPar.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));


        System.out.println(manoPar.getPokerHand2().size());
    }

    @Test
    public void shouldBeColor(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(JDiamante);
        cartas.add(DosDiamane);
        cartas.add(TresDiamane);
        cartas.add(TresDiamane);
        cartas.add(TresDiamane);
        Carta Ascarta = new Carta(Trebol,"A",null,null,null);
        Carta QTrebol = new Carta(Trebol,"Q",null,null,null);
        cartas.add(Ascarta);
        cartas.add(QTrebol);
        cartas.add(Ascarta);
        cartas.add(QTrebol);
        cartas.add(Ascarta);

        ManoColor manoColor  = new ManoColor(cartas);
        Assert.assertTrue(manoColor.isPokerHand(cartas));
        Assert.assertTrue(manoColor.getPokerHand().size() == 10);
        manoColor.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));
        Assert.assertTrue(manoColor.getPokerHand2().size() == 2);
    }

    @Test
    public void shouldBeFullHouse(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        Carta KDiamante = new Carta(Diamante,"K",null,null,null);

        cartas.add(AsCorazon);
        cartas.add(AsPica);
        cartas.add(AsTreboles);
        cartas.add(KCorazon);
        cartas.add(KCorazon);
        cartas.add(KDiamante);
        cartas.add(KDiamante);
        cartas.add(Cinco);
        cartas.add(Cinco);

        ManoFullHouse manoFullHouse = new ManoFullHouse(cartas);
        Assert.assertTrue(manoFullHouse.isPokerHand(cartas));
        Assert.assertTrue(manoFullHouse.getPokerHand().size() == 9);
        manoFullHouse.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));


    }


    @Test
    public void shouldBePoker(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        Carta AsDiamante = new Carta(Diamante,"A",null,null,null);
        cartas.add(AsPica);
        cartas.add(AsTreboles);
        cartas.add(AsCorazon);
        cartas.add(AsDiamante);
        cartas.add(AsDiamante);
        ManoPoker manoPar = new ManoPoker(cartas);
        Assert.assertTrue(manoPar.isPokerHand(cartas));
        Assert.assertTrue(manoPar.getPokerHand().size() == 5);
        manoPar.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));

    }

    @Test
    public void shouldBeColorStair(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        Carta Jcorazon = new Carta(Corazon,"J",null,null,null);
        Carta DiezCorazon = new Carta(Corazon,"10",null,null,null);
        cartas.add(AsCorazon);
        cartas.add(Jcorazon);
        cartas.add(DiezCorazon);
        cartas.add(KCorazon);
        cartas.add(QCorazon);
        cartas.add(QCorazon);

        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta DosPica = new Carta(Pica,"2",null,null,null);
        Carta TresPica = new Carta(Pica,"3",null,null,null);
        Carta CuatroPica = new Carta(Pica,"4",null,null,null);
        Carta CincoPica = new Carta(Pica,"5",null,null,null);
        cartas.add(AsPica);
        cartas.add(DosPica);
        cartas.add(TresPica);
        cartas.add(CuatroPica);
        cartas.add(CincoPica);


        ManoEscaleraCorrida manoEscalera = new ManoEscaleraCorrida(cartas);
        Assert.assertTrue(manoEscalera.isPokerHand(cartas));
        manoEscalera.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));

    }

    @Test
    public void shouldBeQuintilla(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        Carta AsDiamante = new Carta(Diamante,"A",null,null,null);
        Carta AsDiamante2 = new Carta(Diamante,"A",null,null,null);
        cartas.add(AsPica);
        cartas.add(AsTreboles);
        cartas.add(AsCorazon);
        cartas.add(AsDiamante);
        cartas.add(AsDiamante2);
        cartas.add(AsDiamante2);
        ManoQuintilla manoPar = new ManoQuintilla(cartas);
        Assert.assertTrue(manoPar.isPokerHand(cartas));
        manoPar.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));

    }


    @Test
    public void shouldBeFullHouseColor(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta AsPica2 = new Carta(Pica,"A",null,null,null);
        Carta DiezPicas2 = new Carta(Pica,"10",null,null,null);
        Carta DiezPicas3 = new Carta(Pica,"10",null,null,null);
        cartas.add(AsPica2);
        cartas.add(AsPica);

        cartas.add(DiezPicas);
        cartas.add(DiezPicas2);
        cartas.add(DiezPicas3);
        cartas.add(DiezPicas3);

        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        Carta Jcorazon = new Carta(Corazon,"J",null,null,null);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);
        cartas.add(Jcorazon);
        cartas.add(Jcorazon);
        cartas.add(Jcorazon);


        ManoFullHouseColor manoFullHouse = new ManoFullHouseColor(cartas);

        Assert.assertTrue(manoFullHouse.isPokerHand(cartas));

        manoFullHouse.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));
       Assert.assertTrue(manoFullHouse.getPokerHand().size() == 11);
    }

    @Test
    public void shouldBeQuintillaColor(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        Carta AsPica = new Carta(Pica,"A",null,null,null);
        Carta AsPica2 = new Carta(Pica,"A",null,null,null);
        Carta AsPica3 = new Carta(Pica,"A",null,null,null);
        Carta AsPica4 = new Carta(Pica,"A",null,null,null);
        Carta AsPica5 = new Carta(Pica,"A",null,null,null);
        cartas.add(AsPica);
        cartas.add(AsPica2);
        cartas.add(AsPica3);
        cartas.add(AsPica4);
        cartas.add(AsPica5);

        Carta AsCorazon = new Carta(Corazon,"A",null,null,null);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);
        cartas.add(AsCorazon);

        ManoQuintillaColor manoPar = new ManoQuintillaColor(cartas);
        Assert.assertTrue(manoPar.isPokerHand(cartas));

        manoPar.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));

        Assert.assertTrue(manoPar.getPokerHand().size() == 11);
    }

    @Test
    public void shouldBePair2(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta AsPica = new Carta(Pica,"A",null,null,null);
        cartas.add(AsPica);
        cartas.add(AsPica);
        cartas.add(AsTreboles);
        cartas.add(AsTreboles);
        ManoDoblePar manoPar = new ManoDoblePar(cartas);
        System.out.println(manoPar.isPokerHand(cartas));
        manoPar.getPokerHand().stream().forEach(c->System.out.println("tipo "+c.getTipo() + " Valor "+c.getValor()));
        Assert.assertTrue(manoPar.isPokerHand(cartas));

        Assert.assertTrue(manoPar.getPokerHand().size() == 4);

    }

}
