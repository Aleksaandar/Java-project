/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author Isidora
 */
import communication.Operation;
import static communication.Operation.UCITAJ_SVE_STUDENTE;
import communication.Request;
import communication.Response;
import controller.Controller;
import domain.Admin;
import domain.Dokument;
import domain.Jezik;
import domain.Profesor;
import domain.Student;
import domain.TerminCasa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

//ova klasa kontrolise jednu korisnicku nit
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Server server;
    private boolean isRunning;
    private Admin ulogovaniAdmin;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.isRunning = true;
    }

    //primamo zahteve i saljemo odgovore pomocu clasa iz common-a
    @Override
    public void run() {
        System.out.println("ClientHandler: Početak run metode");
        try {
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());

            while (isRunning) {
                try {
                    Request request = (Request) input.readObject();
                    System.out.println("ClientHandler: Primljen request: " + request.getOperation());
                    Response response = handleRequest(request);
                    output.writeObject(response);
                    System.out.println("ClientHandler: Poslat response: " + response.isSuccessful());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    isRunning = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
        try {

            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metoda za obradu svih pristiglih zahteva pozivanjem kontrolera
    private Response handleRequest(Request request) {
        Response response = new Response();
        try {
            switch ((Operation) request.getOperation()) {
                case LOGIN:
                    Admin admin = (Admin) request.getRequestObject();
                    if (server.isAdminLoggedIn(admin)) {
                        response.setSuccessful(false);
                        response.setMessage("Admin je već ulogovan.");
                    } else {
                        Admin ulogovaniAdmin = Controller.getInstance().prijaviAdmina(admin);
                        if (ulogovaniAdmin != null) {
                            this.ulogovaniAdmin = ulogovaniAdmin;
                            response.setSuccessful(true);
                            response.setResult(ulogovaniAdmin);
                        } else {
                            response.setSuccessful(false);
                            response.setMessage("Neuspešna prijava.");
                        }
                    }
                    break;
                case LOGOUT:
                    System.out.println("Primljen zahtev za odjavu admina.");
                    if (ulogovaniAdmin != null) {
                        Controller.getInstance().odjaviAdmina(ulogovaniAdmin);
                        //server.removeLoggedInAdmin(ulogovanAdmin);
                        ulogovaniAdmin = null;
                        System.out.println("Admin uspešno odjavljen na serveru.");
                    }
                    //isRunning = false;
                    response.setResult(true);
                    response.setSuccessful(true);
                    response.setMessage("Uspešna odjava.");
                    System.out.println("Poslat response za odjavu admina: " + response.isSuccessful());
                    break;
                case UCITAJ_SVE_JEZIKE:
                    System.out.println("Primljen zahtev za prikaz svih jezika.");
                    try {
                        List<Jezik> listaJezika = Controller.getInstance().ucitajSveJezike();
                        response.setResult(listaJezika);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih jezika: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja jezika: " + e.getMessage());
                    }
                    break;
                case UCITAJ_SVE_STUDENTE:
                    System.out.println("Primljen zahtev za prikaz svih studenata.");
                    try {
                        List<Student> listaStudenata = Controller.getInstance().ucitajSveStudente();
                        response.setResult(listaStudenata);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih studenata: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja studenata: " + e.getMessage());
                    }
                    break;
                case UCITAJ_SVE_STUDENTE_USLOV:
                    System.out.println("Primljen zahtev za prikaz svih studenata po zadatom uslovu.");
                    try {
                        Student st = (Student) request.getRequestObject();
                        List<Student> listaStudenata = Controller.getInstance().ucitajSveStudentePoUslovu(st);
                        response.setResult(listaStudenata);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih studenata po zadatom uslovu: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja studenata po zadatom uslovu: " + e.getMessage());
                    }
                    break;
                case SACUVAJ_STUDENTA:
                    Student student = (Student) request.getRequestObject();
                    try {
                        Controller.getInstance().sacuvajStudenta(student);
                        response.setSuccessful(true);
                        response.setResult(true);
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage(e.getMessage());
                    }
                    break;
                case OBRISI_STUDENTA:
                    Student studentZaBrisanje = (Student) request.getRequestObject();
                    Controller.getInstance().obrisiStudenta(studentZaBrisanje);
                    response.setSuccessful(true);
                    response.setResult(true);
                    break;
                case UCITAJ_STUDENTA:
                      try {
                    System.out.println("Primljen zahtev za učitavanje studenta");
                    Student studentZaUcitavanje = (Student) request.getRequestObject();
                    Student rezultat = Controller.getInstance().ucitajStudenta(studentZaUcitavanje);
                    response.setResult(rezultat);
                    response.setSuccessful(true);
                } catch (Exception e) {
                    response.setSuccessful(false);
                    response.setMessage("Greška prilikom učitavanja studenta " + e.getMessage());
                }
                break;
                case IZMENI_STUDENTA:
                    Student studentZaIzmenu = (Student) request.getRequestObject();
                    Student izmenjenStudent = Controller.getInstance().izmeniStudenta(studentZaIzmenu);
                    response.setSuccessful(true);
                    response.setResult(izmenjenStudent);

                    break;
                case SACUVAJ_PROFESORA:
                    Profesor profesor = (Profesor) request.getRequestObject();
                    try {
                        Controller.getInstance().sacuvajProfesora(profesor);
                        response.setSuccessful(true);
                        response.setResult(true);
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage(e.getMessage());
                    }
                    break;
                case UCITAJ_SVE_PROFESORE:
                    System.out.println("Primljen zahtev za prikaz svih profesora.");
                    try {
                        List<Profesor> listaProfesora = Controller.getInstance().ucitajSveProfesore();
                        response.setResult(listaProfesora);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih profesora: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja profesora: " + e.getMessage());
                    }
                    break;
                case UCITAJ_PROFESORA:
                    Profesor profesorZaUcitavanje = (Profesor) request.getRequestObject();
                    Profesor rezultatUcitavanja = Controller.getInstance().ucitajProfesora(profesorZaUcitavanje);
                    response.setResult(rezultatUcitavanja);
                    response.setSuccessful(true);
                    break;
                case IZMENI_PROFESORA:
                    Profesor profesorZaIzmenu = (Profesor) request.getRequestObject();
                    Profesor izmenjenProfesor = Controller.getInstance().izmeniProfesora(profesorZaIzmenu);
                    response.setSuccessful(true);
                    response.setResult(izmenjenProfesor);
                    break;
                case UCITAJ_SVE_PROFESORE_USLOV:

                    System.out.println("Primljen zahtev za prikaz svih profesora po zadatom uslovu.");
                    try {
                        Profesor ps = (Profesor) request.getRequestObject();
                        List<Profesor> listaProfesora = Controller.getInstance().ucitajSveProfesorePoUslovu(ps);
                        response.setResult(listaProfesora);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih profesora po zadatom uslovu: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja profesora po zadatom uslovu: " + e.getMessage());
                    }
                    break;
                case UCITAJ_SVE_ZAKAZANE_TERMINE:
                    System.out.println("Primljen zahtev za prikaz svih zakazanih termina.");
                    try {
                        List<TerminCasa> listaZakazanihTermina = Controller.getInstance().ucitajSveZakazaneTermine();
                        response.setResult(listaZakazanihTermina);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih zakazanih termina: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja zakazanih termina: " + e.getMessage());
                    }
                    break;
                case SACUVAJ_TERMIN:

                    TerminCasa termin = (TerminCasa) request.getRequestObject();
                    try {
                        Controller.getInstance().sacuvajTermin(termin);
                        response.setSuccessful(true);
                        response.setResult(true);
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage(e.getMessage());
                    }
                    break;
                case UCITAJ_TERMIN:
                    TerminCasa terminZaUcitavanje = (TerminCasa) request.getRequestObject();
                    TerminCasa rezultatUcitavanjaTermina = Controller.getInstance().ucitajTermin(terminZaUcitavanje);
                    response.setResult(rezultatUcitavanjaTermina);
                    response.setSuccessful(true);
                    break;
                case UCITAJ_SVE_DOKUMENTE_USLOV:

                    System.out.println("Primljen zahtev za prikaz svih profesora po zadatom uslovu.");
                    try {
                        Dokument dokument = (Dokument) request.getRequestObject();
                        List<Dokument> listaDokumenata = Controller.getInstance().ucitajSveDokumentePoUslovu(dokument);
                        response.setResult(listaDokumenata);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih dokumenata po zadatom uslovu: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja dokumenata po zadatom uslovu: " + e.getMessage());
                    }
                    break;
                case IZMENI_TERMIN:
                    TerminCasa izmeniTermin = (TerminCasa) request.getRequestObject();
                    try {
                        Controller.getInstance().izmeniTermin(izmeniTermin);
                        response.setSuccessful(true);
                        response.setResult(true);
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage(e.getMessage());
                    }
                    break;
                case UCITAJ_SVE_TERMINE_CASA_USLOV:
                    System.out.println("Primljen zahtev za prikaz svih termina po zadatom uslovu.");
                    try {
                        TerminCasa tc = (TerminCasa) request.getRequestObject();
                        List<TerminCasa> listaTermina = Controller.getInstance().ucitajSveTerminePoUslovu(tc);
                        response.setResult(listaTermina);
                        response.setSuccessful(true);
                        System.out.println("Poslat response za prikaz svih termina po zadatom uslovu: " + response.isSuccessful());
                    } catch (Exception e) {
                        response.setSuccessful(false);
                        response.setMessage("Greška prilikom učitavanja termina po zadatom uslovu: " + e.getMessage());
                    }

                    break;
                case PREKINI_KONEKCIJU:
                    isRunning = false;
                    break;
                default:
                    response.setSuccessful(false);
                    response.setMessage("Nepoznata operacija.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccessful(false);
            response.setMessage("Greška: " + e.getMessage());
        }
        return response;
    }

}
