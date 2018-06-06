package Einkaufliste;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeinTableModel extends AbstractTableModel {

    private List<Gegenstand> gegenstands;
    private String[] columns = {"Anzahl", "Gegenstand", "Einzelpreis", "Preis"};

    public MeinTableModel() {
        gegenstands = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return gegenstands.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gegenstand gegenstand = gegenstands.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return gegenstand.getAnzahl();
            case 1:
                return gegenstand.getBezeichnung();
            case 2:
                return gegenstand.getEinzelpreis();
            case 3:
                return gegenstand.getEinzelpreis() * gegenstand.getAnzahl();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void hinzufuegen(Gegenstand gegenstand) {

        Iterator<Gegenstand> iterator = gegenstands.iterator();

        while (iterator.hasNext()) {
            Gegenstand next = iterator.next();
            if (next.getBezeichnung().equals(gegenstand.getBezeichnung())) {
                next.setAnzahl(next.getAnzahl() + gegenstand.getAnzahl());
                this.fireTableDataChanged();
                return;
            }
        }
        this.gegenstands.add(gegenstand);
        this.fireTableDataChanged();
    }

    public void speichern(File file) throws IOException {

        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(file));

        for (Gegenstand gegenstand : gegenstands) {
            bw.write(gegenstand.getBezeichnung() + "," + gegenstand.getAnzahl() + "," + gegenstand.getEinzelpreis() + "," + gegenstand.getAnzahl() * gegenstand.getEinzelpreis());
            bw.newLine();
        }
        if (bw != null) {
            bw.close();
        }
    }

    public double getGesamtpreis() {

        double gesamtpreis = 0.0;
        for (Gegenstand gegenstand : gegenstands) {
            gesamtpreis += gegenstand.getAnzahl() * gegenstand.getEinzelpreis();
        }
        return gesamtpreis;
    }

    public void loescheEintrag(String bezeichnung) {

        Iterator<Gegenstand> iterator = gegenstands.iterator();

        while (iterator.hasNext()) {

            Gegenstand gegenstand = iterator.next();
            if (gegenstand.getBezeichnung().equals(bezeichnung)) {
                iterator.remove();
                fireTableDataChanged();
                return;
            }
        }
    }
}


