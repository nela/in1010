public class PResept extends Hvit {

  public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
    super(legemiddel, lege, pasient, 3);
  }

  @Override
  public double prisAaBetale() {
    double pris = this.legemiddel.hentPris() - 108;
    return (pris > 0) ? pris : 0;
  }
}
