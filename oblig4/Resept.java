public abstract class Resept {

    private static int nextId = 0;

    protected int id;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit){
        this.id = ++nextId;
        this.legemiddel = legemiddel;
        this.lege = lege;
        this.reit = reit;
        this.pasient = pasient;
    }

    public int hentId() { return this.id; }

    public Legemiddel hentLegemiddel() { return this.legemiddel; }

    public Lege hentLege() { return this.lege; }

    public Pasient hentPasient() { return this.pasient; }

    public int hentReit() { return this.reit; }

    public boolean bruk() {
      if (this.reit <= 0) { return false; }
      this.reit--;
      return true;
    }

    public String skrivUt(String ltype) {

        String pNavn = this.pasient.hentNavn();
        String pNavnUt = pNavn.substring(0, 1).toUpperCase() + pNavn.substring(1);

        String lmNavn = this.legemiddel.hentNavn();
        String lmNavnUt = lmNavn.substring(0, 1).toUpperCase() + lmNavn.substring(1);

        String lgNavn = this.lege.hentNavn();
        String lgNavnUt = lgNavn.substring(0, 1).toUpperCase() + lgNavn.substring(1);

        return "Legemiddel Id: " + this.legemiddel.id + ": [" + ltype + "] - "
            + lmNavnUt + " (reit: " + this.reit + ")\n" + "Lege: " + lgNavnUt
            + "\nPasient: " + pNavnUt + "\n---\n";
    }

    @Override
    public String toString() {
        if (this.legemiddel instanceof Narkotisk)
            return skrivUt("Narkotisk");
        else if (this.legemiddel instanceof Vanedannende)
            return skrivUt("Vanedannende");
        else if (this.legemiddel instanceof Vanlig)
            return skrivUt("Vanlig");

        return "Legemidde - Noe gikk galt";
    }

    abstract public String farge();

    abstract public double prisAaBetale();
}
