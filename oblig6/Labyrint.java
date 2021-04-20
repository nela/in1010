import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.locks.*;

public class Labyrint {
    private final Rute[][] brett;
    int yCount;
    int xCount;
    Monitor utveier;

    public Labyrint(File fil) throws FileNotFoundException {
        Scanner sc = new Scanner(fil);

        int yCount = sc.nextInt(), xCount = sc.nextInt();
        sc.nextLine();

        this.xCount = xCount;
        this.yCount = yCount;

        System.out.println(yCount + " " + xCount);
        this.brett = new Rute[yCount][xCount];
        lagBrett(this.brett, this.yCount, this.xCount, sc);
        settNaboer(this, this.brett, this.yCount, this.xCount);
    }

    private static void lagBrett(Rute[][] brett, int yCount, int xCount,
            Scanner sc) {
        for (int i = 0; i < yCount; i++) {
            String l = sc.nextLine();
            for (int j = 0; j < xCount; j++)
                brett[i][j] = lagRute(l.charAt(j), i, j, yCount, xCount);
        }
    }

    private static Rute lagRute(char tegn, int y, int x, int yCount,
            int xCount) {

        if (tegn == '#') return new SortRute(new Tuppel(y, x));

        if (y == 0 || x == 0 || y == yCount - 1 || x == xCount - 1
                && tegn == '.')
            return new Aapning(new Tuppel(y, x));

        return new HvitRute(new Tuppel(y, x));
    }

    private static void settNaboer(Labyrint l, Rute[][] brett, int yCount, int xCount) {
        for (int y = 0; y < yCount; y++)
            for (int x = 0; x < xCount; x++)
                sjekkNaboer(l, brett, y, x, yCount, xCount);
    }

    private static void sjekkNaboer(Labyrint l, Rute[][] brett, int y, int x, int yCount, int xCount) {
        // Rute[][] brett = labyrint.brett;
        brett[y][x].ref(l);

        System.out.println(y + " " + x);
        if (y > 0) brett[y][x].settNabo(brett[y - 1][x], 'N');
        if (y < yCount - 1) brett[y][x].settNabo(brett[y + 1][x], 'S');

        if (x > 0) brett[y][x].settNabo(brett[y][x - 1], 'V');
        if (x < xCount - 1) brett[y][x].settNabo(brett[y][x + 1], 'O');
    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int x, int y) {
        utveier = new Monitor();
        brett[y][x].finnUtvei();
        return utveier.hentRuter();
    }

    public ArrayList<Tuppel> finnKortesteUtvei() {
        if (utveier.stoerrelse() == 0) return null;

        ArrayList<Tuppel> minste = this.utveier.hent(0);

        for (int i = 1; i < this.utveier.stoerrelse(); i++) {
            ArrayList<Tuppel> r = this.utveier.hent(i);
            if (r.size() < minste.size()) minste = r;
        }

        return minste;
    }

    @Override
    public String toString() {
        StringBuilder builder =
            new StringBuilder(new String(new char[50]).replace("\0", "\n"));
        for (int y = 0; y < yCount; y++) {
            builder.append("\n");
            for (int x = 0; x < xCount; x++)
                builder.append(brett[y][x].tilTegn()).append(" ");
        }
        return builder.toString();
    }

    protected static class Monitor {
        Lock lock = new ReentrantLock();
        ArrayList<ArrayList<Tuppel>> ruter = new ArrayList<>();

        public void leggTil(ArrayList<Tuppel> nyRute) {
            this.lock.lock();
            try {
                this.ruter.add(nyRute);
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        }

        public int stoerrelse() {
            return this.ruter.size();
        }

        public ArrayList<Tuppel> hent(int x) {
            return this.ruter.get(x);
        }

        public ArrayList<ArrayList<Tuppel>> hentRuter() {
            return this.ruter;
        }

    }

    public void finnAapninger() {
        for (int y = 0; y < brett.length; y++) {
            for (int x = 0; x < brett[y].length; x++) {
                if (brett[y][x] instanceof Aapning) {
                    System.out.println("Åpning på posisjon: [" + x + ", " + y + "] – " +  brett[y][x] + "\n");
                }
            }
        }
    }

    public void finnNaboer(int y, int x) {
        Rute rute = brett[y][x];
        System.out.println("Rute: [" + rute + "]");
        System.out.println("Nord: [" + rute.nord + "]");
        System.out.println("Sør:  [" + rute.sor + "]");
        System.out.println("Øst:  [" + rute.ost + "]");
        System.out.println("Vest:  [" + rute.vest + "]");
    }

    public void printLabyrint() {
        for (Rute [] y : brett) {
            for (Rute x : y) System.out.print(x.tilTegn() + " ");
            System.out.println("\n");
        }
    }
}
