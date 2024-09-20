/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.TerminCasa;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hrckok
 */
public class TerminCasaTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Student", "Profesor", "Datum","jezik"};
    private List<TerminCasa> zakazaniTermini;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public TerminCasaTableModel(List<TerminCasa> zakazaniTermini) {
        this.zakazaniTermini = zakazaniTermini;
    }

    @Override
    public int getRowCount() {
        if (zakazaniTermini != null) {
            return zakazaniTermini.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TerminCasa zakazanTermin = zakazaniTermini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zakazanTermin.getTerminCasaID();
            case 1:
                return zakazanTermin.getStudent().getIme()+ " "+zakazanTermin.getStudent().getPrezime();
            case 2:
                return zakazanTermin.getProfesor().getIme()+" "+zakazanTermin.getProfesor().getPrezime();
            case 3:
                return zakazanTermin.getDatumTermina();
            case 4:
                return zakazanTermin.getStudent().getJezik().getNazivJezika();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<TerminCasa> getZakazaniTermini() {
        return zakazaniTermini;
    }

    public TerminCasa getClassTime(int row) {
        return zakazaniTermini.get(row);
    }

    public void setClassTimes(List<TerminCasa> zakazaniTermini) {
        this.zakazaniTermini = zakazaniTermini;
        fireTableDataChanged();
    }

}
