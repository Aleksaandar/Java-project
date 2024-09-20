/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import domain.Jezik;
import domain.Pol;
import domain.Profesor;
import domain.StepenStrucneSpreme;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hrckok
 */
public class ProfesorTableModel extends AbstractTableModel {
      List<Profesor> profesori;
    private final String[] kolone = {"ID", "Ime", "Prezime", "Datum rodjenja", "Pol", "Stepen strucne spreme", "Jezik"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public ProfesorTableModel(List<Profesor> profesori) {
        this.profesori = profesori;
    }

    @Override
    public int getRowCount() {
        if (profesori == null) {
            return 0;
        }
        return profesori.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesor profesor = profesori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return profesor.getProfesorID();
            case 1:
                return profesor.getIme();
            case 2:
                return profesor.getPrezime();
            case 3:
                return profesor.getDatumRodjenja();
            case 4:
                return profesor.getPol();
            case 5:
                return profesor.getStepenStrucneSpreme();
            case 6:
                return profesor.getJezik().getNazivJezika();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Profesor> getProfessors() {
        return profesori;
    }

    public Profesor getProfessor(int row) {
        return profesori.get(row);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Profesor p = profesori.get(rowIndex);
            switch (columnIndex) {
                case 1:
                    p.setIme((String) aValue);
                    break;
                case 2:
                    p.setPrezime((String) aValue);
                    break;
                case 3:
                    p.setDatumRodjenja(sdf.parse((String) aValue));
                case 4:
                    p.setPol((Pol) aValue);
                case 5:
                    p.setStepenStrucneSpreme((StepenStrucneSpreme) aValue);
                case 6:
                    p.setJezik((Jezik) aValue);
                default:
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProfesorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setProfessors(List<Profesor> profesori) {
        this.profesori = profesori;
        fireTableDataChanged();
    }
    
}
