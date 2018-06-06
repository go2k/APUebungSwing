package Einkaufliste;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MeinTableModel extends AbstractTableModel {

    private List<Gegenstand> gegenstands;
    private String[] columns = {"Anzahl", "Gegenstand", "Einzelpreis", "Preis"};

    public MeinTableModel() {
        gegenstands = new ArrayList<>();
        gegenstands.add(new Gegenstand("Apfel", 0.45, 2));
        gegenstands.add(new Gegenstand("Birne", 0.55, 5));
        gegenstands.add(new Gegenstand("Bananen", 0.56, 7));
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

}
