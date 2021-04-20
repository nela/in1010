public class Node {

  private int minne, antProsessorer;
  public Node(int minne, int antProsessorer) {
    this.minne = minne;
    this.antProsessorer = antProsessorer;
  }

  public int getAntProsessorer() {
    return this.antProsessorer;
  }

  public int getAntMinne() {
    return this.minne;
  }
}
