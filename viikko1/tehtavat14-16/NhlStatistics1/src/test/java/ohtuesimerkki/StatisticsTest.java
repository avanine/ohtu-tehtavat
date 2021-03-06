package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void olemassaolevaNimiLoytyy() {
        assertEquals("Kurri", stats.search("Kurri").getName());
        assertNull(stats.search("moi"));
    }

    @Test
    public void pelaajatOikeissaTiimeissa() {
        assertEquals(3, stats.team("EDM").size());
        assertEquals(1, stats.team("PIT").size());
        assertEquals(1, stats.team("DET").size());
    }

    @Test
    public void parhaatPelaajatOikein() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
}
