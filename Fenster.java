package Einkaufliste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Fenster extends JFrame {

    private JPanel jpNorth, jpSouth;
    private JComboBox<String> jComboBox;
    private JTextField jtxtAnzahl;
    private JButton jbtnEintragen;
    private JLabel jlblGesmatpreis;
    private JMenuBar jMenuBar;
    private JMenu jMenuDatei;
    private JMenuItem jmiNeu, jmiSpeichern, jmiBeenden;

    public Fenster() throws HeadlessException {
        super("Einkaufsliste");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(400, 400);
        initMenu();
        initComponents();
        initEvents();

        // muss am ende stehen:
        this.setVisible(true);
    }

    private void initEvents() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                beenden();
            }
        });

        jmiBeenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beenden();
            }
        });
    }

    private void beenden() {
        int result = JOptionPane.showConfirmDialog(this, "Wollen sie wirklich Beenden?", "Beenden ?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(NORMAL);
        }
    }

    private void initMenu() {
        jMenuBar = new JMenuBar();
        jMenuDatei = new JMenu("Datei");
        jmiBeenden = new JMenuItem("Beenden");
        jmiSpeichern = new JMenuItem("Speichern");
        jmiNeu = new JMenuItem("Neu");

        jMenuDatei.add(jmiNeu);
        jMenuDatei.add(jmiSpeichern);
        jMenuDatei.add(jmiBeenden);
        jMenuBar.add(jMenuDatei);

        this.setJMenuBar(jMenuBar);
    }

    private void initComponents() {
        jpNorth = new JPanel();
        jComboBox = new JComboBox<>();
        jComboBox.addItem("Bitte Auswählen...");
        jtxtAnzahl = new JTextField(2);
        jbtnEintragen = new JButton("Eintragen");
        jpNorth.add(jComboBox);
        jpNorth.add(new JLabel("Anzahl"));
        jpNorth.add(jtxtAnzahl);
        jpNorth.add(jbtnEintragen);

        jpSouth = new JPanel();
        jpSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jpSouth.add(new JLabel("Gesamtpreis"));
        jlblGesmatpreis = new JLabel("0,00");
        jpSouth.add(jlblGesmatpreis);

        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jpSouth, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new Fenster();
    }
}
