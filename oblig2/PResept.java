public class PResept extends Hvit {

  public PResept(Legemiddel legemiddel, Lege lege, int pasientid) {
    super(legemiddel, lege, pasientid, 3);
  }

  @Override
  public double prisAaBetale() {
    double pris = legemiddel.hentPris() - 108;
    return (pris > 0) ? pris : 0;
  }
}
