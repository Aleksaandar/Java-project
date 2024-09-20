/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

/**
 *
 * @author Isidora
 */
import domain.Admin;
import domain.Dokument;
import domain.Jezik;
import domain.Profesor;
import domain.Student;
import domain.TerminCasa;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class Communication {

    private Socket socket;
    private CommunicationHelper helper;

    //otvara komunikaciju i postavlja comm helper
    public void connect() {
        if (isConnected()) {
            System.out.println("Konekcija je već otvorena.");
            return; // Konekcija je već otvorena
        }
        try {
            socket = new Socket("127.0.0.1", 9999);
            helper = new CommunicationHelper(socket);
            System.out.println("Konekcija uspostavljena.");
        } catch (SocketException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error connecting to server", ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("I/O error occurred", ex);
        }
    }

    public boolean isConnected() {
        boolean connected = socket != null && socket.isConnected() && !socket.isClosed();
        System.out.println("Provera konekcije: " + connected);
        return connected;
    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Konekcija zatvorena.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //metoda koja se uvek koristi za slanje zahteva koji se sastoji iz objekta i operacije
    //koristi helper da se to prosledi
    private void sendRequest(Operation operation, Object requestObject) {
        try {
            Request request = new Request(operation, requestObject);
            helper.send(request);
        } catch (IOException ex) {
            ex.printStackTrace();
            close();
            throw new RuntimeException("Error sending request", ex);
        }
    }

    //metoda koja se uvek koristi za primanje odgovora od servera
    //koristi helper da se to prosledi
    private Object getResult() {
        try {
            Response response = (Response) helper.receive();
            if (response.isSuccessful()) {
                return response.getResult();
            } else {
                System.err.println(response.getMessage());
                return null;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            close();
            throw new RuntimeException("Error receiving response", ex);
        }
    }

    public void prekiniKonekciju() {
        sendRequest(Operation.PREKINI_KONEKCIJU, null);
        getResult();
    }

    Admin login(Admin admin) {
        sendRequest(Operation.LOGIN, admin);
        return (Admin) getResult();
    }

    boolean odjaviAdmina(Admin admin) {
        sendRequest(Operation.LOGOUT, admin);
        return (boolean) getResult();
    }

    List<Jezik> ucitajSveJezike() {
        sendRequest(Operation.UCITAJ_SVE_JEZIKE, null);
        return (List<Jezik>) getResult();
    }

    boolean sacuvajStudenta(Student student) {
        sendRequest(Operation.SACUVAJ_STUDENTA, student);
        return (boolean) getResult();
    }

    List<Student> ucitajSveStudente() {
        sendRequest(Operation.UCITAJ_SVE_STUDENTE, null);
        return (List<Student>) getResult();
    }

    boolean obrisiStudenta(Student student) {
        sendRequest(Operation.OBRISI_STUDENTA, student);
        return (Boolean) getResult();
    }

    List<Student> ucitajSveStudenteUslov(Student s) {
        sendRequest(Operation.UCITAJ_SVE_STUDENTE_USLOV, s);
        return (List<Student>) getResult();
    }

    Student ucitajStudenta(Student student) {
        sendRequest(Operation.UCITAJ_STUDENTA, student);
        return (Student) getResult();
    }

    Student izmeniStudenta(Student student) {
        sendRequest(Operation.IZMENI_STUDENTA, student);
        return (Student) getResult();
    }

    boolean sacuvajProfesora(Profesor profesor) {
        sendRequest(Operation.SACUVAJ_PROFESORA, profesor);
        return (boolean) getResult();
    }

    List<Profesor> ucitajSveProfesore() {
        sendRequest(Operation.UCITAJ_SVE_PROFESORE, null);
        return (List<Profesor>) getResult();
    }

    Profesor ucitajProfesora(Profesor p) {
        sendRequest(Operation.UCITAJ_PROFESORA, p);
        return (Profesor) getResult();
    }

    Profesor izmeniProfesora(Profesor profesor) {
        sendRequest(Operation.IZMENI_PROFESORA, profesor);
        return (Profesor) getResult();
    }

    List<Profesor> ucitajSveProfesoreUslov(Profesor ps) {
        sendRequest(Operation.UCITAJ_SVE_PROFESORE_USLOV, ps);
        return (List<Profesor>) getResult();
    }

    List<TerminCasa> ucitajSveZakazaneTermine() {
        sendRequest(Operation.UCITAJ_SVE_ZAKAZANE_TERMINE, null);
        return (List<TerminCasa>) getResult();
    }

    boolean sacuvajTerimn(TerminCasa zakazanTermin) {
        sendRequest(Operation.SACUVAJ_TERMIN, zakazanTermin);
        return (boolean) getResult();
    }

    TerminCasa ucitajTermin(TerminCasa terminCasa) {
        sendRequest(Operation.UCITAJ_TERMIN, terminCasa);
        return (TerminCasa) getResult();
    }

    List<Dokument> ucitajSveDokumenteUslov(Dokument dokument) {
        sendRequest(Operation.UCITAJ_SVE_DOKUMENTE_USLOV, dokument);
        return (List<Dokument>) getResult();
    }

    List<TerminCasa> ucitajSveTermineUslov(TerminCasa tc) {
        sendRequest(Operation.UCITAJ_SVE_TERMINE_CASA_USLOV, tc);
        return (List<TerminCasa>) getResult();
    }

    boolean izmeniTerminCasa(TerminCasa zakazanTermin) {
        sendRequest(Operation.IZMENI_TERMIN, zakazanTermin);
        return (boolean) getResult();
    }

}
