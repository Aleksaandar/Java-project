/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.terminCasa;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.TerminCasa;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajTerminePoUslovu extends AbstractSO {

    private List<TerminCasa> listaTermina;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        // Nema posebne validacije za ovu operaciju
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Izvlačimo termine
        List<AbstractDomainObject> rezultatTermini = DBBroker.getInstance().select(ado);
        listaTermina = new ArrayList<>();
        for (AbstractDomainObject objekat : rezultatTermini) {
            listaTermina.add((TerminCasa) objekat);
        }

    }

    public List<TerminCasa> getListaTerminaUslov() {
        return listaTermina;
    }
}
