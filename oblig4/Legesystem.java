public class Legesystem {
    public IOHandler ioHandler;

    private Lenkeliste<Legemiddel> legemidler;
    private Lenkeliste<Resept> resepter;
    private Lenkeliste<Pasient> pasienter;
    private SortertLenkeliste<Lege> leger;

    public final String[] legemiddelTyper = new String[] {
        "Vanlig", "Vanedannende", "Narkotisk" };

    public Legesystem() {
        ioHandler = new IOHandler();
        legemidler = new Lenkeliste<>();
        resepter = new Lenkeliste<>();
        pasienter = new Lenkeliste<>();
        leger = new SortertLenkeliste<>();
    }

    public void opprettPasient() {
        this.ioHandler.skrivOverskrift("Opprett ny pasient");
        String navn = this.ioHandler.lesTekst("Navn:");
        String fnr = this.ioHandler.lesTekst("Fødselsnummer:");
        Pasient p = new Pasient(navn, fnr);
        this.pasienter.leggTil(p);
        this.ioHandler.skrivMelding("Opprettet ny pasient\n\tNavn: " + navn
            + "\n\tFødselsnummer: " + fnr);
    }

    public void opprettLegemiddel() {
        this.ioHandler.skrivOverskrift("Opprett nytt legemiddel");
        String navn = this.ioHandler.lesTekst("Legemiddel navn: ");
        String type = this.ioHandler.lesTekst("Legemiddel type", legemiddelTyper);
    }
}
