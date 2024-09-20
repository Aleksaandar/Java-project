/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Jezik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hrckok
 */
public class JezikTableModel extends AbstractTableModel {

    private List<Jezik> listaJezika;
    private final String[] kolone = {"jezik ID", "naziv jezika","broj studenata"};

    public JezikTableModel(List<Jezik> listaJezika) {
        this.listaJezika = listaJezika;
    }

    @Override
    public int getRowCount() {
        return listaJezika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jezik j = listaJezika.get(rowIndex);
        switch (columnIndex) {
            case 0:

                return j.getJezikID();
            case 1:
                return j.getNazivJezika();
            case 2:
                return j.getBrojStudenataKojiSlusaju();
            default:
                throw new AssertionError();
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
