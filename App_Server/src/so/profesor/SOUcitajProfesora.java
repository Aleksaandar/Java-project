/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.profesor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Profesor;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajProfesora extends AbstractSO {
       private Profesor profesor;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Profesor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Profesor!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Profesor profesorZaUcitavanje = (Profesor) ado;
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(profesorZaUcitavanje);
        
        if (rezultat.isEmpty()) {
            throw new Exception("Student ne postoji.");
        }

        profesor = (Profesor) rezultat.get(0);
    }

    public Profesor getProfesor() {
        return profesor;
    }
}
