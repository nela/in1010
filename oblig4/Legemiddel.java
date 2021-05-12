public abstract class Legemiddel {

  private static int nextId = 0;

  protected int id;
  protected String navn;
  protected double pris;
  private double virkestoff;

  public Legemiddel(String navn, double pris, double virkestoff){
    this.id = ++nextId;

    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }

  public String hentNavn() { return this.navn; }

  public int hentId() { return this.id; }

  public double hentVirkestoff() { return this.virkestoff; }

  public double hentPris() { return this.pris; }

  public double settNyPris(double pris) {
    this.pris = pris;
    return this.pris;
  }

  @Override
  public String toString() {
    return ("Navn: " + this.navn + ".\n" +
            "Pris: " + this.pris + "kr.\n" +
            "Mengde: " + this.virkestoff + "mg.\n");
  }
}
