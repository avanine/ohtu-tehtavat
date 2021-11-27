package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.common.annotations.VisibleForTesting;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);

        // tuote #1 - hinta 5, saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tuote #2 - hinta 4, saldo 8
        when(varasto.saldo(2)).thenReturn(8);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feta", 4));

        // tuote #3 - hinta 100, saldo 0
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kaviaari", 100));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        when(viite.uusi()).thenReturn(42);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {

        when(viite.uusi()).thenReturn(43);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(43), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void kahdenEriTuotteenOstamisessaOikeatParametrit() {

        when(viite.uusi()).thenReturn(44);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(44), eq("12345"), eq("33333-44455"), eq(9));
    }

    @Test
    public void kahdenSamanTuotteenOstamisessaOikeatParametrit() {

        when(viite.uusi()).thenReturn(45);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(45), eq("12345"), eq("33333-44455"), eq(10));
    }

    @Test
    public void saldollisenJaLoppuneenTuotteenOstamisessaOikeatParametrit() {

        when(viite.uusi()).thenReturn(46);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(46), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi()).thenReturn(46);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("batman", "112233");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("robin", "223344");

        verify(pankki).tilisiirto(eq("robin"), eq(46), eq("223344"), eq("33333-44455"), eq(4));
    }

    @Test
    public void uusiViitenumeroJokaiselleMaksutapahtumalle() {
        when(viite.uusi()).thenReturn(12).thenReturn(13).thenReturn(14);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("asiakas", "111111");

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("ossi", "000000");

        verify(pankki).tilisiirto(eq("ossi"), eq(14), eq("000000"), anyString(), anyInt());
    }

    @Test
    public void koristaPoistaminenPalauttaaVarastoon() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);

        verify(varasto).saldo(eq(1));
    }

}