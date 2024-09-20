/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.student;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Student;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOSacuvajStudenta extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Student)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Student!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Student student = (Student) ado;

        // Čuvanje studenta u bazi podataka
        DBBroker.getInstance().insert(student);
    }

}
