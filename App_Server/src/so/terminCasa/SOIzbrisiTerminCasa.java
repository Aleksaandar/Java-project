/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.terminCasa;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dokument;
import domain.TerminCasa;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOIzbrisiTerminCasa extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TerminCasa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TerminCasa!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        TerminCasa terminCasa = (TerminCasa) ado;

        // Brisanje iz glavne tabele Dokument
        DBBroker.getInstance().deleteWithCondition(terminCasa, "studentID =" +terminCasa.getStudent().getStudentID());
    }
}
