/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Admin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hrckok
 */
public class AdminTableModel extends AbstractTableModel {
    
    String[] kolone = new String[]{"username", "ime", "prezime"};
    List<Admin> admini;

    public AdminTableModel() {
        admini = new ArrayList<>();
    }

    public AdminTableModel(List<Admin> admins) {
        this.admini = admins;
    }

    @Override
    public String getColumnName(int column) {
        if (column > kolone.length) {
            return "n/a";
        }
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return admini.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Admin a = admini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getUsername();
            case 1:
                return a.getIme();
            case 2:
                return a.getPrezime();

            default:
                return "n/a";
        }
    }

    public void addUser(Admin a) {
        if (admini.contains(a)) {
            return;
        }
        admini.add(a);
        fireTableDataChanged();
    }
}
