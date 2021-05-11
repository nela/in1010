import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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

        JPanel brettPanel = new JPanel(new GridLayout(labyrint.yCount, labyrint.xCount));

        RuteKnapp[][] brettKnapper = lagGrid(labyrint, brettPanel);

        window.add(brettPanel);
        window.pack();
        window.setVisible(true);

    }

    public static RuteKnapp[][] lagGrid(Labyrint l, JPanel brettPanel) {
        Rute[][] brett = l.hentBrett();

        RuteKnapp[][] brettKnapper = new RuteKnapp[l.yCount][l.xCount];
        for (int y = 0; y < l.yCount; y++) {
            for (int x = 0; x < l.xCount; x++) {
                RuteKnapp ruteKnapp = null;

                System.out.println(x + " " + y);
                if (brett[y][x] instanceof HvitRute) {
                    ruteKnapp = new HvitRuteKnapp(l, x, y, brettKnapper);
                }
                if (brett[y][x] instanceof SortRute) {
                    ruteKnapp = new SortRuteKnapp(l, brettKnapper, x, y);
                }

                brettKnapper[y][x] = ruteKnapp;
                brettPanel.add(ruteKnapp);
            }
        }

        return brettKnapper;
    }
}

abstract class RuteKnapp extends JLabel implements MouseListener {
    protected final int x;
    protected final int y;
    protected final Labyrint l;

    public RuteKnapp(Labyrint l, int x, int y) {
        this.x = x;
        this.y = y;
        this.l = l;
        setPreferredSize(new Dimension(25, 25));
        setOpaque(true);
        addMouseListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}


class HvitRuteKnapp extends RuteKnapp {
    RuteKnapp[][] brettKnapper;

    public HvitRuteKnapp(Labyrint l, int x, int y, RuteKnapp[][] brettKnapper) {
        super(l, x, y);
        this.brettKnapper = brettKnapper;
        setBackground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.l.finnUtveiFra(this.x, this.y);

        ArrayList<Tuppel> losning = this.l.finnKortesteUtvei();

        for (Tuppel t : losning)
            brettKnapper[t.y][t.x].setBackground(Color.BLUE);
    }

}

class SortRuteKnapp extends RuteKnapp {
    public SortRuteKnapp(Labyrint l, RuteKnapp[][] brettKnapper, int x, int y) {
        super(l, x, y);
        setBackground(Color.BLACK);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
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

class MenuBar extends JMenuBar {
    public MenuBar() {

    }

}
