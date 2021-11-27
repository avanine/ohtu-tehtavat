package ohtu;

import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;
    Tuote maito;

    @Before
    public void setUp() {
        kori = new Ostoskori();
        maito = new Tuote("maito", 3);
    }

    // step 1
    @Test
    public void ostoskorinHintaJaTavaroidenMaaraAlussa() {
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {

        kori.lisaaTuote(maito);

        assertEquals(1, kori.tavaroitaKorissa());

    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenOstoskorinHintaSamaKuinTuotteenHinta() {

        kori.lisaaTuote(maito);

        assertEquals(3, kori.hinta());

    }

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenOstoskorissaOnKaksiTavaraa() {
        Tuote leipa = new Tuote("leipä", 4);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(leipa);

        assertEquals(2, kori.tavaroitaKorissa());

    }

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenOstoskorinHintaOikea() {
        Tuote leipa = new Tuote("leipä", 4);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(leipa);

        assertEquals(7, kori.hinta());

    }

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenOstoskorissaOnKaksiTavaraa() {

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        assertEquals(2, kori.tavaroitaKorissa());

    }

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenOstoskorinHintaOnOikea() {

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        assertEquals(6, kori.hinta());

    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlio() {
        kori.lisaaTuote(maito);

        List<Ostos> ostokset = kori.ostokset();

        // testaa että metodin palauttamin listan pituus 1
        assertEquals(1, ostokset.size());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlioJollaOikeaTuotteenNimiJaMaara() {
        kori.lisaaTuote(maito);

        Ostos ostos = kori.ostokset().get(0);

        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(1, ostos.lukumaara());
    }

    @Test
    public void kahdenEriTuotteenLisaamisenJalkeenKorissaKaksiOstosta() {
        Tuote sokeri = new Tuote("sokeri", 2);

        kori.lisaaTuote(maito);
        kori.lisaaTuote(sokeri);

        assertEquals(2, kori.tavaroitaKorissa());
    }

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaYksiOstos() {

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        assertEquals(1, kori.ostokset().size());
    }

    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaOstosJollaSamaNimiJaLkmOnKaksi() {

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);

        Ostos ostos = kori.ostokset().get(0);

        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(2, ostos.lukumaara());
    }

    @Test
    public void koriinJaaYksiKplTuotettaKunToinenPoistetaan() {

        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        kori.poista(maito);

        Ostos ostos = kori.ostokset().get(0);

        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(1, ostos.lukumaara());
    }

    @Test
    public void koriOnTyhjaKunAinoaTuotePoistetaan() {

        kori.lisaaTuote(maito);
        kori.poista(maito);

        assertEquals(0, kori.ostokset().size());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

    @Test
    public void tyhjennaTyhjentaaKorin() {
        Tuote sokeri = new Tuote("sokeri", 2);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(sokeri);

        kori.tyhjenna();

        assertEquals(0, kori.ostokset().size());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }
}
