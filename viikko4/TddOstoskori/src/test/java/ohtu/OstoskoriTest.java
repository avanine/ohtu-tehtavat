package ohtu;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;

    @Before
    public void setUp() {
        kori = new Ostoskori();
    }

    // step 1
    @Test
    public void ostoskorinHintaJaTavaroidenMaaraAlussa() { 
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {
        Tuote maito = new Tuote("maito", 3);
 
        kori.lisaaTuote(maito);

        assertEquals(1, kori.tavaroitaKorissa());
 
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenOstoskorinHintaSamaKuinTuotteenHinta() {
        Tuote maito = new Tuote("maito", 3);
 
        kori.lisaaTuote(maito);

        assertEquals(3, kori.hinta());
 
    }

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenOstoskorissaOnKaksiTavaraa() {
        Tuote maito = new Tuote("maito", 3);
        Tuote leipa = new Tuote("leipä", 4);
 
        kori.lisaaTuote(maito);
        kori.lisaaTuote(leipa);

        assertEquals(2, kori.tavaroitaKorissa());
 
    }

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenOstoskorinHintaOikea() {
        Tuote maito = new Tuote("maito", 3);
        Tuote leipa = new Tuote("leipä", 4);
 
        kori.lisaaTuote(maito);
        kori.lisaaTuote(leipa);

        assertEquals(7, kori.hinta());
 
    }
    
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenOstoskorissaOnKaksiTavaraa() {
        Tuote maito = new Tuote("maito", 3);
 
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        assertEquals(2, kori.tavaroitaKorissa());
 
    }

}
