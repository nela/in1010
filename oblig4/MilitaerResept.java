public class MilitaerResept extends Hvit {

  public MilitaerResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
    super(legemiddel, lege, pasient, reit);
  }

  // @Override
  // public String farge() { return ("Hvit"); }

  @Override
  public double prisAaBetale() { return  0; }
}
