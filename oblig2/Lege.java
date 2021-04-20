public class Lege {

  protected String navn;

  public Lege(String navn){
      this.navn = navn;
  }

  public String hentNavn() { return this.navn; }

  @Override
  public String toString() {
      return ("Legenavn: " + this.navn);
  }
}
