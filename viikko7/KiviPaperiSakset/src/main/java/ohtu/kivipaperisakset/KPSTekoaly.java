package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    private Tekoaly tekoaly;

    public KPSTekoaly() {
        tekoaly = new Tekoaly();
    }

    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}