/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hrckok
 */
public class Jezik extends AbstractDomainObject implements Serializable {

    private long jezikID;
    private String nazivJezika;
    private int brojStudenataKojiSlusaju;

    public Jezik() {
    }

    public Jezik(long jezikID, String nazivJezika) {
        this.jezikID = jezikID;
        this.nazivJezika = nazivJezika;
    }

    public long getJezikID() {
        return jezikID;
    }

    public void setJezikID(long jezikID) {
        this.jezikID = jezikID;
    }

    public String getNazivJezika() {
        return nazivJezika;
    }

    public void setNazivJezika(String nazivJezika) {
        this.nazivJezika = nazivJezika;
    }

    public int getBrojStudenataKojiSlusaju() {
        return brojStudenataKojiSlusaju;
    }

    public void setBrojStudenataKojiSlusaju(int brojStudenataKojiSlusaju) {
        this.brojStudenataKojiSlusaju = brojStudenataKojiSlusaju;
    }

    @Override
    public String toString() {
        return nazivJezika;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.jezikID ^ (this.jezikID >>> 32));
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
        final Jezik other = (Jezik) obj;
        return this.jezikID == other.jezikID;
    }

    @Override
    public String nazivTabele() {
        return "jezik";
    }

    @Override
    public String alijas() {
        return "j";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Jezik jezik = new Jezik(
                    rs.getInt("jezikID"),
                    rs.getString("nazivJezika")
            );

            lista.add(jezik);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String uslov() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
