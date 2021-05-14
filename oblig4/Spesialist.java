public class Spesialist extends Lege implements Godkjenningsfritak{

    int id;
    int antallNarkotiske;

    public Spesialist(String navn, int kontrollId) {
        super(navn);
        this.id = kontrollId;
        this.antallNarkotiske = 0;
    }

    public int hentAntallNarkotiske() { return this.antallNarkotiske; }

    public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        Hvit hvit = new Hvit(legemiddel, this, pasient, reit);
        this.resepter.leggTil(hvit);
        pasient.leggTil(hvit);
        if (legemiddel instanceof Narkotisk) this.antallNarkotiske++;
        return hvit;
    }

    public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        Blaa blaa  = new Blaa(legemiddel, this, pasient, reit);
        this.resepter.leggTil(blaa);
        pasient.leggTil(blaa);
        if (legemiddel instanceof Narkotisk) this.antallNarkotiske++;
        return blaa;
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        MilitaerResept militaer  = new MilitaerResept(legemiddel, this, pasient, reit);
        this.resepter.leggTil(militaer);
        pasient.leggTil(militaer);
        if (legemiddel instanceof Narkotisk) this.antallNarkotiske++;
        return militaer;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) {
        PResept pResept  = new PResept(legemiddel, this, pasient);
        this.resepter.leggTil(pResept);
        pasient.leggTil(pResept);
        if (legemiddel instanceof Narkotisk) this.antallNarkotiske++;
        return pResept;
    }

    @Override
    public int hentKontrollId() { return this.id; }

    @Override
    public String toString() {
        return (this.navn + " Spesialist - KontrollId: " + this.id);
    }
}
