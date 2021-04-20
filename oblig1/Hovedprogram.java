public class Hovedprogram {

  public static void main (String[] args) {

    String[] filer = new String[]{"dataklynge.txt", "dataklynge2.txt",
      "dataklynge3.txt", "dataklynge4.txt"};

    for (String filnavn : filer) {
      DataKlynge abel = new DataKlynge(filnavn, filnavn);

      System.out.println("\nDataKlynge: " + filnavn + "\n");
      int count32gb = abel.noderMedNokMinne(32);
      int count64gb = abel.noderMedNokMinne(64);
      int count128gb = abel.noderMedNokMinne(128);

      System.out.println("Noder med minst 32 GB: " + count32gb);
      System.out.println("Noder med minst 64 GB: " + count64gb);
      System.out.println("Noder med minst 128 GB " + count128gb);

      System.out.println("Antall Prosessorer: " + abel.antProsessorer());
      System.out.println("Antall rack: " + abel.getAntRack());
    }
  }
}
