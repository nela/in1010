import java.util.ArrayList;

public class HvitRute extends Rute {
    public HvitRute(Tuppel pos) { super(pos); }

    public char tilTegn() { return '.'; }

    public void gaa(Rute prev, ArrayList<Tuppel> ruter) {
        if (ruter.contains(this.pos)) return;
        ruter.add(this.pos);

        try {
            if (nord != prev) {
                Thread nord = new Thread(new RuteRunnable(this.nord, this.ruter));
                nord.start();
                nord.join();
            }
            if (sor != prev) {
                Thread sor = new Thread(new RuteRunnable(this.sor, this.ruter));
                sor.start();
                sor.join();
            }
            if (ost != prev) {
                Thread ost = new Thread(new RuteRunnable(this.ost, this.ruter));
                ost.start();
                ost.join();
            }
            if (vest != prev) vest.gaa(this, this.ruter);
                // vest.gaa(this, pos);
        } catch (InterruptedException e) { System.out.println("Feil"); }
    }
}
