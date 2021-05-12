public class Spesialist extends Lege implements Godkjenningsfritak{

    int id;

    public Spesialist(String navn, int kontrollId) {
        super(navn);
        this.id = kontrollId;
    }

    public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        Hvit hvit = new Hvit(legemiddel, this, pasient, reit);
        this.resepter.leggTil(hvit);
        pasient.leggTil(hvit);
        return hvit;
    }

    public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        Blaa blaa  = new Blaa(legemiddel, this, pasient, reit);
        this.resepter.leggTil(blaa);
        pasient.leggTil(blaa);
        return blaa;
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        MilitaerResept militaer  = new MilitaerResept(legemiddel, this, pasient, reit);
        this.resepter.leggTil(militaer);
        pasient.leggTil(militaer);
        return militaer;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) {
        PResept pResept  = new PResept(legemiddel, this, pasient);
        this.resepter.leggTil(pResept);
        pasient.leggTil(pResept);
        return pResept;
    }

    @Override
    public int hentKontrollID() { return this.id; }

    @Override
    public String toString() {
        return ("Spesialist Lege: " + this.navn + ".\n" +
                "KontrollId: " + this.id + ".\n");
    }
}
