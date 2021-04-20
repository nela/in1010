public class MilitaerResept extends Hvit {

  public MilitaerResept(Legemiddel legemiddel, Lege lege, int pasientid, int reit) {
    super(legemiddel, lege, pasientid, reit);
  }

  // @Override
  // public String farge() { return ("Hvit"); }

  @Override
  public double prisAaBetale() { return  0; }
}
