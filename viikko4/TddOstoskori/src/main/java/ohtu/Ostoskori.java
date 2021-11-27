package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Ostoskori {

    List<Ostos> ostokset = new ArrayList<>();
 
    public int tavaroitaKorissa() {
        // kertoo korissa olevien tavaroiden lukumäärän
        // eli jos koriin lisätty 2 kpl tuotetta "maito", 
        //   tulee metodin palauttaa 2 
        // jos korissa on 1 kpl tuotetta "maito" ja 1 kpl tuotetta "juusto", 
        //   tulee metodin palauttaa 2 
        
        if (ostokset.isEmpty()) {
            return 0;
        }

        int maara = 0;

        for (int i = 0; i < ostokset.size(); i++) {
            maara += ostokset.get(i).lukumaara();
        }
        return maara;
    }
 
    public int hinta() {
        // kertoo korissa olevien tuotteiden yhteenlasketun hinnan
 
        if (ostokset.isEmpty()) {
            return 0;
        }
        int hinta = 0;
        for (int i = 0; i < ostokset.size(); i++) {
            hinta += ostokset.get(i).hinta();
        }
        return hinta;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        Ostos ostos = new Ostos(lisattava);
        boolean tuoteOnKorissa = false;

        for (int i = 0; i < ostokset.size(); i++) {
            if (ostokset.get(i).tuotteenNimi().equals(lisattava.getNimi())) {
                tuoteOnKorissa = true;
            }
        }

        if (!tuoteOnKorissa) {
            ostokset.add(ostos);
        } else {
            ostokset.get(ostokset.indexOf(ostos)).muutaLukumaaraa(1);
        }
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
    }
 
    public List<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset
 
        return null;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }
}
