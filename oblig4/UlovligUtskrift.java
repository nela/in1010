public class UlovligUtskrift extends Exception {
    public UlovligUtskrift(Lege lg, Legemiddel lm) {
        super("Legen " + lg.hentNavn() + " har ikke lov til å skrive ut " + lm.hentNavn());
    }
}
