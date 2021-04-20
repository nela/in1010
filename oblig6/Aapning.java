import java.util.ArrayList;

public class Aapning extends HvitRute {
    public Aapning(Tuppel pos) { super(pos); }

    public void gaa(Rute rute, ArrayList<Tuppel> ruter) {
        ruter.add(this.pos);
        System.out.println("Ny åpning! Thread: " + Thread.currentThread());
        labyrint.utveier.leggTil(ruter);
    }

    public void resettBesoekt() {}
}
