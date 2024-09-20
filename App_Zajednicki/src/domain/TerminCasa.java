/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hrckok
 */
public class TerminCasa extends AbstractDomainObject implements Serializable {

    private long terminCasaID;
    private Date datumTermina;
    private Profesor profesor;
    private Student student;
    private List<Dokument> dokumenti;

    public TerminCasa() {
    }

    public TerminCasa(long terminCasaID, Date datumTermina, Profesor profesor, Student student, List<Dokument> dokumenti) {
        this.terminCasaID = terminCasaID;
        this.datumTermina = datumTermina;
        this.profesor = profesor;
        this.student = student;
        this.dokumenti = dokumenti;
    }

    public TerminCasa(long terminCasaID, Date datumTermina, Profesor profesor, Student student) {
        this.terminCasaID = terminCasaID;
        this.datumTermina = datumTermina;
        this.profesor = profesor;
        this.student = student;
    }

    public long getTerminCasaID() {
        return terminCasaID;
    }

    public void setTerminCasaID(long terminCasaID) {
        this.terminCasaID = terminCasaID;
    }

    public Date getDatumTermina() {
        return datumTermina;
    }

    public void setDatumTermina(Date datumTermina) {
        this.datumTermina = datumTermina;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Dokument> getDokumenti() {
        return dokumenti;
    }

    public void setDokumenti(List<Dokument> dokumenti) {
        this.dokumenti = dokumenti;
    }

    @Override
    public String toString() {
        return "TerminCasa{" + "datumTermina=" + datumTermina + ", profesor=" + profesor + ", student=" + student + '}';
    }

    @Override
    public String nazivTabele() {
        return "zakazantermin";
    }

    @Override
    public String alijas() {
        return "zt";
    }

    @Override
    public String join() {
        return "JOIN student s ON zt.studentID=s.studentID JOIN profesor p ON zt.profesorID=p.profesorID JOIN jezik j ON s.jezikID=j.jezikID";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Jezik j = new Jezik(rs.getLong("j.jezikID"), rs.getString("j.nazivJezika"));
            Profesor p = new Profesor(rs.getInt("p.profesorID"), rs.getString("p.ime"), rs.getString("p.prezime"), rs.getDate("p.datumRodjenja"), Pol.valueOf(rs.getString("p.pol")), j, StepenStrucneSpreme.valueOf(rs.getString("p.stepenStrucneSpreme")));
            Student s = new Student(rs.getInt("s.studentID"), rs.getString("s.ime"), rs.getString("s.prezime"), rs.getDate("s.datumRodjenja"),
                    Pol.valueOf(rs.getString("s.pol")), j);
            TerminCasa zt = new TerminCasa(rs.getLong("zt.zakazanTerminID"), rs.getDate("zt.datumTermina"), p, s);

            lista.add(zt);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(datumTermina,studentID,profesorID)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "zakazanTerminID= " + terminCasaID;
    }

    @Override
    public String vrednostiZaInsert() {
        StringBuilder vrednosti = new StringBuilder();
        vrednosti.append("(")
                .append("'").append(new java.sql.Date(getDatumTermina().getTime())).append("', ")
                .append(getStudent().getStudentID())
                .append(",").append(getProfesor().getProfesorID())
                .append("), ");

        vrednosti.setLength(vrednosti.length() - 2); // Uklanja poslednji zarez i razmak
        return vrednosti.toString();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "datumTermina = '" + new java.sql.Date(datumTermina.getTime()) + "', "
                + "studentID = '" + getStudent().getStudentID() + "', "
                + "profesorID = " + getProfesor().getProfesorID();
    }

    @Override
    public String uslov() {
        if (terminCasaID != 0) {
            return " WHERE zt.zakazanTerminID= " + terminCasaID;
        }
        if (student.getIme() != null && student.getIme() != "") {
            return " WHERE s.ime like '%" + student.getIme() + "%'";
        }
        return "";
    }

}
