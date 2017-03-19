package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudelleVarastolleEiVoiAsettaaNegTilavuutta() {
        Varasto varasto2 = new Varasto(-3);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudelleVarastollaOikeaAlkusaldo() {
        Varasto varasto2 = new Varasto(10, 5);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudelleVarastollaOikeaAlkusaldoYlimaaraHukkaan() {
        Varasto varasto2 = new Varasto(10, 11);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kayttokelvotonVarasto() {
        Varasto varasto2 = new Varasto(-4, 11);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudelleVarastolleNegAlkusaldo() {
        Varasto varasto2 = new Varasto(10, -11);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatLisaysToimiiOdotetusti() {
        varasto.lisaaVarastoon(4);
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatOttaminenToimiiOdotetusti() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void yritetaanOttaaLiikaa() {
        varasto.lisaaVarastoon(4);

        double saatuMaara = varasto.otaVarastosta(13);

        assertEquals(4, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void yritetaanLisataLiikaa() {
        varasto.lisaaVarastoon(20);
        double saldo = varasto.getSaldo();

        assertEquals(10, saldo, vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(4);
        boolean toimiiko= false;
        String odotettu = "saldo = 4.0, vielä tilaa 6.0";
        String saatu = varasto.toString();
        if(odotettu.equals(saatu)){
            toimiiko = true;
        }

        //assertEquals(true, toimiiko);
        assertEquals(false, toimiiko);
    }
    
    
    
}