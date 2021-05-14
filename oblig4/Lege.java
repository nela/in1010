public class Lege implements Comparable<Lege> {

    protected String navn;
    protected Lenkeliste<Resept> resepter;

    public Lege(String navn){
        this.navn = navn;
        this.resepter = new Lenkeliste<Resept>();
    }

    public String hentNavn() { return this.navn; }

    public Lenkeliste<Resept> hentResepter() { return this.resepter; }

    public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)
        throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk)
            throw new UlovligUtskrift(this, legemiddel);

        Hvit hvit = new Hvit(legemiddel, this, pasient, reit);
        this.resepter.leggTil(hvit);
        pasient.leggTil(hvit);
        return hvit;
    }

    public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit)
        throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk)
            throw new UlovligUtskrift(this, legemiddel);

        Blaa blaa  = new Blaa(legemiddel, this, pasient, reit);
        this.resepter.leggTil(blaa);
        pasient.leggTil(blaa);
        return blaa;
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit)
        throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk)
            throw new UlovligUtskrift(this, legemiddel);

        MilitaerResept militaer  = new MilitaerResept(legemiddel, this, pasient, reit);
        this.resepter.leggTil(militaer);
        pasient.leggTil(militaer);
        return militaer;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient)
        throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk)
            throw new UlovligUtskrift(this, legemiddel);

        PResept pResept  = new PResept(legemiddel, this, pasient);
        this.resepter.leggTil(pResept);
        pasient.leggTil(pResept);
        return pResept;
    }

    @Override
    public int compareTo(Lege lege) {
        return navn.compareTo(lege.hentNavn());
    }

    @Override
    public String toString() {
        return (this.navn);
    }
}
