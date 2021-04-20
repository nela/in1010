import java.util.ArrayList;

public class HvitRute extends Rute {
    private boolean besoekt;

    public HvitRute(Tuppel pos) {
        super(pos);
        this.besoekt = false;
    }

    public char tilTegn() { return '.'; }

    public void gaa(Rute prev, ArrayList<Tuppel> ruter) {
        // if (ruter.contains(this.pos)) return;
        if (this.besoekt == true) return;
        this.besoekt = true;

        ruter.add(this.pos);

        try {
            if (nord != prev) {
                Thread nord = new Thread(new RuteRunnable(this.nord, ruter));
                nord.start();
                nord.join();
            }
            if (sor != prev) {
                Thread sor = new Thread(new RuteRunnable(this.sor, ruter));
                sor.start();
                sor.join();
            }
            if (ost != prev) {
                Thread ost = new Thread(new RuteRunnable(this.ost, ruter));
                ost.start();
                ost.join();
            }
            if (vest != prev) vest.gaa(this, ruter);

        } catch (InterruptedException e) { System.out.println("Feil"); }
    }

    public void resettBesoekt() {
        this.besoekt = false;
    }
}
