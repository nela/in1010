public class Spesialist extends Lege implements Godkjenningsfritak{

  int id;

  public Spesialist(String navn, int kontrollId) {
      super(navn);
      this.id = kontrollId;
  }

  @Override
  public int hentKontrollID() { return this.id; }

  @Override
  public String toString() {
      return ("Spesialist Lege: " + this.navn + ".\n" +
              "KontrollId: " + this.id + ".\n");
  }
}
