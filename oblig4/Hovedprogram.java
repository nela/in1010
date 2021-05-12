import java.util.Arrays;
import java.util.ArrayList;

public class Hovedprogram {

  public static void main (String[] args) {

    ArrayList<String> printStrings = new ArrayList<String>();
    Lege lege1 = new Lege("Arne");
    Lege lege2 = new Lege("Kari");
    Spesialist spesialist1 = new Spesialist("Spesialist Pål", 58);
    Spesialist spesialist2 = new Spesialist("Spesialist Leif", 18);

    printStrings.addAll(Arrays.asList(lege1.toString(), lege2.toString(),
          spesialist1.toString(), spesialist2.toString()));

    Narkotisk narkotisk = new Narkotisk("Morfin", 149.90, 300.0, 4);
    Vanedannende vanedannende = new Vanedannende("Otrivin", 45.90, 100.0, 2);
    Vanlig vanlig1 = new Vanlig("Paracet", 29.00, 500.0);
    Vanlig vanlig2 = new Vanlig("Ibux", 149.00, 3.4);

    printStrings.addAll(Arrays.asList(vanlig1.toString(), vanlig2.toString(),
          narkotisk.toString(), vanedannende.toString()));

    Hvit hvit = new Hvit(vanlig1, lege1, 0, 2);
    Blaa blaa = new Blaa(vanlig2, lege2, 1, 5);
    MilitaerResept mr = new MilitaerResept(narkotisk, spesialist1, 2, 10);
    PResept pr = new PResept(vanedannende, spesialist2, 3);

    printStrings.addAll(Arrays.asList(hvit.toString(), blaa.toString(),
          mr.toString(), pr.toString()));

    for (String s : printStrings) {
      System.out.println(s);
    }
  }

}
