import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataKlynge{

  private String name;
  private int nodesPerRack;
  private ArrayList<Rack> racks;

  public DataKlynge(String name, int nodesPerRack) {
    this.name = name;
    this.nodesPerRack = nodesPerRack;
    racks = new ArrayList<Rack>();
  }

  public DataKlynge(String name, String filnavn) {
    this.name = name;
    racks = new ArrayList<Rack>();

    try {
      File fil = new File(filnavn);
      BufferedReader br = new BufferedReader(new FileReader(fil));

      String line;
      while ((line = br.readLine()) != null) {
        String[] elements = line.split(" ");

        if (elements.length == 1) {
          this.nodesPerRack = Integer.parseInt(elements[0]);
        } else {
          for (int i = 0; i < Integer.parseInt(elements[0]); i++)
            this.settInn(new Node(Integer.parseInt(elements[1]),
                  Integer.parseInt(elements[2])));
        }
      }
    } catch(Exception e) {
        e.printStackTrace();
    }

  }

  public int getAntRack() {
    return racks.size();
  }

  public void settInn(Node node) {
    Rack r;

    if (this.racks.isEmpty()) {
      r = new Rack(this.nodesPerRack);
      if (r.settInn(node)) racks.add(r);
    } else {
      r = this.racks.get(this.racks.size() - 1);

      if (!r.settInn(node)) {
        Rack newRack = new Rack(this.nodesPerRack);
        if (newRack.settInn(node)) this.racks.add(newRack);
      }
    }
  }

  public int antProsessorer() {
    int p = 0;

    for (int i = 0; i < this.racks.size(); i++)
      p += this.racks.get(i).antProsessorer();

    return p;
  }

  public int noderMedNokMinne(int paakrevdMinne) {
    int count = 0;

    for(int i = 0; i < this.racks.size(); i++)
      count += this.racks.get(i).noderMedNokMinne(paakrevdMinne);

    return count;
  }
}
