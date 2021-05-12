import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class IOHandler {
    private Scanner brukerInput;

    public IOHandler() {
        this.brukerInput = new Scanner(System.in);
    }

    public ArrayList<String> lesFil(String filnavn) {
    Scanner filLeser;
        try {
            File fil = new File(filnavn);
            filLeser = new Scanner(fil);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

        ArrayList<String> linjer = new ArrayList<String>();
        while (filLeser.hasNext()) {
            String l = filLeser.nextLine();
            linjer.add(l);
        }

        filLeser.close();
        return linjer;
    }

    public void skrivMelding(String melding) {
        System.out.println(melding);
    }

    public void skrivOverskrift(String melding) {
        System.out.println("--------------------");
        System.out.println(melding);
        System.out.println("--------------------");
    }

    public String lesInput() {
        String inn = this.brukerInput.nextLine();
        if (inn.equals("a")) System.out.println("System exit.....");
        return inn;

    }

    public String lesTekst(String melding) {
        System.out.println(melding);
        return this.brukerInput.nextLine();
    }

    public String lesTekst(String melding, String var) {
        System.out.println(melding);
        return this.brukerInput.nextLine();
    }

}
