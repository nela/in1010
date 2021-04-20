public class TestResepter {

  public static boolean testHvit() {

    Vanlig vanlig = new Vanlig("Paracet", 29.00, 500.0);
    Lege lege = new Lege("Arne");
    int pasientId = 1;
    int reit = 100;

    Hvit hvit = new Hvit(vanlig, lege, pasientId, reit);

    if (hvit.hentId() != 1) {
      System.out.println("Hvit feilet på Id.");
      return false;
    }
    if (!hvit.hentLegemiddel().hentNavn().equals("Paracet")) {
      System.out.println("Hvit feilet på Legemiddel.");
      return false;
    }
    if (!hvit.hentLege().hentNavn().equals("Arne")) {
      System.out.println("Hvit feilet på lege.");
      return false;
    }
    if (hvit.hentPasientId() != pasientId) {
      System.out.println("Hvit feilet på Pasient Id");
      return false;
    }
    if (hvit.hentReit() != reit) {
      System.out.println("Hvit feilet på reit.");
      return false;
    }
    if (!hvit.bruk()) {
      System.out.println("Hvit feilet på å bruke reit.");
    }
    if (hvit.hentReit() != reit - 1) {
      System.out.println("Hvit feilet på dekrementeringen av reit.");
    }
    if (!hvit.farge().equals("Hvit")) {
      System.out.println("Hvit feilet på farge.");
      return false;
    }
    if (hvit.prisAaBetale() != 29.00) {
      System.out.println("Hvit feilet på pris aa betale.");
    }
    System.out.println(hvit.toString());
    return true;
  }

  public static boolean testMilitaer() {
    Vanlig vanlig = new Vanlig("Paracet", 29.00, 500.0);
    Lege lege = new Lege("Arne");
    int pasientId = 2;
    int reit = 100;

    MilitaerResept mr = new MilitaerResept(vanlig, lege, pasientId, reit);

    if (!mr.farge().equals("hvit")) {
      System.out.println("MilitaerResept feilet på farge.");
      return false;
    }
    if (mr.prisAaBetale() != 0) {
      System.out.println("MilitaerResept feilet på pris aa betale.");
      return false;
    }

    System.out.println(mr.toString());
    return true;
  }

  public static boolean testPResept() {
    double pris = 200.00;
    Vanlig vanlig = new Vanlig("P-Pille Bubille", pris, 50.0);
    Lege lege = new Lege("Kari");
    int pasientId = 2;

    PResept pr = new PResept(vanlig, lege, pasientId);

    if (!pr.farge().equals("Hvit")) {
      System.out.println("Presept feilet på farge.");
      return false;
    }
    if (pr.prisAaBetale() != pris - 108) {
      System.out.println("PResept feilet på pris å betale.");
      return false;
    }

    System.out.println(pr.toString());
    return true;
  }

  public static boolean testBlaa() {
    double pris = 420.69;
    Narkotisk narkotisk = new Narkotisk("Morfin", pris, 50.35, 77);
    Lege lege = new Lege("Kari");
    int pasientId = 2;
    int reit = 100;

    Blaa blaa = new Blaa(narkotisk, lege, pasientId, reit);
    if (!blaa.farge().equals("Blaa")) {
      System.out.println("Blaa feilet på farge.");
      return false;
    }
    if (blaa.prisAaBetale() !=  pris * 0.25) {
      System.out.println("Blå feilet på pris å betale");
      return false;
    }

    System.out.println(blaa.toString());
    return true;
  }

  public static void main (String[] args) {
    testHvit();
    testMilitaer();
    testPResept();
    testBlaa();
  }
}
