public class Blaa extends Resept {

  public Blaa(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
    super(legemiddel, lege, pasientId, reit);
  }

  public String farge() { return ("Blaa"); }

  public double prisAaBetale() {
    double pris = legemiddel.hentPris();
    return (pris > 0) ? pris * 0.25 : 0;
  }
}
