import java.util.ArrayList;

public class SortRute extends Rute {
    public SortRute(Tuppel pos) { super(pos); }

    public char tilTegn() { return '#'; }
    // Basecase - stop rekursjon
    public void gaa(Rute rute, ArrayList<Tuppel> ruter)  {
        System.out.println("Sort rute: " + this.pos.toString());
    }
}
