import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class IOHandler {
    private Scanner brukerInput;

    public IOHandler() {
        this.brukerInput = new Scanner(System.in);
    }

    public HashMap<String, Lenkeliste<String>> lesFil(String filnavn) {
    Scanner filLeser;
        try {
            File fil = new File(filnavn);
            filLeser = new Scanner(fil);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

        HashMap<String, Lenkeliste<String>> map = new HashMap<>();
        Lenkeliste<String> pasienter = new Lenkeliste<>();
        map.put("Pasienter", pasienter);
        Lenkeliste<String> legemidler = new Lenkeliste<>();
        map.put("Legemidler", legemidler);
        Lenkeliste<String> leger = new Lenkeliste<>();
        map.put("Leger", leger);
        Lenkeliste<String> resepter = new Lenkeliste<>();
        map.put("Resepter", resepter);

        int c = 0;
        while (filLeser.hasNext()) {
            String l = filLeser.nextLine();

            if (l.startsWith("#")) { c++; }

            while (c == 1 && filLeser.hasNext()) {
                String p = filLeser.nextLine();
                if (p.startsWith("#")) { c++; }
                else { pasienter.leggTil(p); }
            }

            while (c == 2 && filLeser.hasNext()) {
                String lm = filLeser.nextLine();
                if (lm.startsWith("#")) { c++; }
                else { legemidler.leggTil(lm); }
            }

            while (c == 3 && filLeser.hasNext()) {
                String lg = filLeser.nextLine();
                if (lg.startsWith("#")) { c++;}
                else { leger.leggTil(lg); }
            }
            while (c == 4 && filLeser.hasNext()) {
                String r = filLeser.nextLine();
                if (r.startsWith("#")) { c++; }
                else resepter.leggTil(r);
            }
        }

        filLeser.close();
        for (String s : map.keySet()) {
            System.out.println(s + " " + map.get(s).stoerrelse());
        }

        return map;
    }

    public void skrivMelding(String melding) {
        System.out.println(melding);
    }

    public void skrivOverskrift(String melding) {
        System.out.println("--------------------");
        System.out.println(melding);
        System.out.println("--------------------");
    }

    private String lesInput() {
        String inn = this.brukerInput.nextLine();
        if (inn.equals("q")) {
            System.out.println("System exit.....");
            System.exit(0);
        }
        return inn;
    }

    public String lesTekst(String melding) {
        if (melding != null) System.out.println(melding);
        return this.lesInput();
    }

    public String lesValiderTekst(String melding, String[] validering) {
        boolean ok = false;
        String inn = null;
        while (!ok) {

            if (melding != null) System.out.println(melding);
            inn = this.lesInput();
            for (String s : validering) {
                if (s.equals(inn)) ok = true;
                else System.out.println("Ugyldig input..\nPrøv igjen ");
            }
        }
        return inn;
    }

    public int lesValg(String melding, String[] valg) {
        boolean ok = false;
        Integer alt = null;
        while (!ok) {
            if (melding != null) System.out.println(melding);

                // System.out.println("Velg ett alternativ");
            for (int i = 1; i <= valg.length; i++) {
                System.out.println(i + ". " + valg[i-1]);
            }

            alt = this.lesInt(null);
            if (alt < 1 || alt > valg.length)
                System.out.println("Ugyldig alternativ..");
            else
                ok = true;
        }
        return alt-1;
    }

    public int lesInt(String melding) {
        boolean ok = false;
        Integer i = null;
        while (!ok) {
            if (melding != null) System.out.println(melding);
            try {
                i = Integer.parseInt(this.lesInput());
                ok = true;
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                System.out.println("Ikke et tall..\nPrøv igjen ");
            }
        }
        return i;
    }

    public long lesLong(String melding) {
        boolean ok = false;
        Long i = null;
        while (!ok) {
            if (melding != null) System.out.println(melding);
            try {
                i = Long.parseLong(this.lesInput());
                ok = true;
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                System.out.println("Ikke et tall..\nPrøv igjen ");
            }
        }
        return i;
    }

    public float lesFloat(String melding) {
        boolean ok = false;
        Float f = null;
        while (!ok) {
            if (melding != null) System.out.println(melding);
            try {
                f = Float.parseFloat(this.lesInput());
                ok = true;
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                System.out.println("Ikke et tall..\nPrøv igjen ");
            }
        }
        return f;
    }

    public Pasient velgPasient(String melding, Lenkeliste<Pasient> pasienter) {
        if (pasienter.stoerrelse() <= 0) {
            System.out.println("Ingen pasienter i systemet");
            return null;
        }

        System.out.println(melding);
        for (int i = 1; i <= pasienter.stoerrelse(); i++) {
            System.out.println("\t" + i + ". " + pasienter.hent(i-1).hentNavn());
        }
        int valg = this.lesInt(null);
        if (valg < 1 || valg > pasienter.stoerrelse()) {
            System.out.println("Ugyldig valg...");
            return null;
        }
        return pasienter.hent(valg-1);
    }

    public Resept velgResept(String melding, Pasient pasient) {
        System.out.println(pasient.hentResepter().stoerrelse() );
        if (pasient.hentResepter().stoerrelse() <= 0) {
            System.out.println("Ingen resepter for denne pasienten");
            return null;
        }

        System.out.println(melding);
        Lenkeliste<Resept> resepter = pasient.hentResepter();
        for (int i = 1; i <= resepter.stoerrelse(); i++) {
            Resept r = resepter.hent(i-1);
            System.out.println("\t" + i + ". " + r.hentLegemiddel()
                + "Id: " + r.hentId() + " Antall reit: " + r.hentReit());
        }

        int valg = this.lesInt(null);
        if (valg < 0 || valg > resepter.stoerrelse()) {
            System.out.println("Ugyldig valg...");
            return null;
        }

        return resepter.hent(valg-1);
    }

}
