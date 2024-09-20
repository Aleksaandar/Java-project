/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.profesor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Profesor;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOSacuvajProfesora extends AbstractSO {
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Profesor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Profesor!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Profesor profesor = (Profesor) ado;

        // Čuvanje profesora u bazi podataka
        DBBroker.getInstance().insert(profesor);
    }
    
}
