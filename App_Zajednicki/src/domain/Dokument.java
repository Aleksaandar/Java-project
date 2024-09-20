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
public class Dokument extends AbstractDomainObject implements Serializable {

    private long dokumentID;
    private TerminCasa terminCasa;
    private String naslov;
    private String komentar;
    private Akcija akcija;

    public Dokument() {
    }

    public Dokument(long dokumentID, TerminCasa terminCasa, String naslov, String komentar, Akcija akcija) {
        this.dokumentID = dokumentID;
        this.terminCasa = terminCasa;
        this.naslov = naslov;
        this.komentar = komentar;
        this.akcija = akcija;
    }

    public Dokument(long dokumentID, String naslov, String komentar) {
        this.dokumentID = dokumentID;
        this.naslov = naslov;
        this.komentar = komentar;
    }

    public long getDokumentID() {
        return dokumentID;
    }

    public void setDokumentID(long dokumentID) {
        this.dokumentID = dokumentID;
    }

    public TerminCasa getTerminCasa() {
        return terminCasa;
    }

    public void setTerminCasa(TerminCasa terminCasa) {
        this.terminCasa = terminCasa;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Akcija getAkcija() {
        return akcija;
    }

    public void setAkcija(Akcija akcija) {
        this.akcija = akcija;
    }

    @Override
    public String toString() {
        return "Dokument{" + "terminCasa=" + terminCasa + ", naslov=" + naslov + ", komentar=" + komentar + '}';
    }

    @Override
    public String nazivTabele() {
        return "dokument";
    }

    @Override
    public String alijas() {
        return "d";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            Dokument dokument = new Dokument(
                    rs.getInt("dokumentID"),
                    rs.getString("naslov"),
                    rs.getString("komentar")
            );

            lista.add(dokument);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(zakazanTerminID,naslov,komentar)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "dokumentID= " + dokumentID;
    }

    @Override
    public String vrednostiZaInsert() {
        StringBuilder vrednosti = new StringBuilder();
        vrednosti.append("(")
                .append(terminCasa.getTerminCasaID()).append(",")
                .append("'").append(naslov).append("', ")
                .append("'").append(komentar).append("') ");

//        vrednosti.setLength(vrednosti.length() - 2); // Uklanja poslednji zarez i razmak
        System.out.println(vrednosti.toString());
        return vrednosti.toString();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "zakazanTerminID = " + getTerminCasa().getTerminCasaID() + ", "
                + "naslov = '" + getNaslov() + "', "
                + "komentar = '" + getKomentar() + "'";
    }

    @Override
    public String uslov() {
        return "where zakazanTerminID=" + getTerminCasa().getTerminCasaID();
    }

}
