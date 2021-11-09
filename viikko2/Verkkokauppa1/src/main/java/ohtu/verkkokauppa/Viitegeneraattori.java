package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;

public class Viitegeneraattori implements ViitegeneraattoriRajapinta {
    
    private int seuraava;
    
    @Autowired
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    public int uusi(){
        return seuraava++;
    }
}
