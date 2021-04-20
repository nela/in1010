public class TestLegemiddel {

  public static boolean testVanlig() {
    Legemiddel vanlig = new Vanlig("Paracet", 20.98, 5.35);

    if (vanlig.hentId() != 1) {
      System.out.println("Legemiddel feilet på Id.");
      return false;
    }

    if (!vanlig.hentNavn().equals("Paracet")) {
      System.out.println("Legemiddel feilet på navn.");
      return false;
    }
    if (vanlig.hentPris() != 20.98) {
      System.out.println("Legemiddel feilet på pris");
      return false;
    }
    if (vanlig.hentVirkestoff() != 5.35) {
      System.out.println("Legemiddel feilet på virkestoff.");
      return false;
    }
    if (vanlig.settNyPris(4.48) != 4.48) {
      System.out.println("Legemiddel feilet på å sette ny pris.");
      return false;
    }

    System.out.println(vanlig.toString());
    return true;
  }

  public static boolean testNarkotisk() {
    Narkotisk narkotisk = new Narkotisk("Morfin", 203.98, 50.35, 77);

    if (narkotisk.hentId() != 2) {
      System.out.println("Narkotisk Legemiddel feilet på Id.");
      return false;
    }

    if (narkotisk.hentNarkotiskStyrke() != 77) {
      System.out.println("Narkotisk legemiddel feilet på styrke.");
      return false;
    }

    System.out.println(narkotisk.toString());
    return true;
  }

  public static boolean testVanedannende() {
    Vanedannende vanedannende = new Vanedannende("Otrivin", 90.21, 23.01, 4);

    if (vanedannende.hentId() != 3) {
      System.out.println("Vanedannende Legemiddel feilet på Id.");
      return false;
    }
    if(vanedannende.hentVanedannendeStyrke() != 4) {
      System.out.println("Vanedannende legemiddel feilet på styrke.");
      return false;
    }

    System.out.println(vanedannende.toString());
    return true;
  }

  public static void main (String[] args) {
    testVanlig();
    testNarkotisk();
    testVanedannende();
  }
}
