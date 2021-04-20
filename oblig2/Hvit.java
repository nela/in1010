public class Hvit extends Resept {

  public Hvit(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
    super(legemiddel, lege, pasientId, reit);
  }

  public String farge() { return ("Hvit"); }

  public double prisAaBetale() {
    double pris = legemiddel.hentPris();
    return (pris > 0) ? pris : 0;
  }
}
