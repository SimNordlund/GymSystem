public enum Membership {
    CURRENTMEMBER("Personen är nuvarande medlem."),
    PREVIOUSMEMBER("Personen är en före detta kund."),
    NEVERMEMBER("Personen finns inte i filen");

    public final String Type;

    Membership(String s) {
        Type = s;
    }
}
