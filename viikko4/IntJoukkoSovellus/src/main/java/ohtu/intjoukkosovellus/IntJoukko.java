
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luvut = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            luvut[0] = luku;
            alkioidenLkm++;
            return true;
        }

        if (alkioidenLkm == luvut.length) {
            int[] uusiTaulukko = new int[luvut.length + kasvatuskoko];
            kopioiTaulukko(luvut, uusiTaulukko);
            luvut = uusiTaulukko;
        }

        if (!kuuluu(luku)) {
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                luvut[i] = 0;
                for (int j = i; j < alkioidenLkm - 1; j++) {
                    this.luvut[i] = luvut[j + 1];
                }
        
                alkioidenLkm--;
                return true;
            }
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int koko() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String merkkijono = "{" + luvut[0];
            for (int i = 1; i < alkioidenLkm; i++) {
                merkkijono += ", " + luvut[i];
            }
            merkkijono += "}";
            return merkkijono;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko ekaTaulukko, IntJoukko tokaTaulukko) {
        IntJoukko yhdisteTaulukko = new IntJoukko();
        for (int i = 0; i < ekaTaulukko.alkioidenLkm; i++) {
            yhdisteTaulukko.lisaa(ekaTaulukko.luvut[i]);
        }
        for (int i = 0; i < tokaTaulukko.alkioidenLkm; i++) {
            yhdisteTaulukko.lisaa(tokaTaulukko.luvut[i]);
        }
        return yhdisteTaulukko;
    }

    public static IntJoukko leikkaus(IntJoukko ekaTaulukko, IntJoukko tokaTaulukko) {
        IntJoukko leikkausTaulukko = new IntJoukko();
        for (int i = 0; i < ekaTaulukko.alkioidenLkm; i++) {
            for (int j = 0; j < tokaTaulukko.alkioidenLkm; j++) {
                if (ekaTaulukko.luvut[i] == tokaTaulukko.luvut[j]) {
                    leikkausTaulukko.lisaa(tokaTaulukko.luvut[j]);
                }
            }
        }
        return leikkausTaulukko;
    }
    
    public static IntJoukko erotus ( IntJoukko ekaTaulukko, IntJoukko tokaTaulukko) {
        IntJoukko erotusTaulukko = new IntJoukko();
        for (int i = 0; i < ekaTaulukko.alkioidenLkm; i++) {
            erotusTaulukko.lisaa(ekaTaulukko.luvut[i]);
        }
        for (int i = 0; i < tokaTaulukko.alkioidenLkm; i++) {
            erotusTaulukko.poista(tokaTaulukko.luvut[i]);
        } 
        return erotusTaulukko;
    }    
}
