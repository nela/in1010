public class Hvit extends Resept {

  public Hvit(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
    super(legemiddel, lege, pasient, reit);
  }

  public String farge() { return ("Hvit"); }

  public double prisAaBetale() {
    double pris = this.legemiddel.hentPris();
    return (pris > 0) ? pris : 0;
  }
}
