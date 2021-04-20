import java.util.ArrayList;

public abstract class Rute {
    protected Tuppel pos;
    protected Labyrint labyrint;

    protected Rute nord, sor, vest, ost;

    static int threadCount = 0;

    public Rute(Tuppel pos) {
        this.pos = pos;
    }

    abstract public char tilTegn();

    abstract public void gaa(Rute prev, ArrayList<Tuppel> ruter);

    abstract public void resettBesoekt();

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
        ArrayList<Tuppel> ruter = new ArrayList<Tuppel>();
        gaa(this, ruter);
        threadCount = 0;
    }

    protected static class RuteRunnable implements Runnable {
        Rute rute;
        ArrayList<Tuppel> ruter;

        RuteRunnable(Rute rute, ArrayList<Tuppel> ruter) {
            this.rute = rute;
            this.ruter = new ArrayList<>(ruter);
        }

        @Override
        public void run() {
            threadCount++;
            rute.gaa(rute, ruter);
        }
    }
}
