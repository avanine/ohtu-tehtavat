package ohtu;

import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenOstoskorinHintaOnOikea() {
        Tuote maito = new Tuote("maito", 3);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        assertEquals(6, kori.hinta());

    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlio() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);

        List<Ostos> ostokset = kori.ostokset();

        // testaa että metodin palauttamin listan pituus 1
        assertEquals(1, ostokset.size());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlioJollaOikeaTuotteenNimiJaMaara() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);

        Ostos ostos = kori.ostokset().get(0);

        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(1, ostos.lukumaara());
    }

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenKorissaKaksiOstosta() {
        Tuote maito = new Tuote("maito", 3);
        Tuote sokeri = new Tuote("sokeri", 2);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(sokeri);

        assertEquals(2, kori.tavaroitaKorissa());
    }

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaYksiOstos() {
        Tuote maito = new Tuote("maito", 3);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        assertEquals(1, kori.ostokset().size());
    }

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaOstosJollaSamaNimiJaLkmOnKaksi() {
        Tuote maito = new Tuote("maito", 3);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        Ostos ostos = kori.ostokset().get(0);

        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(2, ostos.lukumaara());
    }
}
