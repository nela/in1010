public class Pasient extends Stabel<Resept> {

    private static int nextId = 0;

    protected int id = 0;
    protected String navn;
    protected String fnr;
    protected Stabel<Resept> resepter;

    public Pasient(String navn, String fnr) {
        this.navn = navn;
        this.fnr = fnr;
        this.id = nextId++;
        this.resepter = new Stabel<Resept>();
    }

    public String hentNavn() { return this.navn; }

    public String hentfNr() { return this.fnr; }

    public int hentId() { return this.id; }

    public Lenkeliste<Resept> hentResepter() { return this.resepter; }

    public void addResept(Resept resept) { this.resepter.leggTil(resept); }

    @Override
    public String toString() {
        return (this.id +": "+ this.navn + " (fnr "+ this.fnr +")");
    }
}
