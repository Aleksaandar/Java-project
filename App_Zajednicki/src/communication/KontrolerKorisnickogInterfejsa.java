/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Admin;
import domain.Dokument;
import domain.Jezik;
import domain.Profesor;
import domain.Student;
import domain.TerminCasa;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author Isidora
 */
public class KontrolerKorisnickogInterfejsa {

    private static KontrolerKorisnickogInterfejsa instance;
    private Communication communication;
    private Admin prijavljeniAdmin;
    private Timer connectionChecker;

    private KontrolerKorisnickogInterfejsa() {
        communication = new Communication();
        communication.connect();
        startConnectionChecker();
    }

    public static KontrolerKorisnickogInterfejsa getInstance() {
        if (instance == null) {
            instance = new KontrolerKorisnickogInterfejsa();
        }
        return instance;
    }

    public Admin getPrijavljeniAdmin() {
        return prijavljeniAdmin;
    }

    private void startConnectionChecker() {
        connectionChecker = new Timer(true);
        connectionChecker.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!communication.isConnected()) {
                    System.out.println("Konekcija sa serverom je prekinuta.");
                    JOptionPane.showMessageDialog(null, "Konekcija sa serverom je prekinuta. Aplikacija će se zatvoriti.", "Greška", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }, 5000, 5000); // Provera svakih 5 sekundi
    }

    //otvara komunikaciju koja sluzi za rad s serverom i poziva njenu metodu register
    //ukoliko je uspesna, formi vraca menadzera i succes
    public Response login(String username, String password) {
        Response response = new Response();
        communication.connect();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        Admin result = communication.login(admin);
        if (result != null) {
            prijavljeniAdmin = result;
            response.setSuccessful(true);
            response.setResult(result);
        } else {
            response.setSuccessful(false);
            response.setMessage("Registracija nije uspela.");
        }
        return response;
    }

    public void prekiniKonekciju() {
        communication.connect();
        communication.prekiniKonekciju();
    }

    public boolean odjaviAdmina() {
        System.out.println("Pokušaj odjave admina...");
        boolean result = false;
        try {
            communication.connect();
            result = communication.odjaviAdmina(prijavljeniAdmin);
            System.out.println("Rezultat operacije odjave admina u kontroleru: " + result);
            if (result) {
                prijavljeniAdmin = null;
                System.out.println("Admin uspešno odjavljen.");
            } else {
                throw new RuntimeException("Odjava nije uspela.");
            }
        } catch (RuntimeException e) {
            // Obrada izuzetka
            throw e;
        } finally {
            return result;
        }
    }

    public ArrayList<Jezik> ucitajSveJezike() {
        List<Jezik> jezici = new ArrayList<>();
        try {
            communication.connect();
            jezici = communication.ucitajSveJezike();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Jezik>) jezici;
    }

    public Response sacuvajStudenta(Student student) {
        Response response = new Response();
        try {
            communication.connect();
            boolean result = communication.sacuvajStudenta(student);
            if (result) {
                response.setSuccessful(true);
            } else {
                response.setSuccessful(false);
                response.setMessage("Čuvanje studenta nije uspelo.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ArrayList<Student> ucitajSveStudente() {
        List<Student> studenti = new ArrayList<>();
        try {
            communication.connect();
            studenti = communication.ucitajSveStudente();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Student>) studenti;
    }

    public Response obrisiStudenta(Student student) {
        Response response = new Response();
        try {
            communication.connect();
            boolean uspesno = communication.obrisiStudenta((Student) student);
            if (uspesno) {
                response.setSuccessful(true);
            } else {
                response.setSuccessful(false);
                response.setMessage("Brisanje studenta nije uspelo.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ArrayList<Student> ucitajSveStudenteUslov(Student s) {
        List<Student> studenti = new ArrayList<>();
        try {
            communication.connect();
            studenti = communication.ucitajSveStudenteUslov(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Student>) studenti;
    }

    public Student ucitajStudenta(Student s) {
        Student student = null;
        try {
            communication.connect();
            student = communication.ucitajStudenta(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public Response izmeniStudenta(Student student) {
        Response response = new Response();
        try {
            communication.connect();
            Student result = communication.izmeniStudenta(student);
            if (result != null) {
                response.setSuccessful(true);
                response.setResult(result);
            } else {
                response.setSuccessful(false);
                response.setMessage("Izmena studenta nije uspela.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response sacuvajProfesora(Profesor profesor) {
        Response response = new Response();
        try {
            communication.connect();
            boolean result = communication.sacuvajProfesora(profesor);
            if (result) {
                response.setSuccessful(true);
            } else {
                response.setSuccessful(false);
                response.setMessage("Čuvanje profesora nije uspelo.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ArrayList<Profesor> ucitajSveProfesore() {
        List<Profesor> profesori = new ArrayList<>();
        try {
            communication.connect();
            profesori = communication.ucitajSveProfesore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Profesor>) profesori;
    }

    public Profesor ucitajProfesora(Profesor p) {
        Profesor profesor = null;
        try {
            communication.connect();
            profesor = communication.ucitajProfesora(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profesor;
    }

    public Response izmeniProfesora(Profesor profesor) {
        Response response = new Response();
        try {
            communication.connect();
            Profesor result = communication.izmeniProfesora(profesor);
            if (result != null) {
                response.setSuccessful(true);
                response.setResult(result);
            } else {
                response.setSuccessful(false);
                response.setMessage("Izmena profesora nije uspela.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ArrayList<Profesor> ucitajSveProfesoreUslov(Profesor ps) {
        List<Profesor> profesori = new ArrayList<>();
        try {
            communication.connect();
            profesori = communication.ucitajSveProfesoreUslov(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Profesor>) profesori;

    }

    public List<TerminCasa> ucitajSveZakazaneTrmine() {
        List<TerminCasa> zakazaniTermini = new ArrayList<>();
        try {
            communication.connect();
            zakazaniTermini = communication.ucitajSveZakazaneTermine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<TerminCasa>) zakazaniTermini;
    }

    public Response sacuvajTermin(TerminCasa zakazanTermin) {
        Response response = new Response();
        try {
            communication.connect();
            boolean result = communication.sacuvajTerimn(zakazanTermin);
            if (result) {
                response.setSuccessful(true);
            } else {
                response.setSuccessful(false);
                response.setMessage("Čuvanje termina nije uspelo.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public TerminCasa ucitajTermin(TerminCasa termin) {
           TerminCasa terminCasa = null;
        try {
            communication.connect();
            terminCasa = communication.ucitajTermin(termin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terminCasa; 
    }

    public List<Dokument> ucitajDokumenteUslov(Dokument dokument) {
          List<Dokument> dokumenti = new ArrayList<>();
        try {
            communication.connect();
            dokumenti = communication.ucitajSveDokumenteUslov(dokument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Dokument>) dokumenti;
    }

    public ArrayList<TerminCasa> ucitajSveTermineUslov(TerminCasa tc) {
        List<TerminCasa> termini = new ArrayList<>();
        try {
            communication.connect();
            termini = communication.ucitajSveTermineUslov(tc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<TerminCasa>) termini; 
    }

    public Response izmeniTerminCasa(TerminCasa zakazanTermin) {
         Response response = new Response();
        try {
            communication.connect();
            boolean result = communication.izmeniTerminCasa(zakazanTermin);
            if (result) {
                response.setSuccessful(true);
                response.setResult(result);
            } else {
                response.setSuccessful(false);
                response.setMessage("Izmena termina nije uspela.");
            }
        } catch (RuntimeException e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }



}
