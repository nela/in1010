public abstract class Resept {

  private static int nextId = 0;

  protected int id;
  protected Legemiddel legemiddel;
  protected Lege lege;
  protected int pasientId;
  protected int reit;

  public Resept(Legemiddel legemiddel, Lege lege, int pasientid, int reit){
      this.id = ++nextId;
      this.legemiddel = legemiddel;
      this.lege = lege;
      this.reit = reit;
      this.pasientId = pasientid;
  }

  public int hentId() { return this.nextId; }

  public Legemiddel hentLegemiddel() { return this.legemiddel; }

  public Lege hentLege() { return this.lege; }

  public int hentPasientId() { return this.pasientId; }

  public int hentReit() { return this.reit; }

  public boolean bruk() {
    if (this.reit <= 0) { return false; }
    this.reit--;
    return true;
  }

  abstract public String farge();

  abstract public double prisAaBetale();

  @Override
  public String toString() {
      return ("Lege: " + this.lege + ".\n" +
              "Legemiddel: " + this.lege + ".\n" +
              "Pasient Id: " + this.pasientId + ".\n" +
              "Resept Id: " + this.nextId + ".\n" +
              "Reit: " + this.reit + ".\n");
  }
}
