/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Dokument;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hrckok
 */
public class DokumentTableModel extends AbstractTableModel {
   private final List<Dokument> dokumenti;

    private final String[] kolone = {"Naslov", "Komentar"};

    public DokumentTableModel(List<Dokument> dokumenti) {
        this.dokumenti = dokumenti;
    }

  

    @Override
    public int getRowCount() {
        if (dokumenti != null) {
            return dokumenti.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dokument dokument = dokumenti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dokument.getNaslov();
            case 1:
                return dokument.getKomentar();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Dokument> getDocuments() {
        return dokumenti;
    }

    public void add(Dokument dokument) {
        dokumenti.add(dokument);
        fireTableDataChanged();
    }

    public void update() {
        fireTableDataChanged();
    }

    public void delete(int row) {
        dokumenti.remove(row);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    
}
