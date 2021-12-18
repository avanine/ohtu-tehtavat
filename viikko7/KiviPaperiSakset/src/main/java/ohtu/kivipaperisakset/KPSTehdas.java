package ohtu.kivipaperisakset;

public class KPSTehdas {

    public KiviPaperiSakset luoPeli(String pelinTyyppi) {

        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");


        if (pelinTyyppi == "a") {
            return new KPSPelaajaVsPelaaja();
        } else if (pelinTyyppi == "b") {
            return new KPSTekoaly();
        } else {
            return new KPSParempiTekoaly();
        }
    }
}
