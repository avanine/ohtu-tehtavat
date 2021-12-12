package statistics.matcher;

public class QueryBuilder {

    private Matcher matcher;

    public QueryBuilder() {
        this.matcher = new All();
    }

    public Matcher build() {
        Matcher temp = matcher;
        this.matcher = new All();
        return temp;
    }

    public Matcher playsIn(String team) {
        matcher = new And(matcher, new PlaysIn(team));
        return matcher;
    }

    public Matcher hasAtLeast(int value, String category) {
        matcher = new And(matcher, new HasAtLeast(value, category));
        return matcher;
    }

    public Matcher hasFewerThan(int value, String category) {
        matcher = new And(matcher, new HasFewerThan(value, category));
        return matcher;
    }

    public Matcher oneOf(Matcher m1, Matcher m2) {
        matcher = new And(matcher, new Or(m1, m2));
        return matcher;
    }
}
