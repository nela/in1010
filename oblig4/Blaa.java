public class Blaa extends Resept {

  public Blaa(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
    super(legemiddel, lege, pasient, reit);
  }

  public String farge() { return ("Blaa"); }

  public double prisAaBetale() {
    double pris = this.legemiddel.hentPris();
    return (pris > 0) ? pris * 0.25 : 0;
  }
}
