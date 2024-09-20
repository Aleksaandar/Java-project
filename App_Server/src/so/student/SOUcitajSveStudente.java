/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.student;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Jezik;
import domain.Student;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajSveStudente extends AbstractSO {

    private List<Student> listaStudenata;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        // Nema posebne validacije za ovu operaciju
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Izvlačimo studente
        List<AbstractDomainObject> rezultatStudenti = DBBroker.getInstance().bezuslovniSelect(new Student());
        listaStudenata = new ArrayList<>();
        for (AbstractDomainObject objekat : rezultatStudenti) {
            listaStudenata.add((Student) objekat);
        }
        for (Student student : listaStudenata) {
            System.out.println(student.getIme());
        }

    }

    public List<Student> getListaStudenata() {
        return listaStudenata;
    }
 
}
