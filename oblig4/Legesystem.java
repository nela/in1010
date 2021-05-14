import java.util.HashMap;

public class Legesystem {
    public IOHandler ioHandler;

    private Lenkeliste<Legemiddel> legemidler;
    private Lenkeliste<Resept> resepter;
    private Lenkeliste<Pasient> pasienter;
    private SortertLenkeliste<Lege> leger;
    private static int antallVanedannende;
    private static int antallNarkotiske;
    // private static int antallVanlige;

    public final String[] legemiddelTyper = new String[] {
        "Vanlig", "Vanedannende", "Narkotisk"
    };

    public final String[] legeTyper = new String[] {
        "Lege", "Spesialist"
    };

    public final String[] reseptTyper = new String[] {
        "Hvit", "Blå", "Militær", "Prevensjon"
    };

    public Legesystem(IOHandler ioHandler) {
        this.ioHandler = ioHandler;
        legemidler = new Lenkeliste<>();
        resepter = new Lenkeliste<>();
        pasienter = new Lenkeliste<>();
        leger = new SortertLenkeliste<>();
    }

    public void init(HashMap<String, Lenkeliste<String>> map) {
        if (map == null) return;

        Lenkeliste<String> pasientLinjer = map.get("Pasienter");
        Lenkeliste<String> legemiddelLinjer = map.get("Legemidler");
        Lenkeliste<String> legeLinjer = map.get("Leger");
        Lenkeliste<String> reseptLinjer = map.get("Resepter");

        for (String pl : pasientLinjer) this.parsePasient(pl);
        for (String lml : legemiddelLinjer) this.parseLegemiddel(lml);
        for (String ll : legeLinjer) this.parseLege(ll);
        for (String r : reseptLinjer) this.parseResept(r);

    }

    private void parseLegemiddel(String linje) {
        String[] data = linje.split(",");
        String navn = data[0].trim();
        String type = data[1].trim();
        Double pris = null;
        Double virkestoff = null;

        try {
            pris = Double.parseDouble(data[2].trim());
        } catch (NumberFormatException ex) {
            System.out.println("Ugyldig input - Kan ikke formatere \"" + pris
            + "\" til float.");
        }

        try {
            virkestoff = Double.parseDouble(data[3].trim());
        } catch (NumberFormatException ex) {
            System.out.println("Ugyldig input - Kan ikke formatere \"" + virkestoff
            + "\" til float.");
        }

        boolean finnesAllerede = false;
        for (Legemiddel lm : this.legemidler)
            if (lm.hentNavn().equals(navn)) finnesAllerede = true;

        if (virkestoff == null || pris == null) return;
        else if (finnesAllerede) System.out.println("Legemiddelet " + navn
            + " er allerede registrert.");
        else if (type.equals("vanlig")) this.legemidler.leggTil(
            new Vanlig(navn, pris, virkestoff));
        else if (type.equals("vanedannende")) this.legemidler.leggTil(
            new Vanedannende(navn, pris, virkestoff,
                Integer.parseInt(data[4].trim())));
        else if (type.equals("narkotisk")) this.legemidler.leggTil(
            new Narkotisk(navn, pris, virkestoff,
                Integer.parseInt(data[4].trim())));
    }

    private void parsePasient(String linje) {
        String[] data = linje.split(",");
        String navn = data[0].trim();
        long fnr = Long.parseLong(data[1].trim());

        boolean finnesAllerede = false;
        for (Pasient p : this.pasienter)
            if (p.hentFnr() == fnr) finnesAllerede = true;

        if (finnesAllerede) System.out.println("Pasient allerede registrert");
        else this.pasienter.leggTil(new Pasient(navn, fnr));
    }

    private void parseLege(String linje) {
        String[] data = linje.split(",");
        String navn = data[0].trim();
        int id = Integer.parseInt(data[1].trim());

        boolean finnesAllerede = false;
        for (Lege l : this.leger)
            if (l.hentNavn().equals(navn)) finnesAllerede = true;

        if (finnesAllerede) System.out.println("Legen " + navn
            + "er allerede registrert.");
        if (id == 0) this.leger.leggTil(new Lege(navn));
        else this.leger.leggTil(new Spesialist(navn, id));
    }

    private void parseResept(String linje) {
       String data[] = linje.split(",");
       int lmid = Integer.parseInt(data[0].trim());
       String legenavn = data[1].trim();
       int pid = Integer.parseInt(data[2].trim());
       String type = data[3].trim();

       if (type.equals("hvit") || type.equals("blaa") || type.equals("millitaer"))
           registrerResept(0, lmid, pid, legenavn, Integer.parseInt(data[4].trim()));
       else if (type.equals("p"))
           registrerResept(3, lmid, pid, legenavn, null);
    }

    public void registrerPasient() {
        this.ioHandler.skrivOverskrift("Registrer pasient");
        String navn = this.ioHandler.lesTekst("Navn:");
        if (this.finnesPasient(navn)) {
            this.ioHandler.skrivMelding("Pasienten er allerede registrert." +
                "\nGår til hovedmeny..");
            return;
        }
        long fnr = this.ioHandler.lesLong("Fødselsnummer:");
        Pasient p = new Pasient(navn, fnr);
        this.pasienter.leggTil(p);
        this.ioHandler.skrivMelding("Opprettet ny pasient\t" + p.toString());
    }

    public void registrerLegemiddel() {
        this.ioHandler.skrivOverskrift("Registrer legemiddel");
        String navn = this.ioHandler.lesTekst("Legemiddel navn: ");
        if (this.finnesLegemiddel(navn)) {
            this.ioHandler.skrivMelding("Legemiddelet er allerede registrert." +
                "\nGår til hovedmeny..");
            return;
        }
        int valg = this.ioHandler.lesValg("Legemiddel type: ", legemiddelTyper);
        double pris = this.ioHandler.lesFloat("Pris: ");
        double virkestoff = this.ioHandler.lesFloat("Virkestoff: ");

        Integer styrke = null;
        if (valg != 0) styrke = this.ioHandler.lesInt("Styrke: ");

        if (registrerLegemiddel(valg, navn, pris, virkestoff, styrke) != null)
            this.ioHandler.skrivMelding(navn + " er nå registrert.");
    }

    private Legemiddel registrerLegemiddel(int valg, String navn, double pris,
        double virkestoff, Integer styrke) {
        Legemiddel lm = null;

        switch (valg) {
            case 0:
                lm = new Vanlig(navn, pris, virkestoff);
                break;
            case 1:
                lm = new Vanedannende(navn, pris, virkestoff, styrke);
                break;
            case 2:
                lm = new Narkotisk(navn, pris, virkestoff, styrke);
                break;
            default:
                break;
        }

        if (lm != null) legemidler.leggTil(lm);
        return lm;
    }

    public void registrerLege() {
        this.ioHandler.skrivOverskrift("Registrer Lege");
        int valg = this.ioHandler.lesValg("Lege type:", legeTyper);
        String navn = this.ioHandler.lesTekst("Legens navn: ");

        if (this.finnesLege(navn)) {
            this.ioHandler.skrivMelding("Legen er allerede registrert. " +
                "\nGår til hovedmeny..");
            return;
        }

        Integer kid = null;
        if (valg == 1) kid = this.ioHandler.lesInt("Kontroll Id: ");

        if (registrerLege(valg, navn, kid) != null)
            this.ioHandler.skrivMelding("Lege " + navn + " er nå registrert.");

    }

   private Lege registrerLege(int valg, String navn, Integer kid) {
        Lege l = null;
        switch (valg) {
            case 0:
                l = new Lege(navn);
                break;
            case 1:
   ;             l = new Spesialist(navn, kid);
            default:
                break;
        }
        if (l != null) this.leger.leggTil(l);
        return l;
    }

    public void registrerResept() {
        this.ioHandler.skrivOverskrift("Registrer resept");
        int valg = this.ioHandler.lesValg("Resept type: ", reseptTyper);
        int lmId = this.ioHandler.lesInt("Legemiddel Id: ");
        String lege = this.ioHandler.lesTekst("Lege: ");
        int pId = this.ioHandler.lesInt("Pasient Id: ");
        Integer reit = null;

        if (!finnesLegemiddelId(lmId)) {
            this.ioHandler.skrivMelding("Legemiddelet ikke registrert " +
                "\nGår til hovedmeny...");
            return;
        } else if (!finnesPasientId(pId)) {
            this.ioHandler.skrivMelding("Pasient ikke registrert " +
                "\nGår til hovedmeny...");
            return;
        } else if (!finnesLege(lege)) {
            this.ioHandler.skrivMelding("Lege ikke registrert " +
                "\nGår til hovedmeny...");
            return;
        }

        if (valg != 3)
            reit = this.ioHandler.lesInt("Reit: ");

        if (valg != 3 && reit <= 0) {
            this.ioHandler.skrivMelding("Reit kan ikke være likt eller mindre enn 0" +
                "\nGår til hovedmeny...");
            return;
        }

        if (registrerResept(valg, lmId, pId, lege, reit) != null)
            this.ioHandler.skrivMelding("Nytt resept på registrert");
    }

    private Resept registrerResept(int valg, int legemiddelId, int pasientId,
        String legenavn, Integer reit) {

       Legemiddel lm = null;
       Lege lege = null;
       Pasient p = null;

       try {
           lm = this.legemidler.hent(legemiddelId-1); // siden indekseringen for id begynner på 1.
           p = this.pasienter.hent(pasientId-1);
       } catch (NullPointerException ex) {
           this.ioHandler.skrivMelding("Objektet ikke funnet");
           ex.printStackTrace();
           return null;
       } catch (UgyldigListeIndeks ex) {
           this.ioHandler.skrivMelding("UgyldigListeIndeks");
           ex.printStackTrace();
           return null;
       }

        for (Lege l : leger)
            if (l.hentNavn().equals(legenavn)) lege = l;

        if (lm instanceof Vanedannende) antallVanedannende++;
        else if (lm instanceof Narkotisk) antallNarkotiske++;

        Resept r = null;
        try {
            switch (valg) {
                case 0:
                    r = new Hvit(lm, lege, p, reit);
                    lege.skrivHvitResept(lm, p, reit);
                    break;
                case 1:
                    r = new Blaa(lm, lege, p, reit);
                    lege.skrivBlaaResept(lm, p, reit);
                    break;
                case 2:
                    r = new MilitaerResept(lm, lege, p, reit);
                    lege.skrivMilitaerResept(lm, p, reit);
                    break;
                case 3:
                    r = new PResept(lm, lege, p);
                    lege.skrivPResept(lm, p);
                default:
                    break;
            }
        } catch (UlovligUtskrift ex) {
            this.ioHandler.skrivMelding("Legen " + lege.navn + " har ikke autorisasjon" +
                " til å skrive ut denne resepten. Kun en spesielist har " +
                " til dette legemiddelet.");
            ex.printStackTrace();
        }

        System.out.println("Legger resept til pasient " + p.hentNavn());
        if (r != null) {
            p.addResept(r);
            this.resepter.leggTil(r);
        }

        return r;
    }

    public void brukResept() {
        this.ioHandler.skrivOverskrift("Bruk Resept");
        Pasient pasient = this.ioHandler.velgPasient("Velg pasient", this.pasienter);
        if (pasient == null) return;
        else this.ioHandler.skrivMelding("Pasient " + pasient.hentNavn() + " valgt.");

        Resept resept = this.ioHandler.velgResept("Velg resept", pasient);
        if (resept == null) return;
        else this.ioHandler.skrivMelding("Resept " + resept.hentId() + " "
            + resept.hentLegemiddel().hentNavn() + " valgt");

        if (resept.bruk()) {
            this.ioHandler.skrivMelding("Bruke resept på " + resept.hentLegemiddel().hentNavn()
                + ". Antall reit igjen: " + resept.hentReit());
        } else {
            this.ioHandler.skrivMelding("Kunne ikke bruke resepten på "
                + resept.hentLegemiddel().navn + ". Ingen reit igjen.");
        }
    }

    public void statistikk() {
        this.ioHandler.skrivOverskrift("Statistikk");
        this.ioHandler.skrivMelding("Antall utskrevne Vanedannende resepter: "
            + antallVanedannende);
        this.ioHandler.skrivMelding("Antall utskrevne Narkotiske respeter: "
            + antallNarkotiske);

        for (Lege l : this.leger)
            if (l instanceof Spesialist)
                this.ioHandler.skrivMelding("Lege\t" + ((Spesialist)l).hentNavn()
                    + "\tAntall utskrifter på Narkotiske resepter: "
                    + ((Spesialist)l).hentAntallNarkotiske());

        for (Pasient p : pasienter) {
            Lenkeliste<Resept> _resepter = p.hentResepter();
            int pAntNarc = 0;
            for (Resept r : _resepter)
                if (r.hentLegemiddel() instanceof Narkotisk) pAntNarc++;

            if (pAntNarc > 0)
                this.ioHandler.skrivMelding("Pasient " + p.hentNavn() + ", Id: "
                    + p.hentId() + "\tAntall narkotiske resepter: " + pAntNarc);
        }
    }

    private boolean finnesPasientId(int id) {
        for (Pasient p : pasienter)
            if (p.hentId() == id) return true;
        return false;
    }

    private boolean finnesLegemiddelId(int id) {
        for (Legemiddel lm : legemidler)
            if (lm.hentId() == id) return true;
        return false;
    }

    private boolean finnesLegemiddel(String navn) {
        for (Legemiddel lm : legemidler)
            if (lm.hentNavn().equals(navn)) return true;
        return false;
    }

    private boolean finnesLege(String navn) {
        for (Lege l: leger)
            if (l.hentNavn().equals(navn)) return true;
        return false;
    }

    private boolean finnesPasient(String navn) {
        for (Pasient p : pasienter)
            if (p.hentNavn().equals(navn)) return true;
        return false;
    }

    public Lenkeliste<String> pasientOversikt() {
        Lenkeliste<String> pasientOversikt = new Lenkeliste<>();
        for (Pasient p : this.pasienter) pasientOversikt.leggTil(p.toString());
        return pasientOversikt;
    }

    public Lenkeliste<String> legeOveriskt() {
        SortertLenkeliste<String> legeOversikt = new SortertLenkeliste<>();
        for (Lege l : this.leger) legeOversikt.leggTil(l.toString());
        return legeOversikt;
    }

    public Lenkeliste<String> legemiddelOversikt() {
        Lenkeliste<String> legemiddelOversikt = new Lenkeliste<>();
        for (Legemiddel lm : this.legemidler) legemiddelOversikt.leggTil(lm.toString());
        return legemiddelOversikt;
    }

    public Lenkeliste<String> reseptOversikt() {
        Lenkeliste<String> reseptOversikt = new Lenkeliste<>();
        for (Resept r : this.resepter) reseptOversikt.leggTil(r.toString());
        return reseptOversikt;
    }

    public void oversikt() {

        for (Pasient p : this.pasienter)
            System.out.println(p.toString());

        for (Legemiddel lm : this.legemidler) {
            System.out.print("\n" + lm.hentNavn() + " Pris: " + lm.hentPris()
                + " Virkestoff" + lm.hentVirkestoff());
            if (lm instanceof Vanedannende) {
                Vanedannende vlm = (Vanedannende) lm;
                System.out.print(" Styrke: " + vlm.hentVanedannendeStyrke());
            } else if (lm instanceof Narkotisk) {
                Narkotisk nlm = (Narkotisk) lm;
                System.out.print(" Styrke: " + nlm.hentNarkotiskStyrke());
            }
        }

        for (Lege l : this.leger) {
            System.out.println(l.hentNavn());
            if (l instanceof Spesialist)
                System.out.println("Kontroll id: " + ((Spesialist)l).hentKontrollId());
        }

        for (Resept r : this.resepter) {
            System.out.println(r.hentPasient() + " " + r.hentLegemiddel() + " "
                + r.hentLege() + " " + r.hentReit());
        }

    }

}

