public class Rack {

  private Node[] nodes;
  private int antNoder;
  private int maxNoder;

  public Rack() {}

  public Rack(int maxNoder){
    this.antNoder = 0;
    this.maxNoder = maxNoder;
    this.nodes = new Node[maxNoder];
  }

  public boolean settInn(Node node) {
    if (this.antNoder == this.maxNoder) return false;

    this.nodes[antNoder] = node;
    antNoder++;
    return true;
  }

  public int antProsessorer() {
    int p = 0;

    for(int i = 0; i < this.antNoder; i++)
      p += nodes[i].getAntProsessorer();

    return p;
  }

  public int noderMedNokMinne(int paakrevdMinne) {
    int count = 0;

    for (int i = 0; i < this.antNoder; i++) {
      if (this.nodes[i].getAntMinne() >= paakrevdMinne) count++;
    }
    return count;
  }
}
