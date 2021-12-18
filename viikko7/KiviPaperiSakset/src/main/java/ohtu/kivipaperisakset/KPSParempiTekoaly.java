package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KiviPaperiSakset {

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly() {
        tekoaly = new TekoalyParannettu(100);
    }

    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}
