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

/**
 *
 * @author Hrckok
 */
public class Profesor extends AbstractDomainObject implements Serializable {

    private int profesorID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private Pol pol;
    private Jezik jezik;
    private StepenStrucneSpreme stepenStrucneSpreme;

    public Profesor() {
    }

    public Profesor(int profesorID, String ime, String prezime, Date datumRodjenja, Pol pol, Jezik jezik, StepenStrucneSpreme stepenStrucneSpreme) {
        this.profesorID = profesorID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.jezik = jezik;
        this.stepenStrucneSpreme = stepenStrucneSpreme;
    }

    public Profesor(String ime, String prezime, Date datumRodjenja, Pol pol, Jezik jezik, StepenStrucneSpreme stepenStrucneSpreme) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.jezik = jezik;
        this.stepenStrucneSpreme = stepenStrucneSpreme;
    }

    public long getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(int profesorID) {
        this.profesorID = profesorID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Jezik getJezik() {
        return jezik;
    }

    public void setJezik(Jezik jezik) {
        this.jezik = jezik;
    }

    public StepenStrucneSpreme getStepenStrucneSpreme() {
        return stepenStrucneSpreme;
    }

    public void setStepenStrucneSpreme(StepenStrucneSpreme stepenStrucneSpreme) {
        this.stepenStrucneSpreme = stepenStrucneSpreme;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        return this.profesorID == other.profesorID;
    }

    @Override
    public String toString() {
       return ime + " "  + prezime + ", " + jezik ;
    }

    @Override
    public String nazivTabele() {
        return "profesor";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return "join jezik j on p.jezikID=j.jezikID";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Jezik j = new Jezik(rs.getInt("jezikID"), rs.getString("nazivJezika"));
            Profesor profesor = new Profesor(
                    rs.getInt("profesorID"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getDate("datumRodjenja"),
                    Pol.valueOf(rs.getString("pol")),
                    j,
                    stepenStrucneSpreme.valueOf(rs.getString("stepenStrucneSpreme"))
            );

            lista.add(profesor);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime,prezime,datumRodjenja,pol,stepenStrucneSpreme,jezikID)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
               return "profesorID= " + profesorID;
    }

    @Override
    public String vrednostiZaInsert() {
        StringBuilder vrednosti = new StringBuilder();
        vrednosti.append("(")
                .append("'").append(getIme()).append("', ")
                .append("'").append(getPrezime()).append("', ")
                .append("'").append(new java.sql.Date(getDatumRodjenja().getTime())).append("', ")
                .append("'").append(getPol().toString()).append("', ")
                .append("'").append(getStepenStrucneSpreme().toString()).append("', ")
                .append(getJezik().getJezikID())
                .append("), ");

        vrednosti.setLength(vrednosti.length() - 2); // Uklanja poslednji zarez i razmak
        return vrednosti.toString();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime = '" + getIme() + "', "
                + "prezime = '" + getPrezime() + "', "
                + "datumRodjenja = '" + new java.sql.Date(datumRodjenja.getTime()) + "', "
                + "pol = '" + getPol().toString() + "', "
                + "stepenStrucneSpreme = '" + getStepenStrucneSpreme().toString() + "', "
                + "jezikID = " + getJezik().getJezikID();
    }

    @Override
    public String uslov() {
        if (this.profesorID != 0) {
            return " WHERE p.profesorID= " + profesorID;
        }
        if (this.ime != null && this.ime != "") {
            return " WHERE p.ime like '%" + this.ime + "%'";
        }

        if (this.prezime != null && this.prezime != "") {
            return " WHERE p.prezime like '%" + this.prezime + "%'";
        }

        return "";
    }

}
