public class Main {

    public static IOHandler ioHandler;
    public static Legesystem legesystem;

    public static final String[] hovedmenyAlt = new String[] {
        "Oversikt over Pasienter, Leger, Legemidler og Resepter",
        "Opprett Elementer", "Bruke Resept", "Hent Statistikk",
    };

    public static final String[] oppretteAlt = new String[] {
        "Registrer ny pasient", "Registrer ny lege",
        "Registrer nytt legemiddel", "Registrer nytt resept", "Tilbake"
    };

    public static final String[] oversiktAlt = new String[] {
        "Pasienter", "Leger", "Legemidler", "Resepter", "Tilbake"
    };

    public static void main(String[] args) {
        ioHandler = new IOHandler();
        legesystem = new Legesystem(ioHandler);

        legesystem.init(ioHandler.lesFil(args[0]));
        // legesystem.oversikt();

        while(true) {
            ioHandler.skrivOverskrift("Hovedmeny - 'q' for å avslutte når som helst");
            int valg = ioHandler.lesValg(null, hovedmenyAlt);
            switch (valg) {
                case 0:
                    oversikt();
                    break;
                case 1:
                    opprett();
                    break;
                case 2:
                    legesystem.brukResept();
                    break;
                case 3:
                    legesystem.statistikk();
                    break;
                default:
                    break;
            }

        }
    }

    public static void oversikt() {
        switch (ioHandler.lesValg("Velg oversikt for", oversiktAlt)) {
            case 0:
                for (String s : legesystem.pasientOversikt())
                    ioHandler.skrivMelding(s);
                break;
            case 1:
                for (String s : legesystem.legeOveriskt())
                    ioHandler.skrivMelding(s);
                break;
            case 2:
                for (String s : legesystem.legemiddelOversikt())
                    ioHandler.skrivMelding(s);
                break;
            case 3:
                for (String s : legesystem.reseptOversikt())
                    ioHandler.skrivMelding(s);
                break;
            case 4:
                ioHandler.skrivMelding("Går tilbake til Hovedmeny");
                break;
            default:
                break;
        }
    }

    public static void opprett() {
        switch (ioHandler.lesValg("Velg element å opprette", oppretteAlt)) {
            case 0:
                legesystem.registrerPasient();
                break;
            case 1:
                legesystem.registrerLege();
                break;
            case 2:
                legesystem.registrerLegemiddel();
                break;
            case 3:
                legesystem.registrerResept();
                break;
            case 4:
                ioHandler.skrivMelding("Går tilbake til Hovedmeny");
                break;
            default:
                break;
        }
    }

}
