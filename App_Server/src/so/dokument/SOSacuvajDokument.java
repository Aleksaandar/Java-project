/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dokument;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dokument;
import domain.TerminCasa;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOSacuvajDokument extends AbstractSO {
       @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dokument)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dokument!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Dokument dokument = (Dokument) ado;

        // Čuvanje studenta u bazi podataka
        DBBroker.getInstance().insert(dokument);
    } 
}
