import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Gui {

    public static Labyrint labyrint;
    public static void main(String[] args) {


        JFileChooser fc = new JFileChooser();
        int choice = fc.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) System.exit(1);
        File file = fc.getSelectedFile();

        try {
            labyrint = new Labyrint(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        labyrint.printLabyrint();

        JFrame window = new JFrame("Labyrint");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuBar.add(new ExitButton("Avslutt"));

        JPanel panel = new JPanel(new GridLayout(labyrint.xCount, labyrint.yCount));
        makeGrid(labyrint.hentBrett(), panel);

        window.add(panel);
        window.pack();
        window.setVisible(true);

    }

    public static void makeGrid(Rute[][] board, JPanel panel) {
        for (int x = 0; x < labyrint.xCount; x++) {
            for (int y = 0; y < labyrint.yCount; y++) {
                RuteKnapp ruteKnapp = null;

                System.out.println(x + " " + y);
                if (board[y][x] instanceof HvitRute) {
                    ruteKnapp = new HvitRuteKnapp(x, y);
                }
                if (board[y][x] instanceof SortRute) {
                    ruteKnapp = new SortRuteKnapp(x, y);
                    // lk set on action
                    // set style
                }

                panel.add(ruteKnapp);
            }
        }
    }
}

abstract class RuteKnapp extends JLabel implements ActionListener {
    private final int x;
    private final int y;
    String farge = "";

    public RuteKnapp(int x, int y) {
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(25, 25));
        setOpaque(true);
    }

    public void settFarge(char c) {
        if (c == 's') farge = "svart";
        else if (c == 'h') farge = "hvit";
        else if (c == 'b') farge = "blaa";
    }

    public String hentFarge() { return this.farge; }
}

class HvitRuteKnapp extends RuteKnapp {
    public HvitRuteKnapp(int x, int y) {
        super(x, y);
        setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}


class SortRuteKnapp extends RuteKnapp {
    public SortRuteKnapp(int x, int y) {
        super(x, y);
        setBackground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}

class ExitButton extends Button implements ActionListener {
    ExitButton(String s) {
        super(s);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Avslutter...");
        System.exit(0);
    }
}
