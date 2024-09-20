/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dokument;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dokument;
import domain.Student;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOIzbrisiDokument extends AbstractSO {
        @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dokument)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dokument!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Dokument dokument = (Dokument) ado;


        // Brisanje iz glavne tabele Dokument
        DBBroker.getInstance().deleteWithCondition(dokument,"zakazanTerminID IN (SELECT zakazanTerminID FROM zakazantermin WHERE studentID ="+dokument.getTerminCasa().getStudent().getStudentID()+")");
    }
}
