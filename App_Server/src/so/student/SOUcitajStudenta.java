/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.student;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Student;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajStudenta extends AbstractSO {
        private Student student;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Student)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Student!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Student studantZaUcitavanje = (Student) ado;
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(studantZaUcitavanje);
        
        if (rezultat.isEmpty()) {
            throw new Exception("Student ne postoji.");
        }

        student = (Student) rezultat.get(0);
    }

    public Student getStudent() {
        return student;
    }
    
}
