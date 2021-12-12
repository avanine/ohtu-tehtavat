package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matcher;

    public Or(Matcher... matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher m : matcher) {
            if (m.matches(p)) {
                return true;
            }
        }
        return false;
    }
    
}
