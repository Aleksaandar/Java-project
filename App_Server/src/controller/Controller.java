/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Admin;
import domain.Dokument;
import domain.Jezik;
import domain.Profesor;
import domain.Student;
import domain.TerminCasa;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import server.Server;
import so.admin.SOLoginAdmin;
import so.dokument.SOSacuvajDokument;
import so.dokument.SOUcitajDokumentePoUslovu;
import so.jezik.SOUcitajSveJezike;
import so.profesor.SOIzmeniProfesora;
import so.profesor.SOSacuvajProfesora;
import so.profesor.SOUcitajProfesora;
import so.profesor.SOUcitajSveProfesore;
import so.profesor.SOUcitajSveProfesoreUslov;
import so.student.SOIzmeniStudenta;
import so.student.SOObrisiStudenta;
import so.student.SOSacuvajStudenta;
import so.student.SOUcitajStudenta;
import so.student.SOUcitajSveStudente;
import so.student.SOUcitajSveStudenteUslov;
import so.terminCasa.SOIzmeniTerminCasa;
import so.terminCasa.SOSacuvajTerminCasa;
import so.terminCasa.SOUcitajTermin;
import so.terminCasa.SOUcitajTermineCasova;
import so.terminCasa.SOUcitajTerminePoUslovu;

/**
 *
 * @author Hrckok
 */
public class Controller {
    // Singleton pattern

    private static Controller instance;
    //private ArrayList<MenadzerProizvodnje> prijavljeniMenadzeri;
    private Server server; // Referenca na server

    private Controller() {

    }

    public Server getServer() {
        return server;
    }

    public Controller(Server server) {
        this.server = server;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Admin prijaviAdmina(Admin admin) throws Exception {
        System.out.println("ServerController: prijavaAdmina - Početak");
        SOLoginAdmin so = new SOLoginAdmin();
        so.templateExecute(admin);

        Admin ulogovaniAdmin = so.getUlogovaniAdmin();

        if (server.isAdminLoggedIn(ulogovaniAdmin)) {
            throw new Exception("Admin je već prijavljen.");
        }

        if (ulogovaniAdmin != null) {
            server.addLoggedInAdmin(ulogovaniAdmin);
        }

        return ulogovaniAdmin;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Admin odjaviAdmina(Admin ulogovaniAdmin) throws Exception {
        if (!server.isAdminLoggedIn(ulogovaniAdmin)) {
            throw new Exception("Admin nije prijavljen.");
        }

        server.removeLoggedInAdmin(ulogovaniAdmin);
        System.out.println("Usao je u izbacivanje iz liste admina");
        System.out.println("Da li je ostao ulogovan slucajno " + server.isAdminLoggedIn(ulogovaniAdmin));

        /*SOOdjaviAdmina so = new SOOdjaviAdmina();
        so.templateExecute(admin);*/
        return ulogovaniAdmin;
    }

    public boolean isAdminLoggedIn(Admin admin) {
        return server.isAdminLoggedIn(admin);
    }

    public List<Jezik> ucitajSveJezike() throws Exception {
        SOUcitajSveJezike so = new SOUcitajSveJezike();
        so.templateExecute(null);
        return so.getListaJezika();
    }

    public void sacuvajStudenta(Student student) throws Exception {
        SOSacuvajStudenta so = new SOSacuvajStudenta();
        so.templateExecute(student);
    }

    public List<Student> ucitajSveStudente() throws Exception {
        SOUcitajSveStudente so = new SOUcitajSveStudente();
        so.templateExecute(null);
        return so.getListaStudenata();
    }

    public void obrisiStudenta(Student studentZaBrisanje) throws Exception {
        SOObrisiStudenta so = new SOObrisiStudenta();
        so.templateExecute(studentZaBrisanje);
    }

    public List<Student> ucitajSveStudentePoUslovu(Student s) throws Exception {
        SOUcitajSveStudenteUslov so = new SOUcitajSveStudenteUslov();
        so.templateExecute(s);
        return so.getListaStudenataUslov();
    }

    public Student ucitajStudenta(Student studentZaUcitavanje) throws Exception {
        SOUcitajStudenta soUcitajStudenta = new SOUcitajStudenta();
        soUcitajStudenta.templateExecute(studentZaUcitavanje);
        return soUcitajStudenta.getStudent();
    }

    public Student izmeniStudenta(Student studentZaIzmenu) throws Exception {
        SOIzmeniStudenta so = new SOIzmeniStudenta();
        so.templateExecute(studentZaIzmenu);
        return studentZaIzmenu;
    }

    public void sacuvajProfesora(Profesor profesor) throws Exception {
        SOSacuvajProfesora so = new SOSacuvajProfesora();
        so.templateExecute(profesor);
    }

    public List<Profesor> ucitajSveProfesore() throws Exception {
        SOUcitajSveProfesore so = new SOUcitajSveProfesore();
        so.templateExecute(null);
        return so.getListaProfesora();
    }

    public Profesor ucitajProfesora(Profesor profesorZaUcitavanje) throws Exception {
        SOUcitajProfesora soUcitajProfesora = new SOUcitajProfesora();
        soUcitajProfesora.templateExecute(profesorZaUcitavanje);
        return soUcitajProfesora.getProfesor();
    }

    public Profesor izmeniProfesora(Profesor profesorZaIzmenu) throws Exception {
        SOIzmeniProfesora so = new SOIzmeniProfesora();
        so.templateExecute(profesorZaIzmenu);
        return profesorZaIzmenu;
    }

    public List<Profesor> ucitajSveProfesorePoUslovu(Profesor ps) throws Exception {
        SOUcitajSveProfesoreUslov so = new SOUcitajSveProfesoreUslov();
        so.templateExecute(ps);
        return so.getListaProfesoraUslov();
    }

    public List<TerminCasa> ucitajSveZakazaneTermine() throws Exception {
        SOUcitajTermineCasova so = new SOUcitajTermineCasova();
        so.templateExecute(null);
        return so.getListaTermina();
    }

    public void sacuvajTermin(TerminCasa termin) throws Exception {
        SOSacuvajTerminCasa so = new SOSacuvajTerminCasa();
        so.templateExecute(termin);
    }

    public TerminCasa ucitajTermin(TerminCasa terminZaUcitavanje) throws Exception {
        SOUcitajTermin so = new SOUcitajTermin();
        so.templateExecute(terminZaUcitavanje);
        return so.getTermin();
    }

    public List<Dokument> ucitajSveDokumentePoUslovu(Dokument dokument) throws Exception {
        SOUcitajDokumentePoUslovu so = new SOUcitajDokumentePoUslovu();
        so.templateExecute(dokument);
        return so.getListaDokumenataUslov();
    }

    public void izmeniTermin(TerminCasa terminZaIzmenu) throws Exception {
        SOIzmeniTerminCasa so = new SOIzmeniTerminCasa();
        so.templateExecute(terminZaIzmenu);
    }

    public List<TerminCasa> ucitajSveTerminePoUslovu(TerminCasa tc) throws Exception {
        SOUcitajTerminePoUslovu so = new SOUcitajTerminePoUslovu();
        so.templateExecute(tc);
        return so.getListaTerminaUslov();
    }

}
