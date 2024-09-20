/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.terminCasa;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Student;
import domain.TerminCasa;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajTermin extends AbstractSO {

    private TerminCasa termin;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TerminCasa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TerminCasa!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        TerminCasa terminZaUcitavanje = (TerminCasa) ado;
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(terminZaUcitavanje);

        if (rezultat.isEmpty()) {
            throw new Exception("Termin ne postoji.");
        }

        termin = (TerminCasa) rezultat.get(0);
    }

    public TerminCasa getTermin() {
        return termin;
    }
}
