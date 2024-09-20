/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import domain.Jezik;
import domain.Pol;
import domain.Student;
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
public class StudentTableModel extends AbstractTableModel {

    List<Student> studenti;
    private final String[] kolone = {"ID", "Ime", "Prezime", "Datum rodjenja", "Pol", "Jezik"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public StudentTableModel(List<Student> students) {
        this.studenti = students;
    }

    @Override
    public int getRowCount() {
        if (studenti == null) {
            return 0;
        }
        return studenti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = studenti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return student.getStudentID();
            case 1:
                return student.getIme();
            case 2:
                return student.getPrezime();
            case 3:
                return student.getDatumRodjenja();
            case 4:
                return student.getPol();

            case 5:
                return student.getJezik().getNazivJezika();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Student> getStudents() {
        return studenti;
    }

    public Student getStudent(int row) {
        return studenti.get(row);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Student s = studenti.get(rowIndex);
            switch (columnIndex) {
                case 1:
                    s.setIme((String) aValue);
                    break;
                case 2:
                    s.setPrezime((String) aValue);
                    break;
                case 3:
                    s.setDatumRodjenja(sdf.parse((String) aValue));
                case 4:
                    s.setPol((Pol) aValue);

                case 5:
                    s.setJezik((Jezik) aValue);
                default:
            }
        } catch (ParseException ex) {
            Logger.getLogger(StudentTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStudents(List<Student> students) {
        this.studenti = students;
        fireTableDataChanged();
    }
}
