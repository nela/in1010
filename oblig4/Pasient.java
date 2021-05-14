public class Pasient extends Stabel<Resept> {

    private static int nextId = 0;

    protected int id = 0;
    protected String navn;
    protected long fnr;
    protected Stabel<Resept> resepter;

    public Pasient(String navn, long fnr) {
        this.navn = navn;
        this.fnr = fnr;
        this.id = ++nextId;
        this.resepter = new Stabel<Resept>();
    }

    public String hentNavn() { return this.navn; }

    public long hentFnr() { return this.fnr; }

    public int hentId() { return this.id; }

    public Lenkeliste<Resept> hentResepter() { return this.resepter; }

    public void addResept(Resept resept) { this.resepter.leggTil(resept); }

    @Override
    public String toString() {
        return ("Id: " + this.id +": "+ this.navn + " (fnr " + fnr +")");
    }
}
