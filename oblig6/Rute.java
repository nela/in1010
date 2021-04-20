import java.util.ArrayList;

public abstract class Rute {
    // protected int y;
    // protected int x;

    protected Tuppel pos;
    protected ArrayList<Tuppel> ruter;
    // protected String pos;
    protected Labyrint labyrint;

    protected Rute nord, sor, vest, ost;

    static int threadCount = 0;

    public Rute(Tuppel pos) {
        this.pos = pos;
        this.ruter = new ArrayList<Tuppel>();
    }

    abstract public char tilTegn();

    abstract public void gaa(Rute prev, ArrayList<Tuppel> ruter);

    public void ref(Labyrint l) {
        this.labyrint = l;
    }

     public void settNabo(Rute rute, char tegn) {
        if (tegn == 'N') this.nord = rute;
        if (tegn == 'S') this.sor = rute;
        if (tegn == 'O') this.ost = rute;
        if (tegn == 'V') this.vest = rute;
    }

    public void finnUtvei() {
        gaa(this, this.ruter);
        //gaa(this, ">>>");
        System.out.println("Tråder: " + threadCount);
        threadCount = 0;
    }

    protected static class RuteRunnable implements Runnable {
        Rute rute;
        ArrayList<Tuppel> ruter;
        // String vei;

        // RuteRunnable(Rute rute, String vei) {
        RuteRunnable(Rute rute, ArrayList<Tuppel> ruter) {
            this.rute = rute;
            this.ruter = new ArrayList<>(ruter);

            System.out.println("Ruter size: " + ruter.size());
            System.out.println("Ruter size: " + this.ruter.size());
            // this.vei = vei;
        }

        @Override
        public void run() {
            threadCount++;
            System.out.println("Threadcount: " + threadCount);
            rute.gaa(rute, ruter);
            // rute.gaa(rute, vei);
        }
    }
}
